package kr.or.ddit.validate.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MimeTypeCheckValidator.class)  //검증 목적이니 이거 넣어야. 한 쌍처럼 동작하는 벨리데이터 필요.
public @interface MimeTypeCheck {
	String mainType();
	//속성 정해뒀을 때, 이걸 임포트하면 속성도 같이 쓰거나 기본값이 적용 가능해야.
	//기본값이 정해져있으면 안 줘도 되는 속성이 됨.
	
	//필수속성으로 주고 싶으면 default 안 주고,
	//옵션으로 주고 싶으면 default 줌
	
	//다 옵션
	String message() default "파일 MIME 의 메인 타입 확인";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };

}
