package generator.util;

import generator.model.EntityModel;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NameGetter {
	private static final Logger logger = LogManager.getLogger(NameGetter.class);
	public static String project_top_packagename = "com.bxb";

	/****
	 * 取dao相关名字
	 * 
	 * @param model
	 * @return
	 */
	public static Map<String, String> getNames(EntityModel model) {

		Map<String, String> objMap = new HashMap<String, String>();

		String module = model.getClassmodule();// 模块名
		String classname = StringUtils.capitalize(model.getClassname()); // 类名

		// com.bxb.modules.user.model
		String domainfullname = project_top_packagename + ".modules."
				+ module + ".model." + classname;

		objMap.put("domainfullname", domainfullname);// 总的业务模块包名

		// logger.debug("domainfullname[{}]",domainfullname);
		String simpleName = classname;

		String packagename = project_top_packagename + ".modules." + module
				+ ".model";

		int index = packagename.lastIndexOf(".");
		String bussnesspackagename = packagename.substring(0, index);
		logger.debug("bussnesspackagename[{}]", bussnesspackagename);

		String bussnessmodule = bussnesspackagename
				.substring(bussnesspackagename.lastIndexOf(".") + 1);
		logger.debug("bussnessmodule[{}]", bussnessmodule);

		// com.bxb.modules.infrastructure.
		// com.bxb.modules.infrastructure.model
		objMap.put("bussnessmodule", bussnessmodule);
		objMap.put("bussnesspackagename", bussnesspackagename);// 总的业务模块包名
		objMap.put("bussnesdomainname", simpleName);// 业务对象类名
		objMap.put("bussnesdomainnameL", simpleName.toLowerCase());// 业务对象类名的小写形式

		// controller
		String controllername = simpleName + "Controller";
		objMap.put("controllername", controllername);// Controller名
		objMap.put("controllerfilename", controllername + ".java");
		String controllerpackagename = bussnesspackagename + ".controller";
		objMap.put("controllerpackagename", controllerpackagename);// service包名

		// service
		// com.bxb.modules.infrastructure.service
		String servicename = simpleName + "Service";
		String servicezhujiename = servicename.substring(0, 1).toLowerCase()
				+ servicename.substring(1);
		String servicepackagename = bussnesspackagename + ".service";

		String serviceinterfacename = "I" + simpleName + "Service";
		String servicefullname = servicepackagename + "." + servicename;
		objMap.put("servicefullname", servicefullname);// 总的业务模块包名

		String serviceinterfacefullname = servicepackagename + "."
				+ serviceinterfacename;
		objMap.put("serviceinterfacefullname", serviceinterfacefullname);// 总的业务模块包名
		objMap.put("servicepackagename", servicepackagename);// service包名
		objMap.put("serviceinterfacename", serviceinterfacename);
		objMap.put("serviceinterfacefilename", "I" + simpleName
				+ "Service.java");// service接口文件类名
		objMap.put("servicename", servicename);
		objMap.put("servicezhujiename", servicezhujiename);
		objMap.put("servicefilename", servicename + ".java");

		logger.debug("servicezhujiename[{}]", servicezhujiename);
		// dao

		String daoName = simpleName + "Dao";
		String daozhujiename = daoName.toLowerCase();
		String daopackagename = bussnesspackagename + ".dao";
		String daofullname = daopackagename + "." + daoName;

		objMap.put("daofullname", daofullname);
		objMap.put("daopackagename", daopackagename);// dao包名
		objMap.put("daoname", daoName); // 类名
		objMap.put("daozhujiename", daozhujiename); // 注解名
		objMap.put("collectionname", simpleName.toLowerCase()); // 集合名
		objMap.put("daofilename", daoName + ".java");

		/*---------------------------------------------*/
		/*---------------    jsp     ------------------*/
		/*---------------------------------------------*/
		objMap.put("addfilename", "add.jsp");
		objMap.put("updatefilename", "update.jsp");
		objMap.put("detailfilename", "detail.jsp");
		objMap.put("listfilename", "list.jsp");
		objMap.put("listjsfilename", "list.js");

		return objMap;
	}

	/****
	 * 取dao相关名字
	 * 
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> getNames(Class clazz) {

		Map<String, String> objMap = new HashMap<String, String>();

		String domainfullname = clazz.getName();
		objMap.put("domainfullname", domainfullname);// 总的业务模块包名

		// logger.debug("domainfullname[{}]",domainfullname);
		String simpleName = clazz.getSimpleName();

		String packagename = clazz.getPackage().getName();
		int index = packagename.lastIndexOf(".");
		String bussnesspackagename = packagename.substring(0, index);
		logger.debug("bussnesspackagename[{}]", bussnesspackagename);

		String bussnessmodule = bussnesspackagename
				.substring(bussnesspackagename.lastIndexOf(".") + 1);
		logger.debug("bussnessmodule[{}]", bussnessmodule);

		// com.bxb.modules.infrastructure.
		// com.bxb.modules.infrastructure.model
		objMap.put("bussnessmodule", bussnessmodule);
		objMap.put("bussnesspackagename", bussnesspackagename);// 总的业务模块包名
		objMap.put("bussnesdomainname", simpleName);// 业务对象类名
		objMap.put("bussnesdomainnameL", simpleName.toLowerCase());// 业务对象类名的小写形式

		// controller
		String controllername = simpleName + "Controller";
		objMap.put("controllername", controllername);// Controller名
		objMap.put("controllerfilename", controllername + ".java");
		String controllerpackagename = bussnesspackagename + ".controller";
		objMap.put("controllerpackagename", controllerpackagename);// service包名

		// service
		// com.bxb.modules.infrastructure.service
		String servicename = simpleName + "Service";
		String servicezhujiename = servicename.substring(0, 1).toLowerCase()
				+ servicename.substring(1);
		String servicepackagename = bussnesspackagename + ".service";

		String serviceinterfacename = "I" + simpleName + "Service";
		String servicefullname = servicepackagename + "." + servicename;
		objMap.put("servicefullname", servicefullname);// 总的业务模块包名

		String serviceinterfacefullname = servicepackagename + "."
				+ serviceinterfacename;
		objMap.put("serviceinterfacefullname", serviceinterfacefullname);// 总的业务模块包名
		objMap.put("servicepackagename", servicepackagename);// service包名
		objMap.put("serviceinterfacename", serviceinterfacename);
		objMap.put("serviceinterfacefilename", "I" + simpleName
				+ "Service.java");// service接口文件类名
		objMap.put("servicename", servicename);
		objMap.put("servicezhujiename", servicezhujiename);
		objMap.put("servicefilename", servicename + ".java");

		logger.debug("servicezhujiename[{}]", servicezhujiename);
		// dao

		String daoName = simpleName + "Dao";
		String daozhujiename = daoName.toLowerCase();
		String daopackagename = bussnesspackagename + ".dao";
		String daofullname = daopackagename + "." + daoName;

		objMap.put("daofullname", daofullname);
		objMap.put("daopackagename", daopackagename);// dao包名
		objMap.put("daoname", daoName); // 类名
		objMap.put("daozhujiename", daozhujiename); // 注解名
		objMap.put("collectionname", simpleName.toLowerCase()); // 集合名
		objMap.put("daofilename", daoName + ".java");

		/*---------------------------------------------*/
		/*---------------    jsp     ------------------*/
		/*---------------------------------------------*/
		objMap.put("addfilename", "add.jsp");
		objMap.put("updatefilename", "update.jsp");
		objMap.put("detailfilename", "detail.jsp");
		objMap.put("listfilename", "list.jsp");
		objMap.put("listjsfilename", "list.js");

		return objMap;
	}

	public static void main(String[] args) {
		// System.out.println(getNames(SysConst.class));

		EntityModel model = new EntityModel();
		model.setClassmodule("user");
		model.setClassname("User");
		model.setClassrmk("用户");
		
		System.out.println(getNames(model));
	}
}
