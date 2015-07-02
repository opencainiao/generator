package generator.generators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import freemarker.template.TemplateException;
import generator.generators.backend.GenController;
import generator.generators.backend.GenDao;
import generator.generators.backend.GenEntityFromModel;
import generator.generators.backend.GenService;
import generator.model.Field;
import generator.model.EntityModel;

public class BackendGenerator {

	/****
	 * 生成后端
	 * 
	 * @param clazz
	 * @param jspfoldername
	 * @param domainname
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void genBackend(EntityModel model, String jspfoldername,
			String domainname, String controllerparentpath) throws IOException,
			TemplateException {

		GenEntityFromModel.genEntityFile(model);
		
		GenDao.genDaoFile(model);

		GenService.genServiceAndInterface(model);

		GenController.genController(model, jspfoldername, domainname,
				controllerparentpath);
	}

	public static void main(String[] args) throws IOException,
			TemplateException {

		EntityModel model = createModel();

		BackendGenerator.genBackend(model, "admin", model.getClassRmk(),
				"backend");
	}

	/****
	 * 创建模型
	 * 
	 * @return
	 */
	private static EntityModel createModel() {
		EntityModel model = new EntityModel();
		model.setModule("user");
		model.setClassname("User");
		model.setClassRmk("用户");

		List<Field> fields = new ArrayList<Field>();

		for (int i = 0; i < 5; ++i) {
			Field field = new Field();
			field.setNo(i);
			field.setName("名称_" + i);
			field.setTitle("col_" + i);
			EntityModel.setFieldType(field, i);

			fields.add(field);
		}

		model.setFields(fields);

		return model;
	}
}
