package ${controllerpackagename};

import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mou.common.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bxb.common.globalhandler.ErrorHandler;
import com.bxb.common.globalobj.RequestResult;
import com.bxb.common.util.HttpServletRequestUtil;
import com.bxb.common.util.RegexPatternUtil;
import com.bxb.controller.global.BaseController;

import ${domainfullname};
import ${serviceinterfacefullname};

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * ${domainname}管理Controller
 * 
 * @author NBQ
 *
 */
@Controller
@RequestMapping("/${controllerparentpath}/${bussnesdomainnameL}")
public class ${bussnesdomainname}Controller extends BaseController {

	private static final Logger logger = LogManager
			.getLogger(${bussnesdomainname}Controller.class);

	@Resource(name = "${servicezhujiename}")
	private ${serviceinterfacename} ${servicezhujiename};

	/****
	 * 进入添加${domainname}页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(
			@ModelAttribute("${bussnesdomainnameL}") ${bussnesdomainname} ${bussnesdomainnameL},
			HttpServletRequest request) {

		// 开启modelDriven
		return "${jspfoldername}/${bussnessmodule}/${bussnesdomainnameL}/add";
	}

	/****
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(@Validated ${bussnesdomainname} ${bussnesdomainnameL}, BindingResult br,
			HttpServletRequest request) {

		HttpServletRequestUtil.debugParams(request);

		logger.debug("传入的${domainname}对象\n{}", ${bussnesdomainnameL});

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}
		try {
			// 1.校验是否已存在相同的类型码
			boolean isExist = this.${servicezhujiename}
					.isExistSameTypecode(${bussnesdomainnameL}.getTypecode());
			if (isExist) {
				RequestResult rr = new RequestResult();
				rr.setSuccess(false);
				rr.setMessage("已经存在类型码【" + ${bussnesdomainnameL}.getTypecode().trim()
						+ "】的${domainname}!");
				return rr;
			}

			isExist = this.${servicezhujiename}.isExistSameTypename(${bussnesdomainnameL}
					.getTypename());
			if (isExist) {
				RequestResult rr = new RequestResult();
				rr.setSuccess(false);
				rr.setMessage("已经存在类型名【" + ${bussnesdomainnameL}.getTypename().trim()
						+ "】的${domainname}!");
				return rr;
			}

			// 2.新增
			${bussnesdomainnameL}.setUseflg("1");
			String _id = this.${servicezhujiename}.add(${bussnesdomainnameL});

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);

			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查看所有系统${domainname} 信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {

		return "${jspfoldername}/${bussnessmodule}/${bussnesdomainnameL}/list";
	}

	/****
	 * 查询系统${domainname}信息（条件查询，查询多笔，按照系统${domainname}码或名称）
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

			HttpServletRequestUtil.debugParams(request);

			String search_condition = request.getParameter("search_condition");
			if (StringUtil.isNotEmpty(search_condition)) {
				search_condition = search_condition.trim();
			}

			DBObject query = new BasicDBObject();

			if (StringUtil.isNotEmpty(search_condition)) {
				Pattern pattern = RegexPatternUtil
						.getLikePattern(search_condition);

				BasicDBList condList = new BasicDBList();

				condList.add(new BasicDBObject("typename", pattern));
				condList.add(new BasicDBObject("typecode", pattern));

				query.put("$or", condList);
			}
			query.put("useflg", "1");

			DBObject sort = new BasicDBObject();
			sort.put("typename", 1);
			DBObject returnFields = null;

			return this.${servicezhujiename}.batchSearchPage(query, sort,
					returnFields);

		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 查看单个${domainname} 信息
	 * 
	 * @param _id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{_id}", method = RequestMethod.GET)
	public String detail(@PathVariable String _id, Model model) {

		${bussnesdomainname} ${bussnesdomainnameL} = this.${servicezhujiename}
				.findOneByIdObject(_id);

		model.addAttribute("${bussnesdomainnameL}", ${bussnesdomainnameL});

		return "${jspfoldername}/${bussnessmodule}/${bussnesdomainnameL}/detail";
	}

	/****
	 * 进入更新页面
	 * 
	 * @param zzdhid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{_id}/update", method = RequestMethod.GET)
	public String update(@PathVariable String _id, Model model) {

		${bussnesdomainname} ${bussnesdomainnameL} = this.${servicezhujiename}
				.findOneByIdObject(_id);

		model.addAttribute("${bussnesdomainnameL}", ${bussnesdomainnameL});

		model.addAttribute("_id", _id);

		return "${jspfoldername}/${bussnessmodule}/${bussnesdomainnameL}/update";
	}

	/****
	 * 更新系统${domainname} 信息，返回json给客户端
	 * 
	 * @param _id
	 * @param ${bussnesdomainnameL}
	 * @param br
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{_id}/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable String _id,
			@Validated ${bussnesdomainname} ${bussnesdomainnameL}, BindingResult br,
			HttpServletRequest request) {

		if (br.hasErrors()) {
			return ErrorHandler.getRequestResultFromBindingResult(br);
		}

		String name = ${bussnesdomainnameL}.getTypename();
		if (StringUtil.isEmpty(name)) {
			return handleValidateFalse("${domainname}名称不能为空");
		}

		name = name.trim();

		${bussnesdomainname} sysconsttypeori = this.${servicezhujiename}
				.findOneByIdObject(_id);
		String nameOri = ${bussnesdomainnameL}.getTypename();

		if (nameOri.equals(name)) {
			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);
			return rr;
		}

		boolean isExitByName = this.${servicezhujiename}
				.isExistSameTypename(name);
		if (isExitByName) {
			RequestResult rr = new RequestResult();
			rr.setSuccess(false);
			rr.setMessage("已经存在名称为【" + name + "】的${domainname}!");
			return rr;
		}

		try {
			${bussnesdomainnameL}.set_id(_id);
			DBObject updateResult = this.${servicezhujiename}.updatePart(null,
					${bussnesdomainnameL});

			logger.debug("更新后的结果[{}]", updateResult);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);
			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}

	/****
	 * 删除一条记录
	 * 
	 * @param zzdhid
	 * @return
	 */
	@RequestMapping(value = "/{_id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(@PathVariable String _id, HttpServletRequest request) {

		try {

			this.${servicezhujiename}.RemoveOneByIdLogic(_id);

			RequestResult rr = new RequestResult();
			rr.setSuccess(true);
			rr.setMessage(_id);
			return rr;
		} catch (Exception e) {
			return this.handleException(e);
		}
	}
}
