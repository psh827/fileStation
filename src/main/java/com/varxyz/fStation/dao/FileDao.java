package com.varxyz.fStation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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
			
			//for문을 돌면서 파일 배열들을 DB에 입력
			for(OurFile of : ourFile) {
				jdbcTemplate.update(sql, of.getPasswd(), of.getFileOriName(), of.getFileName(), of.getFileSize(), of.getFileType(), of.getUrl());
			}
			
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
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	/**
	 * 텍스트 불러오기
	 * @param passwd
	 * @return
	 */
	public List<Text> getTextByPasswd(String passwd) {
		try {
			String sql = "SELECT * FROM Text WHERE passwd = ? AND deleteCheck = ? ORDER BY regDate DESC";
			return jdbcTemplate.query(sql, new RowMapper<Text>() {

				@Override
				public Text mapRow(ResultSet rs, int rowNum) throws SQLException {
					Text text = new Text();
					text.setContent(rs.getString("content"));
					text.setRegDate(rs.getTimestamp("regDate"));
					text.setDeleteDate(rs.getTimestamp("deleteDate"));
					return text;
				}
				
			}, passwd, "NO");
			
		}catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Text> getAllText() {
		try {
			String sql = "SELECT * FROM Text WHERE deleteCheck = ?";
			return jdbcTemplate.query(sql, new RowMapper<Text>() {

				@Override
				public Text mapRow(ResultSet rs, int rowNum) throws SQLException {
					Text text = new Text();
					text.setTId(rs.getLong("tId"));
					text.setDeleteDate(rs.getTimestamp("deleteDate"));
					return text;
				}
			}, "NO");
		} catch (Exception e) {
			return null;
		}
	}
	
	public int deleteAllText(Text text) {
		try {
			String sql = "UPDATE Text SET deleteCheck = ? WHERE tId = ?";
			jdbcTemplate.update(sql, "YES", text.getTId());
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 텍스트 삭제
	 * @param deleteType
	 * @param passwd
	 * @return
	 */
	public int deleteText(String deleteType, String passwd) {
		try {
			String sql = "UPDATE Text SET deleteCheck = ? WHERE passwd = ?";
			jdbcTemplate.update(sql, deleteType, passwd);
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
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
			String sql = "SELECT * FROM File WHERE passwd = ? AND deleteCheck = ?";
			
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
					of.setDeleteCheck(rs.getString("deleteCheck"));
					of.setRegDate(rs.getTimestamp("regDate"));
					of.setDeleteDate(rs.getTimestamp("deleteDate"));
					return of;
				}
				
			}, passwd, "NO");
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<OurFile> getAllFile() {
		try {
			String sql = "SELECT * FROM File WHERE deleteCheck = ?";
			return jdbcTemplate.query(sql, new RowMapper<OurFile>() {

				@Override
				public OurFile mapRow(ResultSet rs, int rowNum) throws SQLException {
					OurFile of = new OurFile();
					of.setFileId(rs.getLong("fId"));
					of.setFileOriName(rs.getString("fileOriName"));
					of.setDeleteDate(rs.getTimestamp("deleteDate"));
					return of;
				}
				
			}, "NO");
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 파일pk로 파일 가져오기
	 * @param fileId
	 * @return
	 */
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

	
	public int deleteAll(OurFile of) {
		try {
			String sql = "UPDATE File SET deleteCheck = ? WHERE fId = ?";
			jdbcTemplate.update(sql, "YES", of.getFileId());
			System.out.println("삭제완료");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	/**
	 * 파일 삭제
	 * @param passwd
	 * @return
	 */
	public int deleteFile(String deleteType, String passwd) {
		try {
			String sql = "UPDATE File SET deleteCheck = ? WHERE passwd = ?";
			jdbcTemplate.update(sql, deleteType, passwd);
			
			return 1;
		} catch (Exception e) {
			
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public List<Integer> jee() {
		List<String> checkList = new ArrayList<String>();
		List<Integer> countList = new ArrayList<Integer>();
		checkList.add("IMG");
		checkList.add("VIDEO");
		checkList.add("DOCUMENT");
		checkList.add("ETC");
		try {
			String sql = "SELECT count(*) FROM File WHERE fileType=? ";
			String sql2 = "SELECT count(*) FROM Text";
			//for문을 돌면서 데이터들을 가져온다.
			for(int i = 0; i < checkList.size(); i++) {
				countList.add(jdbcTemplate.queryForObject(sql, Integer.class, checkList.get(i)));
			}
			countList.add(jdbcTemplate.queryForObject(sql2, Integer.class));
			return countList;
			
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	/**
	 * 월별 업로드된 총 파일크기 가져오기
	 */
	public long getFileAmountByMonth(String month) {
		try {
			String sql = "SELECT SUM(fileSize) FROM File WHERE Month(regDate) = MONTH(?) AND YEAR(regDate) = YEAR(CURRENT_DATE())";
			return jdbcTemplate.queryForObject(sql, Long.class, month);
		} catch (NullPointerException e) {
			return 0;
		}
	}
		
	//월별 이용자 수 구하기
	public List<Integer> jung(List<String> monthList) {
		List<Integer> countList = new ArrayList<Integer>();
		String sql = "SELECT count(distinct passwd) FROM File WHERE Month(regDate) = MONTH(?) AND YEAR(regDate) = YEAR(CURRENT_DATE());";
		for(int i = 0; i < monthList.size(); i++) {
			countList.add(jdbcTemplate.queryForObject(sql, Integer.class, monthList.get(i)));
		}
		return countList;		
	}

	public void hoon() {
		
	}

}
