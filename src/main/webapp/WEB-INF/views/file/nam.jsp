<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/initial.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/default.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/nam.css'/>">
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
        <div class="input-container">
        <form:form method="get">
        	<form:select class="month_selection" path="month">
        		<option value="unknown">--선택--</option>
        		<option label="1월" value="1월">
        		<option label="2월" value="2월">
        		<option label="3월" value="3월">
        		<option label="4월" value="4월">
        		<option label="5월" value="5월">
        		<option label="6월" value="6월">
        		<option label="7월" value="7월">
        		<option label="8월" value="8월">
        		<option label="9월" value="9월">
        		<option label="10월" value="10월">
        		<option label="11월" value="11월">
        		<option label="12월" value="12월">
        	</form:select>
        </form:form>
          <!--막대그래프 바  -->
          <div class="zt-span6 last">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<h3><strong>월별 업로드된 파일 크기</strong></h3>
			<div class="zt-skill-bar"><div data-width="${amount }" style="">8월<span>${amount }MB</span></div></div>
        </div>
      </div>
    </div>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="<c:url value='/resources/js/home.js'/>"></script>
    <script>
    (function($) {
    	  "use strict";
    	  $(function() {
    	    function animated_contents() {
    	      $(".zt-skill-bar > div ").each(function(i) {
    	        var $this = $(this),
    	          skills = $this.data('width');

    	        $this.css({
    	          'width': skills + '%'
    	        });

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
    </script>
  </body>
</html>