package ${servicepackagename};

import com.bxb.common.globalobj.PageVO;
import ${domainfullname};
import com.mongodb.DBObject;

/****
 * ${domainname}服务接口
 * 
 * @author NBQ
 *
 */
public interface I${bussnesdomainname}Service {

	/****
	 * 根据id，查询一个对象
	 * 
	 * @param _id
	 * @return
	 */
	public ${bussnesdomainname} findOneByIdObject(String _id);

	/****
	 * 条件查询，分页
	 * 
	 * @param query
	 * @param sort
	 * @param returnFields
	 * @return
	 */
	public PageVO batchSearchPage(DBObject queryCondition, DBObject sort,
			DBObject returnFields);

	/****
	 * 条件查询，1页，查询所有
	 * 
	 * @param query
	 * @param sort
	 * @param returnFields
	 * @return
	 */
	public PageVO batchSearchOnePage(DBObject query, DBObject sort,
			DBObject returnFields);

	/****
	 * 插入对象，返回插入后的生成的ObjectId
	 * 
	 * @param ${bussnesdomainnameL}
	 * @return
	 */
	public String add(${bussnesdomainname} ${bussnesdomainnameL});

	/****
	 * 更新一条记录，返回更新后的结果，根据对象的主键ObjectId
	 * 
	 * @param returnFields
	 * @param ${bussnesdomainnameL}
	 * @return
	 */
	public DBObject updatePart(DBObject returnFields, ${bussnesdomainname} ${bussnesdomainnameL});

	/****
	 * 查询并删除一条记录
	 * 
	 * @param _id
	 * @return
	 */
	public DBObject RemoveOneById(String _id);

	/****
	 * 查询并删除一条记录(逻辑删除)<br>
	 * 
	 * delflg - 1 已删除 0 未删除
	 * 
	 * @param _id
	 * @return
	 */
	public DBObject RemoveOneByIdLogic(String _id);

}
