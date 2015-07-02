package generator.generators;

import generator.model.EntityModel;
import generator.model.Field;
import generator.util.ConfigurationManager;
import generator.util.NameGetter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllGenerator {

	private static final Logger logger = LogManager.getLogger(NameGetter.class);

	public static void genAllFiles(EntityModel model) throws Exception {
		String jspfoldername = "admin";
		String controllerparentpath = "backend";

		BackendGenerator.genBackend(model, jspfoldername, model.getClassRmk(),
				controllerparentpath);
		FrontGenerator.genFront(model, jspfoldername, model.getClassRmk(),
				controllerparentpath);

		logger.info("生成所有文件完毕！");
		logger.info("文件目录\n{}\n", ConfigurationManager.getGenFileDir());

	}

	public static void main(String[] args) throws Exception {

		EntityModel model = createModel();

		AllGenerator.genAllFiles(model);

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
