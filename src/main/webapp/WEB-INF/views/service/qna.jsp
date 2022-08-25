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
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/modal.css'/>" /> 
    <link rel="stylesheet" href="<c:url value='/resources/css/qna.css'/>" />
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
        <div id="Accordion_wrap">
          <h1>자주하는 질문</h1>
            <div class="que">
              <span>파일은 언제 삭제되나요?</span>
              <div class="arrow-wrap">
                <span class="arrow-top">↑</span>
                <span class="arrow-bottom">↓</span>
              </div>     
            </div>
            <div class="anw">
              <span>24시간에서 30시간까지 보장됩니다. 24시간이 지난 파일은 매일 00시, 06시, 12시, 18시를 기준으로 삭제됩니다. 다운로드페이지에 남은시간이 표시됩니다.</span>
            </div>
              <div class="que">
              <span>로그인은 어디서 하나요?</span>
            </div>
            <div class="anw">
              <span>저희 fileStation은 로그인 없이 url로만 업로드, 다운로드 되는 사이트 입니다.</span>
            </div>
              <div class="que">
              <span>최대 업로드 용량은 얼마인가요?</span>
            </div>
            <div class="anw">
              <span>단일 200MB이며, 최대 4000MB 까지 가능합니다.</span>
            </div>
               <div class="que">
              <span>비밀번호 설정은 무엇인가요?</span>
            </div>
            <div class="anw">
              <span>8자리 이상 특수기호를 포함한 비밀번호를 설정해 주시고 다운로드시 설정한 비밀번호를 입력하면 다운이 가능합니다.</span>
            </div>
               <div class="que">
              <span>보안상의 문제는 없나요?</span>
            </div>
            <div class="anw">
              <span>저희 fileStation은 24시간안에 사용자가 입력한 정보들이 삭제되기 때문에 보안상의 문제는 철저히 보장됩니다.</span>
            </div>
        </div>
      </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="<c:url value='/resources/js/common.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/qna.js'/>"></script>
  </body>
</html>