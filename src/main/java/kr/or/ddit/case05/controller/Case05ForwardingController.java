package kr.or.ddit.case05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case05")
public class Case05ForwardingController {
	@RequestMapping("start01")
	public String start01(Model model) {  //이 model은 req의 스코프에 전달됨
		model.addAttribute("modelInfo", "전달할 모델");
		return "forward:/case05/dest01";  
		//lvn이 forward:로 시작되면 뷰를 찾는 게 아니라 또다른 컨트롤러로 이동하는 데 사용된단 의미. 정해진 단어.
		//view01까지 계속 포워드로 가므로, 최초의 요청이 그대로 전달돼서, modelInfo를 view01에서 꺼낼 수 있다.
	}
	
	@RequestMapping("dest01")
	public String dest01(@RequestAttribute String modelInfo){  
		//호출자인 핸들러 어뎁터에게 아규먼트인 modelInfo가 생김
		//->아규먼트를 어디서 꺼내야 되는지 어노테이션으로 알려줄 수 있음
		//꺼내기 위한 키는 modelInfo의 이름에서 찾음, 이름이 다를 땐 name으로 알려줌
		log.info("{}", modelInfo);  //위에서 옮겨담은 model은 값이 빠져나갔기 때문에 비게됨~
		return "case05/view01";	
		//request dispatcher 방식으로, 클라이언트는 dest01의 존재를 모른 채 start01->dest01로 자동 연결돼서 view01의 결과가 나옴.
	}
}
