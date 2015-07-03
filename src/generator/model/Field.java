package generator.model;

import org.mou.common.JsonUtil;

/****
 * 类对象的一个字段
 * 
 * @author NBQ
 *
 */
public class Field {

	private String coltitle; // 字段名
	private String colname; // 字段中文说明
	private int colorder; // 序号
	private String coltype; // 类型

	public String getColtitle() {
		return coltitle;
	}

	public void setColtitle(String coltitle) {
		this.coltitle = coltitle;
	}

	public String getColname() {
		return colname;
	}

	public void setColname(String colname) {
		this.colname = colname;
	}

	public int getColorder() {
		return colorder;
	}

	public void setColorder(int colorder) {
		this.colorder = colorder;
	}

	public String getColtype() {
		return coltype;
	}

	public void setColtype(String coltype) {
		this.coltype = coltype;
	}

	@Override
	public String toString() {
		return JsonUtil.toJsonStr(this);
	}
}
