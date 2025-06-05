package kr.or.ddit.buyer.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.annotation.RootContextConfig;

//필드 인젝션. 제일 간결하고 다소 위험. 필드 나가면 많이 사용.
@RootContextConfig  //1. 커스텀 어노테이션으로 상위 컨텍스트 동시 설정
class BuyerServiceImplTest {
	@Autowired  //2. 혹은 인젝트나 리소스
	BuyerService service;
	
	@Test
	void testReadBuyer() {
		fail("Not yet implemented");
	}

	@Test
	void testReadBuyerList() {
		service.readBuyerList();
	}

	@Test
	void testCreateBuyer() {
		fail("Not yet implemented");
	}

	@Test
	void testModifyBuyer() {
		fail("Not yet implemented");
	}

}
