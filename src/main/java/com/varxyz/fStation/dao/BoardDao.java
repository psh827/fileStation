package com.varxyz.fStation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
		try {
			jdbcTemplate.update(sql,post.getTitle(), post.getNickname(), post.getPasswd(), post.getContent());
			return 1;
			
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 모든 글 가져오기(게시판에 표출하기위해서)
	 * @return
	 */
	public List<Post> getAllPost() {
		String sql = "SELECT * FROM Board";

		return jdbcTemplate.query(sql, new RowMapper<Post>() {
			
			@Override
			public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
				Post post = new Post();
				post.setBoardId(rs.getLong("bId"));
				post.setTitle(rs.getString("title"));
				post.setNickname(rs.getString("nickName"));
				post.setPasswd(rs.getString("passwd"));
				post.setContent(rs.getString("content"));
				post.setRegDate(rs.getDate("regDate"));
				return post;	
			}
		});
	}
	
	/**
	 * 관리자페이지 새로운 글 가져오기.
	 * @return
	 */
	public List<Post> getAllPostToAdmin() {
		try {
			String sql = "SELECT * FROM Board WHERE adminContent IS null";
			return jdbcTemplate.query(sql, new RowMapper<Post>() {

				@Override
				public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
					Post post = new Post();
					post.setBoardId(rs.getLong("bId"));
					post.setTitle(rs.getString("title"));
					post.setNickname(rs.getString("nickName"));
					post.setPasswd(rs.getString("passwd"));
					post.setContent(rs.getString("content"));
					post.setRegDate(rs.getDate("regDate"));
					return post;
				}
				
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	
	/**
	 * 자기글 보기
	 * @param passwd
	 * @return
	 */
	public Post viewPostByPasswd(long bId, String passwd) {
		String sql = "SELECT * FROM Board WHERE bId = ? AND passwd = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new RowMapper<Post>() {
				
				@Override
				public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
					Post post = new Post();
					post.setBoardId(rs.getLong("bId"));
					post.setTitle(rs.getString("title"));
					post.setNickname(rs.getString("nickName"));
					post.setPasswd(rs.getString("passwd"));
					post.setContent(rs.getString("content"));
					post.setAdminContent(rs.getString("adminContent"));
					post.setRegDate(rs.getDate("regDate"));
					return post;	
				}
			}, bId, passwd);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public Post viewPostByBid(long bId) {
		String sql = "SELECT * FROM Board WHERE bId = ?";
		
		return jdbcTemplate.queryForObject(sql, new RowMapper<Post>() {
			
			@Override
			public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
				Post post = new Post();
				post.setBoardId(rs.getLong("bId"));
				post.setTitle(rs.getString("title"));
				post.setNickname(rs.getString("nickName"));
				post.setPasswd(rs.getString("passwd"));
				post.setContent(rs.getString("content"));
				post.setAdminContent(rs.getString("adminContent"));
				post.setRegDate(rs.getDate("regDate"));
				return post;	
			}
		}, bId);
	}
	
	/**
	 * 자기 글 수정
	 * @param passwd
	 * @return
	 */
	public int modifyPost(Post post) {
		String sql = "UPDATE Board SET content = ? WHERE bId = ?";
		try {
			jdbcTemplate.update(sql, post.getContent(), post.getBoardId());
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 관리자 댓글
	 * @param post
	 * @return
	 */
	public int adminComment(Post post) {
		String sql = "UPDATE Board SET adminContent = ? WHERE bId = ?";
		try {
			jdbcTemplate.update(sql, post.getAdminContent(), post.getBoardId());
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 자기 글 삭제
	 * @param passwd
	 * @return
	 */
	public int deletePost(Post post) {
		String sql = "DELETE FROM Board WHERE BId = ?";
		try {
			jdbcTemplate.update(sql, post.getBoardId());
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 게시글 수 가져오기
	 * @param 
	 * @return
	 */
	public int getPagingCount() {
		String sql = "SELECT COUNT(*) FROM Board";
		return jdbcTemplate.update(sql);
	}
	
	/**
	 * 페이징하기
	 * @param pageable
	 * @return
	 */
	public Page<Post> findAll(Pageable pageable) {
		Order order = pageable.getSort().isEmpty()
				? Order.by("bId")
				: pageable.getSort().toList().get(0);
		String sql = "SELECT bId, title, nickName, passwd,"
				+ "content, adminContent, regDate FROM Board "
				+ "	ORDER BY " + order.getProperty() + " " + order.getDirection().name()
				+ "	LIMIT " + pageable.getPageSize() 
				+ " OFFSET " + pageable.getOffset();
				
		return new PageImpl<Post>(
				jdbcTemplate.query(sql, new RowMapper<Post>() {
					
					@Override
					public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
						Post post = new Post();
						post.setBoardId(rs.getLong("bId"));
						post.setTitle(rs.getString("title"));
						post.setNickname(rs.getString("nickName"));
						post.setPasswd(rs.getString("passwd"));
						post.setContent(rs.getString("content"));
						post.setAdminContent(rs.getString("adminContent"));
						post.setRegDate(rs.getDate("regDate"));
						return post;	
					}
				}),
				pageable,
				countPost());
		
	}
	
	public long countPost() {
		String sql = "SELECT count(*) FROM Board";
		return jdbcTemplate.queryForObject(sql, Long.class);
	}
	
	/**
	 * 닉네임으로 자기글 검색하기
	 * @param nickName
	 * @return
	 */
	public Page<Post> getPostByNickName(String nickName, Pageable pageable) {
		Order order = pageable.getSort().isEmpty()
				? Order.by("bId")
				: pageable.getSort().toList().get(0);
		String sql = "SELECT bId, title, nickName, passwd,"
				+ "content, regDate FROM Board WHERE nickName = ?"
				+ "	ORDER BY " + order.getProperty() + " " + order.getDirection().name()
				+ "	LIMIT " + pageable.getPageSize()
				+ " OFFSET " + pageable.getOffset();
		
		try {
			return new PageImpl<Post>(
					jdbcTemplate.query(sql, new RowMapper<Post>() {
						
						@Override
						public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
							Post post = new Post();
							post.setBoardId(rs.getLong("bId"));
							post.setTitle(rs.getString("title"));
							post.setNickname(rs.getString("nickName"));
							post.setPasswd(rs.getString("passwd"));
							post.setContent(rs.getString("content"));
							post.setRegDate(rs.getDate("regDate"));
							return post;	
						}
					},nickName),
					pageable,
					countPostByNickName(nickName));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public long countPostByNickName(String nickName) {
		String sql = "SELECT count(*) FROM Board WHERE nickName = ?";
		return jdbcTemplate.queryForObject(sql, Long.class, nickName);
	}

}
