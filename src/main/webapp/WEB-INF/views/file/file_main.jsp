<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/upload.css'/>">
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
        <div class="input-container">
          <form name="uploadForm" id="uploadForm" enctype="multipart/form-data" method="post">
            <div class="filearea">
              <p class="filearea-p textarea-p">
                <span>파일</span>
              </p>
                <div id="dropZone">
                  <div id="fileListTable" style="width: 100%;">
                    <div id="fileDragDesc">파일을 끌어다 놓거나 버튼을 눌러 추가하세요</div>
                      <ul id="fileTableTbody">

                      </ul>
                    </div>
                </div>
            </div>
            <div class="textarea">
              <div class="textarea_inner">
                  <p class="textarea-p">
                    <span>텍스트</span>
                  </p>
                  <button type="button" class="delete_all">전체삭제</button>
              </div>
              <textarea placeholder="TEXT" class="textInput" name="textInput" ></textarea>
            </div>
            <div class="file_input_grp">
                <div class="passwd_input">
                  <label class="input_label">비밀번호 입력</label>
                  <input class="input_passwd" placeholder="8글자이상 특수기호를 포함!" type="password" minlength="8" required>
                </div>
                <input type="button" class="submit_file" onclick="uploadFile(); return false;" class="btn bg_01" value="파일 업로드">
            </div>
          </form>
          <!-- <div class="banner_container">
            <div class="banner_inner">
              <div class="banner first_banner"></div>
              <div class="banner second_banner"></div>
            </div>
          </div> -->
        </div>
        <div class="ad_box"></div>
      </div>
    </div>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="<c:url value='/resources/js/home.js'/>"></script>
  </body>
</html>