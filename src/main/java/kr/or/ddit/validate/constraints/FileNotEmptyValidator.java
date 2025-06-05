package kr.or.ddit.validate.constraints;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileNotEmptyValidator 
				implements ConstraintValidator<FileNotEmpty, MultipartFile>{

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		if(value==null) return true;   //무조건 예외 발생 없게 널값이라도 true로
		else
			return !value.isEmpty();
	}
}
