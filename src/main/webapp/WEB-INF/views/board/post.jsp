<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/initial.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/default.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/style.css'/>" />
     <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/board.css'/>" />
     <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/modal.css'/>" />
     
    <script
      src="https://kit.fontawesome.com/62a067f302.js"
      crossorigin="anonymous"
    ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
   <script src="<c:url value='/resources/js/modify.js'/>"></script>
  </head>
  <body>
    <div class="main_container">
      <!--왼쪽 navigation-->

        <jsp:include page="../incl/nav.jsp"/>

      <!-- 하얀색 영역 -->
      <form>
      <div class="main-inner__container">
        <div class="Questions_container">
          <div class="post_box">
              <p class="writer">작성자 : ${post.nickname}</p>
            <div class="Questions_title">
            ${post.title }
            </div>
            <span hidden class="bId_hidden">${bId}</span>
            <div class="Questions_inner"><c:out value="${post.content}" escapeXml="false"/></div>
            <c:choose>
            	<c:when test="${empty post.adminContent}">
		            <div class="admin_inner">아직 답변이 달리지 않았습니다.</div>
            	</c:when>
            	<c:otherwise>
            		<div class="admin_inner">${post.adminContent}</div>
            	</c:otherwise>
            </c:choose>
            <div class="write_btn_box">
              <c:choose>
              	<c:when test="${!empty admin}">
              		<button class="Questions_btn" type="button" name="list" onclick='location.href="<c:url value='/admin/admin'/>"'>관리자페이지로</button>
              		<button class="Questions_btn admin_modify middle" type="button" name="revoke">댓글달기</button>
              	</c:when>
              	<c:otherwise>
					<button class="Questions_btn" type="button" name="list" onclick='location.href="<c:url value='/board/boardmain?page=0'/>"'>목록으로</button>
              		<button class="Questions_btn modify middle" type="button" name="revoke">수정하기</button>
              		<a class="modal_btn delete" rel="modal:open" href="#modal1" type="button">삭제하기</a>              	
              	</c:otherwise>
              </c:choose>
            </div>
          </div>
          </div>
        </div>
      </form>
      </div>
    </div>
<!-- modal body -->
<div id="modal1" class="modal" >
   <form action="delete" method="post" class="modal_delete">
      <p class="delete_title">삭제 하시겠습니까?</p>
      <div class="yes_or_no">
      <label for="Yes" >확인</label> 
      <input type="checkbox" name="delete" value="1" id="Yes" onclick='checkOnlyOne(this)' checked />&nbsp;&nbsp;&nbsp;&nbsp;
      <label for="No">취소</label>
      <input type="checkbox" name="delete" value="0" onclick='checkOnlyOne(this)' id="No"/>
      </div>
      <input class="submit_btn" type="submit" value="확인"/>

   </form>
</div>
    <script>
       $(document).on('click', '.modify', function(){
          var toTextarea = $(".Questions_inner")
          var text = $(".Questions_inner").html()
          text = text.replaceAll("<br>", "\n")
          $(toTextarea).replaceWith('<textarea class="Questions_inner textarea">' + text + '</textarea>')
          $('.modify').replaceWith('<button class="Questions_btn comfirm middle" type="button" name="revoke">등록하기</button>')
       })
       
       $(document).on('click', '.admin_modify', function(){
    	   var toTextarea = $(".admin_inner")
           $(toTextarea).replaceWith('<textarea class="admin_inner textarea"></textarea>')
           $('.admin_modify').replaceWith('<button class="Questions_btn admin_comfirm middle" type="button" name="revoke">등록하기</button>')
       })
       
  function checkOnlyOne(element) {
  
  const checkboxes 
      = document.getElementsByName("delete");
  
  checkboxes.forEach((cb) => {
    cb.checked = false;
  })
  
  element.checked = true;
  }
   </script>
   <script src="<c:url value='/resources/js/common.js'/>"></script>
  </body>
</html>