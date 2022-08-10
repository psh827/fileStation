<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 페이지</title>
</head>
<body>
<div class="main-inner__container">
        <div class="Questions_container">
          <div class="subheading">글 작성</div>
          
		<form:form modelAttribute="postCommand" method="post" >
          <div class="post_box">
            <div class="title_box">
             작성자이름 <form:input path="nickName" type="text" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>비밀글</label><form:input type="password" path="passwd"  maxlength="4" placeholder="네자리를 입력하세요." />
            </div>
            <label>제목</label><br>
            <form:input class="write_title" type="text" placeholder="제목을 입력하세요." path="title" /><br>
            <label>내용</label><br>
            <form:textarea rows="20" cols="170" placeholder="글 내용을 입력하세요." path="content" /><br>
            <label>첨부파일</label><input type="file"><br>
            <button class="write_btn" type="submit" onclick="location.href='<c:url value="/board/success_board"/>'" >작성하기</button>
            <div class="write_btn_box">
              <button class="Questions_btn" type="button">목록으로</button><button class="Questions_btn right" type="button" >취소하기</button>
            </div>
          </div>
		</form:form>
	
          
          </div>
        </div>
</body>
</html>