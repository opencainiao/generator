package controller;

import generator.generators.AllGenerator;
import generator.model.EntityModel;
import generator.model.Field;
import generator.model.RequestResult;
import generator.util.ConfigurationManager;
import generator.util.HttpServletRequestUtil;
import generator.util.ValidateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
 * 代码生成器<br>
 * 
 * @author NBQ
 *
 */
@Controller
public class GenController extends BaseController {

	private static final Logger logger = LogManager
			.getLogger(GenController.class);

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
			
			EntityModel entityModel = createModel(request ,errors);

			if (!errors.isEmpty()){
				return this.handleValidateFalse(errors.toString());
			}
			
			AllGenerator.genAllFiles(entityModel);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(ConfigurationManager.getGenFileDir());
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
	private EntityModel createModel(HttpServletRequest request,
			List<String> errors) {

		Map<String, String> params = HttpServletRequestUtil
				.getParamsMap(request);

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

			String orderno = key.substring(key.length() - 1);

			if (ValidateUtil.isNumeric(orderno)) {
				setKeysOrdered.add(orderno);

				if (key.indexOf("title") >= 0) {
					titleMap.put(orderno, params.get(key));
				} else if (key.indexOf("name") >= 0) {
					nameMap.put(orderno, params.get(key));
				} else if (key.indexOf("type") >= 0) {
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
			field.setNo(no);
			
			StringBuffer sb = new StringBuffer();

			String title = titleMap.get(ordernum);
			if (StringUtil.isEmpty(title)) {
				sb.append("英文名不能为空,");
			}
			if (!ValidateUtil.isOnlyCharacter(title)){
				sb.append("英文名只能为字母,");
			}
			field.setTitle(titleMap.get(ordernum));

			if (StringUtil.isEmpty(nameMap.get(ordernum))) {
				sb.append("中文名不能为空,");
			}
			field.setName(nameMap.get(ordernum));
			
			if (sb.length()>0){
				errors.add("【" + no + "】" + sb.substring(0, sb.length()-1) + "\n");
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
		model.setModule(params.get("module").toLowerCase());
		model.setClassname(StringUtils.capitalize(params.get("classname")));
		model.setClassRmk(params.get("classrmk"));

		model.setFields(fields);

		logger.debug("model==========================================================");
		logger.debug(model);

		return model;
	}
}
