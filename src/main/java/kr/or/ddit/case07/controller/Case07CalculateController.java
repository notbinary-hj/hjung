package kr.or.ddit.case07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import kr.or.ddit.case07.vo.CalculateVO;

/**
 * 	피연산자 두개를 입력받고, 더하기 연산의 결과를 생성함.
 *  두개의 피연산자는 모두 양의 정수로 가정함.
 *  커맨드 오브젝트와 스프링의 객체 검증 모델(BindingResult 사용)을 사용함.
 *  form UI 는 form 커스텀 태그 사용.
 */
@Controller
@RequestMapping("/case07/calculate")
public class Case07CalculateController {
	static final String MODELNAME = "cal";  //오타 줄이게 상수로
	
	//2. 모델 어트리뷰트 만들어둠(먼저 실행케)
	@ModelAttribute(MODELNAME)  //cal 대신 MODELNAME 넣어줌
	public CalculateVO calculateVO() {
		return new CalculateVO();
	}
	
	//1. 데이터 입력 폼으로
	@GetMapping
	public void formUI() {
//		return "case07/calculate";  //상위 뎁스와 똑같아서 생략, void로 변경
	}

	//3. 폼 보낼 때 파라미터 두 개 받음
	@PostMapping
	public String formProcess(
		@Valid @ModelAttribute(MODELNAME) CalculateVO cal  
		//그냥 두면 calculateVO라는 이름으로 모델 어트리뷰트가 생성되므로, cal이란 이름을 제공.
		, BindingResult errors  //에러스(검증 실패한 데이터의 밸류)는 커멘드 오브젝트 다음에.
		, RedirectAttributes redirectAttributes
	) {
		redirectAttributes.addFlashAttribute(MODELNAME, cal);
		if(errors.hasErrors()) {
			//에러 이름 결정하는 메커니즘 있어야 뷰의 커스텀 태그가 이해함
			String errorName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;  //에러네임(검증 실패한 데이터의 키?)
			redirectAttributes.addFlashAttribute(errorName, errors);
		}else {
			cal.setResult(cal.getOp1()+cal.getOp2());
		}
		//검증 통과시(연산 결과) 혹은 실패시(기존의 입력 데이터) 모두
		//모델인 cal을 가지고 페이지까지 가는 건 공통이므로 바깥에 만듦.
		return "redirect:/case07/calculate";
		
	}
}
