package generator.generators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import freemarker.template.TemplateException;
import generator.generators.front.GenAddJsp;
import generator.generators.front.GenDetailJsp;
import generator.generators.front.GenEditJsp;
import generator.generators.front.GenListJs;
import generator.generators.front.GenListJsp;
import generator.model.Field;
import generator.model.EntityModel;

public class FrontGenerator {

	/****
	 * 生成前
	 * 
	 * @param clazz
	 * @param jspfoldername
	 * @param domainname
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void genFront(EntityModel model, String jspfoldername,
			String domainname, String controllerparentpath) throws IOException,
			TemplateException {

		GenAddJsp.genFile(model, jspfoldername, domainname,
				controllerparentpath);
		GenEditJsp.genFile(model, jspfoldername, domainname,
				controllerparentpath);
		GenDetailJsp.genFile(model, jspfoldername, domainname,
				controllerparentpath);
		GenListJsp.genFile(model, jspfoldername, domainname,
				controllerparentpath);
		GenListJs.genFile(model, jspfoldername, domainname,
				controllerparentpath);
	}

	public static void main(String[] args) throws IOException,
			TemplateException {

		EntityModel model = createModel();

		FrontGenerator.genFront(model, "admin", model.getClassrmk(), "backend");

	}

	/****
	 * 创建模型
	 * 
	 * @return
	 */
	private static EntityModel createModel() {
		EntityModel model = new EntityModel();
		model.setClassmodule("user");
		model.setClassname("User");
		model.setClassmodule("用户");
		
		List<Field> fields = new ArrayList<Field>();

		for (int i = 0; i < 5; ++i) {
			Field field = new Field();
			field.setColorder(i);
			field.setColname("名称_" + i);
			field.setColtitle("col_" + i);
			EntityModel.setFieldType(field, i);

			fields.add(field);
		}

		model.setFields(fields);

		return model;
	}
}
