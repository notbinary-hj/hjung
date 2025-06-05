package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.BuyerVO;

/**
 *  /buyer/buyerInsert.do (GET, POST)
 */
@Controller  //webservlet 때는 톰캣에 서블릿이 등록됐지만, 이제 스프링에 빈으로 등록됨
@RequestMapping("/buyer/buyerInsert.do")
public class BuyerInsertController{
	//인젝션을 받아야(autowired 혹은 inject)
	@Autowired  //어노테이션 통한 건 필드 인젝션(스프링이 비권장)
	private BuyerService service;
	
	static final String MODELNAME = "buyer";
	
	@ModelAttribute(MODELNAME)
	public BuyerVO buyer() {
		return new BuyerVO();
	}
	
	/**
	 * 등록 form 제공
	 */
	@GetMapping
	public String formUI() {		
		return "buyer/buyerForm";
	}
	
	/**
	 * form 으로 입력받은 데이터 처리
	 */
	@PostMapping
	public String formProcess(
		@Validated(InsertGroup.class) @ModelAttribute(MODELNAME) BuyerVO buyer
		, BindingResult errors
		, RedirectAttributes redirectAttributes
	) {
		String lvn;
		if(!errors.hasErrors()) {
			service.createBuyer(buyer);
			lvn = "redirect:/buyer/buyerList";  //왜 디테일로 가면 안되지... 방금 넣은 값의 상세목록이 떠야하는거 아닌가...
		}else {
			String errorsName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
			redirectAttributes.addFlashAttribute(errorsName, errors);
			lvn = "redirect:/buyer/buyerForm";
		}
		return lvn;
	}
}















