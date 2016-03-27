$(document).ready(function(){
    $('#change_image').click(function(){
		$.get("validateCode?t="+Math.random(), function(data){
			$("#imgCheckCode").attr('src', 'validateCode?t='+Math.random());
		});
		return false;
	}); 
	$("#commentform").validate({
   		//errorElement :"span",
   		//errorClass :"validation",
   		errorPlacement: function(error, element) {
			error.appendTo(element.next());
		},
   		//submitHandler: function() { alert(messages) },
		rules: {
			submitter: {
				required: true,
				minlength: 4
			},
			email: {
				required: true,
				email: true
			}
		},
		highlight: function(element, errorClass) {
        	$(element).removeClass(errorClass);
    	},
		messages: {
			author: "&nbsp;",
			email: "&nbsp;",
			comment: "&nbsp;",
		},
		success: function(label) {
			// set &nbsp; as text for IE
			label.html("&nbsp;").addClass("checked");
		}
	});
	
	$('.PostContent img').each(function(){
		var $url = $(this)[0].src;
		$(this).wrap("<a></a>").parent('a').attr("href", $url).addClass("fancy");
	});
	
	$("a.fancy").fancybox();
	
});

$.validator.setDefaults({
	submitHandler: function() {
		$.ajax({
    		type: "post",
    		url: "node/postComment",
            dataType: "json",
            data: {	"submitter": $("#submitter").val(), 
        		"email":$("#email").val(), 
        	   	"postId":$("#postId").val(),
        	   	"content":$("textarea#commentText").val(),
        	   	"homepage":$("#homepage").val(),
        	   	"validateCode":$("#validateCode").val(),},
            success: function (data) {
            	if(!data.checkcodevalid){
            		alert(data.msg);
            	}
            	else{
            		$('#Comment').load(window.location.pathname+' #Comment', function(){
            		    $('#Comment').fadeIn('slow');
            		});
            	}
            	$("#author_info img").attr('src', 'validateCode?t='+Math.random());
            	$("#commentform")[0].reset();
            	$("label").removeClass("checked");
            	$("label").removeClass("checked");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                  alert(errorThrown);
            }
        });
        return false;
	}
});