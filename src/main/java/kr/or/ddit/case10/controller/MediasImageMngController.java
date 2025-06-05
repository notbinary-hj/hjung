package kr.or.ddit.case10.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import kr.or.ddit.validate.utils.ErrorsUtils;

/**
 * 특정 폴더 아래의 파일을 관리하기 위한 컨트롤러
 *  /case10/files (GET)
 *  /case10/files/파일명 (GET)  --파일을 조회 즉 다운로드 가능
 *  /case10/files (POST)
 *  /case10/files/파일명 (DELETE)
 */
@Controller
@RequestMapping("/case10/files")
public class MediasImageMngController {
	@Value("${imagesFolder}")  //FileInfo.properties의 placeholder로 쓰게
	private Resource imageRes;
	private File saveFolder;
	@Autowired
	private ServletContext application;
	
	//리소스를 파일로 만드는 건 전부 쓰일 거고, 리소스가 주입돼있어야 파일을 생성하므로 콜백 만듦.
	@PostConstruct  //웹에선 많이 사용하는 주입 끝난 뒤 생성되는 아이
	public void init() throws IOException{  //서블릿이니까 던져
		//스프링 컨테이너가 호출->예외도 스프링 컨테이너가 가져감
		//->스프링 컨테이너의 exception resolver에서 내부적으로 처리.
		saveFolder = imageRes.getFile();  //saveFolder에 담아서 밑에서 쭉 쓰게 담음
	}
	
	@GetMapping
	public void fileList(  //lvn이 루트랑 같아서 void로 만들고 리턴값 없앰
		Model model
	) {
		//파일 업로드하려면 리소스만으로 안됨. 리소스에서 꺼내서 파일 만들어야됨.
//		1. (생성자 주입이 끝나고 만들어지는 init)콜백을 이용해 리소스에서 파일을 꺼냄
//		2. 파일 안에 든 파일 목록을 모델로 jsp까지 전달(${fileNames })
		String[] fileNames = saveFolder.list();
		model.addAttribute("fileNames", fileNames);  //el에서 키값이 있어야 읽음. object인 밸류만 있던거
	}

//	1. 페이지 전환X 2. 서버에서 2진데이터 가져옴 3. 특정 파일명으로 저장할 구조
	/**
	 * 중요! 헤더에 대한 부분.
	 * 파일 다운로드 처리 - 응답 데이터 소비 방식 결정.(바로 그릴지, 저장할지)
	 * Content-Disposition 헤더의 사용
	 * 	1) inline (기본 처리형태) : 브라우저의 창 내에서 응답 컨텐츠를 소비함.
	 *  2) attachment : 별도 파일로 저장함.
	 *  	예) attachment;filename="파일명"
	 *  
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	@GetMapping(value="{fileName}")
//	@ResponseBody  //response가 그 자체로 나감->리스폰즈의 바디를 사용. 가장 단순한 형태의 다운로드.->body 설정 안해도 되면 불필요.
	public ResponseEntity<Resource> fileDownload(@PathVariable String fileName) throws IOException {
		Resource targetRes = imageRes.createRelative(fileName);
		//디렉토리 아래 파일 경로를 상대경로로 접근, 그 아래 파일 이름을 리소스로 반환해줌.
		if(!targetRes.exists()) {  //Resource는 File과 쓰는 방식 유사.
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
			//잘못된 파일이 경로변수일 경우 404 에러.
		}
		//다운로드 처리
		HttpHeaders headers = new HttpHeaders();
		headers.setContentLength(targetRes.contentLength());  //다운로드 진행정도 시각화로(다운로드 바) 보여주기 위해 파일 크기도 필요
		//이전엔 톰캣의 마임db를 이용해 톰캣이 마임을 가져와줌
		
		//리소스의 파일명으로 마임을 동적으로 가져옴->마임이 있다면...
		MediaType mediaType = Optional.ofNullable(application.getMimeType(targetRes.getFilename()))
								.map(MediaType::parseMediaType)  //mime을 받아서 그걸 미디어타입의 mime으로 그대로 받음(메소드 레퍼런스)
								.orElse(MediaType.APPLICATION_OCTET_STREAM);  //없다고 다운 못하면 안되니 기본값 제공
								//OCTET 즉 숫자 8, 8비트로 데이터를 표현 가능, 1바이트로 스트림을 표현하는 거.-> 어떤 타입의 마임을 모를 때 많이 사용.
		headers.setContentType(mediaType);
		
		headers.setContentDisposition(
			//다시 빌더 패턴
			ContentDisposition.attachment()
				.filename(targetRes.getFilename(), Charset.defaultCharset())
				.build()
			//파일명이 한글이 포함됐거나(인코딩), 공백이 들었으면 처리해야  //defaultCharset은 기본값이 utf-8로 인코딩해줌
		);
		//좀 예쁜 응답 박스(response entity - 엔터티는 디비에서 하나의 객체인 거니까)
		//예쁘게 entity 쓰거나, 그냥 나가는 @ResponseBody 쓰거나.(얜 jsp 안 찾아줌, 잭슨 라이브러리가 문자열로 만들고 json 포맷함)
		return ResponseEntity.ok()  //라인
			.headers(headers)  //헤더
			.body(targetRes);  //바디: 리턴시키려던 targetRes
//			.build();  //빌더 패턴으로 response entity를 만들어냄, body 넣었을땐 따로 빌드X
		//다운로드라서 lvn 불필요.
	}
	
	//새로 업로드된 파일 리스트->새로 가져온 이름과 redirect 이름이 같으면 class 적용.
	@PostMapping
	public String upload(
		MultipartFile uploadFile  //업로드할 파일. @RequestPart(required=true)가 앞에 생략됨
		, RedirectAttributes redirectAttributes
	) throws IllegalStateException, IOException {
		if(!uploadFile.isEmpty()) {  //required 가 true래도 비었을 수 있으니 검증
			String saveName = UUID.randomUUID().toString();
			File saveFile = new File(saveFolder, saveName);
			uploadFile.transferTo(saveFile);
			//여길 지나면 업로드 끝난거
			redirectAttributes.addFlashAttribute("uploaded", saveName);
			//리다이랙션으로 저장된 파일 이름 가져가
		}
		//업로드가 끝났으면 리다이랙트
		return "redirect:/case10/files";
	}
	
	@DeleteMapping("{fileName}")
	@ResponseBody  //이거 필수! response 객체의 write 가 붙어서 그대로 브라우저로 보내지는 거.
	public Map<String, Object> deleteOne(@PathVariable String fileName) throws IOException {
		Resource targetRes = imageRes.createRelative(fileName);
		if(!targetRes.exists()) { 
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
		}
		//readonly라서 지우려면 파일이 있어야
		targetRes.getFile().delete();
		//success와 target 을 가진 json이 응답으로 나감 - @ResponseBody랑 세트.  //그냥 객체로 보내면 브라우저가 못 알아챔.
		return Map.of("success", true, "target", fileName);
	}
}
