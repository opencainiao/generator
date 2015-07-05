package generator.util;

import generator.model.PageVO;

import java.util.List;

/****
 * 对分页查询的结果进行封装处理
 * 
 * @author NBQ
 *
 */
public class PageSearchResultHandler {

	/****
	 * 根据数据，组装flexigrid需要的结果（无分页的情况）
	 * 
	 * @param list
	 * @return
	 */
	public static PageVO handleDBObjListNoPage(List list) {

		PageVO pageVO = new PageVO();
		if (list != null) {
			pageVO.setPage(1);
			pageVO.setPageCount(list.size());
			pageVO.setTotal(list.size());

			pageVO.setRows(list);
		}

		return pageVO;
	}
}
