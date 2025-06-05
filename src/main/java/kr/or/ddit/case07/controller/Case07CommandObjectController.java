package kr.or.ddit.case07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import kr.or.ddit.case07.vo.DummyVO;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * command object 를 이용한 form data 바인딩. 
 * formUI를 이용해 여러 개의 command data가 전송될때 사용.
 * (command data가 없거나, 한두개면 그닥 불필요)
 * 1. form UI 구성.
 * 	1) controller 구현
 * 	- GetMapping 핸들러 메소드
 * 	- model attribute 를 전달해야 함.
 * 	  Model, ModelAndView, @ModelAttribute(가장 에러 발생률 적음) 중
 * 	  @ModelAttribute 메소드를 구현하고, 모델 객체를 생성 및 반환.
 * 	2) view 구현
 * 	- form 커스텀 태그 사용(modelattribute 속성).
 * 
 * 2. form data 처리.
 * 	1) PostMapping 핸들러 메소드
 *		- 핸들러 메소드 아규먼트로 커맨드 오브젝트 사용. : 동시에 모델 속성으로도 저장됨(CoC 반영).
 *		  예)
 *		  DummyVO dummy  -- CoC로 이름이 dummyVO가 됨
 *		  @ModelAttribute 로 속성명 변경 가능.
 *		  예) @ModelAttribute("dummy")  -- 이름이 dummy가 됨
 *		- 커맨드 오브젝트에 대한 검증
 *		  @Valid, @Validated(group hint) 와 Errors(BindingResult) 활용
 *		  ex) handlerMethod(@Valid CommandObject co, BindingResult errors)
 *			*주의! 검증 결과는 검증 대상인 커맨드 오브젝트 바로 다음 인자로 받아야 함. 
 *			순서 바뀌면 어뎁터가 인식 못함.
 *			예)
 *			DummyVO dummy  -- 검증 대상인 커맨드 오브젝트
			, BindingResult errors  -- 바로 다음으로 검증 결과 받기
		- 검증 실패시 form UI 로 이동. 커맨드 오브젝트(이름 임의 가능)와 
		  검증 결과(이름 임의X 고정됨, 커스텀 태그가 내부적으로 사용)를 모델 속성으로 저장해야 함.
		  	*주의! 검증 결과를 모델 속성으로 저장할 때 사용할 모델명은
		  		 BindingResult.MODEL_KEY_PREFIX + "커맨드 오브젝트의 모델 속성명"
 */
@Slf4j
@Controller
@RequestMapping("/case07")
public class Case07CommandObjectController {
	//아래 방식과 다르게, 핸들러 어뎁터가 이걸 먼저 실행해서
	//이하의 모든 메서드가 이 모델명 사용 가능해짐.
	//메소드를 이렇게 선언->언제나 핸들러보다 먼저 실행
	//1. 빈 dummy 생김
	//2. 폼 거치고 나면->검증 끝난 dummy가 헨들러 어뎁터에 담김
	//3. 오류 있을 시 redirection한 dummy가 dummyForm에 넣어짐
	//->갖고 있는 flash 어트리뷰트에서 같은 이름을 검색하는데, 이미 dummy가 있으면 안 만들고 건너뛰므로
	@ModelAttribute("dummy")
	public DummyVO dummy() {
		log.info("dummy() 메소드 실행 및 'dummy' 속성 생성");
		return new DummyVO();
	}
	
	@GetMapping("dummyForm")
//	public String formUI(@ModelAttribute("dummy") DummyVO dummy) {  //dummy 여기서 넣으면 에러메시지 안 들어간 dummy가 뷰로 넘어가버림. 왜지?=>이름이 달라. dummyVO가 생성된 거야! 그래서 위에서 만든거 안 사용한 거고. 
	public String formUI() {
//		DummyVO dummy = new DummyVO();
		//dummy를 모델(리퀘스트)에 넣음
//		model.addAttribute("dummy", dummy);
		log.info("dummyForm 핸들러 메소드 실행");
		return "case07/form";
	}
	
	// 문자로 전달된 name(필수), 숫자로 전달된 age(옵셔널)
	// 문자들로 전달된 hobbies(옵셔널)
	@RequestMapping("commandObject02")
	public String handler02(
		@Validated(InsertGroup.class) @ModelAttribute("dummy") DummyVO dummy
		, BindingResult errors  //검증 결과를 넣어줌
		//Errors 대신 필드에선 BindingResult(에러스 하위 계층구조의 인터페이스) 많이 쓰긴 함
		, RedirectAttributes redirectAttributes
	) {
		if(errors.hasErrors()) {
			redirectAttributes.addFlashAttribute("dummy", dummy);  //플래시라도 꺼내오는 값 이름 dummy는 유지돼야 맞음
			String errorsName = BindingResult.MODEL_KEY_PREFIX+"dummy";
			redirectAttributes.addFlashAttribute(errorsName, errors);
			log.error("검증 실패, {}", errors);
			errors.getAllErrors().forEach(oe->{  //에러는 리스트
				log.error("{}", oe);
			});
			return "redirect:/case07/dummyForm";  //그냥 폼 아니고. 위의 Get으로 가야!
			//redirection이라서 model이 날아감
			//redirectAttribute로 flash attribute 하면 model 가져올 수 있음
		}else {
			log.info("dummy vo : {}", dummy);
			return "case07/view01";
		}
	}
	@RequestMapping("commandObject01")
	public String handler01(@ModelAttribute("dummy") DummyVO dummy/*, Model model*/) {  //이 안에 모든 파라미터 바인딩돼있음
		//핸들러 어뎁터가 beanutils.populate 역할 다 함
//		model.addAttribute("dummy", dummy);
		log.info("dummy vo : {}", dummy);
		return "case07/view01";
	}
}
