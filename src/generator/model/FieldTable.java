package generator.model;

public class FieldTable {

	private String classmodule;// 模块名
	private String classname; // 类名
	private String classrmk;// 类说明
	private String ctime; // 创建时间

	private String coltitle; // 字段名
	private String colname; // 字段中文说明
	private int colorder; // 序号
	private String coltype; // 类型

	public String getClassmodule() {
		return classmodule;
	}

	public void setClassmodule(String classmodule) {
		this.classmodule = classmodule;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getClassrmk() {
		return classrmk;
	}

	public void setClassrmk(String classrmk) {
		this.classrmk = classrmk;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

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

}
