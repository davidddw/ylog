jQuery.fn.category = function(opts){
	opts = jQuery.extend({
		num_entries:10,
		pagesize:10,
		current_page:0,
		show_edit: true,
		show_delete: true,
		show_more: true,
		get_all_url: "admin/category",
		load_url: "admin/category/",
		delete_url: "admin/category/",
		update_url: "admin/category/",
		callback:function(){return false;},	
	},opts||{});
	
	return this.each(function() {
		var header = '';
		header += '<thead id="headerInfo">';
		header += '<tr><th width="30px">#</th><th width="40%">cnName</th><th width="40%">enName</th>' +
			'<th width="70px">操作</th></tr>';
		header += '</thead>';
		header += '<tfoot><tr><td colspan="4"><div id="CPagination" class="pagination"></div><div class="clear"></div></td></tr></tfoot><tbody class="bodyInfo"></tbody>';
		$(this).empty().html(header);
		var initPagination = function(page_index, num_entries, pagesize, pageselectCallback) {
			// 创建分页
			$("#CPagination").pagination(num_entries, {
				num_edge_entries: 1, //边缘页数
				num_display_entries: 10, //主体页数
				callback: pageselectCallback,
				items_per_page: pagesize, //每页显示5项
				current_page: page_index,
				prev_text: "前一页",
				next_text: "后一页"
			});
		};
		
		var callback = function(page_index,jq){		
			var url = opts.get_all_url;
			$.loadAllObject(url, page_index, function(data){
	        	var content = '';  
	        	$.each(data.category, function(i, item) {
	        		content += '<tr id="category'+item.id + '"><td>'+item.id +'</td><td><a href="node/category/' + item.enName + '" title="title">' +
                		item.cnName + '</a></td><td>' + item.enName + '</td><td>';
	        		if(opts.show_edit) {
	       				content += '<a herf="" class="btn-link editcategory" id="' + item.id + '" title="Edit"><img src="static/admin/images/icons/hammer_screwdriver.png" alt="Edit" /></a>&nbsp;&nbsp;';
	       			}
	       			if(opts.show_delete) {
	       				content += '<a herf="" class="btn-link deletecategory" id="' + item.id + '" title="Delete"><img src="static/admin/images/icons/cross.png" alt="Delete"/></a>&nbsp;&nbsp;';
	       			}
	        		if(opts.show_more){
	        			content += '<a herf="" class="btn-link managearticle" id="'+ item.id + 
                		'" name="'+item.cnName+'"><img src="static/admin/images/icons/comments_add.png" alt="Manage Article" /></a> ';
	        		}
	        		content += '</td></tr>';
	        	});
	        	$('.bodyInfo').html(content);

	    		$('.loading').hideLoading();
	    		
	    		$('.check-all').on('click', function (){
	   			 	$(this).parent().parent().parent().parent().find("input[type='checkbox']").prop('checked', $(this).is(':checked'));   
	    		});
	   		
	    		$('tbody tr:even').addClass("alt-row");
	    		
	    		$('.editcategory').on('click', function (){
	        		loadCategory(this.id, page_index);
	        		return false; 
	        	});
	        		
	        	$('.deletecategory').on('click', function (){
	        		var myid = this.id;
	        		bootbox.confirm("确定删除么?", function(result) {
	        			if(result) {
	        				deleteCategory(myid, page_index);
	        			}
	        		});
	        		return false;
	        	});	
	        	
	        	$('.managearticle').on('click', function (){  
	        		$(".tabletop").hide();
	    			var $current = this.name;
	    			$(".breadcrumb li").first().append('<li><a href="admin/listCategory">' + $current + '</a><span class="divider">/</span></li>');
	    			$(".breadcrumb li.active").html("文章管理");
	    			
	    			var articleCount=0;
	    			$.getTotalObject('admin/getTotalArticleByCategory', 'id='+this.id, function(data){
	    				articleCount = data.count;
	    			});
	    			$('#loadingCategory').article({
	    				num_entries: articleCount,
	    				get_all_url: "admin/article?id="+this.id,
	    				show_more: false
	    			});
	    			//return false;
	    		});
			}); 
			return false;
		};
		
		initPagination(0, opts.num_entries, opts.pagesize, callback);
		
		function deleteCategory(id, page_index){
			var url = opts.delete_url;
			$.deleteObject(url, id, 0, function(data){
				if(data.result) {
					initPagination(page_index, opts.num_entries, opts.pagesize, callback);
					bootbox.alert("删除成功!!!");
				} else {
					bootbox.alert("删除失败!!!");
				}
			});
			return false;
		};

		function loadCategory(id, page_index) {
			var url = opts.load_url; 
			$.loadObject(url, id, function(data){
				updateCategoryDialog(data.id, page_index, data.enName, data.cnName);
			});
			return false;
		}
		
		function updateCategoryDialog(id, page_index, enName, cnName){
			var $myhtml = '<form class="modal-form"><fieldset>' +
				'<div class="inlines"><label>enName:</label><input type="text" class="input-xxlarge" name="enName" id="enName" value="'+ enName +'"></div>' +
				'<div class="inlines"><label>cnName:</label><input type="text" class="input-xxlarge" name="cnName" id="cnName" value="'+ cnName +'"></div>' +
				'</fieldset></form>';
		
			var cancelCallback = function() {
			    if (typeof cb === 'function') {
			        return cb(false);
			    }
			};
			
			bootbox.dialog($myhtml, [{
				"label" : "更新",
				"icon" : "icon-ok",
				"class" : "btn-success",
				"callback": function() {
					var url = opts.update_url;
					var jsonData = JSON.stringify({"id": id, "enName":$("#enName").val(), "cnName": $("#cnName").val()});
					$.updateObject(url, id, jsonData, function(data){
						initPagination(page_index, opts.num_entries, opts.pagesize, callback);
			        });
				}}, 
				{
					"label" : "取消",
					"icon" : "icon-off",
					"class" : "btn"
				}],{
		            "onEscape": cancelCallback,
		            "header" :"<i class=\"icon-info-sign icon-white\"></i> Info"
		    });
		    return false;
		}
	});
};

$(document).ready(function(){
	$('#loadingCategory').category({
		num_entries: $("#total").val(),
		pagesize: $("#pagesize").val()
	});	
	
	$('#addnew').on('click', function (){
		var $myhtml = "<form class=\"modal-form\"><fieldset>" +
			"<div class=\"inlines\"><label>enName:</label><input type=\"text\" name=\"enName\" id=\"enName\" value=\"\"></div>" +
			"<div class=\"inlines\"><label>cnName:</label><input type=\"text\" name=\"cnName\" id=\"cnName\" value=\"\"></div>" +
			"</fieldset></form>";
	
		var cancelCallback = function() {
		    if (typeof cb === 'function') {
		        return cb(false);
		    }
		};
				
		bootbox.dialog($myhtml, [{
			"label" : "提交",
			"class" : "btn-success",
			"callback": function() {
				var url = "admin/category";
				var jsonData = JSON.stringify({"id": null, "name":$("#enName").val(), "intro": $("#cnName").val()});
				$.addNewObject(url, jsonData, function(data){
					$('#loadingCategory').category({
	    				num_entries:$("#total").val(),
	    				pagesize: $("#pagesize").val()
	    			});	
		        });
			}}, 
			{
				"label" : "取消",
				"class" : "btn"
			}],{
	            "onEscape": cancelCallback,
	            "header" :"<i class=\"icon-info-sign icon-white\"></i> Info"
	    });
	});
	
	
});