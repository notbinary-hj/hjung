package kr.or.ddit.validate.utils;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Component  //상위에 등록
//상위에 등록시 하위 컨테이너가 여러 개 만들어져도 두고 사용가능
public class ErrorsUtils {
	//커스텀 에러는 필수는 아님-사용할 시 AA가 만들어두고 커스텀 태그를 팀원 모두가 사용.
	//별도의 유틸리티로 분리해도 됨
	public MultiValueMap<String, String> errorsToMap(BindingResult errors) {
		MultiValueMap<String, String> customErrors
			= new LinkedMultiValueMap<>();
		List<ObjectError> allErrors = errors.getAllErrors();
		for(ObjectError single : allErrors) {
			//single을 통해 하나하나 에러에 접근
			if(single instanceof FieldError) {  //싱글의 타입을 체킹, 필드 하나에 대한 에러(필드에러)를 가졌나 확인
				//필드에서 찾는 에러 키값(프로퍼티 이름. errors.buyerName이라면 buyerName)부터
				FieldError fe = (FieldError) single;
				String fieldName = fe.getField();  //리턴타입이 스트링. 키로 사용.
				String message = fe.getDefaultMessage();  //에러 메시지
				customErrors.add(fieldName, message);  //put은 맵이 쓰는거고 멀티밸류 api니까 
				//이미 존재하는게 있으면 add로 넣어주는 멀티밸류 api의 메소드 사용하자
			}
		}
		return customErrors;
	}
}

