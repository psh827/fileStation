package com.varxyz.fStation.service;

import java.util.List;

import com.varxyz.fStation.domain.OurFile;
import com.varxyz.fStation.domain.Text;

public interface FileService {
	int	addFile(List<OurFile> ourFile);
	int addText(Text text);
	List<OurFile> getFile(String passwd);
	int deleteFile(String passwd);
	OurFile getFileByfileId(String fileId);
	void jee();
	void nam();
	void jung();
	void hoon();
}
