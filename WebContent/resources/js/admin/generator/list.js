$().ready(function() {

	data_manage.init();

	$("#btn_add").click(function() {
		var url_to = $.getSitePath() + '/genhome';

		window.location.href = url_to;
	});

	$("#btn_search").click(function() {
		data_manage.search();
	});
});

var data_manage_functions = {

	/***************************************************************************
	 * 进入详细信息页面
	 * 
	 * @param data
	 * @returns {Boolean}
	 */
	toDetail : function(data) {

		var url = $.getSitePath() + '/detail?classmodule=' + data["classmodule"] + "&classname=" + data["classname"];
		
		$.showDetailWindow("实体类信息", url, "90%", "90%");
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
		var classmodulecondition = {};
		classmodulecondition["name"] = "classmodule";
		classmodulecondition["value"] = $("#classmodule").val().trim();
		
		var classnamecondition = {};
		classnamecondition["name"] = "classname";
		classnamecondition["value"] = $("#classname").val().trim();

		var params = [];
		params.push(classmodulecondition);
		params.push(classnamecondition);

		data_manage.gridsetting.url = $.getSitePath() + '/list?ts=' + new Date().getTime();

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

		data_manage.gridsetting.url = $.getSitePath() + '/list?ts=' + new Date().getTime();
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
		usepager : false,
		title : '系统类信息',
		useRp : true,
		rp : 20, // 默认分页条数
		pagestat : '共有{total}条记录，显示{from} - {to}条记录',
		procmsg : '正在查询，请稍候 ...',
		nomsg : '没有符合条件的数据',
		colModel : [ {
			display : '选择',
			hide : true,
			m_type : 'radio',
			align : 'center',
			select : [ "classmodule", "classname" ]
		}, {
			display : '序号',
			m_type : 'sno',
			align : 'center'
		},  
			{
				display : '模块',
				name : 'classmodule',
				width : 120,
				m_type : 'link',
				select : [ "classmodule","classname" ],
				callback : data_manage_functions.toDetail
		},
			{
				display : '类',
				name : 'classname',
				width : 120
		},
			{
				display : '类说明',
				name : 'classrmk',
				width : 120
		}]

	}
};
