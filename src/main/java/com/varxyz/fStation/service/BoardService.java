package com.varxyz.fStation.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.varxyz.fStation.domain.Post;

public interface BoardService {
	int writePost(Post post);
	List<Post> getAllPost();
	Post viewPostByPasswd(long bId, String passwd);
	int	modifyPost(Post post);
	int deletePost(Post post);
	public Post viewPostByBid(long bId);
	
	Page<Post> findAll(Pageable pageable);
	long countPost();
}
