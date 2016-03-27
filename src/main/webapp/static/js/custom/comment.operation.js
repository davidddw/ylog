jQuery.fn.comment = function(opts){
	opts = jQuery.extend({
		num_entries:10,
		pagesize:10,
		current_page:0,
		show_edit: true,
		show_delete: true,
		show_more: true,
		get_all_url: "admin/comment",
		load_url: "admin/comment/",
		delete_url: "admin/comment/",
		update_url: "admin/comment/",
		callback:function(){return false;},	
	},opts||{});
	
	return this.each(function() {
		var header = '';
		header += '<thead id="headerInfo">';
		header += '<tr><th width="30px">#</th><th>content</th><th width="100px">用户</th><th width="100px">发表日期</th>';
		header += '<th width="150px">email</th><th width="100px">url</th><th width="80px">操作</th></tr>';
		header += '</thead>';
		header += '<tfoot><tr><td colspan="7"><div id="MPagination" class="pagination"></div><div class="clear"></div></td></tr></tfoot><tbody class="bodyInfo"></tbody>';
		$(this).html(header);
		var initPagination = function(page_index, num_entries, pagesize, pageselectCallback) {
			// 创建分页
			$("#MPagination").pagination(num_entries, {
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
	        	var content = "";     
	        	$.each(data.comment, function(i, item) {
	       			content += '<tr id="article"'+item.id +'"><td>'+item.id +'</td><td>' + item.content + '</td><td>' + item.user +
	       				'</td><td>' + item.date + '</td><td>' + item.email +'</td><td>' + item.url +'</td><td>';
	       			if(opts.show_edit) {
	       				content += '<a herf="" class="btn-link editarticle" id="'+item.id + '" title="Edit"><img src="static/admin/images/icons/pencil_go.png" alt="Edit" /></a>&nbsp;&nbsp;';
	       			}
	       			if(opts.show_delete) {
	       				content += '<a herf="" class="btn-link deletearticle" id="' + item.id + '" title="Delete"><img src="static/admin/images/icons/cross.png" alt="Delete"/></a>&nbsp;&nbsp;';
	       			}
	        		if(opts.show_more){
	        			content += '<a herf="" class="btn-link" id="' + item.id + '" title="Edit Comments"><img src="static/admin/images/icons/comments_add.png" alt="Edit comment" /></a> ';
	        		}
	        		content += '</td></tr>';
	        	});
	        	$('.bodyInfo').html(content);
	    		$('.loading').hideLoading();
	    		$('.check-all').on('click', function (){
	   			 	$(this).parent().parent().parent().parent().find("input[type='checkbox']").prop('checked', $(this).is(':checked'));   
	    		});
	   		
	    		$('tbody tr:even').addClass("alt-row");
	    		
	    		$('.editarticle').on('click', function (){
	    			loadComment(this.id, page_index);
	        		return false; 
	    		});
	    		
	    		$('.deletearticle').on('click', function (){
	    			var myid = this.id;
	    			bootbox.confirm("确定删除么?", function(result) {
	    				if(result) {
	    					deleteComment(myid, page_index);
	    				}
	    			});
	    			return false;
	    		});
			}); 
			return false;
		};
		
		initPagination(0, opts.num_entries, opts.pagesize, callback);
		
		function deleteComment(id, pageindex){
			var url = opts.delete_url;
			$.deleteObject(url, id, pageindex, function(data){
				if(data.result) {
					initPagination(pageindex, opts.num_entries, opts.pagesize, callback);
					bootbox.alert("删除成功!!!");
				} else {
					bootbox.alert("删除失败!!!");
				}
			});
		}
		
		function loadComment(id, page_index){
			var url = opts.load_url;
			$.loadObject(url, id, function(data){
				updateComment(data.id, page_index, data.submitter, data.content);
			});
		}
		
		function updateComment(id, page_index, user, content){
			var $myhtml = "<form class=\"modal-form\"><fieldset>" +
				"<div><label>user:</label><span class=\"input-xlarge uneditable-input\">" + user +"</span>" +
				"<div><label>content:</label><textarea rows=\"3\" name=\"value\" id=\"value\">"+ content +"</textarea></div>" +
				"</fieldset></form>";
		
			var cancelCallback = function() {
			    if (typeof cb === 'function') {
			        return cb(false);
			    }
			};
			
			bootbox.dialog($myhtml, [{
				"label" : "更新",
				"class" : "btn-success",
				"callback": function() {
					var url = opts.update_url;
					var jsonData = JSON.stringify({"id": id, "submitter":user, "content": $("#value").val()});
					$.updateObject(url, id, jsonData, function(data){
						initPagination(page_index, opts.num_entries, opts.pagesize, callback);
					});
				}}, 
				{
					"label" : "取消",
					"class" : "btn"
				}],{
		            "onEscape": cancelCallback,
		            "header" :"<i class=\"icon-info-sign icon-white\"></i> Info"
		    });
		}
	});
};

$(document).ready(function(){
	$('#loadingComment').comment({
		num_entries:$("#total").val(),
		get_all_url: "admin/comment"
	});	
});