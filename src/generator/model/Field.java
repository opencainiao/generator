package generator.model;

import org.mou.common.JsonUtil;

/****
 * 类对象的一个字段
 * 
 * @author NBQ
 *
 */
public class Field {

	private int no; // 序号
	private String type; // 类型
	private String title; // 字段名
	private String name; // 字段中文说明

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return JsonUtil.toJsonStr(this);
	}
}
