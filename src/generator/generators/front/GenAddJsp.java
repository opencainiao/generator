package generator.generators.front;

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
public class GenAddJsp {

	private static final Logger logger = LogManager.getLogger(GenAddJsp.class);

	/****
	 * 
	 * 
	 * @param clazz
	 * @param jspfoldername
	 *            jsp文件存放的父目录的父目录名称
	 * @param domainname
	 *            表的中文名
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void genFile(EntityModel model, String jspfoldername,
			String domainname, String controllerparentpath) throws IOException,
			TemplateException {

		Configuration cfg = ConfigurationManager.getConfiguration();
		Template temp = cfg.getTemplate("add.tpl");

		Map data = getData(model);

		data.put("jspfoldername", jspfoldername);
		data.put("domainname", domainname);
		data.put("controllerparentpath", controllerparentpath);
		data.put("fields", model.getFields());

		logger.debug("data\n{}", data);

		String filename = ConfigurationManager.getGenFileDir(
				model.getClassmodule(), model.getClassname())
				+ File.separator + data.get("addfilename");

		File file = new File(filename);
		FileUtil.ensureNewFile(file);

		// Writer out = new OutputStreamWriter(System.out);
		Writer out = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
		temp.process(data, out);

		logger.debug("\n生成文件完毕\n{}", filename);

	}

	/****
	 * 
	 * @param model
	 * @return
	 */
	public static Map getData(EntityModel model) {

		Map data = NameGetter.getNames(model);

		return data;
	}

	public static void main(String[] args) {

		EntityModel model = new EntityModel();
		model.setClassmodule("user");
		model.setClassname("User");
		model.setClassrmk("用户");

		model.setFields(EntityModel.getFieldInfoForTest());

		try {
			genFile(model, "admin", model.getClassrmk(), "backend");
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
	}
}
