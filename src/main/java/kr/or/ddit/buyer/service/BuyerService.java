package kr.or.ddit.buyer.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.BuyerVO;

/**
 * 제조사 관리용 business logic layer
 */
public interface BuyerService {
	public Optional<BuyerVO> readBuyer(String buyerId);
	public List<BuyerVO> readBuyerList();
	//데이터 흐름 파악이 중요!
	public void createBuyer(BuyerVO buyer);  //성공하면 아무 일도 안 일어남, 실패시 예외 발생
	public void modifyBuyer(BuyerVO buyer);
}
