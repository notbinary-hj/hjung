package kr.or.ddit.case10.servlet;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@WebServlet("/case10/servlet/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet{
	
	private File saveFolder = new File("D:/uploadFiles");  //저장 위치부터 설정  //폴더지만 properties 파일 아니니 /를 뒤에 안붙임
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/case10/uploadForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploader = req.getParameter("uploader");  //문자열 업로드는 그냥 파라미터로 오고, 이름은 인풋 태그 네임값으로.
		log.info("uploader : {}", uploader);
		
		//원래라면 문자열로 오는 거라 여기도 캐릭터 인코딩부터지만, 필터가 해주고 있으니 생략.
		Part uploadFile = req.getPart("uploadFile");
		//파일 마임은 uploadFile이 가짐
		String mime = uploadFile.getContentType();
		if(!mime.startsWith("image/")) {  //image/jpeg, image/png 및 image/svg+xml 등으로 시작하지 않는건 400 내보내겠다
			//검증하다 걸린 거니 400 상태코드.
			resp.sendError(400);
			return;
		}
		log.info("uploadFile : {}", uploadFile);
		String originalFileName = uploadFile.getSubmittedFileName();  //이게 원본파일 네임
		//uploadFile.getName()  //주의) 이건 파트의 네임. 즉 인풋 태그 속성값!
		String saveName = UUID.randomUUID().toString();  //겹칠 수 없는 이름으로, 확장자 개념도 없어짐.
//		File saveFile = new File(saveFolder, originalFileName);  //saveFolder는 저장할 폴더 위치
		File saveFile = new File(saveFolder, saveName);
		//UUID가 아닌 방식으로는, 임시 이름 만들 때 계속 바뀌므로 밀리세컨드를 적용하기 좋은 시간을 이용해 파일 데이터에 이름을 바꿈.
		uploadFile.write(saveFile.getAbsolutePath());  //파일 이름이 아니라 저장할 파일 전체 경로(퀄러파이드 네임)
	}
}
