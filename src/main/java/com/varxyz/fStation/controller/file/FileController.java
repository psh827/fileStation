package com.varxyz.fStation.controller.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping("/file/file_main")
	public String addMenuItem(Model model, HttpServletRequest request, @RequestParam("report") MultipartFile[] report) throws IOException {
		OurFile ourFile = new OurFile();		
		
		String filePath = "C:\\ncs\\eclipse\\workspace\\fileStation\\src\\main\\webapp\\resources\\files\\";
//		String filePath = "C:\\workspace\\java-cafe\\src\\main\\webapp\\resources\\menuImg\\";
		
		for(MultipartFile mt : report) {	
	        //파일명
	        String originalFile = mt.getOriginalFilename();
	        System.out.println(originalFile);
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
	        ourFile.setFileName(originalFile);
	        ourFile.setFileSize(mt.getSize());
	        ourFile.setFileType(originalFileExtension);
	//        request.setAttribute("menuitem", menuItem);
	//        request.setAttribute("imgName", storedFileName);
	        
	        //파일을 저장하기 위한 파일 객체 생성
	        File file = new File(filePath + storedFileName);
	        //파일 저장
	        mt.transferTo(file);
	        v
	        System.out.println(ourFile + "가 업로드한 파일은");
	        System.out.println(originalFile + "은 업로드한 파일이다.");
	        System.out.println(storedFileName + "라는 이름으로 업로드 됐다.");
	        System.out.println("파일사이즈는 " + mt.getSize());
		}
		return "file/file_main";
	}
	
	
}
