package com.varxyz.fStation.service;

import java.util.List;

import com.varxyz.fStation.domain.OurFile;

public interface FileService {
	int	addFile(List<OurFile> ourFile);
	int addText(String textarea);
	List<OurFile> getFile(String passwd);
	int deleteFile(String passwd);
}
