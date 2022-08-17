package com.varxyz.fStation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
			String sql = "INSERT INTO File (passwd, fileOriName, fileName, fileSize, fileType, url)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";
			
			for(OurFile of : ourFile) {
				jdbcTemplate.update(sql, of.getPasswd(), of.getFileOriName(), of.getFileName(), of.getFileSize(), of.getFileType(), of.getUrl());
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
		
		try {
			String sql = "SELECT * FROM File WHERE passwd = ?";
			
			return jdbcTemplate.query(sql, new RowMapper<OurFile>() {

				@Override
				public OurFile mapRow(ResultSet rs, int rowNum) throws SQLException {
					OurFile of = new OurFile();
					of.setFileId(rs.getLong("fId"));
					of.setFileName(rs.getString("fileName"));
					of.setFileOriName(rs.getString("fileOriName"));
					of.setFileSize(rs.getLong("fileSize"));
					of.setFileType(rs.getString("fileType"));
					of.setPasswd(rs.getString("passwd"));
					of.setUrl(rs.getString("url"));
					of.setRegDate(rs.getTimestamp("regDate"));
					return of;
				}
				
			}, passwd);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public OurFile getFileByfileId(String fileId) {
		
		try {
			String sql = "SELECT * FROM File WHERE fId = ?";
			
			return jdbcTemplate.queryForObject(sql, new RowMapper<OurFile>() {

				@Override
				public OurFile mapRow(ResultSet rs, int rowNum) throws SQLException {
					OurFile of = new OurFile();
					of.setFileId(rs.getLong("fId"));
					of.setFileName(rs.getString("fileName"));
					of.setFileOriName(rs.getString("fileOriName"));
					of.setFileSize(rs.getLong("fileSize"));
					of.setFileType(rs.getString("fileType"));
					of.setPasswd(rs.getString("passwd"));
					of.setUrl(rs.getString("url"));
					of.setRegDate(rs.getTimestamp("regDate"));
					return of;
				}
				
			}, fileId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
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

	public void jee() {
		
	}

	public void nam() {
		
	}

	public void jung() {
		
	}

	public void hoon() {
		
	}

	
	
	
	
}
