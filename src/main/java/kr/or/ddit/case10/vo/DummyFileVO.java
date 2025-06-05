package kr.or.ddit.case10.vo;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import kr.or.ddit.validate.constraints.FileNotEmpty;
import kr.or.ddit.validate.constraints.MimeTypeCheck;
import lombok.Data;

@Data
public class DummyFileVO {
	@NotBlank
	private String uploader;
	@MimeTypeCheck(mainType="image")  //괄호에 속성 아무것도 안 써있으면 white space 적용된 셈.
	@FileNotEmpty
	private MultipartFile uploadFile;
	//empty 판단 : 문자열, 컬렉션 판단 가능. 멀티파일도 가능!(추가적인 벨리데이션 구현만 하면)
}
