$(".month_selection").on("change", function(){
    var month = $(this).val();
    var monthstr = month.split("-")[0]
    $.ajax({
		url: "getMonth",
		type: "POST",
		enctype: "application/json; charset=utf-8",
		dataType: 'text',
		data: {
			'month': month
		},
		success: function(data){
			if(data / 10 < 4){
				$('.zt-skill-bar > div').attr('data-width', data).css('width', '10%')
				$('.month').text(monthstr + "월")
			}else{
				$('.zt-skill-bar > div').attr('data-width', data).css('width', data / 10 + "%")
				$('.month').text(monthstr + "월")
			}
			$('.amount').text(data + "MB")
		},
		eeror: function(){
			console.log("실패")
		}
		
	})
});