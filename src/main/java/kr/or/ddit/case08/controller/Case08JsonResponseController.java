package kr.or.ddit.case08.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import kr.or.ddit.case03.view.GsonView;

@Controller
@RequestMapping("/case08")
public class Case08JsonResponseController {
	
	/**
	 * @param model
	 * 	accept 요청 헤더를 기준으로 content-type 을 협상하는 방식.
	 * 	{@link ContentNegotiatingViewResolver} 의 동작 방식에 따라
	 *  {@link GsonView} 에서 Model 을 대상으로 마샬링이 처리되는 방식.
	 */
	// map to json
	@GetMapping("json02")
	public void handler02(Model model) {
		Map<String, Object> original = new HashMap<>();
		original.put("prop1", "문자열");
		original.put("prop2", 23);
		original.put("prop3", new String[] {"a1", "b2"});
		model.addAllAttributes(original);  
		//단점)
		//1. gson 뷰에서는 무조건 모델을 대상으로 마샬링.
		//2. 어셉트 헤더가 없으면 json이란 걸 몰라서 jsp로 보내려고 함. 헤더에 따라 마샬링이 달라짐.
	}

	/**
	 * accept 헤더와 무관하게 마샬링이 처리됨.
	 * HandlerAdapter 에 의해 response entity(res의 바디구조) 가 처리되는 형태.
	 *  마샬링의 대상이 되는 response entity 는
	 *  핸들러 메소드의 리턴타입에 @ResponseBody 로 표현함.
	 * @return
	 */
	@GetMapping("json01")
	@ResponseBody
	public Map<String, Object> handler01() {
		Map<String, Object> original = new HashMap<>();
		original.put("prop1", "문자열");
		original.put("prop2", 23);
		original.put("prop3", new String[] {"a1", "b2"});
		return original;
		//내부적으로, 어셉트 헤더 없어도 마샬링 함.
	}
}
