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
     
    <script
      src="https://kit.fontawesome.com/62a067f302.js"
      crossorigin="anonymous"
    ></script>
  </head>
  <body>
    <div class="main_container">
      <!--왼쪽 navigation-->
      <nav>
		<jsp:include page="../incl/nav.jsp"/>
      </nav>
      <!-- 하얀색 영역 -->
      <form>
      <div class="main-inner__container">
        <div class="Questions_container">
          <div class="subheading">글 내용</div>
          <div class="post_box">
           	<p class="writer">작성자 : ${post.nickname}</p>
            <div class="Questions_title">
            ${post.title }
            </div>
            <textarea class="Questions_inner" value="${post.content}">${post.content}</textarea>
            <div class="write_btn_box">
              <button class="Questions_btn" type="button" name="list" onclick='location.href="<c:url value='/board/boardmain'/>"'>목록으로</button>
              <button class="Questions_btn right" type="button" name="revoke" onclick='location.href="<c:url value='/board/boardmain'/>"'>등록하기</button>
            </div>
          </div>
          </div>
        </div>
      </form>
      </div>
    </div>
  </body>
</html>
