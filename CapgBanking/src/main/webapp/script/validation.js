function validate(){
	var flag=false;
	var userName=f1.userName.value;
	var userPass=f1.userPwd.value;
	
	if(userName==""||userName==null){
		document.getElementById('userErrMsg').innerHTML="*Please enter UserName.";
		flag=false;
	}else if(userPass==""||userPass==null){
		document.getElementById('pwdErrMsg').innerHTML="*Please enter Password.";
		document.getElementById('userErrMsg').innerHTML="";
		flag=false;
	}else{
		document.getElementById('pwdErrMsg').innerHTML="";
		document.getElementById('userErrMsg').innerHTML="";
		flag=true;
	}
	
	return flag;
}
//function validate_Acc(){
//	var flag=false;
//	var openingbal=f2.
//}
function performTrans(){
	var flg=false;
	
}