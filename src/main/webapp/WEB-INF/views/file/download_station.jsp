<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/initial.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/default.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/upload.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/download.css'/>">
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
      <c:choose>
      	<c:when test="${fn:length(fileList) <= 20 }">
      	<div class="table_flex" style="display:flex;">
      		<table>
      		<thead>
      			<tr>
	      		<th>No</th>
	      		<th>파일이름</th>
	      		<th>파일용량</th>
	      		<th> </th>
	      		</tr>
      		</thead>
	        <c:forEach var="i" begin="1" end="${fn:length(fileList) }">
	        	<tr>
	        		<td class="no${i}">${i}</td>
	        		<td class="file_name_${i}">${fileList[i - 1].fileOriName}<span hidden> ${fileList[i - 1].fileId}</span></td>
	        		<td class="file_size_${i}">${fileList[i - 1].fileSize}</td>
	        		<td><a href="<c:url value='/file/download_detail?fileId=${fileList[i - 1].fileId}'/>">다운로드</a></td>
	        	</tr>
	        </c:forEach>
        	</table>
        	</div>
      	</c:when>
      </c:choose>
      <div class="text_box"></div>
      </div>
    </div>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="<c:url value='/resources/js/download.js'/>"></script>
  </body>
</html>