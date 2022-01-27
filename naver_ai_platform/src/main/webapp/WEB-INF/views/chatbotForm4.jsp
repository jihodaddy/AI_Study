<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>챗봇</title>
		<script src="<c:url value='/js/jquery-3.6.0.min.js'/>"></script>
    	<script src="<c:url value='/js/chatbot4.js'/>"></script>
    	<link rel="stylesheet" type="text/css" href="<c:url value='/css/chatbot3.css'/>" >
	</head>
	<body>
		<div id="wrap">
		<!-- HEADER -->
		<div id="chatHeader">
			<span>챗봇</span>
			<button id="btnClose">X</button>
		</div>
		
		<!-- 채팅내용 출력 -->
		
		
		<!-- 응답 메시지 출력 -->
		<div id="chatBox"></div>
		
		<div>
		<!-- 질문 메세지 입력  -->
		<form id="chatForm">
			<input type="text" id="message" name="message" size="30" placeholder="질문을 입력하세요."/>
			<input type="submit" value="전송" />
		</form>
		</div><br><br>
		
		<div>
		<!-- 음성녹음  -->
		음성메세지: <button id="record">녹음</button>
						<button id="stop">정지</button>
						<div id="sound-clips"></div>
		</div>
		
		<div>
			<audio preload="auto" controls></audio>
		</div>
		
		<br>	<br>
		<a href="/">index 페이지로 이동</a>
		
		</div>
	</body>
	
</html>