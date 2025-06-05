package kr.or.ddit.case01.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 *	 /cas01/rest/api 로 발생하는 rest 요청에 대한 핸들러 매핑.
 *	 CRUD 요청 처리 가능해야 함.
 *	 해당 요청에서 POST/PUT 요청의 경우, body 컨텐츠로 json을 수신함.
 */
@Slf4j
@Controller  
//@Controller는 
// 스프링 컨테이너가 (하위 컨테이너에) 빈을 등록하는 조건으로 사용되고,
//내부에 핸들러 메소드가 있을 거라 가정하고(아직 없을 때조차도)
// HandlerMapping 에 의해 핸들러 메소드의 클래스로 수집됨.
//컨트롤러 클래스를 수집하고 그 안에 든 핸들러 메소드를 수집해서 컨트롤러 완성하는 역할.
@RequestMapping(value = "/case01/rest/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Case01RestController {
	
	@GetMapping
	private void doGet() {
		log.info("get 요청 수신");
	}
	
//	@PostMapping(consumes = "application/json")  
	//consume은 content-type이 있는 헤더에 쓰임. header는 옛날 거.
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	private void doPost() {
		log.info("post 요청 수신");
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	private void doPut() {
		log.info("put 요청 수신");
	}
	
	@DeleteMapping
	private void doDelete() {
		log.info("delete 요청 수신");
	} 
}
