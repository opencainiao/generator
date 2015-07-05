$().ready(function() {

	data_manage.init();

	$("#btn_add").click(function() {
		var url_to = $.getSitePath() + '/${controllerparentpath}/${bussnesdomainnameL}/add';

		window.location.href = url_to;
	});

	$("#btn_search").click(function() {
		data_manage.search();
	});
});

var data_manage_functions = {

	/***************************************************************************
	 * 删除
	 * 
	 * @param data
	 */
	delOne : function(data) {

		var url_to = $.getSitePath() + '/${controllerparentpath}/${bussnesdomainnameL}/' + data["_id_m"] + '/delete';

		$.ajax({
			type : 'POST',
			url : url_to,
			data : $.extend({
				ts : new Date().getTime()
			}, data),
			dataType : 'json',
			success : function(data) {
				data_manage.gridsetting.params = [ {
					name : 'reload',
					value : true
				} ];
				$("#list").flexReload(data_manage.gridsetting);
			}
		});
	},
	/***************************************************************************
	 * 进入修改页面
	 * 
	 * @param data
	 */
	toEdit : function(data) {

		var url = $.getSitePath() + '/${controllerparentpath}/${bussnesdomainnameL}/' + data["_id_m"] + "/update";

		$.popUpWindow("编辑${domainname}信息", url, "800px", "400px", "edit", $("#data_manage"));
	},
	/***************************************************************************
	 * 进入详细信息页面
	 * 
	 * @param data
	 * @returns {Boolean}
	 */
	toDetail : function(data) {

		var url = $.getSitePath() + '/${controllerparentpath}/${bussnesdomainnameL}/' + data["_id_m"];

		$.showDetailWindow("${domainname}信息", url, "600px", "300px");
	},
	/***************************************************************************
	 * 关闭编辑窗口
	 */
	closeEditWindow : function() {
		$.closeWindow("edit", $("#data_manage"));
	},
	refreshPage:function(){
		data_manage.search();
	}
};

var data_manage = {

	search : function() {
		var searchcondition = {};
		searchcondition["name"] = "search_condition";
		searchcondition["value"] = $("#search_condition").val().trim();

		var params = [];
		params.push(searchcondition);

		data_manage.gridsetting.url = $.getSitePath() + '/${controllerparentpath}/${bussnesdomainnameL}/list?ts=' + new Date().getTime();

		if ($("#data_manage").attr("s_times")) {
			params.push({
				name : 'reload',
				value : true
			});
			data_manage.gridsetting.params = params;

			$("#list").flexReload(data_manage.gridsetting);
		} else {
			data_manage.gridsetting.params = params;
			$("#list").flexigrid(data_manage.gridsetting);
		}

		$("#data_manage").attr("s_times", 1);
	},
	/***************************************************************************
	 * 页面加载后的初始化方法
	 */
	init : function() {

		data_manage.gridsetting.url = $.getSitePath() + '/${controllerparentpath}/${bussnesdomainnameL}/list';
		// alert(data_manage.gridsetting.url);
		$("#list").flexigrid(data_manage.gridsetting);

		data_manage.pageLayout();
	},
	pageLayout : function() {

		var inPage_h = $(parent.window).height();
		var nav_h = 160;
		var btnbar_h = 40;
		var table_h = mou_grid_ux.getTableH($("#content_inner_page"), inPage_h - nav_h - btnbar_h - 2 - 8);
		mou_grid_ux.resetHeight_grid($("#content_inner_page"), table_h);
	},
	/***************************************************************************
	 * 表格配置
	 */
	gridsetting : {
		showTableToggleBtn : true,
		resizable : false,
		url : '',
		method : 'POST',
		dataType : 'json',
		nowrap : true, // 是否不换行
		autoload : true,// 自动加载
		usepager : true,
		title : '系统常量类型列表',
		useRp : true,
		rp : 20, // 默认分页条数
		pagestat : '共有{total}条记录，显示{from} - {to}条记录',
		procmsg : '正在查询，请稍候 ...',
		nomsg : '没有符合条件的数据',
		colModel : [ {
			display : '选择',
			hide : true,
			m_type : 'radio1',
			align : 'center',
			select : [ "_id_m", "name" ]
		}, {
			display : '序号',
			m_type : 'sno',
			align : 'center'
		}, {
			display : '逻辑主键',
			name : '_id_m',
			width : 150,
			hide : false,
			callback : data_manage_functions.toDetail
		}, 
		<#list fields as model>  
			{
				display : '${model.colname}',
				name : '${model.coltitle}',
				width : 120
		},
      	</#list>
		{
			display : '启用状态',
			name : 'useflg',
			width : 120,
			condition : {
				'0' : '已停用',
				'1' : '启用',
			}
		}, {
			display : '操作',
			name : 'operation',
			m_type : 'buttons',
			width : 200,
			buttons : [ {
				r_name : 'del',
				text : '删除',
				callback : data_manage_functions.delOne,
				paramConfig : [ "_id_m" ],
				css : "btn btn-xs btn-danger"
			}, {
				r_name : 'toEdit',
				text : '修改',
				callback : data_manage_functions.toEdit,
				paramConfig : [ "_id_m" ]
			} ]
		} ]

	}
};
