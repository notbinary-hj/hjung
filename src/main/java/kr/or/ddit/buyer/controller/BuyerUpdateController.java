package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.BuyerVO;
import lombok.RequiredArgsConstructor;

/**
 *  /buyer/buyerUpdate.do (GET, POST)
 */
@Controller
@RequestMapping("/buyer/buyerUpdate.do")
@RequiredArgsConstructor  //생성자 주입 방식(스프링이 권장)
public class BuyerUpdateController{
	private final BuyerService service;  //final 안 넣었더니 널 떴어! 왜지...
	
	//errors Utils라는 빈 사용해야 함.
	private ErrorsUtils errorsUtils;
	@Autowired(required = false)  //세터 호출해 주입받기, 필수는 아니니 "있으면 주입해줘"
	public void setErrorsUtils(ErrorsUtils errorsUtils) {
		this.errorsUtils = errorsUtils;
	}
	
	static final String MODELNAME = "buyer";
	
	/**
	 * 등록 form 제공
	 */
	@GetMapping
	public String editForm(
		//필수 파라미터 왓->@RequestParam(스프링이 자동 붙임), required(자동 트루)
		String what
		, Model model
	) {  
		if(!model.containsAttribute(MODELNAME)) {  //모델에 buyer가 없으면, db에서 가져옴
			BuyerVO buyer = service.readBuyer(what).get();
			model.addAttribute(MODELNAME, buyer);
		}
		return "buyer/buyerEdit";
	}
	
	/**
	 * form 으로 입력받은 데이터 처리
	 */
	//핸들러 어뎁터에게 책임을 떠넘기는 중. 스프링 개발자는 핸들러 어뎁터를 얼마냐 잘 쓰느냐가 중요
	@PostMapping
	public String formProcess(
		String what
		, @Validated(UpdateGroup.class) @ModelAttribute(MODELNAME) BuyerVO buyer  //커맨드 오브젝트면서 모델
		//커맨드 오브젝트 what이 BuyerVO에 못 들어가므로 따로 선언했음. 아예 what 빼버리고 buyer로 통일한듯
		, BindingResult errors
		, RedirectAttributes redirectAttributes  //httpSession 받던 게 플래시 어트리뷰트로 바뀜. 플래시맵을 통해 session에 저장되므로, 세션을 우리가 직접 안쓸뿐이지 세션을 대신 사용해줌.
	) {
		String lvn;
		if(!errors.hasErrors()) {
			service.modifyBuyer(buyer);
			// PRG 패턴
			lvn = "redirect:/buyer/buyerDetail.do?what=" + buyer.getBuyerId();
		}else {
			redirectAttributes.addFlashAttribute(MODELNAME, buyer);
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			//errorsUtils가 있을 때만 사용하는 거니 if문으로 묶는게 안전하긴함...
			redirectAttributes.addFlashAttribute("errors", customErrors);
//			/buyer/buyerInsert.do (redirect--GET)
			lvn = "redirect:/buyer/buyerUpdate.do?what=" + what;  //새로고침시 db 저장 데이터가 불려와짐
		}
		return lvn;
	}
}
