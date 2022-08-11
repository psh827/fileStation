$(document).on('click', '.modal_btn' , function(){
	let bId = $(this).data("value");
	$('.modal_hidden_input').attr('value', bId);

})