package com.varxyz.fStation.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PostCommand {
	private String title;
	private String nickName;
	private String passwd;
	private String content;
}
