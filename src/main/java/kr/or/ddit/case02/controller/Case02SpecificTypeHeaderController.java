package kr.or.ddit.case02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/case02")
public class Case02SpecificTypeHeaderController {
	/**
	 *  "my-flag" 요청 헤더 수신.
	 *  옵셔널 헤더이고, 헤더값으로 true/false만 수신, 생략된 경우, 기본으로 true 수신
	 */
	@GetMapping("header09")
	private void header02(
		@RequestHeader(name = "my-flag", required = false, defaultValue = "true") boolean myFlag
	){
		log.info("/case02/header09 수신, my-flag : {}", myFlag);
	}
	@GetMapping("header08")
	private void header01(
			@RequestHeader(value="my-age", required = false, defaultValue = "3")
			int myAge) {
		//기본형 타입으로 파라미터를 먼저 선언하면, int는 null데이타를 받을 수 없으니 기본값이 반드시 필요함.
	}
}
