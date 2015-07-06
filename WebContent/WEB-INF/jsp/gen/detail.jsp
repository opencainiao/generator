<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>appendGrid</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/appendGrid/jquery-ui.structure.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/appendGrid/jquery-ui.theme.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/appendGrid/jquery.appendGrid-1.5.2.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/bootstrap-3.3.2-dist/css/bootstrap.min.css"
	type="text/css" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/appendGrid/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/appendGrid/jquery-ui-1.11.1.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/appendGrid/jquery.appendGrid-1.5.2.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/mou.ajax.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/layer/layer.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/layer/extend/layer.ext.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.nbq.ux.js"></script>
<style>

label{
}
div.form-group > input{
	width: 300px
}
</style>
</head>
<body>
	<script id="jsSource" type="text/javascript">
		$(function() {
			// Initialize appendGrid
			$('#tblAppendGrid').appendGrid({
				caption : '属性',
				initRows : 1,
				columns : [ {
					name : 'coltitle',
					display : '英文名',
					type : 'text',
					ctrlAttr : {
						maxlength : 100
					},
					ctrlCss : {
						width : '160px'
					}
				}, {
					name : 'colname',
					display : '中文名',
					type : 'text',
					ctrlAttr : {
						maxlength : 100
					},
					ctrlCss : {
						width : '300px'
					}
				}, {
					name : 'coltype',
					display : '数据类型',
					type : 'select',
					ctrlOptions : {
						'string' : 'string',
						'double' : 'double',
						'int'    : 'int'
					}
				},{
					name : '00',
					display : ' ',
					type : 'text',
					ctrlAttr : {
						maxlength : 100
					},
					ctrlCss : {
						width : '100px'
					}
				}]
			});
			
			
			// Handle `Serialize` button click
			$('#btnSerialize').button().click(function() {
				alert('Here is the serialized data!!\n' + $(document.forms[0]).serialize());
			});
			
			function genSno(){
				
				var order = {};
				
				$(".ui-widget-content.first").each(function(){
					var sno = $(this).text();
					
					var rowid= $(this).closest('tr').attr("id");
					
					var rowno = rowid.substr(rowid.length-1,1) ;
					//alert( rowno + "____" +  sno);					
					order[rowno] = sno;
				});
				
				return order;
			}
			
			$("input").each(function() {

				$(this).on('keyup', function(e) {
					
					if ($(this).attr("id") == "classmodule" ){
						
						// 禁止输入空格
						$(this).val($(this).val().replace(/(^\s+)|(\s+$)/g, ""));
						
						// 禁止输入非英文的字符串
						$(this).val($(this).val().replace(/[^a-zA-Z]+/gi,""));
						var valnow = $(this).val().toLowerCase();
						
						$(this).val(valnow);
					}else if ($(this).attr("id") == "classname" ){
						
						// 禁止输入空格
						$(this).val($(this).val().replace(/(^\s+)|(\s+$)/g, ""));
						
						// 禁止输入非英文的字符串
						$(this).val($(this).val().replace(/[^a-zA-Z]+/gi,""));
						
					}else if ($(this).attr("id") == "classrmk"  ){
							
							// 禁止输入空格
							$(this).val($(this).val().replace(/(^\s+)|(\s+$)/g, ""));
					}
				});
			});
			
			var validate = function() {

				var val = $("#classmodule").val().trim();
				if (val == "") {
					alert("模块名不能为空");
					return false
				}
				
				var val = $("#classname").val().trim();
				if (val == "") {
					alert("类名不能为空");
					return false
				}
				var val = $("#classrmk").val().trim();
				if (val == "") {
					alert("类中文名不能为空");
					return false
				}

				return true;
			}
			
			$('#btnGen').button().click(function() {
				//alert('Here is the serialized data!!\n' + $(document.forms[0]).serialize());

				if (!validate()){
					return;
				}
				
				var order = genSno();
				
				var orderstr = JSON.stringify(order);
				
				//alert(orderstr);
				
				$.disableButton("btnGen");
				
				var flag = true;

				var url_to = "<%=request.getContextPath()%>/generate";
				var paramForm = $(document.forms[0]).getFormParam_ux();
				
				//alert(JSON.stringify(paramForm));
				//return;
				$.ajax({
					type : 'POST',
					url : url_to,
					data : $.extend({
						ts : new Date().getTime(),
						order:orderstr
					}, paramForm),
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						
						var message = data['message'];
						
						// alert(data.success);
						if (data['success'] == 'n') {
							layer.alert(message);
						} else {
							layer.alert("生成文件完毕！文件目录:\n" + message);
						}
					},
					complete : function(XMLHttpRequest, textStatus) {

						// 控制按钮为可用
						$.enableButton("btnGen");
					}
				});

			});
		});
		
		$().ready(function() {

			$('#tblAppendGrid').appendGrid('load', ${fields});
		});
		
		
	</script>
	
	<ul class="breadcrumb" style="margin: 15px">
	    <li><a href="<%=request.getContextPath()%>/list">类管理</a> <span class="divider"></span></li>
	    <li class="active">添加新类</li>
	</ul>

	<form action="" method="post">
		<div class="panel panel-primary" style="margin: 20px;">
			<div class="panel-heading">
				<h3 class="panel-title">类信息</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<label for="classmodule"  style="width: 100px;">模块名</label> 
					<input  id="classmodule" type="text" name="classmodule" 
							placeholder="模块名" err-info="不允许为空" style="" value="${classmodule }"/>
				</div>
				<div class="form-group">
					<label for="classname"  style="width: 100px;">类名</label> 
					<input type="text" name="classname"   id="classname" value="${classname }"
						placeholder="类名">
				</div>
				
				<div class="form-group">
					<label for="classrmk"  style="width: 100px;">类中文名</label> 
					<input type="text" name="classrmk"  id="classrmk" value="${classrmk }"
						placeholder="类中文名">
				</div>
			</div>
			<table id="tblAppendGrid" style="margin-left: 15px;">
			</table>
			<br />

			<hr />
			<div class="col-sm-offset-5 col-sm-2" style="height: 70px; margin-top: 15px;">
				<button id="btnGen" type="button" style="width: 100%"
					class="btn btn-lg btn-primary">生成程序代码</button>
			</div>
		</div>
	</form>

</body>
</html>