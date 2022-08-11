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
</head>
<body>
<div class="main_container">
      <!--왼쪽 navigation-->
      <nav>
        <!-- <a class="logo" href="#">File Station</a> -->
        <ul class="nav_ul">
          <li class="nav_item">
            <a href="#" class="nav_link logo">
              <span class="logo_span">File Station</spanc>
              <span class="hidden" hidden>로고</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="#" class="nav_link">
              <i class="fa-solid fa-house fa-lg"></i>
              <span class="hidden" hidden>홈(업로드)</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="" class="nav_link">
              <i class="fa-solid fa-download fa-lg"></i>
              <span class="hidden" hidden>다운로드</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="" class="nav_link">
              <i class="fa-solid fa-person-chalkboard fa-lg"></i>
              <span class="hidden" hidden>서비스 소개</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="" class="nav_link">
              <i class="fa-solid fa-question fa-lg"></i>
              <span class="hidden" hidden>자주 하는 질문</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="" class="nav_link">
              <i class="fa-solid fa-chalkboard-user fa-lg"></i>
              <span class="hidden" hidden>건의사항</span>
            </a>
          </li>
        </ul>
      </nav>
      <!-- 하얀색 영역 -->
      <div class="main-inner__container">
        <div class="Questions_container">
          <div class="subheading">글 작성</div>
          
          <form:form modelAttribute="postCommand" method="post">
          <div class="post_box">
            <div class="title_box">
              작성자이름<form:input path="nickName" type="text" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <label>비밀글</label>
              <form:input type="password" path="passwd"  maxlength="4" name="hide" placeholder="네자리를 입력하세요." />
            </div>
            <label>제목</label><br>
            <form:input class="write_title" type="text" placeholder="제목을 입력하세요." path="title" /><br>
            <label>내용</label><br>
            <form:textarea name="textarea" rows="20" cols="170" placeholder="글 내용을 입력하세요." path="content"></form:textarea><br>
            <label>첨부파일</label><input type="file"><br>
            <button class="write_btn" type="submit">작성하기</button>
            <div class="write_btn_box">
              <button class="Questions_btn" type="button"><a href="/fileStation/board/boardmain">목록으로</a></button>
            </div>
          </div>
          </form:form>
          
          </div>
        </div>
      </div>
    </div>
</body>
</html>