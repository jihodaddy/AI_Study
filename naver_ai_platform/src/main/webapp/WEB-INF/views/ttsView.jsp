<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>음성인식2</title>
    	<script src="<c:url value='/js/jquery-3.6.0.min.js'/>"></script>
    	<script src="<c:url value='/js/tts.js'/>"></script>
	</head>
	<body>
		<!-- 파일 업로드 -->
			<h3>CSR: STT(음성을 텍스트로 변환)</h3>
		<form id="ttsForm"  enctype= "multipart/form-data">
		<!-- 	<input type="text" id="ttsText" name="ttsText"/> -->
			파일: <input type="file" id="uploadFile" name="uploadFile"><br> 
			언어: <select name="language">
						<option value="nara">한국어, 여성</option>
						<option value="jinho">한국어, 남성</option>
						<option value="nhajun">하준 : 한국어, 아동 음색 (남)</option>
						<option value="ndain">다인 : 한국어, 아동음색 (여)</option>
						<option value="clara">클라라, 영어, 여성</option>
						<option value="matt">매트 : 영어, 남성 음색</option>
						<option value="carmen">카르멘, 스페인어, 여성</option>
						<option value="shinji">신지: 일본어, 남성 음색</option>
						<option value="meimei">메이메이 : 중국어, 여성 음색</option>
					</select>
			<input type="submit" value="결과 확인">
		</form>
		<br><br>
		
				
		<!-- 음성인식 결과 출력-->
		<h3>TTS: 텍스트를 음성으로 변환한 결과</h3> 
		<div id="resultDiv"></div><br><br>
	
		<div>
			<audio preload="auto" controls></audio>
		</div>
	
		<br>	<br>
		<a href="/">index 페이지로 이동</a>
		
	</body>
</html>