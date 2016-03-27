$(document).ready(function(){
	$("#articleForm").validate({
   		errorElement :"span",
   		errorClass :"validation",

   		//submitHandler: function() { alert(messages) },
		rules: {
			title: {
				required: true,
				minlength: 4
			},
			user: "required",
			category: "required",
			tags: "required",
			content: {
				required: true,
				minlength: 20
			}
		},
		highlight: function(element, errorClass) {
        	$(element).removeClass(errorClass);
    	},
		messages: {
			title: "Please enter your title",
			user: "Please select your name",
			category: "Please select your category",
			tags: "Please enter your tag",
			content: {
				required: "Please enter your content",
				minlength: "Your username must consist of at least 5 characters"
			}
		}
	});
	$("#btnLogin").click(function(){
		if($("form#name").val()=="") {
        	alert("用户名不为空！");
        	$('form#name').focus();
        	return false;
    	}
    	if($("#passwd").val()=="") {
        	alert("密码不为空！");
        	$("#txtPassword").focus();
        	return false;
    	}
    	$.ajax({
    		type: "post",
    		url: "/login",
            dataType: "json",
            data: {"name": $("#name").val(), "passwd":enc($("#passwd").val())},
            success: function (data) {
            	if(data.logged){
                	window.location = '/admin';
            	}else{
            		alert("登录失败!!!");
            		window.location='/login';
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                  alert(errorThrown);
            }
        });
        return false;
    });
});