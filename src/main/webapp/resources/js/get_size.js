//월별 사용량 통계 ajax통신
$(".month_selection").on("change", function(){
	//선택 월
    var month = $(this).val();
    //value = 08-01이니 - 로 split
    var monthstr = month.split("-")[0]
    $.ajax({
		//메소드 명 설정
		url: "getMonth",
		type: "POST",
		enctype: "application/json; charset=utf-8",
		dataType: 'text',
		data: {
			'month': month
		},
		success: function(data){
			let target = $(".zt-skill-bar > div")
			iswhat = "";
    	    amountText = $('.amount')
    	    //10000MB - > 10GB
    	    if (data >= (1024 * 1024 * 1024)) {
    	    	  iswhat = "GB";
    	       	  data = data / (1024 * 1024 * 1024) ;
    	       	  target.css({
    	              'width': (data / 20) * 100 + "%"
    	            });
    	          $('.amount').text(data + "GB")
    	      	  $(amountText).text(data.toFixed(0) + "GB");
    		  }else if (data >= (1024 * 1024)) { //mb처리
    			  isWhat = "MB"
    			  data = data / (1024 * 1024);
    			  target.css({
    	              'width': (data / 20000) * 100 + "%"
    	            });
    	          $(amountText).text(data + "MB")
    			  $(amountText).text(data.toFixed(0) + "MB");
    		  }else if (data >= 1024) {
    			  //20gb 10% = 2bg 1% = 200mb 0.1 = 2mb 0.01% = 200kb
    			  iswhat = "KB";
    			  data = data / 1024;
    			  target.css({
    	              'width': '10%' 
    	            });
    	          $('.amount').text(data + "KB")
    			  $(amountText).text(data.toFixed(2) + "KB");
    		  }else{
    			  iswhat = "byte";
    			  target.css({
    	              'width': '10%'
    	            });
    	          $('.amount').text(data + "byte")
    			  $(amountText).text(data.toFixed(0) + "byte");
    		  }
    		  $('.month').text(monthstr + "월")
		},error: function(){
			console.log("실패")
		}
		
	})
});