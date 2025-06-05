package kr.or.ddit.validate.constraints;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MimeTypeCheckValidator 
		implements ConstraintValidator<MimeTypeCheck, MultipartFile>{
	private String mainType;  //어노테이션에서 속성은 그냥 메서드라서... 아래에서 바로 쓸수있음
	@Override
	public void initialize(MimeTypeCheck constraintAnnotation) {
		this.mainType = constraintAnnotation.mainType();
	}
	
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		if(value==null) return true;  //null이면 체크할 필요 없음. 건너뜀
		
		//else 면 즉, 널이 아니라 있으면. 마임을 꺼냄
		String mime = value.getContentType();
		//우리가 아는 파일 형태일 경우면서(마임이 널이 아니면서), image 마임이면.
		return mime!=null && mime.startsWith(mainType);
	}

}
