package controller;

import generator.model.RequestResult;

import org.mou.common.StringUtil;

public class BaseController {

	public RequestResult handleException(Exception e) {

		e.printStackTrace();
		RequestResult rr = new RequestResult();
		rr.setSuccess(false);
		rr.setMessage(StringUtil.getStackTrace(e));

		return rr;
	}

	/****
	 * 对校验不合法的情况进行处理，生成对应的返回对象
	 * 
	 * @param message
	 * @return
	 */
	public RequestResult handleValidateFalse(String message) {

		RequestResult rr = new RequestResult();
		rr.setSuccess(false);
		rr.setMessage(message);

		return rr;
	}

}
