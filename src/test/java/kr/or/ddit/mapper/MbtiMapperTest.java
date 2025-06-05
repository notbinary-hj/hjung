package kr.or.ddit.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.annotation.RootContextConfig;
import kr.or.ddit.vo.MbtiVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RootContextConfig
class MbtiMapperTest {
	
	@Autowired
	MbtiMapper mapper;

	@Test
	void testInsertMbti() {
		MbtiVO newMbti = new MbtiVO("hhhh");
		newMbti.setMtTitle("AAAA 타입 요약");
		newMbti.setMtContent("AAAA 타입 상세");
		int cnt = mapper.insertMbti(newMbti);
		assertEquals(1, cnt);
	}

	@Test
	void testSelectMbtiList() {
		List<MbtiVO> list = mapper.selectMbtiList();
		log.info("기록되겠지? {}", list);
		list.forEach((mt)->log.info("===>{}", mt));
	}

	@Test
	void testSelectMbti() {
		assertNotNull(mapper.selectMbti("aaaa"));
	}

	@Test
	void testUpdateMbti() {
//		put / patch
		MbtiVO mbti = mapper.selectMbti("aaaa");
//		MbtiVO newMbti = new MbtiVO("aaaa");  //이건 put방식. mtContent는 비어있음. null값이 들어감
		mbti.setMtTitle("AAAA 타입 수정");
		int cnt = mapper.updateMbti(mbti);
		assertEquals(1, cnt);
	}

	@Test
	void testDeleteMbti() {
		assertEquals(1, mapper.deleteMbti("aaaa"));
	}

}
