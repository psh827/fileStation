package com.varxyz.fStation.jdbc;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/fStation?serverTimezone=Asia/Seoul");
		ds.setUsername("fStation");
		ds.setPassword("fStation");
		ds.setInitialSize(2); //커넥션 풀 초기화시 생성할 초기 커넥션 갯수(기본값 10)
		ds.setMaxActive(10); //풀에서 가져올 수 있는 최대 커넥션 갯수 (기본값 100)
		ds.setMaxIdle(10); //풀에 유지할 수 있는 최대 커넥션 수 (기본값은 max와 같다.)I
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
}
