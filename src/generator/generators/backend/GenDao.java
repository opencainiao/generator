package generator.generators.backend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import generator.model.EntityModel;
import generator.util.ConfigurationManager;
import generator.util.FileUtil;
import generator.util.NameGetter;

/****
 * Dao层生成器
 * 
 * @author NBQ
 *
 */
public class GenDao {
	private static final Logger logger = LogManager.getLogger(GenDao.class);

	public static void genDaoFile(Class clazz) throws IOException,
			TemplateException {
		Configuration cfg = ConfigurationManager.getConfiguration();
		Template temp = cfg.getTemplate("dao.tpl");

		Map data = getData(clazz);

		String filename = ConfigurationManager.getGenFileDir() + "\\"
				+ data.get("daofilename");

		File file = new File(filename);
		FileUtil.ensureNewFile(file);

		// Writer out = new OutputStreamWriter(System.out);
		Writer out = new OutputStreamWriter(new FileOutputStream(file));
		temp.process(data, out);

		logger.debug("\n生成Dao完毕\n{}", filename);
	}

	/****
	 * 生成一个类对应的Dao类
	 * 
	 * @param clazz
	 */
	public static Map getData(Class clazz) {

		return NameGetter.getNames(clazz);
	}

	/****
	 * 生成一个类对应的Dao类
	 * 
	 * @param clazz
	 */
	public static Map getData(EntityModel model) {

		return NameGetter.getNames(model);
	}

	public static void genDaoFile(EntityModel model) throws IOException,
			TemplateException {
		Configuration cfg = ConfigurationManager.getConfiguration();
		Template temp = cfg.getTemplate("dao.tpl");

		Map data = getData(model);

		String filename = ConfigurationManager.getGenFileDir() + "\\"
				+ data.get("daofilename");

		File file = new File(filename);
		FileUtil.ensureNewFile(file);

		// Writer out = new OutputStreamWriter(System.out);
		Writer out = new OutputStreamWriter(new FileOutputStream(file));
		temp.process(data, out);

		logger.debug("\n生成Dao完毕\n{}", filename);
	}

	public static void main(String[] args) throws IOException,
			TemplateException {
		// genDaoFile(SysConst.class);

		EntityModel model = new EntityModel();
		model.setClassmodule("user");
		model.setClassname("User");
		model.setClassrmk("用户");
		
		model.setFields(EntityModel.getFieldInfoForTest());
		
		genDaoFile(model);
	}

}
