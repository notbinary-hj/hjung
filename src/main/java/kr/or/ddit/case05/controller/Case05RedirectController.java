package kr.or.ddit.case05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case05")
public class Case05RedirectController {
	@GetMapping("start02")
	public String start02(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("modelInfo", "전달데이터");
		// PRG pattern
		return "redirect:/case05/dest02";  
		//중간에 300번대 응답이 나가서 req가 사라지므로 model데이터를 session 스코프에 담아야
		//세션 데이타를 넣고 꺼내자마자 지우기 위해 플래시 어트리뷰트 방식 사용.
	}
	// addFlashAttribute -> FlashMap 에 데이터 저장
	// --> redirect
	// Model 생성 -> FlashMap 에 저장되어 있는 flash attribute --> Model 로 이동 --> flash attribute 삭제
	//이 전체 과정을 관리해주는 게 플래시 맵 매니저(스프링 기본구조로 내장)
	@GetMapping("dest02")
	public String dest02(Model model) {  
		//플래시 매니저가 model에 위의 데이터를 담아줌. 그게 req로 들어가니 view에서 쓸 수 있는것
		//여기서 옮겨담으면서 바로 지워짐!
		if(model.containsAttribute("modelInfo")) {
			log.info("model : {}", model.getAttribute("modelInfo"));
		}
		return "case05/view01";
	}
}
