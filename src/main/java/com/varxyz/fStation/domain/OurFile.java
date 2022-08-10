package com.varxyz.fStation.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OurFile {
	
	private long fId;
	private String passwd;
	private String fileName;
	private long fileSize;
	private String fileType;
	private Date regDate;
	
}
