package com.varxyz.fStation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
	public int addFile(List<OurFile> ourFile) {
		return fileDao.addFile(ourFile);
	}
	
	@Override
	public int addText(Text text) {
		return fileDao.addText(text);
	}

	@Override
	public List<OurFile> getFile(String passwd) {
		return fileDao.getFile(passwd);
	}
	
	@Override
	public OurFile getFileByfileId(String fileId) {
		return fileDao.getFileByfileId(fileId);
	}
	
	@Override
	public int deleteFile(String deleteType, String passwd) {
		return fileDao.deleteFile(deleteType, passwd);
	}

	@Override
	public void jee() {
		fileDao.jee();
	}

	@Override
	public void nam() {
		fileDao.nam();
	}

	@Override
	public void jung() {
		fileDao.jung();
	}

	@Override
	public void hoon() {
		fileDao.hoon();
	}


}
