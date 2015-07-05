package generator.generators.backend;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import generator.model.EntityModel;
import generator.util.ConfigurationManager;
import generator.util.FileUtil;
import generator.util.NameGetter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/****
 * Dao层生成器
 * 
 * @author NBQ
 *
 */
public class GenService {

	private static final Logger logger = LogManager.getLogger(GenService.class);

	/****
	 * 
	 * @param clazz
	 */
	public static Map getData(Class clazz) {

		return NameGetter.getNames(clazz);
	}

	public static Map getData(EntityModel model) {

		return NameGetter.getNames(model);
	}

	public static void genInterface(EntityModel model) throws IOException,
			TemplateException {
		Configuration cfg = ConfigurationManager.getConfiguration();
		Template temp = cfg.getTemplate("ISeivice.tpl");

		Map data = getData(model);

		logger.debug("data\n{}", data);

		String filename = ConfigurationManager.getGenFileDir(
				model.getClassmodule(), model.getClassname())
				+ File.separator + data.get("serviceinterfacefilename");

		File file = new File(filename);
		FileUtil.ensureNewFile(file);

		// Writer out = new OutputStreamWriter(System.out);
		Writer out = new OutputStreamWriter(new FileOutputStream(file));
		temp.process(data, out);

		logger.debug("\n生成文件完毕\n{}\n文件目录:\n{}", filename,
				FileUtil.getAbsolutePathParent(filename));

	}

	public static void genService(EntityModel model) throws IOException,
			TemplateException {
		Configuration cfg = ConfigurationManager.getConfiguration();
		Template temp = cfg.getTemplate("Service.tpl");

		Map data = getData(model);

		logger.debug("data\n{}", data);

		String filename = ConfigurationManager.getGenFileDir(
				model.getClassmodule(), model.getClassname())
				+ File.separator + data.get("servicefilename");

		File file = new File(filename);
		FileUtil.ensureNewFile(file);

		// Writer out = new OutputStreamWriter(System.out);
		Writer out = new OutputStreamWriter(new FileOutputStream(file));
		temp.process(data, out);

		logger.debug("\n生成文件完毕\n{}\n文件目录:\n{}", filename,
				FileUtil.getAbsolutePathParent(filename));

	}

	/****
	 * 生成service
	 * 
	 * @param model
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void genServiceAndInterface(EntityModel model)
			throws IOException, TemplateException {
		genInterface(model);
		genService(model);
	}

	public static void main(String[] args) throws IOException,
			TemplateException {

		// genInterface(SysConst.class);
		// genService(SysConst.class);

		EntityModel model = new EntityModel();
		model.setClassmodule("user");
		model.setClassname("User");
		model.setClassrmk("用户");

		model.setFields(EntityModel.getFieldInfoForTest());

		genServiceAndInterface(model);

	}

}
