package sysconst.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.mongodb.ReflectionDBObject;

/****
 * 系统的常量类型
 * 
 * @author NBQ
 *
 */
public class SysConstType extends ReflectionDBObject {

	protected String delflg; // 删除标志(用于逻辑删除)
	protected String useflg; // 启用标志
	protected String _id_m; // _id的字符串表示

	private String typecode; // 常量类型码
	private String typename; // 常量类型名

	@NotEmpty(message = "常量类型不能为空")
	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	@NotEmpty(message = "常量类型名称不能为空")
	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
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
