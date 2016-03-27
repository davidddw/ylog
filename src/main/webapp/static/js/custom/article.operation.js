jQuery.fn.article = function(opts){
	opts = jQuery.extend({
		num_entries:10,
		pagesize:10,
		current_page:0,
		show_edit: true,
		show_delete: true,
		show_more: true,
		get_all_url: "admin/article",
		load_url: "admin/article/",
		delete_url: "admin/article/",
		update_url: "admin/article/",
		callback:function(){return false;},	
	},opts||{});
	
	return this.each(function() {
		var header = '';
		header += '<thead id="headerInfo">';
		header += '<tr><th width="30px">#</th><th>标题</th><th width="100px">用户</th>';
		header += '<th width="100px">发表日期</th><th width="100px">类别</th><th width="80px">操作</th></tr>';
		header += '</thead>';
		header += '<tfoot><tr><td colspan="6"><div id="APagination" class="pagination"></div><div class="clear"></div></td></tr></tfoot><tbody class="bodyInfo"></tbody>';
		$(this).empty().html(header);
		
		var initPagination = function(page_index, num_entries, pagesize, pageselectCallback) {
			// 创建分页
			$("#APagination").pagination(num_entries, {
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
	        	$.each(data.article, function(i, item) {
	       			content += '<tr id="article'+item.id +'"><td>'+item.id +'</td><td><a href="node/article/' + item.id + '.html" title="title">' + item.title +
	                	'</a></td><td>' + item.user + '</td><td>' + item.date +'</td><td>' + item.category + '</td><td>';
	       			if(opts.show_edit) {
	       				content += '<a herf="" class="btn-link editarticle" id="'+item.id + '" title="Edit"><img src="static/admin/images/icons/pencil_go.png" alt="Edit"/></a>&nbsp;&nbsp;';
	       			}
	       			if(opts.show_delete) {
	       				content += '<a herf="" class="btn-link deletearticle" id="' + item.id + '" title="Delete"><img src="static/admin/images/icons/cross.png" alt="Delete" /></a>&nbsp;&nbsp;';
	       			}
	        		if(opts.show_more){
	        			content += '<a herf="" class="btn-link managecomment" id="'+ item.id + 
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
	    		
	    		$('.editarticle').on('click', function (){
	    			var currentTab = $('#editTab');
	    			$(currentTab).siblings().hide();
	    			$(currentTab).show(); 
	    			loadArticle(this.id);
	    			var $current = $(".breadcrumb li.active").text();
	    			$(".breadcrumb li").first().append('<li><a href="admin/listArticle">' + $current + '</a><span class="divider">/</span></li>');
	    			$(".breadcrumb li.active").html("编辑文章");
	    			return false; 
	    		});
	    		
	    		$('.deletearticle').on('click', function (){
	    			var myid = this.id;
	    			bootbox.confirm("确定删除么?", function(result) {
	    				if(result) {
	    					deleteArticle(myid, page_index);
	    				}
	    			});
	    			return false;
	    		});
	    		
	    		$('.managecomment').on('click', function (){
	    			
	    			var $current = $(".breadcrumb li.active").text();
	    			$(".breadcrumb li").first().append('<li><a href="admin/listArticle">' + $current + '</a><span class="divider">/</span></li>');
	    			$(".breadcrumb li.active").html("评论管理");
	    			
	    			var commentCount=0;
	    			$.getTotalObject('getTotalCommentByArticle', 'id='+this.id, function(data){
	    				commentCount = data.count;
	    			});
	    			$('#loadingArticle').comment({
	    				num_entries: commentCount,
	    				get_all_url: "comment?id="+this.id,
	    				show_more: false
	    			});
	    			//return false;
	    		});
			}); 
			return false;
		};
		
		initPagination(0, opts.num_entries, opts.pagesize, callback);
		
		function deleteArticle(id, pageindex){
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
		
		function loadArticle(id){
			var url = opts.load_url;
			$.loadObject(url, id, function(data){
				if(data.id) {
					$('#id').val(data.id);
	    			$('#title').val(data.title);
	    			$('#user').attr("value",data.userName);
	    			$('#user option').filter(function() {
	    			    return $(this).text() == data.userName; 
	    			}).prop('selected', true);
	    			$('#category').attr("value",data.categoryName);
	    			$('#category option').filter(function() {
	    			    return $(this).text() == data.categoryName; 
	    			}).prop('selected', true);
	    			$('#tags').val(data.tagStrings);
	    			editor.sync();
	    			editor.html(data.content);
				} else {
					bootbox.alert("添加失败!!!");
				}
			});
		}	
	});
};

$(document).ready(function(){
	$('#loadingArticle').article({
		num_entries:$("#totalarticle").val()
	});	
	$("#submitAddForm").click(function(event){
		var url = "admin/article";
		var jsonData = JSON.stringify({"title":$("#title").val(), 
		    "user": $("#user").val(),
        	"category":$("#category").val(), 
        	"tags": $("#tags").val(), "content": $("#kindContent").val()});
		$.addNewObject(url, jsonData, function(data){
		    
			if(data.id){
        		bootbox.alert("添加成功!!!");
        	}else{
        		bootbox.alert("添加失败!!!");
            }
			$('#title').val("");
			$("#user").val("");
			$('#category').val("");
			$('#tags').val("");
			editor.sync();
			editor.html("");  
		});   
		
		return false;
    });
	
	$("#returnListForm").click(function(){
		var currentTab = $('#listTab');
        $(currentTab).siblings().hide();
        $(currentTab).show(); 
		return false;
	});
	
	$("#submitEditForm").click(function(){
		var myid = $("#id").val();
		var url = "admin/article/";
		var jsonData = JSON.stringify({"id": myid, "title":$("#title").val(), "user": $("#user").val(),
        	"category":$("#category").val(), "tags": $("#tags").val(), "content": $("#kindContent").val()});
		$.updateObject(url, myid, jsonData, function(data){
			if(data.id) {
    			bootbox.alert("添加成功!!!");
    			location.href ="listArticle";
				//return false; 
			} else {
				bootbox.alert("添加失败!!!");
			}
		}); 
		//return false;
	});
});