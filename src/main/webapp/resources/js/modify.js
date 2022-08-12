$(document).on('click', '.comfirm', function(){
	content = $(".Questions_inner.textarea").val()
	bId = $(".bId_hidden").text()
	let obj = {
		"content": content,
		"bId": bId
	}
	
	console.log(JSON.stringify(obj))
	$.ajax({
		url: "modify_board",
		type: "POST",
		enctype : "application/json; charset=utf-8",
		dataType: "json",
		data: {
			"content": content,
			"bId": bId
		},
		success: function(data){
			location.reload();
		},
		error: function(){
			alert("실패")
		}
	})
	
})
