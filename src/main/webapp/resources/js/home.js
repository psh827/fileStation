$(document).ready(function() {
    $("#input_file").bind('change', function() {
        selectFile(this.files);
        //this.files[0].size gets the size of your file.
        //alert(this.files[0].size);
    });
    $(".textInput").on("change keyup paste", function(){
        $(this).css("line-height", "1")
        if($(".textInput").val() == ""){
            $(this).attr("placeholder", "TEXT")
            $(this).css("font-size", "30px").css("font-weight", "bold").css("line-height", "365px")
        }
    })
});

$(".delete_all").on("click", function(){
    let textarea = $(".textInput")
    textarea.val("")
    textarea.attr("placeholder", "TEXT")
    textarea.css("font-size", "30px").css("font-weight", "bold").css("line-height", "365px")
})
// 파일 리스트 번호
var fileIndex = 0;
// 등록할 전체 파일 사이즈
var totalFileSize = 0;
// 파일 리스트
var fileList = new Array();
// 파일 사이즈 리스트
var fileSizeList = new Array();
// 등록 가능한 파일 사이즈 MB
var uploadSize = 50;
// 등록 가능한 총 파일 사이즈 MB
var maxUploadSize = 500;

var uploadFiles = [];
$(function() {
    // 파일 드롭 다운
    fileDropDown();
});

// 파일 드롭 다운
function fileDropDown() {
    var dropZone = $("#dropZone");
    //Drag기능 
    dropZone.on('dragenter', function(e) {
        e.stopPropagation();
        e.preventDefault();
        // 드롭다운 영역 css
        dropZone.css('background-color', '#E3F2FC');
    });
    dropZone.on('dragleave', function(e) {
        e.stopPropagation();
        e.preventDefault();
        // 드롭다운 영역 css
        dropZone.css('background-color', '#F8F5FF');
    });
    dropZone.on('dragover', function(e) {
        e.stopPropagation();
        e.preventDefault();
        // 드롭다운 영역 css
        dropZone.css('background-color', '#E3F2FC');
    });
    dropZone.on('drop', function(e) {
        e.preventDefault();
        // 드롭다운 영역 css
        dropZone.css('background-color', '#FFFFFF');

        var files = e.originalEvent.dataTransfer.files;
        
        if (files != null) {
            if (files.length < 1) {
                /* alert("폴더 업로드 불가"); */
                console.log("폴더 업로드 불가");
                return;
            } else {
                selectFile(files)
            }
        } else {
            alert("ERROR");
        }
    });
}

// 파일 선택시
function selectFile(fileObject) {
    var files = null;

    if (fileObject != null) {
        // 파일 Drag 이용하여 등록시
        files = fileObject;
    } else {
        // 직접 파일 등록시
        files = $('#multipaartFileList_' + fileIndex)[0].files;
    }

    // 다중파일 등록
    if (files != null) {
        
        if (files != null && files.length > 0) {
            $("#fileDragDesc").hide(); 
            $("fileListTable").show();
        } else {
            $("#fileDragDesc").show(); 
            $("fileListTable").hide();
        }
        
        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            // 파일 이름
            var fileName = files[i].name;
            var fileNameArr = fileName.split("\.");
            // 확장자
            var ext = fileNameArr[fileNameArr.length - 1];
            
            var fileSize = files[i].size; // 파일 사이즈(단위 :byte)
            console.log("fileSize="+fileSize);
            if (fileSize <= 0) {
                console.log("0kb file return");
                return;
            }
            
            var fileSizeKb = fileSize / 1024; // 파일 사이즈(단위 :kb)
            var fileSizeMb = fileSizeKb / 1024;    // 파일 사이즈(단위 :Mb)
            
            var fileSizeStr = "";
            if ((1024*1024) <= fileSize) {    // 파일 용량이 1메가 이상인 경우 
                console.log("fileSizeMb="+fileSizeMb.toFixed(2));
                fileSizeStr = fileSizeMb.toFixed(2) + " Mb";
            } else if ((1024) <= fileSize) {
                console.log("fileSizeKb="+parseInt(fileSizeKb));
                fileSizeStr = parseInt(fileSizeKb) + " kb";
            } else {
                console.log("fileSize="+parseInt(fileSize));
                fileSizeStr = parseInt(fileSize) + " byte";
            }

            /* if ($.inArray(ext, [ 'exe', 'bat', 'sh', 'java', 'jsp', 'html', 'js', 'css', 'xml' ]) >= 0) {
                // 확장자 체크
                alert("등록 불가 확장자");
                break; */
            if ($.inArray(ext, [ 'hwp', 'doc', 'docx', 'xls', 'xlsx', 'ppt', 'pptx', 'txt', 'png', 'pdf', 'jpg', 'jpeg', 'gif', 'zip', 'css', 'js', 'jsp', 'py', 'svg', 'tiff', 'ai', 'bmp', 'mp3', 'mp4' ]) <= 0) {
                // 확장자 체크
                /* alert("등록이 불가능한 파일 입니다.");
                break; */
                alert("등록이 불가능한 파일 입니다.("+fileName+")");
            } else if (fileSizeMb > uploadSize) {
                // 파일 사이즈 체크
                alert("용량 초과\n업로드 가능 용량 : " + uploadSize + " MB");
                break;
            } else {
                // 전체 파일 사이즈
                totalFileSize += fileSizeMb;

                // 파일 배열에 넣기
                fileList[fileIndex] = files[i];

                // 파일 사이즈 배열에 넣기
                fileSizeList[fileIndex] = fileSizeMb;

                // 업로드 파일 목록 생성
                if(file.type.split("/")[0] == "image"){
                    var size = uploadFiles.push(file); //업로드 목록에 추가
                    preview(file, fileIndex); //미리보기 만들기
                } else {
                    addFileList(fileIndex, fileName, fileSizeStr);
                }

                // 파일 번호 증가
                fileIndex++;
            }
        }
    } else {
        alert("ERROR");
    }
}

