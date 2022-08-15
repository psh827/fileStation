package com.varxyz.fStation.jdbc;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.varxyz.fStation.dao.BoardDao;
import com.varxyz.fStation.dao.FileDao;
import com.varxyz.fStation.service.BoardServiceImpl;
import com.varxyz.fStation.service.FileServiceImpl;

@Configuration
public class DataSourceConfig {
	
	/**
	 * 데이터 베이스 생성시 데이터 베이스 이름을 fileStation으로 만들기
	 * 유저이름, 비밀번호도 fileStation으로 
	 * @return
	 */
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		ds.setUrl("jdbc:mysql://localhost:3306/fileStation?serverTimezone=Asia/Seoul");
		ds.setUrl("jdbc:mysql://localhost:3306/fStation?serverTimezone=Asia/Seoul");
//		ds.setUsername("fileStation");
		ds.setUsername("fStation");
//		ds.setPassword("fileStation");
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
	
	@Bean
	public PlatformTransactionManager transactionmanager() {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		txManager.setDataSource(dataSource());
		return txManager;
	}
	
	@Bean
	public FileServiceImpl fileService() {
		return new FileServiceImpl();
	}
	
	@Bean
	public FileDao fileDao() {
		return new FileDao(dataSource());
	}
	
	@Bean
	public BoardServiceImpl boardService() {
		return new BoardServiceImpl();
	}
	
	@Bean
	public BoardDao boardDao() {
		return new BoardDao(dataSource());
	}
	
	
	
}
