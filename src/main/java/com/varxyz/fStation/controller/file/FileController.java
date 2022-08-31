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
import java.text.DateFormat;
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
	 * 파일만 올릴 수도 있고, 텍스트만 올릴수도 있고, 둘다 올릴 수도 있음.
	 * @param model
	 * 
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
				@RequestParam(value="textarea", required = false) String textarea )
						throws IOException { //@RequestParam에 required=false 란 무조건 값이 있어야 하는 건 아님.
		//변수 선언
		int result = 0;
		//패스워드 값이 두번 들어가서 ,로 나눴다.
		passwd = passwd.split(",")[0];
		//우리가 필요한 값을 전달해 줄 list
		List<OurFile> ourFileList = new ArrayList<OurFile>();
		//텍스트가 DB에 있다면 전달 해줄 객체
		Text text = new Text();
		text.setPasswd(passwd);
		//content를 세팅 할 때 html에서 알 수 있게 태그로 변환 시켜준다.
		text.setContent(textarea.replace("\r\n", "<br>").replace("	", "[tab]"));
		//text처리
		//text가 입력되어서 값이 들어왔다면. 만약 이용자가 file만 올리게 되면 118번째 줄은 실행 되지않는다. (파일 + 텍스트) or (텍스트) 이렇게 들어 왔을 때만 실행됨.
		if(!text.getContent().equals("") && text.getContent() != "") {
			int textResult = fileService.addText(text);
		}
		
		//파일을 저장할 주소 설정
		String filePath = "C:\\fileStation\\";
		
		
		//file처리
		//text와 마찬가지로 file이 있다면. file이 없다면 이밑에 코드들은 실행되지 않는다.
		if(files != null) {
			//받은 file들을 for문으로 하나씩 처리.
			for(MultipartFile mt : files) {
				//하나 씩 받을 객체를 생성
				OurFile ourFile = new OurFile();		
				//파일명
				String originalFile = mt.getOriginalFilename();
				//파일명 중 확장자만 추출     //lastIndexOf(".") - 뒤에 있는 . 의 index번호
				String originalFileExtension = originalFile.substring(originalFile.lastIndexOf("."));
				
				//파일명을 UUID값으로 변경시킬 예정. 우리는 사용하지 않았지만 추가 업데이트 시 할 예정.
				String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
				
				//객체에 받은 입력값, 파일에서 뽑아낸 이름, 확장자들을 set해준다.
				ourFile.setPasswd(passwd);
				ourFile.setFileOriName(originalFile);
				ourFile.setFileName(storedFileName);
				ourFile.setFileSize(mt.getSize());
				
				//fileType에 대한 변수. 관리자 쪽에서 그래프 데이터를 뽑아낼 수 있도록 변환 시켜야 함. 
				String fileT = "";
				
				//이 밑에 조건문은 아직 완전한 조건문이 아님. 수많은 확장자들이 있어서 더 효과적인 조건문을 뽑아내는 로직을 생각해야 함.
				//사진에 대한 조건문
				if(originalFileExtension.equals(".jpg") || originalFileExtension.equals(".jpeg") ||
						originalFileExtension.equals(".gif") || originalFileExtension.equals(".svg") ||
						originalFileExtension.equals(".tiff") || originalFileExtension.equals(".ai") ||
						originalFileExtension.equals(".bmp") || originalFileExtension.equals(".png")) {
					fileT = "IMG";
				} else if (originalFileExtension.equals(".mp3") || originalFileExtension.equals(".mp4")) { //동영상에 대한 조건문
					fileT = "VIDEO";
				} else if(originalFileExtension.equals(".zip") || originalFileExtension.equals(".7z") || originalFileExtension.equals(".zar")) { //알집 등에 대한 조건문
					fileT = "ETC";
				}
				else { //나머지는 문서로 취급
					fileT = "DOCUMENT";
				}
				
				//뽑아낸 fileType을 설정해주고
				ourFile.setFileType(fileT);
				//저장할 주소도 지정.
				ourFile.setUrl(filePath);
				//fileList에 저장해서 다음 페이지에 넘겨줄 준비.
				ourFileList.add(ourFile);
				
				//파일을 저장하기 위한 파일 객체 생성
				File file = new File(filePath + originalFile);
				
				//파일 저장	        
				mt.transferTo(file);

			}
			//service 호출
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
	 * file을 삭제시간 안에 새로 올렸을시 남은시간은 제일 빨리 지워지는 시간으로 세팅.
	 * file옆에 언제 삭제되는지에 대한 값들을 설정.
	 * text가 한 비밀번호 안에 여러개 있을 경우 테이블로 표시
	 * text content가 html이 알수있도록 태그로 변환
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@PostMapping("/file/download")
	public String download(HttpServletRequest request) throws ParseException {
		//변수 설정
		String passwd = request.getParameter("passwd");
		//화면에 표시될 fileList를 passwd를 통해서 DB에서 가져오기
		List<OurFile> fileList = fileService.getFile(passwd);
		//화면에 표시될 textList를 passwd를 통해서 DB에서 가져오기
		List<Text> downloadTextList = fileService.getTextByPasswd(passwd);
		
		//fileList, textList 둘다 아무것도 없다면 파일이 삭제 되어 초기화, 혹은 비밀번호 오류이지만 둘다 비밀번호 오류로 처리.(보안)
		if (fileList.size() == 0 && (downloadTextList == null || downloadTextList.size() == 0)) {
			request.setAttribute("msg", "비밀번호가 틀렸습니다.");
			request.setAttribute("url", "download");
			return "alert";
		}
		
		//파일이 1개라도 있다면
		if(fileList.size() >= 1) {
			//for문으로 하나씩 처리
			for(OurFile f : fileList) {
				//시간 처리.
				Calendar cl = Calendar.getInstance();
				cl.setTime(f.getDeleteDate());
				int hour = cl.get(Calendar.HOUR_OF_DAY);
				int minute = cl.get(Calendar.MINUTE);
				if((hour >= 0 && minute >= 0) && (hour <= 5 && minute <= 59)) { //00:00 ~ 05:59 = 06시 삭제
					cl.set(Calendar.HOUR_OF_DAY, 6);
					cl.set(Calendar.MINUTE, 0);
					cl.set(Calendar.SECOND, 0);
					f.setDeleteDate(cl.getTime());
				}else if((hour >= 6 && minute >= 0) && (hour <= 11 && minute <= 59)) { //06:00 ~ 1:59 = 12시 삭제
					cl.set(Calendar.HOUR_OF_DAY, 12);
					cl.set(Calendar.MINUTE, 0);
					cl.set(Calendar.SECOND, 0);
					f.setDeleteDate(cl.getTime());
				}else if((hour >= 12 && minute >= 0) && (hour <= 17 && minute <= 59)) { //12:00 ~ 17:59 = 18시 삭제
					cl.set(Calendar.HOUR_OF_DAY, 18);
					cl.set(Calendar.MINUTE, 0);
					cl.set(Calendar.SECOND, 0);
					f.setDeleteDate(cl.getTime());
				}else if((hour >= 18 && minute >= 0) && (hour <= 23 && minute <= 59)) { //18:00 ~ 23:59 = 다음날 00시 삭제
					cl.add(Calendar.DATE, 1);
					cl.set(Calendar.HOUR_OF_DAY, 0);
					cl.set(Calendar.MINUTE, 0);
					cl.set(Calendar.SECOND, 0);
					f.setDeleteDate(cl.getTime());
				}
				//화면에 표시되게 날자를 숫자로 처리
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				f.setDeleteDateStr(format.format(f.getDeleteDate()));
			}
		}
		
		//텍스트가 있다면
		if(downloadTextList.size() >= 1) {
			for(Text t : downloadTextList) {
				//html에서 알수 있게 태그로 변환.
				t.setContent(t.getContent().replace("<br>", "[br]").replace("<", "&lt;").replace(">", "&gt;"));
			}
			request.setAttribute("textList", downloadTextList);
		}
		//값 넘기기
		request.setAttribute("passwd", passwd);
		request.setAttribute("fileList", fileList);
		
		return "file/download_station";
	}
	
	/**
	 * 파일 개별 다운로드. ajax 통신
	 * @param fileId
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value= "/file/download_detail", method = { RequestMethod.GET })
	@ResponseBody
	public void fileDown(@RequestParam("fileId") String fileId,
						HttpServletRequest request, HttpServletResponse response) throws Exception {
		//다운로드 할 파일을 전달 받은 값으로 가져오기.
		OurFile of = fileService.getFileByfileId(fileId);
		//파일의 url 추출
		String path = of.getUrl();
		//파일의 이름 추출
		String fileName = of.getFileOriName();
		response.setContentType("application/octet-stream");
		String orgname = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		//Content-Disposition, "attachment;  이것을 설정해 주면 반드시 다운로드창을 실행. 
		response.setHeader("Content-Disposition", "attachment; filename=\""+orgname+"\"");
		//OutputStream객체 생성 
		OutputStream os = response.getOutputStream();
		//FileInputStream으로 읽을 파일 지정.
		FileInputStream fis = new FileInputStream(path + File.separator + fileName);
		int n = 0;
		byte[] b = new byte[512];
		//계속 읽으면서 os에 읽기
		while((n = fis.read(b)) != -1 ) {
			os.write(b, 0, n);
		}
		fis.close();
		os.close();
	}

	/**
	 * 전체 파일 다운로드 ajax
	 * @param passwd
	 * @param response
	 */
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
	 * 파일 전체 삭제
	 * @param request
	 * @return
	 */
	@PostMapping("/file/deleteAll")
	public String deleteAll(HttpServletRequest request) {
		//변수 선언
		String passwd = request.getParameter("passwd");
		List<OurFile> fileList = fileService.getFile(passwd);
		
		//passwd로 찾은 파일들을 for문으로 돌면서 처리
		for(OurFile of : fileList) {
			String path = "C:\\fileStation\\";
			String fileName = of.getFileOriName();
			File file = new File(path + fileName);
			//파일 삭제.
			file.delete();
		}
		//sevice호출로 DB에서 정보 처리
		int result = fileService.deleteFile("YES", passwd);
		int result2 = fileService.deleteText("YES", passwd);
		//둘중 하나가 0이라면 오류 표시 후 다시 돌아가기
		if(result == 0 || result2 == 0) {
			request.setAttribute("msg", "삭제오류");
			request.setAttribute("url", "download");
			return "alert";
		}
		
		return "file/download";
	}
}
	
