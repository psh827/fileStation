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
        <div class="input-container">
          <input class="file" id="input_file" type="file" multiple>
          <form name="uploadForm" id="uploadForm" enctype="multipart/form-data" method="post">
          <div class="top-container">
            <div id="root">
                <div class="contents">
                  <div class="upload-box">
                    <div id="drop-file" class="drag-file">
                      <img src="https://img.icons8.com/pastel-glyph/2x/image-file.png" alt="파일 아이콘" class="image">
                      <p class="message">Drag files to upload</p>
                    </div>
                  </div>
                  <div id="files" class="files">
                    
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
            </div>
            <div class="file_input_grp">
                <div class="passwd_input">
                  <label class="input_label">비밀번호 입력</label>
                  <input class="input_passwd" name="passwd" type="password" minlength="8" maxlength="20" required>
                  <label class="notice">8글자 이상 !, @, #, ~ 특수기호를 넣어서 작성해주세요.</label>
                </div>
                <input type="submit" class="submit_file" onclick="uploadFile(); return false;" class="btn bg_01" value="파일 업로드">
            </div>
          </form>
        </div>
        <div class="items-wrap">
          <div class="items">
            <div class="percent">0.00%</div>
          </div>
          <div class="total_now_Count"><span class="left_size"></span> / <span class="total_size"></span></div>
        </div>
      </div>
    </div>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="<c:url value='/resources/js/common.js'/>"></script>
    <script src="<c:url value='/resources/js/home.js'/>"></script>
  </body>
</html>