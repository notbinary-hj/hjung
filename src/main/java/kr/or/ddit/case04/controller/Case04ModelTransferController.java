package kr.or.ddit.case04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

/**
 *	/case04/model01(GET) 요청을 수신하고,
 *	최종 응답은 "case04/view01" 에서 전송함.
 *
 *	Model, ModelAndView, @ModelAttribute 사용 방법.
 *
 *	(***중요!***) 최종적으로 HandlerAdapter 에게 Model과 View에 대한 정보를 전달하는 방법. *
 *  Model : model 은 call by reference 형태로 전달하기 위해 핸들러 메소드의 인자(아규먼트)로 Model(이라는 api) 을 받음.
 *  		view name 은 반환값의 형태로 전달 (전형적인 jsp일 경우만 해당)
 *  ModelAndView : ModelAndView 라는 반환 객체로 model 과 view 를 한번에 전달.
 *  @ModelAttribute : 하나의 컨트롤러에 여러 핸들러가 있고, 각 핸들러가 공통적으로 사용할 model 이 있는 경우,
 *  	model을 반환하는 메소드를 구현하고, 해당 메소드의 반환타입에 @ModelAttribute를 사용함.
 *  
 *  (보통 Model&어노테이션 혹은 MAV&어노테이션 같이 씀)
 */
@Controller
@RequestMapping("/case04")
public class Case04ModelTransferController {
	@GetMapping("model02")
	public ModelAndView handler02() {  //MAV는 나눠 전달할 필요 없어서 아규먼트 없어짐
		ModelAndView mav = new ModelAndView();
		String modelAttr = "MODEL INFO";
		mav.addObject("modelAttr", modelAttr);  //사실상 addObject = addAttribute
		mav.setViewName("case04/view01");
		return mav;
		
		//=>모델과 뷰 네임을 반환객체 하나로 넘긴 경우
	}
	
	//젤 먼저 메소드 시그니처 잡기(Get)
	@GetMapping("model01")
//	public String handler01(HttpServletRequest req) {  //필요한건 Handler Adapter에게 받음
	public String handler01(Model model) {
		//스프링에선 웬만해선 어트리뷰트 직접 안 씀
		String modelAttr = "MODEL INFO";
		//속성 데이터를 키:밸류 쌍으로 만들어줌->내부적으로 req 스코프에 옮겨담음(->내부적으로 모델은 다 맵임)
		model.addAttribute("modelAttr", modelAttr);  //모델 결정(add. 콜 바이 레퍼런스 구조 C.R)
		return "case04/view01";  //lvn 결정(리턴값)
		//호출자에게 데이터를 전달하는 방법 1. 반환값(자바에선 하나의 반환값만 담을수있음) 2. CR로 attribute에 모두 담음 3. 어노테이션
		//핸들러 메소드를 호출하는 호출자는 핸들러 어뎁터
		
		//=>모델과 뷰 네임을 반환객체와 콜 바이 레퍼런스로 넘긴 경우
	}
}
