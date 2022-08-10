package com.varxyz.fStation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.varxyz.fStation.dao.FileDao;
import com.varxyz.fStation.domain.OurFile;

public class FileServiceImpl implements FileService{
	
	@Autowired
	FileDao fileDao;
	
	/**
	 * int형이 반환값인 메소드는 sql이 성공하면 1, 실패하면 0을 반환
	 */
	
	@Override
	public int addFile(List<OurFile> ourFile) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OurFile> getFile(String passwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteFile(String passwd) {
		// TODO Auto-generated method stub
		return 0;
	}

}
