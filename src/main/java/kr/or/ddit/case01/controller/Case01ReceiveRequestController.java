package kr.or.ddit.case01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

/**
 * 컨트롤러를 bean으로 등록하고,
 * @Controller
 * 해당 컨트롤러가 특정 요청을 수신할 수 있는 방법.
 * @RequestMapping(url, method, header, parameter)
 * (***주의!***) 특정 요청에 대한 커맨드 팬들러를 메소드를 기준으로 구현됨.
 *  
 *  HandlerMapping 의 역할
 *  1. 컨트롤러화 핸들러 메소드에 대한 정보를 수집하고, handler map을 형성함.
 *  	- @Controller, @RequestMapping, @Get[Post...]Mapping 등의 어노테이션을 트레이싱함.
 *  	매핑 조건을 표현하는 속성들
 *  	value(혹은 name) : 매핑 url
 *  	method : 매핑 request method
 *  	consumes : request Content-Type 헤더에 대한 매핑
 *  	produces : request Accept 헤더에 대한 매핑
 *  2. Front controller 가 받은 요청을 처리할 수 있는 핸들러를 검색함.
 *  	-> 검색에 실패한 경우, 4XX 계열의 상태코드로 응답이 전송됨. (각 속성마다 다른 상태코드)
 */
@Slf4j
@Controller  //하위 컨테이너로 등록하는 어노테이션. 이거 외 Configuration, Component, Service, Repository 모두 상위에 등록됨.
@RequestMapping("/case01")
public class Case01ReceiveRequestController {
	@RequestMapping(value="handler01", method = RequestMethod.GET)
	public void handler01() {
		log.info("/case01/handler01 요청 수신");
	}

	@RequestMapping(value="handler02")
	public void handler02() {
		log.info("/case01/handler02 요청 수신");
	}
	
	@RequestMapping(value="handler03", headers = "content-type=application/json")
	public void handler03() {
		log.info("/case01/handler03 요청 수신");
	}
	
	@RequestMapping(value="handler04", params = "p1=23")
	public void handler04() {
		log.info("/case01/handler04 요청 수신");
		//파라미터 누락으로 인한 400에러를 직접 발생시키지 않아도 됨
	}
	
	@GetMapping("handler05")  //메타 어노테이션이라 get 메소드 어노테이션을 포함함
	public void handler05_1() {
		log.info("/case01/handler05 GET요청 수신");
	}
	
	@PostMapping("handler05")
	public void handler05_2() {
		log.info("/case01/handler05 POST요청 수신");
	}

	@PutMapping("handler05")
	public void handler05_3() {
		log.info("/case01/handler05 PUT요청 수신");
	}
	
	@DeleteMapping("handler05")
	public void handler05_4() {
		log.info("/case01/handler05 DELETE요청 수신");
	}
	
	@PostConstruct
	public void init() {
		log.info("{} 빈 등록 완료", this.getClass().getSimpleName());
	}
}
