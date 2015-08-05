<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<jsp:include page="/WEB-INF/jsp/include/common_css.jsp"></jsp:include>

</head>

<body>
	<div class="container-fluid inlineone" style="margin-top: 30px">
		<div class="col form-horizontal center-block " style="width: 400px">
			<#list fields as model>
				<div class="form-group ">
					<label for="${model.coltitle}" class="col-sm-3 control-label"> ${model.colname} </label>
					<div>
						<input type="text" class="form-control" id="${model.coltitle}"
							name="${model.coltitle}" value="${r'${'}${bussnesdomainnameL}.${model.coltitle}}" readonly>
					</div>
				</div>
	      	</#list>
		</div>
	</div>
</body>
</html>