package kr.or.ddit.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import jakarta.annotation.PostConstruct;
import kr.or.ddit.annotation.RootContextConfig;
import kr.or.ddit.conf.SpringRootContextConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RootContextConfig
//@SpringJUnitWebConfig(classes = SpringRootContextConfig.class)
		//위 클래스 ComponentScan 있으니 이것만으로 다 가져옴
class LprodMapperTest {

	@Autowired
	LprodMapper mapperProxy;
	
	@PostConstruct
	public void init() {
		log.info("mapper proxy : {}", mapperProxy);		
	}
	
	@Test
	void testSelectLprodList() {
		mapperProxy.selectLprodList();
	}
}
