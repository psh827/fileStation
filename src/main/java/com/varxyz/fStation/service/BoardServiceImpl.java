package com.varxyz.fStation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
	public Post viewPostByPasswd(String passwd) {
		return null;
	}

	@Override
	public int modifyPost(Post post) {
		return 0;
	}

	@Override
	public int deletePost(String passwd) {
		return 0;
	}

}
