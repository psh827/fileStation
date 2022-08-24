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