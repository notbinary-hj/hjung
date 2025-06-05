package kr.or.ddit.conf;

import javax.sql.DataSource;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;


@Configuration
@MapperScan(  //2. 매퍼 스캐너(impl 생략시 이것만 있어도)
	basePackages = "kr.or.ddit.mapper"
	, annotationClass = Mapper.class  //Repository 어노테이션 썼으면 그거로
)
public class MapperContextConfig {
//1. 세션 팩토리(이름이 Bean으로 끝나야해)
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(
		@Value("classpath:kr/or/ddit/mybatis/Configuration.xml") 
			Resource configLocation
		, DataSource dataSource
		, @Value("classpath:kr/or/ddit/mapper/**/*.xml") 
			Resource...mapperLocations  //매퍼 로케이션은 매퍼 인터페이스를 만드는 위치랑 같아
	) {
		SqlSessionFactoryBean factoryBean =
				new SqlSessionFactoryBean();
		factoryBean.setConfigLocation(configLocation);
		factoryBean.setDataSource(dataSource);
		factoryBean.setMapperLocations(mapperLocations);
		return factoryBean;
	}


//이 두개(MapperScan, SessionFactory)는 datasource 통해서. 같은 컨테이너에 등록됐으니 여기서 동시 적용 가능
}
