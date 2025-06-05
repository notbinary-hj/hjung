package kr.or.ddit.case06.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case06")
public class Case06RequestParameterController {
	// 문자로 전달된 name(필수), 숫자로 전달된 age(옵셔널)
	// 문자들로 전달된 hobbies(옵셔널)
	@RequestMapping("parameter03")  //get이든 post든 받음. 받는 방법엔 차이X
	public String handler03( 
		//모든 파라미터를 받는 용도
		//키는 하난데 값이 여러개일 때 MultiValueMap 이용(예: hobbies)
		//모든 맵을 리스트로 관리함. 리스트의 엘리먼트 중 하나를 String으로 잡은거.
		@RequestParam MultiValueMap<String, String> parameters
		, Model model
	) { 
		log.info("parameters : {}", parameters);
		model.addAllAttributes(parameters);
		return "case06/view01";
	}
	@GetMapping("parameter02")
	public String handler02(
			@RequestParam String name  //요청의 파라미터를 어뎁터로부터 받음, coc로 요청 값과 받는 값이 같으니 name 생략
			, @RequestParam(required = false, defaultValue = "-1") int age
			, @RequestParam(required = false) String[] hobbies
			, Model model
			) { 
		//String name은 required=true(생략된 기본값)이라 필수니 불필요
		//나머지도 핸들러 어뎁터가 해줌
		log.info("name : {}", name);
		log.info("age : {}", age);
		log.info("hobbies : {}", Arrays.toString(hobbies));
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		model.addAttribute("hobbies", hobbies);
		return "case06/view01";
	}
	//Handler Method로 만들게 get 어노테이션
	@GetMapping("parameter01")  //파라미터가 query String으로 전달될것.
	public String handler01(HttpServletRequest req) { 
		String name = req.getParameter("name");
		//필수 파라미터 검증(commons.lang3 거여야, StringUtils 자체는 스프링 내부 것도 괜찮지만 다른 것까지 쓰게 디펜던시 추가)
		if(StringUtils.isBlank(name)) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(400));
		}
		//있나없나->옵셔널 api, 있으면 파싱(int로) - 파싱해서 데이터 변환하니 map
		int age = Optional.ofNullable(req.getParameter("age"))
				.map(Integer::parseInt)  //있으면 파싱
				.orElse(-1);  //없으면 orElse로 -1 반환
		String[] hobbies = req.getParameterValues("hobbies");
		log.info("name : {}", name);
		log.info("age : {}", age);
		log.info("hobbies : {}", Arrays.toString(hobbies));
		return "case06/view01";
	}
}
