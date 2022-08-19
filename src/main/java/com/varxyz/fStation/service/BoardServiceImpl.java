package com.varxyz.fStation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.varxyz.fStation.dao.BoardDao;
import com.varxyz.fStation.domain.Post;

public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDao boardDao;
	
	/**
	 * int형이 반환값인 메소드는 sql이 성공하면 1, 실패하면 0을 반환
	 */
	
	@Override
	public int writePost(Post post) {
		return boardDao.writePost(post);
	}

	@Override
	public List<Post> getAllPost() {
		return boardDao.getAllPost();
	}

	@Override
	public Post viewPostByPasswd(long bId, String passwd) {
		return boardDao.viewPostByPasswd(bId, passwd);
	}

	@Override
	public int modifyPost(Post post) {
		return boardDao.modifyPost(post);
	}

	@Override
	public int deletePost(Post post) {
		return boardDao.deletePost(post);
	}

	@Override
	public Post viewPostByBid(long bId) {
		return boardDao.viewPostByBid(bId);
	}

	@Override
	public Page<Post> findAll(Pageable pageable) {
		return boardDao.findAll(pageable);
	}

	@Override
	public long countPost() {
		return boardDao.countPost();
	}

	@Override
	public Page<Post> getPostByNickName(String nickName, Pageable pageable) {
		return boardDao.getPostByNickName(nickName, pageable);
	}

	@Override
	public long countPostByNickName(String nickName) {
		return boardDao.countPostByNickName(nickName);
	}
	
}
