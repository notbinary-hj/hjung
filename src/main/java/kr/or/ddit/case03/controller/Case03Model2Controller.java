package kr.or.ddit.case03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * 스프링 내부에서 logical view name 의 사용 방법
 * ViewResolver 구현체에 의해 결정됨.
 * 뷰 리졸버의 종류)
 * 1. InternalResourceViewResolver
 * 		: jsp view 로 응답을 전송하고, prefix 와 suffix 로 jsp 위치를 일괄 관리할 수 있음.
 * 		: thymeleaf view 로 응답을 전송하고, prefix 와 suffix 로 thymeleaf 위치를 일괄 관리할 수 있음.
 * 		: mustache view 로 응답을 전송하고, prefix 와 suffix 로 mustache 위치를 일괄 관리할 수 있음.
 * 2. ContentNegotiatingViewResolver 콘텐츠를 협상할 수 있는 뷰 리졸버
 * 		: request "Accept" 헤더를 기반으로 응답 데이터의 Mime 을 결정하고,
 * 		  그에 맞는 View (예: GsonView)를 사용하기 위한 전략 객체.
 */
@Slf4j
@Controller
@RequestMapping("/case03")
public class Case03Model2Controller {
	@RequestMapping("handler02")
	private ModelAndView handler02() {  //ModelAndView는 옛날 방식. 필드에선 대부분 아래로 씀
		log.info("case03/handler02 수신");
		String lvn = "case03/view02";
		ModelAndView mav = new ModelAndView();
		mav.setViewName(lvn);
		return mav;
	}
	@RequestMapping("handler01")  //어떤 헤더도 어떤 메소드도 이 url이면 수신함
	private String handler01() {
		log.info("case03/handler01 수신");
		String lvn = "case03/view01";
		return lvn;
	}
}
