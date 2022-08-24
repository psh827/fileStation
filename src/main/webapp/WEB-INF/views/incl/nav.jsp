<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<!-- 햄버거 버튼-->
	<input type="checkbox" id="gnbcheck" name="gnbcheck">
    <label for="gnbcheck" class="gnbcheck" id="gnbbtn">
      <span></span>
      <span></span>
      <span></span>
    </label>
	
<!-- <a class="logo" href="#">File Station</a> -->
	<nav class="gnb">
        <ul class="nav_ul">
          <li class="nav_item">
            <a href="<c:url value='/file/file_main'/>" class="nav_link logo">
              <span class="logo_span">File Station</span>
              <span hidden>로고</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="<c:url value='/file/file_main'/>" class="nav_link file_main">
              <i class="fa-solid fa-house fa-lg"></i>
              <span class="hidden upload">업로드</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="<c:url value='/file/download'/>" class="nav_link download">
              <i class="fa-solid fa-download fa-lg"></i>
              <span class="hidden">다운로드</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="<c:url value='/service/service_introduction'/>" class="nav_link service_introduction">
              <i class="fa-solid fa-person-chalkboard fa-lg"></i>
              <span class="hidden">서비스 소개</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="<c:url value='/service/qna'/>" class="nav_link qna">
              <i class="fa-solid fa-question fa-lg"></i>
              <span class="hidden ques">자주 하는 질문</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="<c:url value='/board/boardmain'/>" class="nav_link boardmain">
              <i class="fa-solid fa-chalkboard-user fa-lg"></i>
              <span class="hidden boardNotice">건의사항</span>
            </a>
          </li>
        </ul>
        <ul class="admin_ul">
          <li class="nav_item" style="margin-bottom: 10px;">
          <c:choose>
          	<c:when test="${!empty admin}">
          	  <a class="modal_btn nav_link admin-nav"  href="<c:url value='/admin/admin' />">		
          	</c:when>
          	<c:otherwise>
	          <a class="modal_btn nav_link admin-nav"  rel="modal:open" href="#modal2">          	
          	</c:otherwise>
          </c:choose>
              <i class="fa-solid fa-gear"></i>
              <span class="hidden" hidden>관리자</span>
            </a>
          </li>
          <c:choose>
          	<c:when test="${!empty admin}">
	          	<li class="nav_item">
	          		<a class="admin-nav nav_link" href="<c:url value="/admin/logout"/>">
	          			로그아웃
	          		</a>
	          	</li>
          	</c:when>
          </c:choose>
        </ul>
     </nav>
     
<div id="modal2" class="modal" >
   <form action="/fileStation/admin/adminLogin" method="post" class="modal_body">
      <p><span>아이디</span> : <input type="text" name="adminId" /></p>
      <p><span>비밀번호</span> : <input type="password" name="adminPasswd" /></p>
      <input class="submit_btn" type="submit" value="확인" />
   </form>
</div>   