$(document).on('click', '.comfirm', function(){
	content = $(".Questions_inner.textarea").val()
	bId = $(".bId_hidden").text()
	let obj = {
		"content": content,
		"bId": bId
	}
	var toTextarea = $(".Questions_inner.textarea")
	
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
			console.log(data.content)
			$(toTextarea).replaceWith('<div class="Questions_inner">' + data.content + '</div>')
			$('.comfirm').replaceWith('<button class="Questions_btn modify middle" type="button" name="revoke">수정하기</button>')
		},
		error: function(){
			alert("실패")
		}
	})
	
})

$(document).on('click', '.admin_comfirm', function(){
	content = $(".admin_inner.textarea").val();
	bId = $(".bId_hidden").text()
	let obj = {
		"content": content,
		"bId": bId
	}
	var toTextarea = $(".admin_inner.textarea")
	
	$.ajax({
		url: "admin_comment",
		type: "POST",
		enctype: "application/json; charset=utf-8",
		dataType: "json",
		data: {
			"content": content,
			"bId": bId
		},
		success: function(data){
			console.log(data.adminContent)
			$(toTextarea).replaceWith('<div class="admin_inner">' + data.adminContent + '</div>')
			$('.admin_comfirm').replaceWith('<button class="Questions_btn admin_modify middle" type="button" name="revoke">댓글달기</button>')
		},
		error: function(){
			alert("실패")
		}
	})
	
})