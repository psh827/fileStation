package com.varxyz.fStation.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.varxyz.fStation.dao.FileDao;
import com.varxyz.fStation.domain.OurFile;
import com.varxyz.fStation.domain.Text;

public class FileServiceImpl implements FileService{

	@Autowired
	FileDao fileDao;
	
	/**
	 * int형이 반환값인 메소드는 sql이 성공하면 1, 실패하면 0을 반환
	 */
	
	@Override
	@Transactional
	public int addFile(List<OurFile> ourFile) {
		return fileDao.addFile(ourFile);
	}
	
	@Override
	@Transactional
	public int addText(Text text) {
		return fileDao.addText(text);
	}
	
	@Override
	public List<Text> getTextByPasswd(String passwd) {
		return fileDao.getTextByPasswd(passwd);
	}

	@Override
	public List<Text> getAllText() {
		return fileDao.getAllText();
	}
	
	@Override
	public int deleteAllText(Text text) {
		return fileDao.deleteAllText(text);
	}
	
	@Override
	@Transactional
	public int deleteText(String deleteType, String passwd) {
		return fileDao.deleteText(deleteType, passwd);
	}
	
	@Override
	public List<OurFile> getFile(String passwd) {
		return fileDao.getFile(passwd);
	}
	
	@Override
	public List<OurFile> getAllFile() {
		return fileDao.getAllFile();
	}
	
	@Override
	public OurFile getFileByfileId(String fileId) {
		return fileDao.getFileByfileId(fileId);
	}
	
	@Override
	public int deleteAll(OurFile of) {
		return fileDao.deleteAll(of);
		
	}
	
	@Override
	@Transactional
	public int deleteFile(String deleteType, String passwd) {
		return fileDao.deleteFile(deleteType, passwd);
	}

	@Override
	public long getFileAmountByMonth(String month) {
		return fileDao.getFileAmountByMonth(month);
	}

	@Override
	public List<Integer> jung(List<String> monthList) {
		return fileDao.jung(monthList);
	}

	@Override
	public void hoon() {
		fileDao.hoon();
	}

	@Override
	public List<Integer> jee() {
		return fileDao.jee();
		
	}
//	1.array
//	동적으로 칼람이나 데이터를 처리하는 경우

}
