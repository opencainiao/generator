package sysconst.model;

import com.mongodb.ReflectionDBObject;

/****
 * 系统常量类
 * 
 * @author NBQ
 *
 */
public class SysConst extends ReflectionDBObject {

	protected String delflg; // 删除标志(用于逻辑删除)
	protected String useflg; // 启用标志
	protected String _id_m; // _id的字符串表示

	private String typecode; // 常量类型
	private String typename; // 常量类型名称
	private String val;// 常量值
	private String dspval; // 常量显示值
	private String valordernum;// 常量值顺序号

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getDspval() {
		return dspval;
	}

	public void setDspval(String dspval) {
		this.dspval = dspval;
	}

	public String getValordernum() {
		return valordernum;
	}

	public void setValordernum(String valordernum) {
		this.valordernum = valordernum;
	}

	public String getDelflg() {
		return delflg;
	}

	public void setDelflg(String delflg) {
		this.delflg = delflg;
	}

	public String getUseflg() {
		return useflg;
	}

	public void setUseflg(String useflg) {
		this.useflg = useflg;
	}

	public String get_id_m() {
		if (super.get_id() == null) {
			return null;
		}
		return super.get_id().toString();
	}

	public void set_id_m(String _id_m) {
		this._id_m = _id_m;
	}
}
