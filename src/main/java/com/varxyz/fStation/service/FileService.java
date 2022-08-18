package com.varxyz.fStation.service;

import java.util.List;

import com.varxyz.fStation.domain.OurFile;
import com.varxyz.fStation.domain.Text;

public interface FileService {
	int	addFile(List<OurFile> ourFile);
	int addText(Text text);
	String getTextByPasswd(String passwd);
	int deleteText(String string, String passwd);
	List<OurFile> getFile(String passwd);
	int deleteFile(String deleteType, String passwd);
	OurFile getFileByfileId(String fileId);
	long getFileAmountByMonth();
	public List<Integer> jee();
	void jung();
	void hoon();
}
