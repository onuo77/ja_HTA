<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>Bootstrap 4 Example</title>
 	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-12">
			<form id="register-form" action="register" method="post" class="border p-3 bg-light">
				<div class="form-group">
					<label>아이디</label>
					<input type="text" class="form-control" id="user-id" name="id" />
				</div>
				<div class="form-group">
					<label>비밀번호</label>
					<input type="password" class="form-control" id="user-pwd" name="password" />
				</div>
				<div class="form-group">
					<label>비밀번호 확인</label>
					<input type="password" class="form-control" id="user-pwd-confirm" name="passwordConfirm" />
				</div>
				<div class="form-group">
					<label>경력사항</label>
					<select class="form-control" name="career">
						<option value=""> 선택하세요</option>
						<option value="0"> 신입</option>
						<option value="1"> 1년 이상</option>
						<option value="3"> 3년 이상</option>
						<option value="5"> 5년 이상</option>
						<option value="7"> 7년 이상</option>
					</select>
				</div>
				<div class="form-group">
					<label>보유기술</label>
					<div class="form-group form-check">
						<div class="form-check form-check-inline">
							<input type="checkbox" class="form-check-input" id="skill1-1" name="skill1" value="java">
							<!-- <label class="form-check-label" onclick="checkSkill(event)">자바</label> -->
							<label class="form-check-label" onclick="checkSkill('skill1-1')">자바</label>
						</div>
						<div class="form-check form-check-inline">
							<input type="checkbox" class="form-check-input" id="skill1-2" name="skill" value="db">
							<!-- <label class="form-check-label" onclick="checkSkill(event)">데이터베이스</label> -->
							<label class="form-check-label" onclick="checkSkill('skill1-2')">데이터베이스</label>
						</div>
						<div class="form-check form-check-inline">
							<input type="checkbox" class="form-check-input" id="skill1-3" name="skill" value="vue">
							<!-- <label class="form-check-label" onclick="checkSkill(event)">Vue</label> -->
							<label class="form-check-label" onclick="checkSkill('skill1-3')">Vue</label>
						</div>
						<div class="form-check form-check-inline">
							<input type="checkbox" class="form-check-input" id="skill1-4" name="skill" value="spring">
							<!-- <label class="form-check-label" onclick="checkSkill(event)">스프링프레임워크</label> -->
							<label class="form-check-label" onclick="checkSkill('skill1-4')">스프링프레임워크</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label>기타사항</label>
					<textarea rows="5" class="form-control" id="etc" name="etc"></textarea>
				</div>
				<div class="form-group form-check">
					<input type="checkbox" class="form-check-input" id="check-me">
					<label class="form-check-label">개인정보 제공 동의</label>
				</div>
				<div class="text-right">
					<button type="button" class="btn btn-primary" onclick="checkForm()">등록</button>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
/*
	function checkSkill(event){
		//console.log(event.target);
		var labelEl = event.target; //event.target은 이벤트가 발생한 엘리먼트를 반환한다.
		var checkBoxEl = labelEl.previousElementSibling;
		checkBoxEl.checked = !checkBoxEl.checked;
	}
*/
	function checkSkill(skillId){
		var checkBoxEl = document.getElementById(skillId);
		checkBoxEl.checked = !checkBoxEl.checked;
	}
	
	function checkForm(){
		//아이디 입력필드의 유효성 체크하기
		var userIdEl = document.getElementById("user-id");	//엘리먼트객체 획득
		//var userId = userIdEl.value; //element객체.value *폼요소, 입력필드일 때만 사용가능
		if(!userIdEl.value.trim()){ //거짓-false, '', 0, null, NaN, undefined
			alert("아이디는 필수입력값입니다.");
			userIdEl.focus();
			return;
		}
		if(userIdEl.value.trim().length < 4){
			alert("아이디는 4글자 이상으로 입력해야 합니다.");
			userIdEl.focus();
			return;
		}
		if(!/^[a-zA-Z0-9]+$/.test(userIdEl.value.trim())) {
			alert("아이디는 영어대소문자, 숫자만 허용됩니다.");
			userIdEl.focus();
			return;
	    }
		//비밀번호 유효성 체크(필수입력값/글자길이 검사는 생략)
		var passwordEl = document.getElementById("user-pwd");
		var passwordConfirmEl = document.getElementById("user-pwd-confirm");
		if(passwordEl.value != passwordConfirmEl.value){
			alert("비밀번호가 서로 일치하지 않습니다.");
			passwordConfirmEl.value = "";
			passwordConfirmEl.focus();
			return;
		}
		//경력사항을 선택했는지 체크
		var careerEl = document.querySelector("select[name='career']");//검색된 엘리먼트 중 첫번재 엘리먼트 반환
		if(!careerEl.value){
			alert("경력사항을 선택하세요");
			careerEl.focus();
			return;
		}
		//보유기술을 하나이상 체크했는지 체크
		var skillEls = document.querySelectorAll("input[name='skill']");//검색된 엘리먼트 전부를 배열에 담아서 반환
		var isChecked = false;
		for(var i=0; i<skillEls.length; i++){
			var skillEl = skillEls[i];
			//console.log(skillEl.value, skillEl.checked); 
			if(skillEl.checked){ //체크박스, 라디오버튼의 체크여부는 checked 프로퍼티로 확인한다.
				isChecked = true;
				break;
			}
		}
		if(!isChecked){
			alert("보유기술을 하나이상 선택하십시오");
			return;
		}
		
		//기타사항에 내용이 입력되어있는지 체크
		var etcEl = document.querySelector("#etc");
		if(!etcEl.value){
			var confirmValue = confirm("기타사항에 입력한 내용이 하나도 없는데, 괜찮아?");
			//console.log(confirmValue); //확인하면 true 취소하면 false
			if(!confirmValue){
				etcEl.focus();
				return;
			}
		}
		
		//개인정보 동의여부에 체크했는지 체크
		var checkMe = document.querySelector("#check-me").checked;
		if(!checkMe){
			alert("개인정보 동의여부는 반드시 체크해야 합니다.");
			return;
		}
		
		//모든 폼 입력값이 유효한 입력값이므로 서버로 제출되게 한다.
		document.getElementById("register-form").submit();//submit()는 element가 form일 경우에만 가능함!!
	}
</script>
</body>
</html>