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
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/modal.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/admin.css'/>" />
	<link rel="stylesheet" href="<c:url value='/resources/css/nam.css'/>">
    <script src="<c:url value='/resources/js/subpage.js'/>"></script>
    <script src="<c:url value='/resources/js/admin.js'/>"></script>
    <script
      src="https://kit.fontawesome.com/62a067f302.js"
      crossorigin="anonymous"
    ></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css">
  </head>
  <body>
    <div class="main_container">
      <!--왼쪽 navigation-->
      <nav>
        <!-- <a class="logo" href="#">File Station</a> -->
        <ul class="nav_ul">
          <li class="nav_item">
            <a href="<c:url value='/file/file_main'/>" class="nav_link logo">
              <span class="logo_span">File Station</spanc>
              <span class="hidden" hidden>로고</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="<c:url value='/admin/admin'/>" class="nav_link">
              <i class="fa-solid fa-house fa-lg"></i>
              <span class="hidden" hidden>홈(관리자)</span>
            </a>
          <li class="nav_item">
            <a href="<c:url value='/board/boardmain'/>" class="nav_link">
              <i class="fa-solid fa-chalkboard-user fa-lg"></i>
              <span class="hidden" hidden>건의사항</span>
            </a>
          </li>
          <li class="nav_item">
            <a href="<c:url value='/admin/logout'/>" class="nav_link">
              <span class="hidden" hidden>로그아웃</span>
            </a>
          </li>
        </ul>
      </nav>
      <!-- 하얀색 영역 -->
      <div class="main-inner__container">
        <div class="Questions_container">
          <div class="subheading">관리자 페이지</div>
          <div class="admin_container">
            <div class="admin_box top left">
            	<div class="input-container">
			        <form method="get">
			        	<select class="month_selection" name="month">
			        		<option label="1월" value="01-01">
			        		<option label="2월" value="02-01">
			        		<option label="3월" value="03-01">
			        		<option label="4월" value="04-01">
			        		<option label="5월" value="05-01">
			        		<option label="6월" value="06-01">
			        		<option label="7월" value="07-01">
			        		<option label="8월" value="08-01">
			        		<option label="9월" value="09-01">
			        		<option label="10월" value="10-01">
			        		<option label="11월" value="11-01">
			        		<option label="12월" value="12-01">
			        	</select>
			        </form>
          <!--막대그래프 바  -->
          <div class="zt-span6 last">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<h3><strong>월별 업로드된 파일 크기</strong></h3>
			<div class="zt-skill-bar"><div data-width="${amount}" style=""><span class="month">8월</span><span class="amount">${amount }MB</span></div></div>
        </div>
      </div>
            
            </div>
            <div class="admin_box top right">
            	<h1>확장자 별 총 업로드 수</h1>
	            <div class="pie_box">
		            <div class="chart-div">
						<div id="piechart" style="width: 250px; height: 250px;margin: -54px;padding:0px 15px;"></div>
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
	      google.charts.load("current", {packages:["corechart"]});
	      google.charts.setOnLoadCallback(drawChart);
	      
	      function drawChart() {
	        var data = google.visualization.arrayToDataTable([
	          ['file', 'number'],
	          ['IMG',  ${checkList[0]}],
	          ['VIDEO',  ${checkList[1]}],
	          ['DOCUMENT', ${checkList[2]}],
	          ['ETC', ${checkList[3]}],
	          ['TEXT', ${checkList[4]}]
	        ]);
	
	      var options = {
	        legend: {
	            display: true,
	            position: 'top'
	        },
	        pieSliceText: 'label',
	        pieStartAngle: 100,
	        pieSliceTextStyle: {
	            color: 'black',
	          },
	        backgroundColor: {
                fill: 'none',
            },
            colors: ['#F94892', '#FF7F3F', '#FBDF07', '#89CFFD',
                '#7FB77E'],
            tooltip: { trigger: 'none' }
	      };
	
	        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
	        chart.draw(data, options);
	      }
    </script>
            <div class="admin_box bottom left" style="padding: 0; overflow: hidden;">
            	<div id="chart_div" style="width: 110%; height: 100%;">
      				<c:forEach var="i" begin="0" end="${fn:length(monthCount) - 1}">
      					<p>${monthList[i]} = ${monthCount[i]}</p>
      				</c:forEach>
   	  			</div>
            </div>
            <div class="admin_box bottom right">
            	<p class="admin_title">새로 올라온 건의사항</p>
            	<table class="admin_board_box" style="width: 100%; margin: 0;">
            		<tr style="border-bottom: 1px solid; border-top: 1px solid; " >
            			<th>순서</th>
            			<th>내용</th>
            			<th>날짜</th>
            		</tr>
            		<c:choose>
            			<c:when test="${fn:length(adminPost) < 6 }">
            				<c:forEach var="post" items="${adminPost}">
            					<tr class="admin_item">
		            					<td class="admin_bId admin_span"><a class="admin_link" href="<c:url value="/board/post?boardId=${post.boardId}"/>">${post.boardId}</a></td>
		            					<td class="admin_span_title admin_span"><a class="admin_link" href="<c:url value="/board/post?boardId=${post.boardId}"/>">${post.title}</a></td>
		            					<td class="admin_regDate admin_span"><a class="admin_link" href="<c:url value="/board/post?boardId=${post.boardId}"/>">${post.regDate }</a></td>
            					</tr>	            					
            				</c:forEach>
            			</c:when>
            			<c:otherwise>
		            		<c:forEach var="i" begin="0" end="5">
		            			<tr class="admin_item">
									<td class="admin_bId admin_span"><a class="admin_link" href="<c:url value="/board/post?boardId=${post.boardId}"/>">${post.boardId}</a></td>
		            					<td class="admin_span_title admin_span"><a class="admin_link" href="<c:url value="/board/post?boardId=${post.boardId}"/>">${post.title}</a></td>
		            					<td class="admin_regDate admin_span"><a class="admin_link" href="<c:url value="/board/post?boardId=${post.boardId}"/>">${post.regDate }</a></td>
								</tr>
		            		</c:forEach>
            			</c:otherwise>
            		</c:choose>
            	</table>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
  <script src="<c:url value='/resources/js/get_size.js'/>"></script>
  <script src="<c:url value='/resources/js/admin.js'/>"></script>
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
			 [month[0] + "월",  52],          
			 [month[1] + "월",  17],          
			 [month[2] + "월",  25],          
			 [month[3] + "월",  52],
			 [month[4] + "월",  Number(count[4])]]);        
			 data.addColumn('number', '명');
		 var options = {
			title: '월 별 사용자 수',
			backgroundColor : '#32314d',
		    colors: [ '#42cef5'],
		 	tooltip: { isHtml: true },
		 	legend: 'none',
		 	 vAxis: {
		          title: '명',
		          textStyle: {
		        	  italic : false
		          }
		        }
		 };        
		 var chart = new google.visualization.LineChart(document.getElementById('chart_div'));        
		 chart.draw(data, options);      }
 
    (function($) {
    	  $(function() {
    	    function animated_contents() {
    	      $(".zt-skill-bar > div ").each(function(i) {
    	        var $this = $(this),
    	          skills = $this.data('width');
    	          iswhat = "";
    	          amountText = $('.amount')
    	          //10000MB - > 10GB
    	          if (skills >= (1024 * 1024 * 1024)) {
    	        	  iswhat = "GB";
    	        	  skills = skills / (1024 * 1024 * 1024) ;
    	        	  $this.css({
    	    	          'width': (20 / skills) * 10 + "%"
    	    	        });
    	        	  $(amountText).text(skills.toFixed(0) + "GB");
    			  }else if (skills >= (1024 * 1024)) {
    				  isWhat = "MB"
    				  skills = skills / (1024 * 1024);
    				  $this.css({
    	    	          'width': (skills / 20000) * 100 + "%"
    	    	        });
    				  $(amountText).text(skills.toFixed(0) + "MB");
    			  }else if (skills >= 1024) {
    				  //20gb 10% = 2bg 1% = 200mb 0.1 = 2mb 0.01% = 200kb
    				  iswhat = "KB";
    				  skills = skills / 1024;
    				  $this.css({
    	    	          'width': '10%' 
    	    	        });
    				  $(amountText).text(skills.toFixed(2) + "KB");
    			  }else{
    				  iswhat = "byte";
    				  $this.css({
    	    	          'width': '10%'
    	    	        });
    				  $(amountText).text(skills.toFixed(0) + "byte");
    			  }

    	      });
    	    }

    	    if (jQuery().appear) {
    	      $('.zt-skill-bar').appear().on('appear', function() {
    	        animated_contents();
    	      });
    	    } else {
    	      animated_contents();
    	    }
    	  });
    	}(jQuery));
	    var default_month = '${defaultMonth}'
	    default_month = default_month.split('-')[1].replace("0", "")
	    default_month = Number(default_month) - 1
	    $('.month_selection option:eq(' + default_month + ')').prop("selected", true);
    
	    
	    $(function(){
	        let elem = $('.admin_span_title .admin_link');
	        const showText = 15;
	        let moreBtnText = "Show More";

	        elem.each(function(i){
	            let content = $(this).html();
	            console.log(content)
	            let changeHtml = '';
	    		
	            let contentLeng = content.length;
	            let lessText = content.substr(0, showText);
	            let moreText = content.substr(showText, contentLeng);
	            if (showText < contentLeng) {
	                changeHtml = lessText + '<span>...</span>';
	                $(this).html(changeHtml);
	                if( i == 0 ){
	                    $(this).html(changeHtml)
	                }else if( i == 1 ){
	                    $(this).html(changeHtml)
	                }else{
	                    $(this).html(changeHtml)
	                }
	            }
	        });
	        
	    });
    </script>
  </body>
</html>
