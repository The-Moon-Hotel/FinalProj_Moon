/**
 * js/signUp.js
 */
 $(function(){
	$('#name').focus();
	
	$('#sub1').click(function(){
		if(!$('#chkAgree').is(':checked')){
			alert("약관에 동의해야합니다.");
			$('#chkAgree').focus();
			event.preventDefault()
		}else if($('#name').val().length<1){
			alert('이름을 입력하세요');
			$('#name').focus();
			event.preventDefault();
		}else if(!validate_userid($('#userid').val())){
			alert('아이디는 영문, 숫자, _(밑줄문자)만 가능합니다.');
			$('#userid').focus();
			event.preventDefault();			
		}else if($('#pwd').val().length<1){
			alert('비밀번호를 입력하세요');
			$('#pwd').focus();
			event.preventDefault();
		}else if($('#pwd').val()!=$('#pwd2').val()){
			alert('비밀번호가 일치하지 않습니다.');
			$('#pwd2').focus();
			event.preventDefault();
		}else if(!validate_phone($('#tel').val())){
			alert('전화번호는 숫자와 '-'만 가능합니다.');
			$('#hp2').focus();
			event.preventDefault();
		}else if($('#chkId').val()!='Y'){
			alert('아이디 중복확인을 하세요');
			$('#btnChkId').focus();
			event.preventDefault();
		}else if(!$('#aggrement').is(':checked')){
			alert("개인정보 수집 및 이용에 동의해야합니다.");
			$('#aggrement').focus();
			event.preventDefault()
		}
	});
	$('#btnChkId').click(function(){
    	var id=$('#userid').val(); 		
	    open('checkUserid.jsp?userid='+$('#userid').val(),'chk',
	    			'width=400,height=300,left=0, top=0, location=yes, resizable=yes');
    });
 });
function validate_phone(tel){
	 var pattern=new RegExp(/^[0-9]*$/g);
	 return pattern.test(tel);
}

function validate_userid(id){
	 var pattern = new RegExp(/^[a-zA-Z0-9_]+$/g);
	 return pattern.test(id);
}