package kr.or.ddit.buyer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.vo.BuyerVO;

@Controller
@RequestMapping("/buyer")  //상위 뎁스 분리(
public class BuyerReadController {  //하나의 핸들러가 하나의 객체가 아닌 메소드니까 여기서 전부 처리 가능
	private BuyerService service;
	
	@Autowired  //setter injection 방식으로 주입
	public void setService(BuyerService service) {
		this.service = service;
	}
	
	@GetMapping("buyerList.do")
	public void listHandler(Model model) {  //lvn이니까 call by reference 로 나눠담을거 model로
		List<BuyerVO> buyerList = service.readBuyerList();
		model.addAttribute("buyerList", buyerList);
//		return "buyer/buyerList";
		//BuyerListController와 매우 유사함. model로 받는 거랑 lvn을 직접 호출하는 것만 다를뿐...
	}
	
	/*
		logical view name 이 명시되지 않은 경우,
		RequestToViewNameTranslator 가 동작하게 됨.
		ex) request url 이 lvn 으로 반영됨.
	
	buyer/buyerList.do나 buyer/buyerDetail.do는 겹치는 부분이 있으니까
	코드 코스트 줄일수있음. buyerInsert 등에선 못 사용. 필드에선 많이 사용.
	*/
	
	//한건의 바이어 띄울수있는 핸들러
	@GetMapping("buyerDetail.do")
	public void detailHandler(
		String what  //어떤 제조사라는 필수 파라미터(앞에 @RequestParam 이 생략)
		, Model model
	) {
		BuyerVO buyer = service.readBuyer(what).get();  
		//있으면 BuyerVO가 뜨고 없으면 NullPointerException 
		//널 포인트 익셉션 싫으면 orElseThrow나 try~catch로 묶어서 예외 전환
		model.addAttribute("buyer", buyer);
//		return "buyer/buyerDetail";
	}
}
