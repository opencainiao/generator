package controller;

import generator.db.ITableService;
import generator.generators.AllGenerator;
import generator.model.ClassTable;
import generator.model.EntityModel;
import generator.model.Field;
import generator.model.FieldTable;
import generator.model.RequestResult;
import generator.util.ConfigurationManager;
import generator.util.HttpServletRequestUtil;
import generator.util.PageSearchResultHandler;
import generator.util.ValidateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mou.common.JsonUtil;
import org.mou.common.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/****
 * 生成所有的系统常量对应的select<br>
 * 如:
 * 
 * <pre>
 * <select id="education_type" class="form-control" data-value="222222" name="education_type">
 *	  <option value="1">高中及以下</option>
 *	  <option value="2">大专</option>
 *	  <option value="3">本科</option>
 *	  <option value="4">硕士及以上</option>
 * </select>
 * </pre>
 * 
 * @author NBQ
 *
 */
@Controller
public class GenSysConstSelectController extends BaseController {

	private static final Logger logger = LogManager.getLogger(GenSysConstSelectController.class);

	@Resource(name = "iTableService")
	private ITableService iTableService;

	/****
	 * 查看所有系统实体类 信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {

		return "/gen/list";
	}

	/****
	 * 查询系统实体类信息（条件查询，查询多笔，按照系统实体类码或名称）
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(Model model, HttpServletRequest request) {

		HttpServletRequestUtil.debugParams(request);
		try {

			Map<String, String> params = HttpServletRequestUtil.getParamsMap(request);

			String classmodule = params.get("classmodule");// 模块名
			String classname = params.get("classname"); // 类名

			List<ClassTable> allClasses = this.iTableService.findBatch(classmodule, classname);

			return PageSearchResultHandler.handleDBObjListNoPage(allClasses);

		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查询系统实体类信息（条件查询，查询多笔，按照系统实体类码或名称）
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(Model model, String classmodule, String classname, HttpServletRequest request) {

		String classrmk = "";
		HttpServletRequestUtil.debugParams(request);
		try {

			List<FieldTable> ft = this.iTableService.findClassFields(classmodule, classname);

			if (ft.size() > 0) {
				classrmk = ft.get(0).getClassrmk();
			}

			model.addAttribute("classrmk", classrmk);
			model.addAttribute("classmodule", classmodule);
			model.addAttribute("classname", classname);

			model.addAttribute("fields", JsonUtil.toJsonStr(ft));

			logger.debug(JsonUtil.toJsonStr(ft));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/gen/detail";
	}

	/****
	 * 进入前端查询单位页面 <br>
	 * http://localhost:8080/generator/genhome
	 * 
	 * @return
	 */
	@RequestMapping(value = "/genhome", method = RequestMethod.GET)
	public String searchSpecification(Model model) {

		return "/gen/togenerate";
	}

	/****
	 * 生成代码
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	@ResponseBody
	public Object generate(Model model, HttpServletRequest request) {

		HttpServletRequestUtil.debugParams(request);

		try {
			List<String> errors = new ArrayList<String>();

			EntityModel entityModel = createModel(request, errors);

			if (!errors.isEmpty()) {
				return this.handleValidateFalse(errors.toString());
			}

			iTableService.savaTable(entityModel);
			AllGenerator.genAllFiles(entityModel);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(ConfigurationManager.getGenFileDir(entityModel.getClassmodule(), entityModel.getClassname()));
			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 根据request构建实体模型
	 * 
	 * @param request
	 * @return
	 */
	private EntityModel createModel(HttpServletRequest request, List<String> errors) {

		Map<String, String> params = HttpServletRequestUtil.getParamsMap(request);

		logger.debug(params);

		Map<String, String> titleMap = new HashMap<String, String>();
		Map<String, String> nameMap = new HashMap<String, String>();
		Map<String, String> typeMap = new HashMap<String, String>();

		String orderstr = params.get("order");
		logger.debug("orderstr【{}】", orderstr);
		Map<String, String> orderMap = JsonUtil.fromJson(orderstr, Map.class);

		Set<String> setKeysOrdered = new TreeSet<String>();

		Set<String> keysParam = params.keySet();
		for (String key : keysParam) {

			String orderno = key.substring(key.lastIndexOf("_") + 1);

			if (ValidateUtil.isNumeric(orderno)) {
				setKeysOrdered.add(orderno);

				if (key.indexOf("coltitle") >= 0) {
					titleMap.put(orderno, params.get(key));
				} else if (key.indexOf("colname") >= 0) {
					nameMap.put(orderno, params.get(key));
				} else if (key.indexOf("coltype") >= 0) {
					typeMap.put(orderno, params.get(key));
				}
			}
		}

		logger.debug("title:\n【{}】", titleMap);
		logger.debug("name:\n【{}】", nameMap);
		logger.debug("type:\n【{}】", typeMap);
		logger.debug("order:\n【{}】", orderMap);

		logger.debug(setKeysOrdered);

		List<Field> fields = new ArrayList<Field>();

		Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();
		Set<Integer> orderSet = new TreeSet<Integer>();
		for (String ordernum : setKeysOrdered) {
			Field field = new Field();

			int no = Integer.parseInt(orderMap.get(ordernum));
			orderSet.add(no);
			field.setColorder(no);

			StringBuffer sb = new StringBuffer();

			String title = titleMap.get(ordernum);
			if (StringUtil.isEmpty(title)) {
				sb.append("英文名不能为空,");
			} else if (!ValidateUtil.isOnlyCharacterOrXhx(title)) {
				sb.append("英文名只能为字母和下划线组合,");
			}
			field.setColtitle(titleMap.get(ordernum));

			if (StringUtil.isEmpty(nameMap.get(ordernum))) {
				sb.append("中文名不能为空,");
			}
			field.setColname(nameMap.get(ordernum));

			if (sb.length() > 0) {
				errors.add("【" + no + "】" + sb.substring(0, sb.length() - 1) + "\n");
			}

			EntityModel.setFieldType(field, typeMap.get(ordernum));

			fieldsMap.put(no, field);
		}

		for (Integer order : orderSet) {
			fields.add(fieldsMap.get(order));
		}

		logger.debug("fields======================\n");
		for (Field field : fields) {
			logger.debug(field);
		}

		EntityModel model = new EntityModel();
		model.setClassmodule(params.get("classmodule").toLowerCase());
		model.setClassname(StringUtils.capitalize(params.get("classname")));
		model.setClassrmk(params.get("classrmk"));

		model.setFields(fields);

		logger.debug("model==========================================================");
		logger.debug(model);

		return model;
	}
}
