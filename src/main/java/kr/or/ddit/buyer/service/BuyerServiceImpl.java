package kr.or.ddit.buyer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.BuyerMapper;
import kr.or.ddit.vo.BuyerVO;
import lombok.RequiredArgsConstructor;

@Service  //1. 빈 등록
@RequiredArgsConstructor  //2. 빈 주입(생성자 주입구조)
public class BuyerServiceImpl implements BuyerService {
	
	private final BuyerMapper mapper;  //3. 필수객체니까 final
	//private 붙이기! 의존객체는 매퍼에서 가져옴.

	@Override
	public Optional<BuyerVO> readBuyer(String buyerId) {
		return Optional.ofNullable(mapper.selectBuyer(buyerId));
	}

	@Override
	public List<BuyerVO> readBuyerList() {
		return mapper.selectBuyerList();
	}

	@Override
	public void createBuyer(BuyerVO buyer) {
		mapper.insertBuyer(buyer);
	}

	@Override
	public void modifyBuyer(BuyerVO buyer) {
		mapper.updateBuyer(buyer);
	}

}
