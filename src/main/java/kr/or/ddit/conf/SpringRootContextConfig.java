package kr.or.ddit.conf;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

/**
 * 1. DataSource 생성
 * 2. Mybatis 연동 설정
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = "kr.or.ddit", excludeFilters = {
	@Filter(Controller.class), @Filter(ControllerAdvice.class)
})
@PropertySource("classpath:kr/or/ddit/FileInfo.properties")
public class SpringRootContextConfig {

	@PostConstruct
	public void init() {
		log.info("상위 컨테이너 생성");
	}
}
