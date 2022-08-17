package com.varxyz.fStation.service;

import java.util.List;

import com.varxyz.fStation.domain.OurFile;
import com.varxyz.fStation.domain.Text;

public interface FileService {
	int	addFile(List<OurFile> ourFile);
	int addText(Text text);
	List<OurFile> getFile(String passwd);
	int deleteFile(String deleteType, String passwd);
	OurFile getFileByfileId(String fileId);
	void jee();
	long getFileAmountByMonth();
	void jung();
	void hoon();
}
