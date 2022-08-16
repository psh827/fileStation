package com.varxyz.fStation.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
	private long boardId;
	private String title;
	private String nickname;
	private String passwd;
	private String content;
	private Date regDate;

}
