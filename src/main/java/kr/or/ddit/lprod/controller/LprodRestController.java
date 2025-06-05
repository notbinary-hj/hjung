package kr.or.ddit.lprod.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.lprod.service.LprodService;
import kr.or.ddit.lprod.service.LprodServiceImpl;
import kr.or.ddit.vo.LprodVO;
import lombok.RequiredArgsConstructor;

//@Controller  //방법1
@RestController  //방법2
@RequiredArgsConstructor  //얘도 생성자래!
public class LprodRestController{
	private final LprodService service;
	
	//방법2) 모델 전체가 마샬링할 필요 없는 구조.
	//accept 헤더 없이도 응답 데이터가 json으로 옴.
	@GetMapping("/ajax/lprod")
	@ResponseBody
	public List<LprodVO> lprodList() {
		List<LprodVO> lprodList = service.readLprodList();
		return lprodList;
	}

	//방법1) 모델 전체가 마샬링되는 구조. ContentNegotitating view Resolver 구조에 의해 동작. - 어셉트 헤더 통함.->브라우저에 직접 주소 치면 어셉트 헤더가 응답으로 안 감->브라우저가 응답 내보내지X
//	@GetMapping("/ajax/lprod")  //js 통해서는 작동하는데, 직접 주소 넣으면 안되네...? 헤더가 없다는데 그럼 js 지날때 헤더가 생기는건가>?아님.
//	public void lprodList(Model model) {
//		List<LprodVO> lprodList = service.readLprodList();
//		model.addAttribute("lprodList", lprodList);
//	}
}
