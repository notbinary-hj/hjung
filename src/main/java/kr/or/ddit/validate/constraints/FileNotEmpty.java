package kr.or.ddit.validate.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = {FileNotEmptyValidator.class})
@Target(ElementType.FIELD)  //전역변수로 쓸거
@Retention(RetentionPolicy.RUNTIME)
public @interface FileNotEmpty {
	//기본 메시지 있어야
	String message() default "파일이 비어있음";
	//그룹 힌트용
	Class<?>[] groups() default { };
	//exception 처리를 도움
	Class<? extends Payload>[] payload() default { };

}
