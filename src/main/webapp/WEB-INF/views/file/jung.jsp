<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, minimum-scale=1"
    />
<title>file Station</title>
<link rel="stylesheet" href="<c:url value='/resources/css/initial.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/default.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
 	<script
      src="https://kit.fontawesome.com/62a067f302.js"
      crossorigin="anonymous"
    ></script>
</head>
<body>
 <div class="main_container">
      <!--왼쪽 navigation-->
      <nav>
          <jsp:include page="../incl/nav.jsp"/>
      </nav>
      <!-- 하얀색 영역 -->
      <div class="main-inner__container">
      <div id="chart_div" style="width: 900px; height: 500px;">
      	<c:forEach var="i" begin="0" end="${fn:length(monthCount) - 1}">
      		<p>${monthList[i]} = ${monthCount[i]}</p>
      	</c:forEach>
   	  </div>
      </div>
  </div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
 <script type="text/javascript" src="https://www.google.com/jsapi"></script>   
  <script type="text/javascript">      
	  google.load("visualization", "1", {packages:["corechart"]});     
	  var month = ${monthList}
	  var count = ${monthCount}
	  console.log(count[0])
	  console.log(month)
	  google.setOnLoadCallback(drawChart);      
	  function drawChart() {        
		 var data = google.visualization.arrayToDataTable([          
			 ['Month', 'Count'],          
			 [month[0],  Number(count[0])],          
			 [month[1],  Number(count[1])],          
			 [month[2],  Number(count[2])],          
			 [month[3],  Number(count[3])],
			 [month[4],  Number(count[4])]  ]);        
		 var options = {          title: '월 별 사용자 수'        };        
		 var chart = new google.visualization.LineChart(document.getElementById('chart_div'));        
		 chart.draw(data, options);      }
  </script>
</body>
</html>