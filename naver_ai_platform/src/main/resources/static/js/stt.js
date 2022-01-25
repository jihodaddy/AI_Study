/**
 * object.js
 */
 

$(function(){
	$('#sttForm').on('submit', function(event){
		event.preventDefault();
		 var formData = new FormData($('#sttForm')[0]);
		 
		 // 업로드된 파일명 알아오기
		 var fileName = $('#uploadFile').val().split("\\").pop();
		 // alert($('#uploadFile').val()); //C:\fakepath\kor1.mp3 가짜경로로 출력
		 $('audio').prop("src", '/voice/'+fileName);
		 
		$.ajax({
			url:"clovaSTT",
			enctype:'multipart/form-data',
			type:"post",
			data:formData,
			processData: false,  // 필수
			contentType: false,  // 필수
			success:function(result){
				//alert(result);
					$('#resultDiv').text(result);
					
					
					
			},
			error:function(e){
				alert("오류가 발생했습니다." + e)
			}
		});
		
		
			
	});		
});