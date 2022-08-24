package com.varxyz.fStation.service;

import java.util.List;
import java.util.Map;

import com.varxyz.fStation.domain.OurFile;
import com.varxyz.fStation.domain.Text;

public interface FileService {
	int	addFile(List<OurFile> ourFile);
	int addText(Text text);
	List<Text> getAllText();
	List<Text> getTextByPasswd(String passwd);
	int deleteAllText(Text text);
	int deleteText(String string, String passwd);
	List<OurFile> getAllFile();
	List<OurFile> getFile(String passwd);
	int deleteAll(OurFile of);
	int deleteFile(String deleteType, String passwd);
	OurFile getFileByfileId(String fileId);
	long getFileAmountByMonth(String month);
	public List<Integer> jee();
	List<Integer> jung(List<String> monthList);
	void hoon();
}