function preview(file, fileIndex) {
    var reader = new FileReader();
    reader.onload = (function(f, idx) {
        return function(e) {
        var div = '<li id="fileTr_' + fileIndex + '" class="thumb fileLi fileImg"> \
        <a class="file-item"><img style="width: 100%; height:100%;" src="' + e.target.result + '" title="' + escape(f.name) + '"/></a> \
        <input value="삭제" class="file_input" type="button" href="#" onclick="deleteFile(' + fileIndex + '); return false;"></li>';
        $("#fileTableTbody").append(div);
    };
})(file, fileIndex);

reader.readAsDataURL(file);

}

// 업로드 파일 목록 생성
function addFileList(fIndex, fileName, fileSizeStr) {
    /* if (fileSize.match("^0")) {
        alert("start 0");
    } */

    var html = "";
    html += "<li id='fileTr_" + fIndex + "' class='fileLi'>";
    html += "    <a class='file-item left' >";
    html +=  "<span class='file_name'>" + fileName + "</span><span class='file_size'>(" + fileSizeStr +")</span>" 
            + "<input value='삭제' class='file_input' type='button' href='#' onclick='deleteFile(" + fIndex + "); return false;'>"
    html += "    </a>"
    html += "</li>"

    $('#fileTableTbody').append(html);
}

// 업로드 파일 삭제
function deleteFile(fIndex) {
    console.log("deleteFile.fIndex=" + fIndex);
    // 전체 파일 사이즈 수정
    totalFileSize -= fileSizeList[fIndex];

    // 파일 배열에서 삭제
    delete fileList[fIndex];

    // 파일 사이즈 배열 삭제
    delete fileSizeList[fIndex];

    // 업로드 파일 테이블 목록에서 삭제
    $("#fileTr_" + fIndex).remove();
    
    console.log("totalFileSize="+totalFileSize);
    
    if (totalFileSize > 0) {
        $("#fileDragDesc").hide(); 
        $("fileListTable").show();
    } else {
        $("#fileDragDesc").show(); 
        $("fileListTable").hide();
    }
}

// 파일 등록
function uploadFile() {
    // 등록할 파일 리스트
    var uploadFileList = Object.keys(fileList);
    
    //비밀번호 체크
    var password = $('.input_passwd').val();

	//텍스트 체크
	var textarea = $('.textInput').val();

    // 파일이 있는지 체크
    if (uploadFileList.length == 0 && textarea == "") {
        // 파일등록 경고창
        alert("파일이 없습니다.");
        return;
    }

    // 비밀번호가 있는지 체크 
    if(password == ""){
        alert("비밀번호를 입력하세요");
        return;
    }

    if(password.length <= 7){
        alert("비밀번호는 8자리 이상입니다.");
        return;
    }
    

    // 용량을 500MB를 넘을 경우 업로드 불가
    if (totalFileSize > maxUploadSize) {
        // 파일 사이즈 초과 경고창
        alert("총 용량 초과\n총 업로드 가능 용량 : " + maxUploadSize + " MB");
        return;
    }

    if (confirm("등록 하시겠습니까?")) {
        // 등록할 파일 리스트를 formData로 데이터 입력
        var form = $('#uploadForm');
        console.log(form)
        var formData = new FormData(form[0]);
        for (var i = 0; i < uploadFileList.length; i++) {
            formData.append('files', fileList[uploadFileList[i]]);
        }
        formData.append("passwd", password);
        formData.append("textarea", textarea);

        $.ajax({
            url : "addFile",
            data : formData,
            type : 'POST',
            enctype : 'multipart/form-data',
            processData : false,
            contentType : false,
            dataType : 'json',
            cache : false,
            success : function(result) {
				console.log(result)
                if (result.data.length > 0) {
                    alert("성공");
                } else {
                    alert("실패");
                    location.reload();
                }
            }
        });
		alert("업로드 성공되었습니다!")
		location.reload();
    }
}

$(".textInput").on("click", function(){
    $(this).attr("placeholder", "")
    $(this).css("font-size", "16px").css("font-weight", "normal")
})
