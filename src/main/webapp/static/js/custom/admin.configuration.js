(function($){
	tile = function() {
		alert("hello");
	};
})(jQuery);
$(document).ready(function(){
	//Sidebar Accordion Menu:	
	$("#main-nav li a.current").parent().parents("li").find("a.nav-top-item").addClass("current");
	// Hide all sub menus
	
	$("#main-nav li ul").hide(); 
	// Hide all sub menus
	
	$("#main-nav li a.current").parent().find("ul").slideToggle("slow"); 
	// Slide down the current menu item's sub menu
	
	// When a top menu item is clicked...
	$("#main-nav li a.nav-top-item").click( 
		function () {
			$(this).parent().siblings().find("ul").slideUp("normal"); // Slide up all sub menus except the one clicked
			$(this).next().slideToggle("normal"); // Slide down the clicked sub menu
			return false;
		}
	);		
	
	// When a menu item with no sub menu is clicked...
	$("#main-nav li a.no-submenu").click( 
		function () {
			window.location.href=(this.href); // Just open the link instead of a sub menu
			return false;
		}
	); 
	
	// Sidebar Accordion Menu Hover Effect:
	$("#main-nav li .nav-top-item").hover(
		function () {
			$(this).stop().animate({ paddingRight: "25px" }, 200);
		}, 
		function () {
			$(this).stop().animate({ paddingRight: "15px" });
		}
	);
	
	//Minimize Content Box
	$(".content-box-header h3").css({ "cursor":"s-resize" }); // Give the h3 in Content Box Header a different cursor
	$(".closed-box .content-box-content").hide(); // Hide the content of the header if it has the class "closed"
	$(".closed-box .content-box-tabs").hide(); // Hide the tabs in the header if it has the class "closed"
		
	$(".content-box-header:not(.column-fix) h3").click( // When the h3 is clicked...
			function () {
				$(this).parent().next().toggle(); // Toggle the Content Box
				$(this).parent().parent().toggleClass("closed-box"); // Toggle the class "closed-box" on the content box
				$(this).parent().find(".content-box-tabs").toggle(); // Toggle the tabs
		}
	);

    // Content box tabs:
	$('.content-box .content-box-content div.tab-content').hide(); // Hide the content divs
	$('ul.content-box-tabs li a.default-tab').addClass('current'); // Add the class "current" to the default tab
	$('.content-box-content div.default-tab').show(); // Show the div with class "default-tab"
		
	$('.content-box ul.content-box-tabs li a').click( // When a tab is clicked...
		function() { 
			$(this).parent().siblings().find("a").removeClass('current'); // Remove "current" class from all tabs
			$(this).addClass('current'); // Add class "current" to clicked tab
			var currentTab = $(this).attr('href'); // Set variable "currentTab" to the value of href of clicked tab
			$(currentTab).siblings().hide(); // Hide all content divs
			$(currentTab).show(); // Show the content div with the id equal to the id of clicked tab
			return false; 
		}
	);

    //Close button:
	$(".close").click(
		function () {
			$(this).parent().fadeTo(400, 0, function () { // Links with the class "close" will close parent
				$(this).slideUp(400);
			});
			return false;
		}
	);
	
});

;(function($) {
	$.extend({		
		loadAllObject: function(url, page_index, handleData) {
			$.ajax({
		    	type: "GET",
		    	url: url,
		        dataType: "json",   
		        timeout: 1000,     
		        beforeSend:function(){
		            $('.loading').showLoading();
		        },
		        data: "page="+page_index,
		        success: handleData,
		        error: function (XMLHttpRequest, textStatus, errorThrown) {
		         	alert(errorThrown);
		        }
		   	});
			return false;
		},
		
		getTotalObject: function(url, data, handleData) {
			$.ajax({
		    	type: "GET",
		    	url: url,
		        dataType: "json",   
		        data: data,
		        success: handleData,
		        error: function (XMLHttpRequest, textStatus, errorThrown) {
		         	alert(errorThrown);
		        }
		   	});
			return false;
		},
		
		//curd operations
		loadObject: function(url, id, handleData){
			$.ajax({
				type: "GET",
				url: url + id,
				dataType: "json",  
				contentType: "application/json; charset=utf-8",
				success: handleData,
				error: function (XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
			return false;
		},
		
		addNewObject: function(url, jsonData, handleData) {
	    	$.ajax({
	    		type: "POST",
	    		url: url,
	            dataType: "json",
	            contentType: "application/json; charset=utf-8",
	            data: jsonData,
	            success: handleData,
	            error: function (XMLHttpRequest, textStatus, errorThrown) {
	                  alert(errorThrown);
	            }
	        });
	        return false;
	    },
		updateObject : function(url, id, jsonData, handleData) {
			$.ajax({
				type: "PUT",
				url: url+id,
				dataType: "json",  
				contentType: "application/json; charset=utf-8",
				data: jsonData,
				success: handleData,
				error: function (XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
			return false;
		},	
		deleteObject: function(url, id, pageindex, handleData){
			$.ajax({
				type: "DELETE",
				url: url+id,
				dataType: "json",  
				contentType: "application/json; charset=utf-8",
				success: handleData,
				error: function (XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
			return false;
		}
	});
})(jQuery);
