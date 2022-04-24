

const memNum = $("#memNum");
const memEmail = $("#memEmail");
const memNickname = $("#memNickname");
let memNicknameCheck = 1;
let memEmailCheck = 1;

function withdrawalSubmit() {
    if(!confirm("탈퇴 하시겠습니까?")){
        return false;
    }else {
        $.ajax({
            type: "delete",
            url: "/api/admin/withdrawal",
            data: {"id": id.val()},
            success: function () {
                alert("회원 탈퇴처리가 완료되었습니다.");
                window.location.href='/admin';

            },
            error: function (request, status, error) {
                alert("ajax 실행 실패");
                alert("code:" + request.status + "\n" + "error :" + error);

            }
        });
    }

}

function checkBirthEdit() {
    const birth_check_msg = $("#birth_check_msg");
    if ($("#year").val() == "") {
        birth_check_msg.html("태어난 년도를 선택해주세요");
        birth_check_msg.css("color", "red");
        return false;
    } else if ($("#month").val() == "") {
        birth_check_msg.html("태어난 월을 선택해주세요");
        birth_check_msg.css("color", "red");
        return false;
    } else if ($("#day").val() == "") {
        birth_check_msg.html("태어난 날짜(일)를 선택해주세요");
        birth_check_msg.css("color", "red");
        return false;
    } else {
        birth_check_msg.html("");
    }
    return true;
}


function modifyCheckAll(){
    
    if(nickname.val()==""){
        alert("닉네임을 입력해주세요!. 필수항목입니다.");
        nickname.focus();
        return false;
  
    } else if (nicknameCheck == 0){
        alert("닉네임을 확인해 주세요!");
        return false;
    } else if(!blankModify()){
        return false;
    } else if (!confirm("정보를 수정하시겠습니까?")){
        return false;
    } alert("수정되었습니다. 다시로그인해주세요");
    location.href="/account/logout";
    return true;
}

function memNicknameEdit(){

    $.ajax({
        type :"get",
        url :"/member/nicknameEdit",
        data : {"memId" : $('#memId').val(), "memNickname" : $("memNickname").val()},
        dataType : "JSON",
        success : function(result){
            if(result.result == true){
                $('.nickname_ok').css({"display" : "inline-block","color" : "blue"});
                $('.nickname_already').css("display", "none");
                nicknameCheck = 1;
                console.log(result.result);
            }else{
                $('.nickname_ok').css("display","none");
                $('.nickname_already').css({"display" :"inline-block", "color" : "red"});
                nicknameCheck = 0;
            }
        },
        error : function(){
            alert("ajax 실행 실패");
        }
    });
}

function withdrawal(){
	if(confirm("모든 정보가 사라집니다. 탈퇴 하시겠습니까?")){
		 actionForm
                    .attr("action", "/member/withdrawal")
                    .attr("method","post")
                    .submit();
                    
         alert("탈퇴가 완료되었습니다.")
         }
       else{
			return "/member/mypage";
	
}
		
	}
	


