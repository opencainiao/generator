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
public class GenController {

	private static final Logger logger = LogManager
			.getLogger(GenController.class);

	/****
	 * 
	 * 
	 * @param clazz
	 * @param jspfoldername
	 *            jsp文件存放的父目录名称
	 * @param domainname
	 *            表的中文名
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void genController(Class clazz, String jspfoldername,
			String domainname, String controllerparentpath) throws IOException,
			TemplateException {
		Configuration cfg = ConfigurationManager.getConfiguration();
		Template temp = cfg.getTemplate("Controller.tpl");

		Map data = getData(clazz);

		data.put("jspfoldername", jspfoldername);
		data.put("domainname", domainname);
		data.put("controllerparentpath", controllerparentpath);

		logger.debug("data\n{}", data);

		String filename = ConfigurationManager.getGenFileDir() + "\\"
				+ data.get("controllerfilename");

		File file = new File(filename);
		FileUtil.ensureNewFile(file);

		// Writer out = new OutputStreamWriter(System.out);
		Writer out = new OutputStreamWriter(new FileOutputStream(file));
		temp.process(data, out);

		logger.debug("\n生成文件完毕\n{}", filename);

	}

	/****
	 * @param clazz
	 */
	public static Map getData(Class clazz) {

		Map data = NameGetter.getNames(clazz);

		return data;
	}

	/****
	 * 
	 * 
	 * @param clazz
	 * @param jspfoldername
	 *            jsp文件存放的父目录名称
	 * @param domainname
	 *            表的中文名
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void genController(EntityModel model, String jspfoldername,
			String domainname, String controllerparentpath) throws IOException,
			TemplateException {
		Configuration cfg = ConfigurationManager.getConfiguration();
		Template temp = cfg.getTemplate("Controller.tpl");

		Map data = getData(model);

		data.put("jspfoldername", jspfoldername);
		data.put("domainname", domainname);
		data.put("controllerparentpath", controllerparentpath);

		logger.debug("data\n{}", data);

		String filename = ConfigurationManager.getGenFileDir() + "\\"
				+ data.get("controllerfilename");

		File file = new File(filename);
		FileUtil.ensureNewFile(file);

		// Writer out = new OutputStreamWriter(System.out);
		Writer out = new OutputStreamWriter(new FileOutputStream(file));
		temp.process(data, out);

		logger.debug("\n生成文件完毕\n{}\n文件目录:\n{}", filename,
				FileUtil.getAbsolutePathParent(filename));

	}

	/****
	 * @param clazz
	 */
	public static Map getData(EntityModel model) {

		Map data = NameGetter.getNames(model);

		return data;
	}

	public static void main(String[] args) throws IOException,
			TemplateException {

		// genController(SysConst.class,"admin","常量","backend");

		EntityModel model = new EntityModel();
		model.setClassmodule("user");
		model.setClassname("User");
		model.setClassrmk("用户");

		model.setFields(EntityModel.getFieldInfoForTest());

		genController(model, "admin", model.getClassrmk(), "backend");

	}

}
