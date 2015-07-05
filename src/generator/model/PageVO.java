package generator.model;

import java.util.List;

import org.mou.common.JsonUtil;

@SuppressWarnings("rawtypes")
public class PageVO {
	private int total; // 总记录数
	private int page; // 当前是第几页
	private int maxPage; // 总共多少页
	private int pageCount; // 每页多少条

	private int offSet; // 查询起始条数(包含该条)
	private List rows; // 数据

	public PageVO() {
		offSet = 0;
		pageCount = Integer.MAX_VALUE;
	}

	/****
	 * 根据传入的当前页码，计算查询条件中的OFFSET
	 */
	public void calOffset() {
		this.offSet = this.page * this.pageCount;
	}

	/****
	 * 根据起始条数，计算当前是第几页
	 */
	private void calCurrentPage() {
		page = offSet / pageCount + 1;
	}

	/****
	 * 根据总记录数，计算共有多少页
	 */
	public void calMaxPage() {
		maxPage = total / pageCount;

		if (total % pageCount != 0) {
			maxPage += 1;
		}
	}

	/****
	 * 根据记录数信息，计算页面信息
	 */
	public void calPageInf() {
		calMaxPage();
		calCurrentPage();
	}

	public int getOffSet() {
		return offSet;
	}

	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int totalCount) {
		this.total = totalCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int currentPage) {
		this.page = currentPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String toString() {
		return JsonUtil.toJsonStr(this);
	}
}
