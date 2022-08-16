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
            <span hidden class="bId_hidden">${bId}</span>
            <div class="Questions_inner">${post.content}</div>
            <div class="write_btn_box">
              <button class="Questions_btn" type="button" name="list" onclick='location.href="<c:url value='/board/boardmain?page=0'/>"'>목록으로</button>
              <button class="Questions_btn modify middle" type="button" name="revoke">수정하기</button>
              <a class="modal_btn delete" rel="modal:open" href="#modal1" type="button">삭제하기</a>
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
      <input type="checkbox" name="delete" value="1" id="Yes" checked />&nbsp;&nbsp;&nbsp;&nbsp;
      <label for="No">취소</label>
      <input type="checkbox" name="delete" value="0" id="No"/>
      </div>
      <input class="submit_btn" type="submit" value="확인"/>

   </form>
</div>
    <script>
       $(document).on('click', '.modify', function(){
          var toTextarea = $(".Questions_inner")
          $(toTextarea).replaceWith('<textarea class="Questions_inner textarea">${post.content}</textarea>')
          $('.modify').replaceWith('<button class="Questions_btn comfirm middle" type="button" name="revoke">등록하기</button>')
       })
   </script>
  </body>
</html>