package kr.or.ddit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import kr.or.ddit.conf.SpringRootContextConfig;

//어노테이션이 또다른 어노테이션을 가짐, 메타 어노테이션- 해당 어노테이션을 상속받은 것과 똑같
//자식이 부모보다 더 많은 거 할수있음(예: 클래스 미리 설정)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringJUnitWebConfig(classes = SpringRootContextConfig.class)
public @interface RootContextConfig {
//어노테이션 특성.
//생성자 안 가짐
//1. 어디 사용할 것인가(Target) - 상속받을 것과 같게
//2. 어느 시점에 사라지는가(Retention) - 상속받을 것과 같게
}
