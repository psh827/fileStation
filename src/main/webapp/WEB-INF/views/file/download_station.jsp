<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, minimum-scale=1"
    />
    <title>file Station</title>
    <!-- <link rel="stylesheet" href="./css/initial.css"> -->
    <link rel="stylesheet" href="<c:url value='/resources/css/initial.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/default.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
    <%-- <link rel="stylesheet" href="<c:url value='/resources/css/upload.css'/>"> --%>
    <link rel="stylesheet" href="<c:url value='/resources/css/download.css'/>">
    <script
      src="https://kit.fontawesome.com/62a067f302.js"
      crossorigin="anonymous"
    ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
  </head>
  <body>
    <div class="main_container">
      <!--왼쪽 navigation-->

          <jsp:include page="../incl/nav.jsp"/>

      <!-- 하얀색 영역 -->
      <div class="main-inner__container">
      <c:choose>
      	<c:when test="${fn:length(fileList) <= 20 }">
      	<div class="table_flex" style="display:flex;">
      		<table>
      		<thead>
      			<tr>
	      		<th>No</th>
	      		<th>파일이름</th>
	      		<th>파일용량</th>
	      		<th> </th>
	      		</tr>
      		</thead>
	        <c:forEach var="i" begin="1" end="${fn:length(fileList) }">
	        	<tr>
	        		<td class="no${i}">${i}</td>
	        		<td class="file_name_${i}">${fileList[i - 1].fileOriName}<span hidden> ${fileList[i - 1].fileId}</span></td>
	        		<td class="file_size_${i}">${fileList[i - 1].fileSize}</td>
	        		<td><a href="<c:url value='/file/download_detail?fileId=${fileList[i - 1].fileId}'/>">다운로드</a></td>
	        	</tr>
	        </c:forEach>
        	</table>
        	</div>
      	</c:when>
      </c:choose>
      <div class="bottom_container">
     	<div class="text_box" id="text">${downloadText.content}</div>
     	<button type="button" id="copyBtn">복사하기</button>
     	<div class="time_container">
	   	    <div class="time_box"><span class="time_span"></span></div>
		   	   <div class="dd_btn">
		         <a class="modal_btn delete" rel="modal:open" href="#modal1" type="button">삭제하기</a>
			      <form class="download_form">
			      	<input hidden name="passwd" value="${passwd}"/>
			      	<button type="submit" formaction="downloadAll" formmethod="post" class="total_download_btn">전체 다운로드</button>
			      </form>
		   	   </div> 
	     	</div>
      </div>
      </div>
    </div>
    <!-- modal body -->
<div id="modal1" class="modal" >
   <form action="deleteAll" method="post" class="modal_delete">
      <p class="delete_title">삭제 하시겠습니까?</p>
      <div class="yes_or_no">
      <label for="Yes" >확인</label> 
      <input type="checkbox" name="delete" value="1" id="Yes" checked />&nbsp;&nbsp;&nbsp;&nbsp;
      <label for="No">취소</label>
      <input type="checkbox" name="delete" value="0" id="No"/>
      </div>
      <input hidden name="passwd" value="${passwd}"/>
      <input class="submit_btn" type="submit" value="확인"/>

   </form>
</div>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="<c:url value='/resources/js/download.js'/>"></script>
    <script>
       $(document).on('click', '.modify', function(){
          var toTextarea = $(".Questions_inner")
          $(toTextarea).replaceWith('<textarea class="Questions_inner textarea">${post.content}</textarea>')
          $('.modify').replaceWith('<button class="Questions_btn comfirm middle" type="button" name="revoke">등록하기</button>')
       })
       
       const remainTime = document.querySelector(".time_span");
	   var regDate = new Date('${fileList[0].deleteDate}')
	   var textDate = new Date('${downloadText.deleteDate}')
	   function diffDay() {
		let masTime;
		if(regDate == 'Invalid Date'){
			masTime = new Date(textDate);			
		}else if(textDate == 'Invalid Date'){
			masTime = new Date(regDate);
		}
		
		console.log(masTime)
	   	const todayTime = new Date();
	   	const diff = masTime - todayTime;
	       
	   	const diffHour =String( Math.floor((diff / (1000*60*60)) % 24)).padStart(2,"0");
		const diffMin = String(Math.floor((diff / (1000*60)) % 60)).padStart(2,"0");
		const diffSec = String(Math.floor(diff / 1000 % 60)).padStart(2,"0");
		remainTime.innerText = diffHour + '시간 ' + diffMin + '분 ' + diffSec + '초';
	   }
	   diffDay();
	   setInterval(diffDay, 1000);
	   if (remainTime.innerText == "0시간 0분 0초"){
		   location.href = "file/download";
	   }
       
   </script>
  </body>
</html>