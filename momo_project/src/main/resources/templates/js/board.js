var sel_files = [];

$(document).ready(function () {
    $("#input_img").on("change", handleImgFilesSelect);
});

function fileUploadAction() {
    console.log("fileUploadAction");
    $("#input_imgs").trigger('click');
}

function handleImgFilesSelect(e) {
    //이미지 정보들을 초기화
    sel_files = [];
    $(".imgs_wrap").empty();

    var files = e.target.files;
    var filesArr = Array.prototype.slice.call(files);

    var index = 0;
    filesArr.forEach(function (f) {
        if (!f.type.match("image.*")) {
            alert("확장자는 이미지 확장자만 가능합니다.");
            return;
        }
        sel_files.push(f);

        var reader = new FileReader();
        reader.onload = function (e) {
            var html = "CONTENT";
            $(".imgs_wrap").append(html);
            index++;
        }
        reader.readAsDataURL(f);
    });
}