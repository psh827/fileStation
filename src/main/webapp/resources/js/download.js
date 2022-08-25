function copyClipboard() {
  const text = document.getElementById('text').textContent;
  const textarea = document.createElement('textarea');
  textarea.textContent = text;
  document.body.append(textarea);
  textarea.select();
  document.execCommand('copy');
  textarea.remove();
}

const button = document.getElementById('copyBtn');
button.addEventListener('click', copyClipboard);

let fileSize = $('.file_size')
$(fileSize).each(function(index, item){
	let sizekb = $(this).text() / 1024;
	let sizeMb = sizekb / 1024;
	if (sizekb < 1000){
		$(this).text(Math.floor(sizekb) + "KB")
	}else{
		$(this).text(Math.floor(sizeMb) + "MB")
	}
	
})

$(window).resize(function(){
	var windowWidth = $( window ).width();
	if(windowWidth < 790){
		$(function(){
		    let elem = $('.file_ori_name');
		    const showText = 10;
		    let moreBtnText = "Show More";
		    let lessBtnText = "Show Less";
		
		    elem.each(function(i){
		        let content = $(this).html();
		        let contentText = $(this).text();
		        let contentLeng = content.length;
		        let lessText = content.substr(0, showText);
		        let moreText = content.substr(showText, contentLeng);
		        if(!contentText.includes("...")){
			        let changeHtml = '';
				    if (showText < contentLeng) {
				        changeHtml = `${lessText}<span>...</span><span class="more-content"><span>${moreText}</span></span>`;
				        $(this).html(changeHtml);
				        if( i == 0 ){
				            $(this).html(changeHtml)
				        }else if( i == 1 ){
				            $(this).html(changeHtml)
				        }else{
				            $(this).html(changeHtml)
				        }
					}
			    }
			});
		});
	}else{
		$(function(){
		    let elem = $('.file_ori_name');
		    elem.each(function(i){
				let text = $(this).text()
				if(text.includes("...")){
					let textarr = $(this).text().replace("...", "")
					$(this).text(textarr)
				}
		        
			 });
		});
	}
})

