package generator.generators.backend;

import generator.model.EntityModel;
import generator.util.ConfigurationManager;
import generator.util.NameGetter;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class GenEntityFromModel {

	private static String[] colnames; // 列名数组
	private static String[] colTypes; // 列名类型数组
	private static String[] colRmks;// 列名中文说明

	private static String modulename; // 模块
	private static String classname; // 类名

	public static void genEntityFile(EntityModel model) {

		Map<String, String[]> fieldInfo = EntityModel.genFieldInfo(model.getFields());

		colnames = fieldInfo.get("COLNAMES"); // 列名数组
		colTypes = fieldInfo.get("COLTYPES"); // 列名类型数组
		colRmks = fieldInfo.get("COLRMKS");// 列名中文说明

		classname = model.getClassname();
		modulename = model.getModule();

		String content = parse(colnames, colTypes);
		try {

			String filename = ConfigurationManager.getGenFileDir() + "\\"
					+ initcap(classname) + ".java";

			FileWriter fw = new FileWriter(filename);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(content);
			pw.flush();
			pw.close();

			System.out.println("生成model类完毕!\n" + "【" + filename + "】");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析处理(生成实体类主体代码)
	 */
	private static String parse(String[] colNames, String[] colTypes) {

		StringBuffer sb = new StringBuffer();

		String packagestr = "package " + NameGetter.project_top_packagename
				+ ".modules." + modulename + ".model;";
		String importstr = "import " + NameGetter.project_top_packagename
				+ ".modules.base.BaseModel; \r\n";

		sb.append(packagestr);
		sb.append("\r\n");
		sb.append("\r\n");

		sb.append(importstr);
		sb.append("\r\n");
		sb.append("\r\n");
		sb.append("\r\n");
		sb.append("public class " + initcap(classname)
				+ " extends BaseModel  {\r\n");
		processAllAttrs(sb);
		processAllMethod(sb);
		sb.append("}\r\n");
		// System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * 生成所有的方法
	 * 
	 * @param sb
	 */
	private static void processAllMethod(StringBuffer sb) {
		sb.append("\r\n");
		for (int i = 0; i < colnames.length; i++) {
			sb.append("\tpublic void set" + initcap(colnames[i]) + "("
					+ type2JavaType(colTypes[i]) + " " + colnames[i] + "){\r\n");
			sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
			sb.append("\t}\r\n");

			sb.append("\tpublic " + type2JavaType(colTypes[i]) + " get"
					+ initcap(colnames[i]) + "(){\r\n");
			sb.append("\t\treturn " + colnames[i] + ";\r\n");
			sb.append("\t}\r\n");
		}
	}

	/**
	 * 解析输出属性
	 * 
	 * @return
	 */
	private static void processAllAttrs(StringBuffer sb) {
		sb.append("\r\n");
		for (int i = 0; i < colnames.length; i++) {
			sb.append("\tprivate " + type2JavaType(colTypes[i]) + " "
					+ colnames[i] + ";");

			sb.append(" \\\\" + colRmks[i]);

			sb.append("\r\n");
		}
	}

	/**
	 * 把输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private static String initcap(String str) {
		return StringUtils.capitalize(str);
	}

	private static String type2JavaType(String sqlType) {
		if (sqlType.equalsIgnoreCase("int")) {
			return "int";
		} else if (sqlType.equalsIgnoreCase("double")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("string")) {
			return "String";
		}
		return "String";
	}

	public static void main(String[] args) {

		EntityModel model = new EntityModel();
		model.setModule("user");
		model.setClassname("User");
		model.setClassRmk("用户");

		model.setFields(EntityModel.getFieldInfoForTest());

		GenEntityFromModel.genEntityFile(model);
	}

}