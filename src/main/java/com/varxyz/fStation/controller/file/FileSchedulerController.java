package com.varxyz.fStation.controller.file;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.varxyz.fStation.domain.OurFile;
import com.varxyz.fStation.domain.Text;
import com.varxyz.fStation.service.FileServiceImpl;

/**
 * 파일 자동 삭제 스케쥴러.
 * 00시, 06시, 12시, 18시를 기준으로 삭제 날짜가 지난 파일들을 삭제한다.
 * @author Administrator
 *
 */
@Controller
@EnableScheduling
public class FileSchedulerController {
	@Autowired
	FileServiceImpl fileService;
	
	@Scheduled(cron = "0 0 0 * * *")
    public void doScheduled() {
		//변수 설정 파일, 텍스트를 전부 들고온다.
        List<OurFile> fileList =  fileService.getAllFile();
        List<Text> textList = fileService.getAllText();
        Date date = new Date();
        //파일 처리
        if(fileList.size() != 0) {
        	for(int i = 0; i < fileList.size(); i++) {
        		OurFile thisFile = fileList.get(i); 
        		Calendar cl = Calendar.getInstance();
        		cl.setTime(thisFile.getDeleteDate());
        		Date checkDate = new Date(cl.getTimeInMillis());
        		if(date.after(checkDate)) { //지금 시간이 설정된 시간보다 지나있다면 파일을 삭제함.
        			fileService.deleteAll(thisFile);
        			String path = "C:\\fileStation\\";
    				String fileName = thisFile.getFileOriName();
    				File file = new File(path + fileName);
    				file.delete();
        		}
        	}
        }
        //텍스트 처리
        if(textList.size() != 0) {
        	for(Text text : textList) {
        		Calendar cl = Calendar.getInstance();
        		cl.setTime(text.getDeleteDate());
        		Date checkDate = new Date(cl.getTimeInMillis());
        		if(date.after(checkDate)) {
        			fileService.deleteAllText(text);
        		}
        	}
        }
        
        System.out.println("00시 스케줄러입니다.");
    }
	
	@Scheduled(cron = "0 0 6 * * *")
    public void doScheduled2() {
        List<OurFile> fileList =  fileService.getAllFile();
        List<Text> textList = fileService.getAllText();
        Date date = new Date();
        
        if(fileList.size() != 0) {
        	for(int i = 0; i < fileList.size(); i++) {
        		OurFile thisFile = fileList.get(i); 
        		Calendar cl = Calendar.getInstance();
        		cl.setTime(thisFile.getDeleteDate());
        		Date checkDate = new Date(cl.getTimeInMillis());
        		if(date.after(checkDate)) {
        			fileService.deleteAll(thisFile);
        			String path = "C:\\fileStation\\";
    				String fileName = thisFile.getFileOriName();
    				File file = new File(path + fileName);
    				file.delete();
        		}
        	}
        }
        
        if(textList.size() != 0) {
        	for(Text text : textList) {
        		Calendar cl = Calendar.getInstance();
        		cl.setTime(text.getDeleteDate());
        		Date checkDate = new Date(cl.getTimeInMillis());
        		if(date.after(checkDate)) {
        			fileService.deleteAllText(text);
        		}
        	}
        }
        
        System.out.println("06시 스케줄러입니다.");
    }
	
	@Scheduled(cron = "0 0 12 * * *")
    public void doScheduled3() {
        List<OurFile> fileList =  fileService.getAllFile();
        List<Text> textList = fileService.getAllText();
        Date date = new Date();
        
        if(fileList.size() != 0) {
        	for(int i = 0; i < fileList.size(); i++) {
        		OurFile thisFile = fileList.get(i); 
        		Calendar cl = Calendar.getInstance();
        		cl.setTime(thisFile.getDeleteDate());
        		Date checkDate = new Date(cl.getTimeInMillis());
        		if(date.after(checkDate)) {
        			fileService.deleteAll(thisFile);
        			String path = "C:\\fileStation\\";
    				String fileName = thisFile.getFileOriName();
    				File file = new File(path + fileName);
    				file.delete();
        		}
        	}
        }
        
        if(textList.size() != 0) {
        	for(Text text : textList) {
        		Calendar cl = Calendar.getInstance();
        		cl.setTime(text.getDeleteDate());
        		Date checkDate = new Date(cl.getTimeInMillis());
        		if(date.after(checkDate)) {
        			fileService.deleteAllText(text);
        		}
        	}
        }
        System.out.println("12시 스케줄러입니다.");
    }
	
	@Scheduled(cron = "0 0 18 * * *")
    public void doScheduled4() {
        List<OurFile> fileList =  fileService.getAllFile();
        List<Text> textList = fileService.getAllText();
        Date date = new Date();
        
        if(fileList.size() != 0) {
        	for(int i = 0; i < fileList.size(); i++) {
        		OurFile thisFile = fileList.get(i); 
        		Calendar cl = Calendar.getInstance();
        		cl.setTime(thisFile.getDeleteDate());
        		Date checkDate = new Date(cl.getTimeInMillis());
        		if(date.after(checkDate)) {
        			fileService.deleteAll(thisFile);
        			String path = "C:\\fileStation\\";
    				String fileName = thisFile.getFileOriName();
    				File file = new File(path + fileName);
    				file.delete();
        		}
        	}
        }
        
        if(textList.size() != 0) {
        	for(Text text : textList) {
        		Calendar cl = Calendar.getInstance();
        		cl.setTime(text.getDeleteDate());
        		Date checkDate = new Date(cl.getTimeInMillis());
        		if(date.after(checkDate)) {
        			fileService.deleteAllText(text);
        		}
        	}
        }
        System.out.println("18시 스케줄러입니다.");
    }
}
