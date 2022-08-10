package com.varxyz.fStation.dao;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.varxyz.fStation.domain.Post;

public class BoardDao {
	
	JdbcTemplate jdbcTemplate;
	
	public BoardDao(DataSource datasource) {
		jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	/**
	 * 건의사항 글 쓰기
	 * @param post
	 * @return
	 */
	public int writePost(Post post) {
		//sql insert 순서 지켜서 넣기
		String sql = "INSERT INTO Board (title, nickName, passwd, content)"
				+ " VALUES (?, ?, ?, ?)";
		return 0;
	}
	
	/**
	 * 모든 글 가져오기(게시판에 표출하기위해서)
	 * @return
	 */
	public List<Post> getAllPost() {
		String sql = "SELECT * FORM Board";
		return null;
	}
	
	/**
	 * 자기글 보기
	 * @param passwd
	 * @return
	 */
	public Post viewPostByPasswd(String passwd) {
		String sql = "SELECT * FROM Board WHERE passwd = ?";
		return null;
	}
	
	/**
	 * 자기 글 수정
	 * @param passwd
	 * @return
	 */
	public int modifyPost(Post post) {
		String sql = "UPDATE Board SET content = ? WHERE passwd = ?";
		return 0;
	}
	
	/**
	 * 자기 글 삭제
	 * @param passwd
	 * @return
	 */
	public int deletePost(String passwd) {
		String sql = "DELETE FROM Board WHERE passwd = ?";
		return 0;
	}
}
