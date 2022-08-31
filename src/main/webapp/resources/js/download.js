//controller에서 받은 값들을 html에서 알 수 있게 태그로 변환
$(document).ready(function(){
	var text = $('.text_repl')
	$(text).each(function(){
		let change = $(this).html().split("<span>")[1].split("</span>")[0]
		change = change.replaceAll('[br]', '<br>').replaceAll("[tab]", "<tab>").replaceAll("<tab>", "&nbsp;&nbsp;&nbsp;&nbsp;");
		$(this).html(change)
	})
})

//controller에서 받은 텍스트를 복사하는 함수
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

//다운받을 텍스트가 2개이상이라면 테이블에서 복사하기 눌렀을 시 실행되는 함수 복사시 태그들을 escape문자, 원래 태그로 변환
$('.text2_btn.copyBtn').click(function(){
	
	let text = $(this).parent().prev().html()
	let textIndex = $(this).parent().prev().find(".more-btn.active").attr("href").split("modal")[1]
	if(text.includes("Show More")){
		text = text.replace("Show More", "")
	}
	text = text.replaceAll("<br>", "\n").replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;", "\t").replaceAll("&lt;", "<").replaceAll("&gt;", ">");
	text = text.split(`</span><a href="#table_modal${textIndex}" type="button" class="more-btn active">`)[0]
	const textarea = document.createElement('textarea');
	textarea.textContent = text;
	document.body.append(textarea);
	textarea.select();
	document.execCommand('copy');
	textarea.remove();
})

//다운받을 텍스트가 2개이상이라면 모달을 띄운 후 복사하기 눌렀을 시 실행되는 함수 복사시 태그들을 escape문자, 원래 태그로 변환
$(".modal_inner_btn.modalCopyBtn").click(function(){
	let text = $(this).next().html()
	text = text.replaceAll("<br>", "\n").replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;", "\t").replaceAll("&lt;", "<").replaceAll("&gt;", ">");
	const textarea = document.createElement('textarea');
	textarea.textContent = text;
	document.body.append(textarea);
	textarea.select();
	document.execCommand('copy');
	textarea.remove();
})


//파일 테이블 파일 크기를 처리하는 함수
let fileSize = $('.file_size')
$(fileSize).each(function(index, item){
	let size = Number($(this).text());
	//kb처리
	let sizekb = size / 1024;
	//mb처리
	let sizeMb = sizekb / 1024;
	
	//mb, kb, byte순으로 처리한다.
	if (size >= (1024 * 1024)){
		$(this).text(sizeMb.toFixed(2) + " Mb");
	}else if (size >= (1024)){
		$(this).text(sizekb.toFixed(2) + " kb");
	}else {
		$(this).text(size + " byte");
	}
})


//화면이 790이하라면 파일이름이 너무 긴 파일을 ellipsis처리
$(window).resize(function(){
	var windowWidth = $( window ).width();
	//윈도의 화면의 변화를 감지해 790 이하라면
	if(windowWidth < 790){
		$(function(){
			//변화시킬 파일들을 가져온다.
		    let elem = $('.file_ori_name');
		    //보여줄 텍스트의 길이
		    const showText = 10;
		    //요소들을 each메소드로 돌려서
		    elem.each(function(i){
				//요소의 html을 들고온다.
		        let content = $(this).html();
		        let contentText = $(this).text();
		        let contentLeng = content.length;
		        let lessText = content.substr(0, showText);
		        let moreText = content.substr(showText, contentLeng);
		        //만약 ...이 포함되어있지 않다면 글자수를 줄여야된다.
		        if(!contentText.includes("...")){
					//text를 줄여서 적용시킨다.
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
	}else{ //790 이상이라면 다시 원래 길이대로 돌려준다.
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

//텍스트가 2개이상이고 글자의 길이가 너무 길다면 파일과 마찬가지로 text ellipsis처리
$(function(){
	//원하는 요소 가져오기
    let elem = $('.table_txt');
    const showText = 80;
    let moreBtnText = "Show More";

    elem.each(function(i){
        let content = $(this).html();
        //만약 너무 많은 줄바꿈이 있을 시를 대비
        let contentmad = $(this).html().split("<br>")
        console.log(contentmad[0].length)
        console.log(contentmad.length)
        let changeHtml = '';
		
        let contentLeng = content.length;
        let lessText = content.substr(0, showText);
        let moreText = content.substr(showText, contentLeng);
        //80자 이상이라면 80자까지의 글자만 보여주고 텍스트 처리
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
        //만약 한글자씩 100줄의 글을 적는다면 첫번째, 두번재 줄만 보여주고 텍스트 처리
        if(contentmad[0].length < 10 && contentmad.length > 5){
			changeHtml = `${contentmad[0]}<br>${contentmad[1]} <span>...</span><span class="more-content"><span></span><a href="#table_modal${i}"type="button" class="more-btn active"><span>${moreBtnText}</span></a></span>`;
            $(this).html(changeHtml);
		}
    });
    
});
  
