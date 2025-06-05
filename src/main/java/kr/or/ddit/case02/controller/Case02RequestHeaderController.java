package kr.or.ddit.case02.controller;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 요청 헤더를 수신하고, 그걸 파싱하고, 핸들러 메소드를 실행하는 건???
 * HandlerAdapter 에 의해 처리됨.
 * 요청 헤더 수신 : @RequestHeader(name, required, defaultValue) - 수신 정책 결정
 * 파싱?? : 핸들러 메소드 아규먼트 타입으로 파싱 타입이 결정됨.
 * ex) @RequestHeader(name="myAge", required=false, defaultValue="34") int myAge
 * 
 * HandlerAdapter
 *  : HandlerMapping 핸들러를 검색하면, HandlerAdapter 가 POJO 기반의 핸들러를 실행하는 역할을 함.
 *  
 */
@Slf4j
@Controller
@RequestMapping("/case02")  //상위 뎁스를 여기서 먼저 제안
public class Case02RequestHeaderController {
	@GetMapping("header07")  //쿠키의 값만 추출
//	public void handler07(@CookieValue(value = "dummyCookie", required = true) String dummyCookie) {  
	public void handler07(@CookieValue(value = "dummyCookie", required = false, defaultValue = "DEFAULTVALUE") String dummyCookie) {  
		//어노테이션으로 꺼낼 곳과 넣어줄 곳 알려줌. 이름이 같으니까 사실 dummyCookie라는 value 지워줘도 됨...
		//required니까 쿠키 반드시 있어야 된다(필수), 없으면 400 상태코드
		//required니까 쿠키 없어도 된다, 없으면 defaultValue를 쿠키 값에 대신 넣어줌
		log.info("case02/header07 수신, dummyCookie : {}", dummyCookie);  
	}
	
	@GetMapping("header06")  //쿠키의 이름:값 모두 추출
	public void handler06(@RequestHeader String cookie) {
		log.info("case02/header06 수신, Cookie : {}", cookie);
	}

	@GetMapping("header05")
	public void handler05(@RequestHeader HttpHeaders headers) {
		log.info("case02/header05 수신, headers : {}", headers);
	}

	@GetMapping("header04")
//	public void handler04(Map<String, String> header) {
	public void handler04(@RequestHeader MultiValueMap<String, String> headers) {  //스프링이 멀티밸류 맵이 리스트로 만들어줌
		log.info("case02/header04 수신, headers : {}", headers);
	}
	
	@GetMapping("header03")
//	public void handler03(@RequestHeader String accept) {  //parameter를 리플렉션해서 coc 적용->알아서 accept로 옴
//	public void handler03(@RequestHeader String accept-language) {  //coc로 리플렉션 안됨
	public void handler03(@RequestHeader("accept-language") String acceptLanguage) {  //카멜 케이스 적용해야
		log.info("case02/header03 수신, accept header : {}", acceptLanguage);
	}

	@GetMapping("header02")
	public void handler02(@RequestHeader(required = false, defaultValue = "*/*") String accept) {  
		//@RequestHeader는 아무것도 안쓰면 자동으로 required=true가 됨
		//기본적으로 accept가 있음. 이건 그냥 accept가 있어서 거기 값이 들어옴. */*는 어지간하면 쓸일없지만 만약~을 대비해 설정한 기본값이고 영향 없음...(파라미터는 뒤에있는 String accept지 얜 아니야!)
		log.info("case02/header02 수신, accept header : {}", accept);
	}

	@GetMapping("header01")
	public void handler01(HttpServletRequest req) {  //누군가 요청을 넣어줌
		log.info("case02/header01 수신, accept header : {}", req.getHeader("accept"));
	}
}
