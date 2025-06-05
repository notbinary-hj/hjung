package kr.or.ddit.case04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/case04")
public class Case04ModelAttributeController {
	
	@ModelAttribute("modelAttr")  //모든 모델에는 키로 사용되는 이름이 있어야.
	public String modelAttr() {
		return "MODEL INFO";
	}
	
	//어노테이션을 쓰면
	//여러 핸들러에서 공통된 모델을 중복돼서 전달받을 때, 중복코드를 제거할 수 있음
	
	@GetMapping("model04")
	public String handler04() {
		return "case04/view01";
	}
	@GetMapping("model03")
	public String handler03() {
		return "case04/view01";
	}
}
