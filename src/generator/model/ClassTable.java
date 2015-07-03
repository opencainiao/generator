package generator.model;

public class ClassTable {

	private String classmodule;// 模块名
	private String classname; // 类名
	private String classrmk;// 类说明

	public static ClassTable genClassTable(EntityModel model) {
		
		ClassTable ct = new ClassTable();
		ct.setClassmodule(model.getClassmodule());
		ct.setClassname(model.getClassname());
		ct.setClassrmk(model.getClassrmk());

		return ct;
	}

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

}
