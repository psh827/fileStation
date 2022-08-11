package com.varxyz.fStation.service;

import java.util.List;

import com.varxyz.fStation.domain.Post;

public interface BoardService {
	int writePost(Post post);
	List<Post> getAllPost();
	Post viewPostByPasswd(long bId, String passwd);
	int	modifyPost(Post post);
	int deletePost(String passwd);
	public Post viewPostByBid(long bId);
	
}
