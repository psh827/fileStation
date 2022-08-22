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
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/pagination.css'/>" />
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
        <jsp:include page="../incl/nav.jsp"/>
      </nav>
      <!-- 하얀색 영역 -->
      <div class="main-inner__container">
        <div class="Questions_container">
          <div class="subheading">건의사항</div>
          <div class="Questions_banner">
            <button class="Questions_btn" style="margin-right: 30px;" type="button" name="writing" onclick='location.href="<c:url value='/board/write_board'/>"'>글작성</button>
            
          <!-- 검색 영역 시작-->
         <form action="boardmain" class="form-inline d-flex justify-content-end"
            method="GET">
            <div class="search_box">
                  <label style="margin: 0 auto; padding-top: 16px;">닉네임</label>
                    <select name="field" id="field" class="form-control form-control-sm">
                     <option value="nickname">닉네임</option>
                  </select>
            <input type="text" id="nickname" name="nickname" class="form-control form-control-sm"
               style="margin: 10px;"> 
            <input type="submit" class="btn btn-outline-info btn-sm" value="검색">
            </div>
         </form>
         <!-- 검색 영역 끝 -->
            
          </div>
          <button class="list-btn" onclick='location.href="<c:url value='/board/boardmain'/>"'>목록보기</button>
          <div class="posting_list">
            <table class="Questions_table">
            <tr>
               <th style="width:10%"><span class="textLine">No</span></th>
               <th><span class="textLine">Title</span></th>
               <th style="width:20%;text-align: center;"><span class="textLine">Date</span></th>
               <th style="width:10%;text-align: center;"><span class="textLine">답변여부</span></th>
            </tr>
            <c:forEach items="${ulist.content}" var="user">
            <tr>
               <td><span class="textLine">${user.boardId}</span></td>
               <td><a data-value="${user.boardId}" class="modal_btn"  rel="modal:open" href="#modal1"><span class="textLine">${user.getTitle()}</span></a></td>
               <td style="text-align: center;"><span class="textLine">${user.regDate}</span></td>
               <c:choose>
               	<c:when test="${empty user.adminContent}">
               		<td style="text-align: center;"><span class="textLine">미답변</span></td>	
               	</c:when>
               	<c:otherwise>
               		<td style="text-align: center;"><span class="textLine">답변완료</span></td>
               	</c:otherwise>
               </c:choose>
               
            </tr>
         </c:forEach>
            </table>
          </div>

<!-- 페이징 영역 시작 -->
   <div class="text-xs-center">
      <ul class="pagination">
      
         <!-- 이전 -->
               <li class="page-item"><a class="page-link" href="<c:url value='/board/boardmain?field=${param.field}&nickname=${param.nickname}&page=0'/>">처음</a></li>
               <li class="page-item"><a class="page-link" href="<c:url value='/board/boardmain?field=${param.field}&nickname=${param.nickname}&page=${ulist.number-1}'/>">&larr;</a></li>
         <!-- 페이지 그룹 -->
         <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
            <c:choose>
               <c:when test="${ulist.pageable.pageNumber+1 == i}">
                  <li class="page-item disabled"><a class="page-link" href="<c:url value='/board/boardmain?field=${param.field}&nickname=${param.nickname}&page=${i-1}'/>">${i}</a></li>
               </c:when>
               <c:otherwise>
                  <li class="page-item"><a class="page-link" href="<c:url value='/board/boardmain?field=${param.field}&nickname=${param.nickname}&page=${i-1}'/>">${i}</a></li>
               </c:otherwise>
            </c:choose>
         </c:forEach>
         <!-- 다음 -->
          <li class="page-item "><a class="page-link" href="<c:url value='/board/boardmain?field=${param.field}&nickname=${param.nickname}&page=${ulist.number+1}'/>">&rarr;</a></li>
          <li class="page-item "><a class="page-link" href="<c:url value='/board/boardmain?field=${param.field}&nickname=${param.nickname}&page=${ulist.totalPages-1}'/>">마지막</a></li>
      </ul>
   </div>
   <!-- 페이징 영역 끝 -->
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

<!-- modal body2 -->
<div id="modal2" class="modal" >
   <form action="findPost" method="get" class="modal_body">
      닉네임 : <input type="text" name="nickName"/>
      <input class="submit_btn" type="submit" value="확인" />
   </form>
</div>

  </body>
</html>