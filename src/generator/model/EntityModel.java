package generator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mou.common.JsonUtil;

/****
 * 类模型
 * 
 * @author NBQ
 *
 */
public class EntityModel {

	private String module;// 模块名
	private String unitname; // 模块内的单元名
	private String classname; // 类名
	private String classRmk;// 类说明
	private List<Field> fields;// 字段信息

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	/****
	 * 根据fields，生成列的信息(英文名，中文名，字段类型)
	 * 
	 * @param fields
	 * @return
	 */
	public static Map<String, String[]> genFieldInfo(List<Field> fields) {
		Map<String, String[]> fieldInfo = new HashMap<String, String[]>();

		String[] colNames = new String[fields.size()]; // 列名数组
		String[] colTypes = new String[fields.size()]; // 列名类型数组
		String[] colRmks = new String[fields.size()];// 列名中文说明

		for (int i = 0; i < fields.size(); ++i) {
			Field field = fields.get(i);

			int no = field.getNo();

			colNames[i] = field.getTitle();
			colTypes[i] = field.getType();
			colRmks[i] = field.getName();
		}

		fieldInfo.put("COLNAMES", colNames);
		fieldInfo.put("COLRMKS", colRmks);
		fieldInfo.put("COLTYPES", colTypes);

		return fieldInfo;
	}

	public static void main(String[] args) {

		List<Field> fields = EntityModel.getFieldInfoForTest();
		Map<String, String[]> fieldInfo = EntityModel.genFieldInfo(fields);

		System.out.println(fieldInfo);
		System.out.println(JsonUtil.getFormatJsonStr(fields));

	}

	public static List<Field> getFieldInfoForTest() {
		List<Field> fields = new ArrayList<Field>();

		for (int i = 0; i < 5; ++i) {
			Field field = new Field();
			field.setNo(i);
			field.setName("名称_" + i);
			field.setTitle("col_" + i);
			setFieldType(field, i);

			fields.add(field);
		}
		return fields;
	}

	public static void setFieldType(Field field, String type) {

		String typeL = type.toLowerCase();
		switch (typeL) {
		case "string":
			field.setType("string");
			break;
		case "double":
			field.setType("double");
			break;
		case "int":
			field.setType("int");
			break;
		default:
			field.setType("string");
			break;
		}
	}
	
	public static void setFieldType(Field field, int i) {

		int mol = i % 5;

		switch (mol) {
		case 0:
			field.setType("string");
			break;
		case 1:
			field.setType("double");
			break;
		case 2:
			field.setType("int");
			break;
		default:
			field.setType("string");
			break;
		}
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getClassRmk() {
		return classRmk;
	}

	public void setClassRmk(String classRmk) {
		this.classRmk = classRmk;
	}
}
