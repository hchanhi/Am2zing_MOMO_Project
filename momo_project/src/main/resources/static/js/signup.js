function checkAll() {
  if (memEmailOverlapCheck == 0) {
    alert('아이디 중복체크를 해주세요');
    return false;
  } else if (!pwSame()) {
    return false;
  } else if (memNicknameOverlapCheck == 0) {
    alert('닉네임 중복체크를 해주세요');
    return false;
  } else if (!checkAge()) {
    return false;
  } else if (
    !confirm('일부 정보는 수정이 불가합니다 \n회원가입을 진행하시겠습니까?')
  ) {
    return false;
  } else {
    alert($('#memEmail').val() + ' 님 회원가입이 완료되었습니다.');
  }
  return true;
}

let memEmailOverlapCheck = 0;
let memNicknameOverlapCheck = 0;

/*ajax csrf 토큰 설정*/
let token = $("meta[name='_csrf']").attr('content');
let header = $("meta[name='_csrf_header']").attr('content');
$(function () {
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });
});

// 이메일 중복 체크
function memEmailCheck() {
  const memEmail = $('#memEmail').val();
 console.log(memEmail);
  if (memEmail == '') {
    alert('이메일 입력해주세요!. 필수항목입니다.');
    $('#memEmail').focus();
    return false;
  }
  
  $.ajax({
    type: 'get',
    url: '/member/emailCheck',
    data: {"memEmail": memEmail},
    dataType: 'JSON',

    success: function (result) {
      if (result.result == '0') {
        if (confirm('이 아이디는 사용 가능합니다. \n사용하시겠습니까?')) {
          memEmailOverlapCheck = 1;
          $('#memEmail').attr('readonly', true);
          $('#memEmailOverlay').attr('disabled', true);
          $('#memEmailOverlay').css('display', 'none');
          $('#resetMemEmail').attr('disabled', false);
          $('#resetMemEmail').css('display', 'inline-block');
        }
        return false;
      } else if (result.result == '1') {
        alert('이미 사용중인 아이디입니다.');
        $('#memEmail').focus();
      } else {
        alert('success이지만 result 값이 undefined 잘못됨');
    }
    },
    error: function (request, status, error) {
      alert('ajax 실행 실패');
      alert('code:' + request.status + '\n' + 'error :' + error);
    },
  });
}

function pwSame() {
  const password = $('#memPassword').val();
  const password_check = $('#memPassword_Check').val();
  const pw_check_msg = $('#pw_check_msg');
  //패스워드 입력되었는지 확인하기
  if (password == '') {
    alert('패스워드를 입력해주세요!. 필수항목입니다.');
    $('#memPassword').focus();
    return false;
  }
  //패스워드 조건 확인
  if (password.length < 6) {
    alert('패스워드는 6글자 이상이어야 합니다.');
    $('#memPassword').focus();
    return false;
  }
  //패스워드 일치 확인
  if (password != '' && password_check != '') {
    if (password == password_check) {
      pw_check_msg.html('비밀번호가 일치합니다.');
      pw_check_msg.css('color', 'blue');
    } else {
      pw_check_msg.html('비밀번호가 다릅니다. 다시 확인해 주세요.');
      pw_check_msg.css('color', 'red');
      return false;
    }
  }
  return true;
}

// 닉네임 중복 체크
function memNicknameCheck() {
  const memNickname = $('#memNickname').val();
  if (memNickname == '') {
    alert('닉네임을 입력해주세요!. 필수항목입니다.');
    $('#memNickname').focus();
    return false;
  }
  $.ajax({
    type: 'get',
    url: '/member/NicknameChk',
    data: { "memNickname": memNickname },
    dataType: 'JSON',
    
    success: function (result) {
      if (result.result == '0') {
        if (confirm('이 이름은 사용 가능합니다. \n사용하시겠습니까?')) {
          memNicknameOverlapCheck = 1;
          $('#memNickname').attr('readonly', true);
          $('#memNicknameOverlay').attr('disabled', true);
          $('#memNicknameOverlay').css('display', 'none');
          $('#resetMemNickname').attr('disabled', false);
          $('#resetMemNickname').css('display', 'inline-block');
        }
        return false;
      } else {
        alert('이미 사용중인 이름입니다.');
        $('#memNickname').focus();
      }
    },
    error: function (request, status, error) {
      alert('ajax 실행 실패');
      alert('code:' + request.status + '\n' + 'error :' + error);
    },
  });
}

function checkAge() {
  const birth_check_msg = $('#birth_check_msg');
  if ($('#year').val() == '') {
    birth_check_msg.html('태어난 년도를 선택해주세요');
    birth_check_msg.css('color', 'red');
    return false;
  } else if ($('#month').val() == '') {
    birth_check_msg.html('태어난 월을 선택해주세요');
    birth_check_msg.css('color', 'red');
    return false;
  } else if ($('#day').val() == '') {
    birth_check_msg.html('태어난 날짜(일)를 선택해주세요');
    birth_check_msg.css('color', 'red');
    return false;
  } else {
    birth_check_msg.html('');
  }
  return true;
}



function reMemEmail() {
  usernameOverlapCheck = 0;
  $('#memEmail').attr('readonly', false).focus();
  $('#memEmail').val('');
  $('#memEmailOverlay').attr('disabled', false);
  $('#memEmailOverlay').css('display', 'inline-block');
  $('#resetMemEmail').attr('disabled', true);
  $('#resetMemEmail').css('display', 'none');
}

function reNickname() {
  nicknameOverlapCheck = 0;
  $('#memNickname').attr('readonly', false).focus();
  $('#memNickname').val('');
  $('#memNicknameOverlay').attr('disabled', false);
  $('#memNicknameOverlay').css('display', 'inline-block');
  $('#resetMemNickname').attr('disabled', true);
  $('#resetMemNickname').css('display', 'none');
}
