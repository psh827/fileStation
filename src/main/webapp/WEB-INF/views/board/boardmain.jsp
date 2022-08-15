<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<script src="<c:url value='/resources/js/subpage.js'/>"></script>
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
          <div class="subheading">건의사항</div>
          <div class="Questions_banner">
            <button class="Questions_btn" type="button" name="writing" onclick='location.href="<c:url value='/board/write_board'/>"'>글작성</button>
            <button class="Questions_btn right" type="button" name="mywriting">내가 작성한 글</button>
          </div>
          <div class="posting_list">
            <table class="Questions_table">
            <tr>
               <th><span class="textLine">No</span></th>
               <th><span class="textLine">Title</span></th>
               <th><span class="textLine">Date</span></th>
            </tr>
            <%-- <c:forEach var="postlist" items="${posttList}"> --%>
            <%-- <c:forEach var="item" items="${posttList}" begin="1" end="${fn:length(posttList) + 1}" step="1" varStatus="i"> --%>
            <c:forEach var="i" begin="1" end="${fn:length(posttList) + 1}">
                <tr>
                  <td class="Q_no"><span class="textLine">${i}</span></td>
                  <td><a data-value="${posttList[i].getBId()}" class="modal_btn"  rel="modal:open" href="#modal1"><span class="textLine">${posttList[i].getTitle()}</span></a></td>
                  <td class="Q_date"><span class="textLine">${posttList[i].getRegDate()}</span></td>
                </tr>
            </c:forEach>
            </table>
          </div>
        <div id="paging"><ul></ul></div>
        </div>
      </div>
    </div>
    
<!-- modal body -->
<div id="modal1" class="modal" >
   <form action="post" method="post" class="modal_body">
      <input class="modal_hidden_input" hidden name="bId" value="" />
      비밀번호 : <input type="password" name="passwd" maxlength="4" />
      <input class="submit_btn" type="submit" value="확인" />
   </form>
</div>

<script>
   let pageNum = 8;
    let pageCount = Math.ceil(($('tr').length - 1) / 8);
    
    for (let i = pageNum + 2; i <= $('tr').length; i++){
       $('tr:nth-child('+ i +')').css('display', 'none');
      };
    
      for (let i = 1; i <= pageCount; i++){
         let str = '<li>' + i + '</li>';
         $('#paging ul').append(str);
      };
      
      $('#paging ul li').click(function(){
         let number = $(this).text();
         console.log(number)
         $('tr:not(tr:nth-child(1))').css('display', 'none');
         for (let i = pageNum * (number - 1) + 2; i <= number * pageNum + 1; i ++) {
            $('tr:nth-child(' + i + ')').css('display', 'table-row');
         }
      });
</script>
<!-- 
<script>

    for
    if($('tr').length == 9){
       
    }
    
       
</script>
-->


  </body>
</html>