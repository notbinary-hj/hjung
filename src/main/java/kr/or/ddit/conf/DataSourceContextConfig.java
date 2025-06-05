package kr.or.ddit.conf;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:kr/or/ddit/db/DBInfo.properties")
public class DataSourceContextConfig {
	@Bean
	public DataSource dataSource(  //이름 주의!! 규칙.
		@Value("${maindb.driverClassName}") String driverClassName
		, @Value("${maindb.url}") String url
		, @Value("${maindb.username}") String username
		, @Value("${maindb.password}") String password
		, @Value("${maindb.autoCommit}") boolean autoCommit
		, @Value("${maindb.minimumIdle}") int minimumIdle
		, @Value("${maindb.maximumPoolSize}") int maximumPoolSize
		, @Value("${maindb.connectionTimeout}") long connectionTimeout
	) {
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setJdbcUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setAutoCommit(autoCommit);
		ds.setMinimumIdle(minimumIdle); 
		ds.setMaximumPoolSize(maximumPoolSize); 
		ds.setConnectionTimeout(connectionTimeout);
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(
		DataSource dataSource 
	) {
		return new JdbcTemplate(dataSource); 
	}
}
