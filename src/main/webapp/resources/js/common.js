var url = $(location).attr('href');
var file_main = $()
console.log(url)
word = url.split("/")
word = word[word.length - 1]
if(word.includes("boardmain") || word.includes("post")){
	word = "boardmain"
}else if(word.includes("download")){
	word = "download"
}
console.log(word)
console.log(url.includes(word))
$('.nav_link').each(function(index, item){
	if($(item).hasClass(word) == true){
		$('i', this).css("color", "white");
	}
})



