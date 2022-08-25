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
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/modal.css'/>" />
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
	        		<td class="file_name_${i} file_name"><span class="file_ori_name">${fileList[i - 1].fileOriName}</span><span hidden> ${fileList[i - 1].fileId}</span></td>
	        		<td class="file_size_${i} file_size">${fileList[i - 1].fileSize}</td>
	        		<td><a href="<c:url value='/file/download_detail?fileId=${fileList[i - 1].fileId}'/>">다운로드</a></td>
	        	</tr>
	        </c:forEach>
        	</table>
        	</div>
      <div class="bottom_container">
     		<c:choose>
     		<c:when test="${fn:length(textList) == 1}"> 		
		     	<div class="text_box text_repl" id="text">
		     		<span>${textList[0].content}</span>
		     	</div>
		     	<button type="button" id="copyBtn">복사하기</button>
     		</c:when>
     		<c:otherwise>
     		<div class="text_box2" id="text">
     			<table class="text_box2_table">
     			<thead>
     				<tr>
     					<th style="width: 10%;">번호</th>
     					<th>내용</th>
     					<th style="width: 10%;"></th>
     				</tr>
     			</thead>
     			<c:choose>
     				<c:when test="${fn:length(textList) < 5}">
	     				<c:forEach var="i" begin="0" end="${fn:length(textList) - 1}">
	     					<tr>
	     						<td>${i + 1}</td>
	     						<td><a class="table_txt text_repl" rel="modal:open" href="#table_modal${i}" type="button"><span>${textList[i].content}</span></a></td>
	     						<td><button type="button" class="text2_btn copyBtn" id="copyBtn">복사하기</button></td>
	     					</tr>
	     				</c:forEach>
     				</c:when>
     				<c:otherwise>
     					<c:forEach var="i" begin="0" end="4">
	     					<tr>
	     						<td>${i + 1}</td>
	     						<td><a class="table_txt text_repl" rel="modal:open" href="#table_modal${i}" type="button"><span>${textList[i].content}</span></a></td>
	     						<td><button type="button" class="text2_btn copyBtn" id="copyBtn">복사하기</button></td>
	     					</tr>
	     				</c:forEach>
     				</c:otherwise>
     			</c:choose>
     			</table>
     		</div>
     		</c:otherwise>
     	</c:choose>
     	
     	<div class="time_container">
	   	    <p class="time_box"><span class="time_span"></span></p>
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
   <form>
      <p class="delete_title">삭제 하시겠습니까?</p>
      <div class="yes_or_no">
      <input hidden name="passwd" value="${passwd}"/>
      <button class="delete-modal" formaction="deleteAll" formmethod="post" type="submit">삭제</button>
      <a href="#close-modal" rel="modal:close" class="delete-modal close-modal close_custom">취소</a>
      </div>
   </form>
</div>
<c:choose>
	<c:when test="${fn:length(textList) < 5}">
		<c:forEach var="i" begin="0" end="${fn:length(textList) - 1}">
			<div id="table_modal${i}" class="modal text_inner_box">
				<button type="button" class="modal_inner_btn modalCopyBtn" id="copyBtn">복사하기</button>
		    	<div class="text_repl">
		    		<span>${textList[i].content}</span>
		    	</div>
		    </div>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<c:forEach var="i" begin="0" end="4">
			<div id="table_modal${i}" class="modal text_inner_box">
				<button type="button" class="modal_inner_btn modalCopyBtn" id="copyBtn">복사하기</button>
				<div class="text_repl">
		    		<span>${textList[i].content}</span>
		    	</div>
		    </div>
		</c:forEach>
	</c:otherwise>
