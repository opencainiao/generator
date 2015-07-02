package generator.util;

import java.io.File;
import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class ConfigurationManager {

	private static Configuration cfg;
	private static String tmpDir; // 模板目录
	private static String genFileDir; // 文件生成目录

	static {

		String classDir = ConfigurationManager.class.getResource("/").getPath();
		System.out.println("classes目录\n" + classDir);

		// tmpDir = "F:\\bx\\wk_space\\build\\classes\\generator\\tpl";

		tmpDir = classDir + "\\generator\\tpl";

		genFileDir = "F:\\bx\\gen";

		FileUtil.createDir(genFileDir);
	}

	public static Configuration getConfiguration() throws IOException {

		if (cfg != null) {
			return cfg;
		}

		// Create your Configuration instance, and specify if up to what
		// FreeMarker
		// version (here 2.3.22) do you want to apply the fixes that are not
		// 100%
		// backward-compatible. See the Configuration JavaDoc for details.
		cfg = new Configuration(Configuration.VERSION_2_3_22);

		// Specify the source where the template files come from. Here I set a
		// plain directory for it, but non-file-system sources are possible too:
		cfg.setDirectoryForTemplateLoading(new File(tmpDir));

		// Set the preferred charset template files are stored in. UTF-8 is
		// a good choice in most applications:
		cfg.setDefaultEncoding("UTF-8");

		// Sets how errors will appear.
		// During web page *development*
		// TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		return cfg;
	}

	public static String getTmpDir() {
		return tmpDir;
	}

	public static void setTmpDir(String tmpDir) {
		ConfigurationManager.tmpDir = tmpDir;
	}

	public static String getGenFileDir() {
		return genFileDir;
	}

	public static void setGenFileDir(String genFileDir) {
		ConfigurationManager.genFileDir = genFileDir;
	}
}
