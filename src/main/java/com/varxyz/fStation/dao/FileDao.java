package com.varxyz.fStation.dao;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.varxyz.fStation.domain.OurFile;

public class FileDao {

	JdbcTemplate jdbcTemplate;
	
	public FileDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * 파일 업로드
	 */
	public int addFile(List<OurFile> ourFile) {
		String sql = "INSERT INTO File (passwd, fileName, fileSize, fileType)"
				+ " VALUES (?, ?, ?, ?)";
		return 0;
	}
	
	/**
	 * 파일 다운로드
	 * @param passwd
	 * @return
	 */
	public List<OurFile> getFile(String passwd) {
		String sql = "SELECT * FROM File WHERE passwd = ?";
		return null;
	}

	/**
	 * 파일 삭제
	 * @param passwd
	 * @return
	 */
	public int deleteFile(String passwd) {
		String sql = "DELETE FROM File WHERE passwd = ?";
		return 0;
	}
}
