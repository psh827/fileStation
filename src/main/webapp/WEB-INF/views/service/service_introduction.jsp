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
    <title>file Station</title>
    <!-- <link rel="stylesheet" href="./css/initial.css"> -->
    <link rel="stylesheet" href="<c:url value='/resources/css/initial.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/default.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/service_introduction.css'/>" />
    <script
      src="https://kit.fontawesome.com/62a067f302.js"
      crossorigin="anonymous"
    ></script>
  </head>
  <body>
    <div class="main_container">
      <!--왼쪽 navigation-->

       <jsp:include page="../incl/nav.jsp"/>

      <!-- 하얀색 영역 -->
      <div class="main-inner__container">
        <h3 class="service_title">"파일 스테이션"은 로그인없이 쉽고 빠르게 파일을 공유할 수 있는 서비스입니다.</h3><br>
        <div class="card_container">
          <div class="card_box">
            <div class="card_img">
              <i class="fa-solid fa-share fa-lg"></i>
            </div>
            <div class="card_text">
              짧은시간 파일을 공유 할 때 복잡한 인증이나 로그인 없이 사용 할 수 있는 무료 파일 공유 서비스 입니다.
            </div>
          </div>
          <div class="card_box">
            <div class="card_img">
              <i class="fa-solid fa-trash-can-arrow-up fa-lg"></i>
            </div>
            <div class="card_text">
              업로드 된 파일은 사용자가 직접 삭제할 수 있고, 기본 파일 유효기간은 24시간입니다.
            </div>
          </div>
          <div class="card_box">
            <div class="card_img">
              <i class="fa-solid fa-key fa-lg"></i>
            </div>
            <div class="card_text">
              모든 데이터는 암호화 되어 전송되어 안전하게 저장 됩니다.
            </div>
          </div>
        <div class="card_box">
          <div class="card_img">
            <i class="fa-solid fa-lock fa-lg"></i>
          </div>
          <div class="card_text">
            파일을 올릴시에 비밀번호를 설정하여 안전한 저장소를 만들 수 있습니다.
          </div>
        </div>
      <div class="card_box">
        <div class="card_img">
          <p class="img_text">200MB</p>
        </div>
        <div class="card_text">
          넉넉하게 파일당 200M 업로드를 지원하여 원활한 파일 공유가 가능 하며, 최대 20개의 파일까지 업로드할 수 있습니다.
        </div>
      </div>
    <div class="card_box">
      <div class="card_img">
        <i class="fa-solid fa-cloud-arrow-up"></i>
      </div>
      <div class="card_text">
        파일 스테이션에서 제공 되는 파일 업로드, 다운로드 서비스는 무료 입니다.
      </div>
    </div>
    
      </div>
    <div class="img-box">
	</div>
    </div>
  </body>
</html>
