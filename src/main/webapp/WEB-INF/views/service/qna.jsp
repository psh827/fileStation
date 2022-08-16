<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, minimum-scale=1"
    />
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <title>file Station</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/initial.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/default.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>" />    
    <link rel="stylesheet" href="<c:url value='/resources/css/qna.css'/>" />
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
      <div class="main-inner__container">
        <div id="Accordion_wrap">
          <h1>자주하는 질문</h1>
            <div class="que">
              <span>This is first question.</span>
              <div class="arrow-wrap">
                <span class="arrow-top">↑</span>
                <span class="arrow-bottom">↓</span>
              </div>     
            </div>
            <div class="anw">
              <span>This is first answer.</span>
            </div>
              <div class="que">
              <span>This is second question.</span>
            </div>
            <div class="anw">
              <span>This is second answer.</span>
            </div>
              <div class="que">
              <span>This is third question.</span>
            </div>
            <div class="anw">
              <span>This is third answer.</span>
            </div>
        </div>
      </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/qna.js'/>"></script>
  </body>
</html>