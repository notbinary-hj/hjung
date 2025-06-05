package kr.or.ddit.case06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case06/calculate")
public class Case06CalculateController {
//	1. form 으로 연결 : get
	@GetMapping
	public String formUI(Model model) {
		return "case06/calForm";
	}
//	2. form-data(연산) 처리 : post
	@PostMapping
	public String formData(
		int op1  //@RequestHeader보단 @RequestParam을 많이 사용하므로 생략해도 있는셈 침.
		, @RequestParam int op2
		, RedirectAttributes redirectAttributes  //flash Map에 옮겨담음
	) {
		int result = op1 + op2;
		redirectAttributes.addFlashAttribute("result", result);
		return "redirect:/case06/calculate";
	}
//	3. 연산 결과 : get(PRG-2에서 3으로 이동시 redirection, flash attribute)
	@GetMapping("result")
	public String result(Model model) {  //결과 확인하고싶으면 model에 담아서 로그로 확인
		log.info("result : {}", model.getAttribute("result"));
		return "case06/result";
	}
}
