package com.varxyz.fStation.dao;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.varxyz.fStation.domain.OurFile;
import com.varxyz.fStation.domain.Text;

public class FileDao {

	JdbcTemplate jdbcTemplate;
	
	public FileDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * 파일 업로드
	 */
	public int addFile(List<OurFile> ourFile) {
		try {
			String sql = "INSERT INTO File (passwd, fileName, fileSize, fileType, url)"
					+ " VALUES (?, ?, ?, ?, ?)";
			
			for(OurFile of : ourFile) {
				jdbcTemplate.update(sql, of.getPasswd(), of.getFileName(), of.getFileSize(), of.getFileType(), of.getUrl());
			}
			
			System.out.println("입력성공");
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 텍스트 업로드
	 * @param textarea
	 * @return
	 */
	public int addText(Text text) {
		try {
			String sql = "INSERT INTO Text (passwd, content) VALUES(?, ?)";
			
			jdbcTemplate.update(sql, text.getPasswd(), text.getContent());
			
			System.out.println("입력성공");
			
			return 1;
		} catch (Exception e) {
			return 0;
		}
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
