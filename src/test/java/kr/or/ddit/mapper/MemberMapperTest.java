package kr.or.ddit.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.annotation.RootContextConfig;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RootContextConfig
class MemberMapperTest {

	@Autowired
	MemberMapper mapper;
	
	@Test
	void testSelectMember() {
		MemberVO member = mapper.selectMember("a001");
		System.out.println(member);
		assertNotNull(member);
	}
}
