<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<!-- 햄버거 버튼-->
	<input type="checkbox" id="gnbcheck" name="gnbcheck">
    <label for="gnbcheck" id="gnbbtn">
      <span></span>
      <span></span>
      <span></span>
    </label>
	
<!-- <a class="logo" href="#">File Station</a> -->
	<nav class="gnb">
        <ul class="nav_ul">
          <li class="nav_item">
            <a href="<c:url value='/file/file_main'/>" class="nav_link logo">
              <span class="logo_span">File Station</spanc>
              <span class="hidden" hidden>로고</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="<c:url value='/file/file_main'/>" class="nav_link">
              <i class="fa-solid fa-house fa-lg"></i>
              <span class="hidden" hidden>홈(업로드)</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="<c:url value='/file/download'/>" class="nav_link">
              <i class="fa-solid fa-download fa-lg"></i>
              <span class="hidden" hidden>다운로드</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="<c:url value='/service/service_introduction'/>" class="nav_link">
              <i class="fa-solid fa-person-chalkboard fa-lg"></i>
              <span class="hidden" hidden>서비스 소개</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="<c:url value='/service/qna'/>" class="nav_link">
              <i class="fa-solid fa-question fa-lg"></i>
              <span class="hidden" hidden>자주 하는 질문</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="<c:url value='/board/boardmain'/>" class="nav_link">
              <i class="fa-solid fa-chalkboard-user fa-lg"></i>
              <span class="hidden" hidden>건의사항</span>
            </a>
          </li>
          <li class="nav_item">
            <a  href="<c:url value='/admin/admin'/>" class="nav_link admin-nav">
              <i class="fa-solid fa-gear"></i>
              <span class="hidden" hidden>관리자</span>
            </a>
          </li>
        </ul>
     </nav>   
        <!-- 반응형 nav -->
        
        
        
       <!--  <script>
        $("li.nav_item").click(function(){
        	$(this).addClass("active");
        }) -->
        
        </script>