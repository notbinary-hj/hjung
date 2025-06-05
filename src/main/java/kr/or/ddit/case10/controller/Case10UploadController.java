package kr.or.ddit.case10.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;
import kr.or.ddit.case10.vo.DummyFileVO;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case10/upload")
public class Case10UploadController {
	@Autowired
	private ErrorsUtils errorUtils;  //빈으로 등록해놨으니 주입받기
	
	//스프링은 File 대신 '리소스/리소스 로더' 사용(인터페이스의 io 패키지 거여야 해!)
	//문자열은 '값'이니까 값을 찾을 수 있게 Value!
	@Value("${dummyUpload}")  //이게 폴더임을 명시하려면 뒤에 언제나 "/" 붙여! 파일vs폴더(/ 없을시) 혼동함.(FileInfo.properties에서 placeholder로 가져옴)
	private Resource saveRes;  //리소스를 찾는건 리소스 로더(스프링이 리소스 로더)
	
	@GetMapping
	public String formUI() {
		return "case10/uploadForm";  //uploadForm.jsp에 액션을 안 줬으니 거기로 간 주소로 그대로 업로드도 함
		//전송 버튼 눌렀을 때 /case10/upload로 올거라구.
	}
	
	//command object를 통해, 하나로 받을 경우...
	@PostMapping
	public String formProcess2(
		@Valid @ModelAttribute("dummyFile") DummyFileVO dummyFile  //command object면서 model
		, BindingResult errors  //검증하고 검증결과 갖기. 모든 검증은 여기서 끝남.
		, RedirectAttributes redirectAttributes
	) throws IllegalStateException, IOException {
		
		if(errors.hasErrors()) {
			//멀티밸류 맵 or 멀티밸류맵의타입인 List로 받아야
			Map<String, List<String>> customErrors = errorUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
		}else {
			MultipartFile other = dummyFile.getUploadFile();
			String mime = other.getContentType();
			if(!mime.startsWith("image/")) {
				throw new ResponseStatusException(HttpStatusCode.valueOf(400));
			}
			String originalFileName = other.getOriginalFilename();
			String saveName = System.currentTimeMillis()+"_"+originalFileName;
			File saveFolder = saveRes.getFile(); 
			File saveFile = new File(saveFolder, saveName);
			
			other.transferTo(saveFile);
			
			log.info("uploader : {}", dummyFile.getUploader());
			log.info("uploadFile : {}", other);
			
			redirectAttributes.addFlashAttribute("saveName", saveName);			
		}

		return "redirect:/case10/upload";  //getMapping으로 보낸거 여기는  //리다이랙트는 '브라우저에게 여기로 다시 요청하는 거' 은행 창구 다른 데로 넘김.
	}
	
	//낱개로 받을 경우...
//	@PostMapping
	public String formProcess(
			@RequestParam String uploader  //파라미터가 됐으니 @RequestParam(혹은 생략)으로 뽑아낼 수 있다
			, @RequestPart(name="uploadFile") MultipartFile other  //"Part"가 필요하단 걸 어노테이션으로 명확히할 수 있음
			, RedirectAttributes redirectAttributes
	) throws IllegalStateException, IOException {  //컨트롤러에서 예외 발생시, 어지간하면 컨트롤러가 직접 처리X. 그대로 던져
//		파일 비었는지 검증
		if(other.isEmpty()) {
			//required=true인데 파일이 안 왔으니 400 에러 보내줘야
			throw new ResponseStatusException(HttpStatusCode.valueOf(400));
		}
//		파일 마임 검증
		String mime = other.getContentType();
		if(!mime.startsWith("image/")) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(400));
		}
		String originalFileName = other.getOriginalFilename();  //원본파일명
		//방법1) UUID 이용
		//방법2) 원본 파일명을 유지하되, 그대로 저장하지 않고 시간 데이터를 붙여 이름 예측 불가하게 만들기
		String saveName = System.currentTimeMillis()+"_"+originalFileName;
						//시스템에서 시간을 뽑아서 원본파일 이름 앞에 컨캣
		//참고로, 원본 파일명~확장자 사이 가운데에 시간 꽂아넣으면 제일 굿.
		File saveFolder = saveRes.getFile();  //파일 저장할 폴더 위치, saveRes를 파일로 갖고놀 수 있게 getFile로 변환
		File saveFile = new File(saveFolder, saveName);
		
		//Part의 write 역할인 transferTo(넘겨줄 파일)
		other.transferTo(saveFile);
		
		log.info("uploader : {}", uploader);
		log.info("uploadFile : {}", other);
		
		//포스트 처리 끝나면 한번은 리다이랙팅
		//업로드 완료된 정보도 같이 보내기(form jsp로 보내서, 이런 이름으로 저장됐어 하고알려주기)
		redirectAttributes.addFlashAttribute("saveName", saveName);
		//저장된 이름을 리다이랙팅에 가져가는 법
		//방법1) 위의 GetMapping formUI에 Model로 넣어주거나
		//방법2) jsp에서 el로 꺼내준다
		return "redirect:/case10/upload";
	}  
}
