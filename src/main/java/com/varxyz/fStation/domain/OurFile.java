package com.varxyz.fStation.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OurFile {
	
	private long fileId;
	private String passwd;
	private String fileOriName;
	private String fileName;
	private long fileSize;
	private String fileType;
	private String url;
	private Date regDate;
	
}
