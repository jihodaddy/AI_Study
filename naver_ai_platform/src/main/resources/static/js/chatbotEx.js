/**
 * chatbot.js
 */
 
 $(function(){
	$('#chatForm').on('submit', function(event){
		event.preventDefault();
		 //var formData = new FormData($('#chatForm')[0]);
		 
			 
		$.ajax({
			url:"clovaChatbot",
			type:"post",
			//data:formData,
			data: {message: $('#message').val()},
			success:function(result){
				$('#chatBox').text(result);
				
			},
			error:function(){
				alert("오류가 발생했습니다.")
			}
		});
		/*입력란 비우기*/
		$('#message').val('');
		
	});		
});