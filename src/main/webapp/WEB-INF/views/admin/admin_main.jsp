<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/Questions.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/admin.css'/>" />
    <script src="<c:url value='/resources/js/subpage.js'/>"></script>
    <script src="<c:url value='/resources/js/admin.js'/>"></script>
    <script
      src="https://kit.fontawesome.com/62a067f302.js"
      crossorigin="anonymous"
    ></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css">
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
          <div class="subheading">관리자 페이지</div>
          <div class="admin_container">
            <div class="admin_box top left"></div>
            <div class="admin_box top right">
            <h1>확장자 별 총 업로드 수</h1>
            <div class="pie_box">
	            <div class="chart-div">
	              <canvas id="pieChartCanvas" width="150px" height="150px"></canvas>
	            </div>
	            <table>
	            <tr class="pie_txt_box">
	            <c:forEach var="i" begin="0" end="${fn:length(checkList) - 1}">
				<td><label>${checkStrList[i]}</label></td>
				</c:forEach>
				</tr>
				<tr class="pie_txt_box">
				<c:forEach var="i" begin="0" end="${fn:length(checkList) - 1}">
				<td class="pie_number">${checkList[i]}</td>
				</c:forEach>
				</tr>
	            </table>
            </div>
            </div>
            <script type="text/javascript">
            window.onload = function () {
            	  pieChartDraw();
            	  document.getElementById('legend-div').innerHTML = window.pieChart.generateLegend();
            		 setLegendOnClick();
            		}
            	let pieChartData = {
            	    labels: ['IMG', 'VIDEO', 'DOCUMENT', 'ETC', 'TEXT'],
            	    datasets: [{
            	        data: [${checkList[0]}, ${checkList[1]}, ${checkList[2]}, ${checkList[3]}, ${checkList[4]}],
            	        backgroundColor: ['rgb(255, 99, 132)', 'rgb(255, 159, 64)', 'rgb(255, 205, 86)', 'rgb(75, 192, 192)', 'rgb(54, 162, 235)', 'rgb(153, 102, 255)']
            	    }]
            	};
            </script>
            <div class="admin_box bottom left"></div>
            <div class="admin_box bottom right"></div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
