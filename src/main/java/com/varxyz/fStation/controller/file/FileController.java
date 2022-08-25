package com.varxyz.fStation.controller.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	/**
	 * 업로드 홈페이지.
	 * @return
	 */
	@GetMapping("/file/file_main")
	public String main() {
		return "file/file_main";
	}
	
	/**
	 * 파일 업로드. ajax통신
	 * @param model
	 * @param files
	 * @param passwd
	 * @param textarea
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/file/addFile", method = { RequestMethod.POST })	
	@ResponseBody
	public int addMenuItem(Model model, @RequestParam(value="files", required = false) 
				MultipartFile[] files, @RequestParam("passwd") String passwd,
				@RequestParam(value="textarea", required = false) String textarea) throws IOException {
		int result = 0;
		passwd = passwd.split(",")[0];
		List<OurFile> ourFileList = new ArrayList<OurFile>();
		Text text = new Text();
		text.setPasswd(passwd);
		text.setContent(textarea.replace("\r\n", "<br>").replace("	", "[tab]"));
		//text처리
		if(!text.getContent().equals("") && text.getContent() != "") {
			int textResult = fileService.addText(text);
		}
		
		String filePath = "C:\\fileStation\\";
		
		
		//file처리
		if(files != null) {
			for(MultipartFile mt : files) {	
				OurFile ourFile = new OurFile();		
				//파일명
				String originalFile = mt.getOriginalFilename();
				//파일명 중 확장자만 추출                                                //lastIndexOf(".") - 뒤에 있는 . 의 index번호
				String originalFileExtension = originalFile.substring(originalFile.lastIndexOf("."));
				
				String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
				
				ourFile.setPasswd(passwd);
				ourFile.setFileOriName(originalFile);
				ourFile.setFileName(storedFileName);
				ourFile.setFileSize(mt.getSize());
				String fileT = "";
				if(originalFileExtension.equals(".jpg") || originalFileExtension.equals(".jpeg") ||
						originalFileExtension.equals(".gif") || originalFileExtension.equals(".svg") ||
						originalFileExtension.equals(".tiff") || originalFileExtension.equals(".ai") ||
						originalFileExtension.equals(".bmp") || originalFileExtension.equals(".png")) {
					fileT = "IMG";
				} else if (originalFileExtension.equals(".mp3") || originalFileExtension.equals(".mp4")) {
					fileT = "VIDEO";
				} else if(originalFileExtension.equals(".zip") || originalFileExtension.equals(".7z") || originalFileExtension.equals(".zar")) {
					fileT = "ETC";
				}
				else {
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
			result = fileService.addFile(ourFileList);
		}
		return result;
	}
	
	/**
	 * 파일 다운로드 홈
	 * @return
	 */
	@GetMapping("/file/download")
	public String downloadForm() {
		return "file/download";
	}
	
	/**
	 * 파일 다운로드 비밀번호 검사
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@PostMapping("/file/download")
	public String download(HttpServletRequest request) throws ParseException {
		String passwd = request.getParameter("passwd");
		List<OurFile> fileList = fileService.getFile(passwd);
		List<Text> downloadTextList = fileService.getTextByPasswd(passwd);
		
		if (fileList.size() == 0 && (downloadTextList == null || downloadTextList.size() == 0)) {
			request.setAttribute("msg", "비밀번호가 틀렸습니다.");
			request.setAttribute("url", "download");
			return "alert";
		}
		
		
		if(downloadTextList.size() >= 1) {
			for(Text t : downloadTextList) {
				t.setContent(t.getContent().replace("<br>", "[br]").replace("<", "&lt;").replace(">", "&gt;"));
			}
			request.setAttribute("textList", downloadTextList);
		}
		request.setAttribute("passwd", passwd);
		request.setAttribute("fileList", fileList);
		
		return "file/download_station";
	}
	
	/**
	 * 파일 다운로드.
	 * @param fileId
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value= "/file/download_detail", method = { RequestMethod.GET })
	@ResponseBody
	public void fileDown(@RequestParam("fileId") String fileId,
						HttpServletRequest request, HttpServletResponse response) throws Exception {
		OurFile of = fileService.getFileByfileId(fileId);
		String path = of.getUrl();
		String fileName = of.getFileOriName();
		response.setContentType("application/octet-stream");
		String orgname = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\""+orgname+"\""); 
		OutputStream os = response.getOutputStream();
			FileInputStream fis = new FileInputStream(path + File.separator + fileName);
			int n = 0;
			byte[] b = new byte[512];
			while((n = fis.read(b)) != -1 ) {
				os.write(b, 0, n);
			}
			fis.close();
			os.close();
	}

	@RequestMapping(value = "/file/downloadAll")
	public void downloadAll(@RequestParam("passwd") String passwd, HttpServletResponse response) {

	    response.setStatus(HttpServletResponse.SC_OK);
	    response.setContentType("application/zip");
	    response.addHeader("Content-Disposition", "attachment; filename=\"allToOne.zip\"");

	    FileOutputStream fos = null;
	    ZipOutputStream zipOut = null;
	    FileInputStream fis = null;

	    try {
	        zipOut = new ZipOutputStream(response.getOutputStream());

		// DB에 저장되어 있는 파일 목록을 읽어온다.
	        List<OurFile> ourFileList = fileService.getFile(passwd);

		// 실제 Server에 파일들이 저장된 directory 경로를 구해온다.
	        String filePath = ourFileList.get(0).getUrl();

		// File 객체를 생성하여 List에 담는다.
	        List<File> fileList = ourFileList.stream().map(fileInfo -> {
	            return new File(filePath + "/" + fileInfo.getFileOriName());
	        }).collect(Collectors.toList());

		// 루프를 돌며 ZipOutputStream에 파일들을 계속 주입해준다.
	        for(File file : fileList) {
	            zipOut.putNextEntry(new ZipEntry(file.getName()));
	            fis = new FileInputStream(file);

	            StreamUtils.copy(fis, zipOut);

	            fis.close();
	            zipOut.closeEntry();
	        }

	        zipOut.close();

	    } catch (IOException e) {
	        System.out.println(e.getMessage());
	        try { if(fis != null)fis.close(); } catch (IOException e1) {System.out.println(e1.getMessage());/*ignore*/}
	        try { if(zipOut != null)zipOut.closeEntry();} catch (IOException e2) {System.out.println(e2.getMessage());/*ignore*/}
	        try { if(zipOut != null)zipOut.close();} catch (IOException e3) {System.out.println(e3.getMessage());/*ignore*/}
	        try { if(fos != null)fos.close(); } catch (IOException e4) {System.out.println(e4.getMessage());/*ignore*/}
	    }
	}
	/**
	 * 파일 삭제
	 */
	@PostMapping("/file/deleteAll")
	public String deleteAll(HttpServletRequest request) {
		String passwd = request.getParameter("passwd");
		List<OurFile> fileList = fileService.getFile(passwd);
		
		for(OurFile of : fileList) {
			String path = "C:\\fileStation\\";
			String fileName = of.getFileOriName();
			File file = new File(path + fileName);
			file.delete();
		}						
		int result = fileService.deleteFile("YES", passwd);
		int result2 = fileService.deleteText("YES", passwd);
		if(result == 0 || result2 == 0) {
			request.setAttribute("msg", "삭제오류");
			request.setAttribute("url", "download");
			return "alert";
		}
		
		return "file/download";
	}
}
	
