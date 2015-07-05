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

<body>
	<input type="hidden" name="ctx" value="<%=request.getContextPath()%>" />

	<ul class="breadcrumb">
		<li class="active">实体类管理</li>
	</ul>

	<nav class="navbar navbar-default" role="navigation"
			style="margin-bottom: 0px; width: 100%; float: left;">
			<ul
				style="float: left; color: #9d9d9d; width: 30%;">
				<li style="display: inline;">
					<input id="unitname" name="unitname" class="form-control" type="text" style="width: 100%" placeholder="输入单位名称">
				</li>
			</ul>
			<ul style="float: left; ">
				<li style="display: inline; width: 10%;">
					<button id="btn_search" class="btn btn-nm btn-default" type="button">查询</button>
				</li>
			</ul>
		</nav>
		
	<div id="content_inner_page" class="innercontent" >
	
		<div class="navbar navbar-default">
		    <form class="navbar-form navbar-left" >
		        <div class="form-group " >
		            <input class="form-control " style="width:300px" type="text" id="search_condition" name="search_condition"  placeholder="">
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