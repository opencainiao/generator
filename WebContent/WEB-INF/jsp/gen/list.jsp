<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<jsp:include page="/WEB-INF/jsp/include/common_css.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/include/common_js.jsp"></jsp:include>

<link media="screen" type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/Flexigrid-master/css/flexigrid.css" />
	
<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/Flexigrid-master/js/flexigrid.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/Flexigrid-master/js/mou.fleligrid.ux.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/mou_grid.js"></script>
</head>

<body style="padding: 15px;">
	<input type="hidden" name="ctx" value="<%=request.getContextPath()%>" />

	<ul class="breadcrumb">
		<li class="active">实体类管理</li>
	</ul>

	<div id="content_inner_page" class="innercontent" >
	
		<div class="navbar navbar-default">
		    <form class="navbar-form navbar-left" >
		        <div class="form-group">
					<label for="classmodule"  style="width: 60px;">模块名</label> 
					<input  id="classmodule" type="text" name="classmodule" 
							placeholder="模块名" err-info="不允许为空" style=""/>
				</div>
				<div class="form-group">
					<label for="classname"  style="margin-left:15px;width: 50px;">类名</label> 
					<input type="text" name="classname"   id="classname"
						placeholder="类名">
				</div>
		        <button class="btn btn-info" type="button" id="btn_search">
		            查询
		        </button>
		        <button class="btn btn-primary" type="button" id="btn_add" style="margin-left: 50px ;">
		            添加实体类
		        </button>
		    </form>
		</div>
		
		
		<div id="data_manage" >
			<table id="list"></table>
		</div>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/admin/generator/list.js"></script>

</body>

</html>