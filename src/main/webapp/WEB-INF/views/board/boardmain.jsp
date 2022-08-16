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
            <button class="Questions_btn" type="button" name="writing" onclick='location.href="<c:url value='/board/write_board'/>"'>글작성</button>
            <button class="Questions_btn right" type="button" name="mywriting"><a class="modal_btn search_btn"  rel="modal:open" href="#modal2">검색하기</a></button>
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
            <%-- <c:forEach var="i" items="${pagePost}">
                <tr>
                  <td class="Q_no"><span class="textLine">${i}</span></td>
                  <td><a data-value="${i.getBId()}" class="modal_btn"  rel="modal:open" href="#modal1"><span class="textLine">${i.getTitle()}</span></a></td>
                  <td class="Q_date"><span class="textLine">${i.getRegDate()}</span></td>
                </tr>
            </c:forEach> --%>
				<%-- <c:forEach var="item" items="${ulist.content}" begin="1" end="${fn:length(posttList) + 1}" step="1" varStatus="i"> --%>
				<c:forEach items="${ulist.content}" var="user">
				<tr>
					<td>${user.boardId}</td>
					<td><a data-value="${user.boardId}" class="modal_btn"  rel="modal:open" href="#modal1"><span class="textLine">${user.getTitle()}</span></a></td>
					<td>${user.regDate}</td>
				</tr>
			</c:forEach>
            </table>
          </div>

<!-- 페이징 영역 시작 -->
	<div class="text-xs-center">
		<ul class="pagination">
		
			<!-- 이전 -->
			<c:choose>
				<c:when test="${ulist.first}"></c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="<c:url value='/board/boardmain?page=0'/>">처음</a></li>
					<li class="page-item"><a class="page-link" href="<c:url value='/board/boardmain?page=${ulist.number-1}'/>">&larr;</a></li>
				</c:otherwise>
			</c:choose>

			<!-- 페이지 그룹 -->
			<c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
				<c:choose>
					<c:when test="${ulist.pageable.pageNumber+1 == i}">
						<li class="page-item disabled"><a class="page-link" href="<c:url value='/board/boardmain?page=${i-1}'/>">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="<c:url value='/board/boardmain?page=${i-1}'/>">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음 -->
			<c:choose>
				<c:when test="${ulist.last}"></c:when>
				<c:otherwise>
					<li class="page-item "><a class="page-link" href="<c:url value='/board/boardmain?page=${ulist.number+1}'/>">&rarr;</a></li>
					<li class="page-item "><a class="page-link" href="<c:url value='/board/boardmain?page=${ulist.totalPages-1}'/>">마지막</a></li>
				</c:otherwise>
			</c:choose>
			
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
   <form action="post" method="post" class="modal_body">
      <input class="modal_hidden_input" hidden name="bId" value="" />
      닉네임 : <input type="text" name="title"/>
      <input class="submit_btn" type="submit" value="확인" />
   </form>
</div>

  </body>
</html>