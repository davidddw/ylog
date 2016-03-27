jQuery.fn.global = function(opts){
	opts = jQuery.extend({
		num_entries:10,
		pagesize:10,
		current_page:0,
		show_edit: true,
		show_delete: true,
		show_more: true,
		get_all_url: "admin/global",
		load_url: "admin/global/",
		delete_url: "admin/global/",
		update_url: "admin/global/",
		callback:function(){return false;},	
	},opts||{});
	
	return this.each(function() {
		var header = '';
		header += '<thead id="headerInfo">';
		header += '<tr><th width="30px">#</th><th width="20%">name</th><th width="40%">value</th>';
		header += '<th width="30%">description</th><th width="70px">操作</th></tr>';
		header += '</thead>';
		header += '<tfoot><tr><td colspan="6"><div id="GPagination" class="pagination"></div><div class="clear"></div></td></tr></tfoot><tbody class="bodyInfo"></tbody>';
		$(this).empty().html(header);
		var initPagination = function(page_index, num_entries, pagesize, pageselectCallback) {
			// 创建分页
			$("#GPagination").pagination(num_entries, {
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
	            $.each(data, function(i, item) {
	           		content += '<tr id="global'+item.id +'"><td>'+item.id +'</td><td>' + item.name + '</td><td>' + item.value + '</td><td>' + item.description +'</td><td>';
	           		content += '<a herf="" class="btn-link editcategory" id="'+item.id + 
	                    	'" title="Edit"><img src="static/admin/images/icons/onebit_23.png" alt="Edit"/></a>&nbsp;&nbsp;';
	           		content += '</td></tr>';
	           		
	            });
	            $('.bodyInfo').html(content);
	    		$('.loading').hideLoading();
	
	        	$('tbody tr:even').addClass("alt-row");
	        		
	        	$('.editcategory').on('click', function (){
	        		loadCategory(this.id);
	        		return false; 
	        	});
		    });	
		};
		
		initPagination(0, opts.num_entries, opts.pagesize, callback);
		
		function loadCategory(id) {
			var url = opts.load_url;
			$.loadObject(url, id, function(data){
				updateCategory(data.id, data.name, data.value, data.description);
			});
			return false;
		}
		
		function updateCategory(id, name, value, description){
			var $myhtml = "<form class=\"modal-form\"><fieldset>" +
				"<div><label>name:</label><span class=\"input-xlarge uneditable-input\">" + name +"</span>" +
				"<div><label>value:</label><textarea rows=\"3\" name=\"value\" id=\"value\">"+ value +"</textarea></div>" +
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
					var jsonData = JSON.stringify({"id": id, "name":name, "value": $("#value").val()});
					$.updateObject(url, id, jsonData, function(data){
						callback();
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
	$('#loadingSettings').global({
	});	
});