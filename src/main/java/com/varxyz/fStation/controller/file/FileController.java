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
import com.varxyz.fStation.domain.Text;
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
	public String addMenuItem(Model model, @RequestParam(value="files", required = false) 
				MultipartFile[] files, @RequestParam("passwd") String passwd,
				@RequestParam(value="textarea", required = false) String textarea) throws IOException {
		
		System.out.println(textarea);
		
		passwd = passwd.split(",")[0];
		List<OurFile> ourFileList = new ArrayList<OurFile>();
		Text text = new Text();
		text.setPasswd(passwd);
		//text처리
		if(textarea != null) {
			text.setContent(textarea);
			int textResult = fileService.addText(text);
			if(textResult == 0) {
				model.addAttribute("msg", "업로드오류");
				model.addAttribute("url", "file_main");
				return "alert";
			}		
		}
		
		String filePath = "C:\\fileStation\\";
		
		
		//file처리
		if(files != null) {
			for(MultipartFile mt : files) {	
				System.out.println(mt);
				OurFile ourFile = new OurFile();		
				//파일명
				String originalFile = mt.getOriginalFilename();
				//파일명 중 확장자만 추출                                                //lastIndexOf(".") - 뒤에 있는 . 의 index번호
				String originalFileExtension = originalFile.substring(originalFile.lastIndexOf("."));
				
				ourFile.setPasswd(passwd);
				ourFile.setFileName(originalFile);
				ourFile.setFileSize(mt.getSize());
				String fileT = "";
				if(originalFileExtension.equals(".jpg") || originalFileExtension.equals(".jpeg") ||
						originalFileExtension.equals(".gif") || originalFileExtension.equals(".svg") ||
						originalFileExtension.equals(".tiff") || originalFileExtension.equals(".ai") ||
						originalFileExtension.equals(".bmp") || originalFileExtension.equals(".png")) {
					fileT = "IMG";
				} else if (originalFileExtension.equals(".mp3") || originalFileExtension.equals(".mp4")) {
					fileT = "VIDEO";
				} else {
					fileT = "DOCUMENT";
				}
				ourFile.setFileType(fileT);
				ourFile.setUrl(filePath);
				
				ourFileList.add(ourFile);
				
				//파일을 저장하기 위한 파일 객체 생성
				File file = new File(filePath + originalFile);
				
				//파일 저장	        
				mt.transferTo(file);

			}
			int result = fileService.addFile(ourFileList);
			System.out.println("result = " + result);
	        if(result == 0) {
	        	model.addAttribute("msg", "업로드 오류");
	        	model.addAttribute("url", "file_main");
	        	return "alert";
	        }
		}
		
		
        
		return "file/file_main";
	}
	
	
	
}
