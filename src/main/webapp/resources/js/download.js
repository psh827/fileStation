$(document).ready(function(){
	var text = $('.text_repl')
	$(text).each(function(){
		let change = $(this).html().split("<span>")[1].split("</span>")[0]
		change = change.replaceAll('[br]', '<br>').replaceAll("[tab]", "<tab>").replaceAll("<tab>", "&nbsp;&nbsp;&nbsp;&nbsp;");
		$(this).html(change)
	})
})

function copyClipboard() {
  let text = document.getElementById('text').innerHTML;
  const textarea = document.createElement('textarea');
  text = text.replaceAll("<br>", "\n").replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;", "\t");
  textarea.textContent = text;
  document.body.append(textarea);
  textarea.select();
  document.execCommand('copy');
  textarea.remove();
}

const button = document.getElementById('copyBtn');
button.addEventListener('click', copyClipboard);

$('.text2_btn.copyBtn').click(function(){
	
	let text = $(this).parent().prev().html()
	if(text.includes("Show More")){
		text = text.replace("Show More", "")
	}
	text = text.replaceAll("<br>", "\n").replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;", "\t");
	text = text.split("type=\"button\">")[1].split("</a>")[0]
	const textarea = document.createElement('textarea');
	  textarea.textContent = text;
	  document.body.append(textarea);
	  textarea.select();
	  document.execCommand('copy');
	  textarea.remove();
})

$(".modal_inner_btn.modalCopyBtn").click(function(){
	let text = $(this).next().html()
	text = text.replaceAll("<br>", "\n").replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;", "\t");
	const textarea = document.createElement('textarea');
	textarea.textContent = text;
	document.body.append(textarea);
	textarea.select();
	document.execCommand('copy');
	textarea.remove();
})

let fileSize = $('.file_size')
$(fileSize).each(function(index, item){
	let size = Number($(this).text());
	let sizekb = size / 1024;
	let sizeMb = sizekb / 1024;
	let fileSizeStr = "";
	
	if (size >= (1024 * 1024)){
		$(this).text(sizeMb.toFixed(2) + " Mb");
	}else if (size >= (1024)){
		$(this).text(sizekb.toFixed(2) + " kb");
	}else {
		$(this).text(size + " byte");
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


$(function(){
    let elem = $('.table_txt');
    const showText = 80;
    let moreBtnText = "Show More";

    elem.each(function(i){
        let content = $(this).html();
        let contentmad = $(this).html().split("<br>")
        let changeHtml = '';
		if(contentmad[0].length < 10 && contentmad.length > 5){
			changeHtml = `${contentmad[0]}<br>${contentmad[1]} <span>...</span><span class="more-content"><span></span><a href="#table_modal${i}"type="button" class="more-btn active"><span>${moreBtnText}</span></a></span>`;
            $(this).html(changeHtml);
		}
        let contentLeng = content.length;
        let lessText = content.substr(0, showText);
        let moreText = content.substr(showText, contentLeng);
        if (showText < contentLeng) {
            changeHtml = `${lessText} <span>...</span><span class="more-content"><span>${moreText}</span><a href="#table_modal${i}"type="button" class="more-btn active"><span>${moreBtnText}</span></a></span>`;
            $(this).html(changeHtml);
            if( i == 0 ){
                $(this).html(changeHtml)
            }else if( i == 1 ){
                $(this).html(changeHtml)
            }else{
                $(this).html(changeHtml)
            }
        }
    });
    
});
  
