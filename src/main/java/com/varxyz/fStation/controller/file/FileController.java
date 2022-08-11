package com.varxyz.fStation.controller.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.varxyz.fStation.domain.OurFile;
import com.varxyz.fStation.service.FileServiceImpl;


/**
 * 파일 컨트롤러
 * 필요한 컨트롤러는 맞는 패키지내에 생성해서 쓰기.
 * ex) 파일을 다운로드 받는다면 controller.file패키지내에 DownloadFileController 생성 
 * 
 * D조 코드 규칙 지키면서 하기!
 * 1. 띄어쓰기 준수 ex) long number=1; (x) long number = 1; (o)
 * 2. 변수, 메소드 앞글자는 무조건 소문자!
 * 3. 단락 사이사이 한줄 띄우기.
 * 4. 메소드 생성시 메소드 위에 간단하 설명주석 달기. /** <-사용
 * 5. 메소드 내에서는 한 줄 주석으로 간단히 처리
 * 
 * @author Administrator
 *
 */
@Controller
public class FileController {
	
	@Autowired
	FileServiceImpl fileService;
	
	@GetMapping("/file/file_main")
	public String main() {
		return "file/file_main";
	}
	
//	@PostMapping("/file/file_main")
	@RequestMapping(value = "/file/addFile", method = { RequestMethod.POST })
	@ResponseBody
	public String addMenuItem(Model model, @RequestParam("files") MultipartFile[] files, @RequestParam("passwd") String passwd) throws IOException {
		
		List<OurFile> ourFileList = new ArrayList<OurFile>();
		
		//희정 주소
//		String filePath = "C:\\ncs\\eclipse\\workspace\\fileStation\\src\\main\\webapp\\resources\\files\\";
//		String filePath = "C:\\workspace\\java-cafe\\src\\main\\webapp\\resources\\menuImg\\";
		//상훈 주소
		String filePath = "C:\\Park\\work\\fileStation\\src\\main\\webapp\\resources\\files\\";
		
		for(MultipartFile mt : files) {	
			System.out.println(mt);
			OurFile ourFile = new OurFile();		
	        //파일명
	        String originalFile = mt.getOriginalFilename();
	        //파일명 중 확장자만 추출                                                //lastIndexOf(".") - 뒤에 있는 . 의 index번호
	        String originalFileExtension = originalFile.substring(originalFile.lastIndexOf("."));
	        //fileuploadtest.doc
	        //lastIndexOf(".") = 14(index는 0번부터)
	        //substring(14) = .doc
	        
	        //업무에서 사용하는 리눅스, UNIX는 한글지원이 안 되는 운영체제 
	        //파일업로드시 파일명은 ASCII코드로 저장되므로, 한글명으로 저장 필요
	        //UUID클래스 - (특수문자를 포함한)문자를 랜덤으로 생성                    "-"라면 생략으로 대체
	        String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
	        
	        
	       /*
	        * 이미지 형식
	        BMP - 마이크로소프트에서 윈도우 환경의 비트맵 데이터를 표현하기 위한 포맷
	        JPEG
	        GIF
	        PNG
	        TIFF -포토샵에서 이미지를 저장할 때 압축방식
	        PSD - 어도비사의 포토샵에서 전용으로 사용하는 파일포맷
	        TGA - 트루비전사에 개발한 타가보드에서 사용하기 위해 만들어진 포맷
	        AI - 어도비사에서 개발한 일러스트레이터 파일 포맷
	        SVG - xml기반으로 한 벡터 파일형식 */ 	
	        ourFile.setPasswd(passwd);
	        ourFile.setFileName(originalFile);
	        ourFile.setFileSize(mt.getSize());
	        String fileT = "";
	        if(originalFileExtension == ".jpg" || originalFileExtension == ".jpeg" ||
	        		originalFileExtension == ".gif" || originalFileExtension == ".svg" ||
	        		originalFileExtension == ".tiff" || originalFileExtension == ".ai" ||
	        		 originalFileExtension == ".bmp" || originalFileExtension == ".png") {
	        	fileT = "IMG";
	        } else if (originalFileExtension == ".mp3" || originalFileExtension == ".mp4") {
	        	fileT = "VIDEO";
	        } else {
	        	fileT = "DOCUMENT";
	        }
	        ourFile.setFileType(fileT);
	        
	        ourFileList.add(ourFile);
	        
	        //파일을 저장하기 위한 파일 객체 생성
	        File file = new File(filePath + storedFileName);
	        
	        //파일 저장	        
	        mt.transferTo(file);
	        
	        
//	        System.out.println(ourFile + "가 업로드한 파일은");
//	        System.out.println(originalFile + "은 업로드한 파일이다.");
//	        System.out.println(originalFileExtension + "라는 확장자를 가지고 있다.");
//	        System.out.println("파일사이즈는 " + mt.getSize());
		}
		
		int result = fileService.addFile(ourFileList);
        if(result == 0) {
        	model.addAttribute("msg", "업로드 오류");
        	model.addAttribute("url", "file_main");
        	return "alert";
        }
        
		return "file/file_main";
	}
	
	
	
}
