<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 페이지</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/initial.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/default.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/style.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/Questions.css'/>" />
<script
      src="https://kit.fontawesome.com/62a067f302.js"
      crossorigin="anonymous"
    ></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
</head>
<body>
<div class="main_container">
      <!--왼쪽 navigation-->

        <jsp:include page="../incl/nav.jsp"/>

      <!-- 하얀색 영역 -->
      <div class="main-inner__container">
        <div class="Questions_container">
          <div class="subheading">글 작성</div>
          
          <form:form modelAttribute="postCommand" method="post">
          <div class="post_box">
            <div class="title_box">
            	<div class="title_name">
 	             작성자이름<form:input path="nickName" type="text" class="input-top" maxlength='20'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	</div>
            	<div class="title_passwd">
    	          <label>비밀글</label>
        	      <span class="placeholder">네자리를 입력하세요.</span>
            	  <form:input class="input-top" type="password" path="passwd"  maxlength="4" name="hide"/>
             	</div>
            </div>
            <div class="title_container">
            <label>제목</label><br>
            <span class="title_label">제목을 입력하세요</span>
            <form:input class="write_title" type="text" path="title" /><br>
            </div>
            <label>내용</label><br>
            <form:textarea name="textarea" required rows="20" cols="170" placeholder="글 내용을 입력하세요." path="content" style="padding: 10px 5px 5px 5px;"></form:textarea><br>
            <div class="botton-btn">
               <button class="write_btn" type="submit">작성하기</button>
               <button class="write_btn" type="button"onclick='location.href="<c:url value='/board/boardmain'/>"'>목록으로</button>
            </div>
          </div>
          </form:form>
          
          </div>
        </div>
      </div>
<script>
$("span + textarea, span + input[type=text], span + input[type=password]").keyup(function(){
	  if($(this).val()!=""){
		   $(this).prev().hide();
		  }else{
			   $(this).prev().show();
		  } 
		}).prev().click(function(){
			  $(this).next().focus();
			 });
</script>
</body>
</html>