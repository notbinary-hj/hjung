package kr.or.ddit.dummy;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DummyListener implements ServletContextListener {

	//서버 구동시...(js의 이벤트 핸들러=스프링의 리스너)
    public void contextInitialized(ServletContextEvent sce)  { 
//    	log.info("서버 구동");
    	System.out.println("서버 구동");
    }

    //서버 종료시...
    public void contextDestroyed(ServletContextEvent sce)  { 
//    	log.info("서버 종료");
    	System.out.println("서버 종료");
    }
	
}
