$(document).on('click', '.download_btn', function(){
	let fileId = $(this).prev().prev().text().split(" ")[1]
	console.log(fileId)
})