</c:choose>
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
	   var textDate = new Date('${textList[0].deleteDate}')
	   function diffDay() {
		let masTime;
		if(regDate == 'Invalid Date'){
			masTime = new Date(textDate);
			if ((masTime.getHours() >= 0 && masTime.getMinutes() >= 1) && (masTime.getHours() <= 5 && masTime.getMinutes() <= 59)){
				masTime.setHours(6)
				masTime.setMinutes(0)
				masTime.setSeconds(0)
			}else if((masTime.getHours() >= 6 && masTime.getMinutes() >= 1) && (masTime.getHours() <= 11 && masTime.getMinutes() <= 59)){
				masTime.setHours(12)
				masTime.setMinutes(0)
				masTime.setSeconds(0)
			}else if((masTime.getHours() >= 12 && masTime.getMinutes() >= 1) && (masTime.getHours() <= 17 && masTime.getMinutes() <= 59)){
				masTime.setHours(18)
				masTime.setMinutes(0)
				masTime.setSeconds(0)
			}else if((masTime.getHours() >= 18 && masTime.getMinutes() >= 1) && (masTime.getHours() <= 23 && masTime.getMinutes() <= 59)){
				masTime.setHours(0)
				masTime.setMinutes(0)
				masTime.setSeconds(0)
			}
		}else if(textDate == 'Invalid Date'){
			masTime = new Date(regDate);
			if ((masTime.getHours() >= 0 && masTime.getMinutes() >= 1) && (masTime.getHours() <= 5 && masTime.getMinutes() <= 59)){
				masTime.setHours(6)
				masTime.setMinutes(0)
				masTime.setSeconds(0)
			}else if((masTime.getHours() >= 6 && masTime.getMinutes() >= 1) && (masTime.getHours() <= 11 && masTime.getMinutes() <= 59)){
				masTime.setHours(12)
				masTime.setMinutes(0)
				masTime.setSeconds(0)
			}else if((masTime.getHours() >= 12 && masTime.getMinutes() >= 1) && (masTime.getHours() <= 17 && masTime.getMinutes() <= 59)){
				masTime.setHours(18)
				masTime.setMinutes(0)
				masTime.setSeconds(0)
			}else if((masTime.getHours() >= 18 && masTime.getMinutes() >= 1) && (masTime.getHours() <= 23 && masTime.getMinutes() <= 59)){
				masTime.setHours(0)
				masTime.setMinutes(0)
				masTime.setSeconds(0)
			}
		}else {
			masTime = new Date(regDate);
			if ((masTime.getHours() >= 0 && masTime.getMinutes() >= 1) && (masTime.getHours() <= 5 && masTime.getMinutes() <= 59)){
				masTime.setHours(6)
				masTime.setMinutes(0)
				masTime.setSeconds(0)
			}else if((masTime.getHours() >= 6 && masTime.getMinutes() >= 1) && (masTime.getHours() <= 11 && masTime.getMinutes() <= 59)){
				masTime.setHours(12)
				masTime.setMinutes(0)
				masTime.setSeconds(0)
			}else if((masTime.getHours() >= 12 && masTime.getMinutes() >= 1) && (masTime.getHours() <= 17 && masTime.getMinutes() <= 59)){
				masTime.setHours(18)
				masTime.setMinutes(0)
				masTime.setSeconds(0)
			}else if((masTime.getHours() >= 18 && masTime.getMinutes() >= 1) && (masTime.getHours() <= 23 && masTime.getMinutes() <= 59)){
				masTime.setHours(0)
				masTime.setMinutes(0)
				masTime.setSeconds(0)
			}
		}
	   	const todayTime = new Date();
	   	const diff = masTime - todayTime;
	   	let diffHour = ""
	    if(diff > 86400000){
	    	diffHour = String(24 + Math.floor((diff / (1000*60*60)) % 24)).padStart(2, "0");
	    }else{
		   	diffHour = String( Math.floor((diff / (1000*60*60)) % 24)).padStart(2,"0");
	    }
		const diffMin = String(Math.floor((diff / (1000*60)) % 60)).padStart(2,"0");
		const diffSec = String(Math.floor(diff / 1000 % 60)).padStart(2,"0");
		remainTime.innerText = diffHour + '시간 ' + diffMin + '분 ' + diffSec + '초';
	   }
	   diffDay();
	   setInterval(diffDay, 1000);
   </script>
   <script src="<c:url value='/resources/js/common.js'/>"></script>
  </body>
</html>