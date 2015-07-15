package ${servicepackagename};

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bxb.common.globalobj.PageVO;
import com.bxb.modules.base.BaseService;
import ${daofullname};
import ${domainfullname};
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/****
 * ${domainname}服务实现
 * 
 * @author NBQ
 *
 */
@Service("${servicezhujiename}")
public class ${servicename} extends BaseService implements
		${serviceinterfacename} {
		
	@Resource(name = "${daozhujiename}")
	private ${daoname}  ${daozhujiename};

	private static final Logger logger = LogManager
			.getLogger(${servicename}.class);

	@Override
	public ${bussnesdomainname} findOneByIdObject(String _id) {

		return this.${daozhujiename}.findOneByIdObject(_id, ${bussnesdomainname}.class);
	}

	@Override
	public PageVO batchSearchPage(DBObject queryCondition, DBObject sort,
			DBObject returnFields) {
		return this.${daozhujiename}.batchSearchPage(queryCondition, sort,
				returnFields);
	}

	@Override
	public PageVO batchSearchOnePage(DBObject query, DBObject sort,
			DBObject returnFields) {
		return this.${daozhujiename}.batchSearchOnePage(query, sort,
				returnFields);
	}

	@Override
	public String add(${bussnesdomainname} ${bussnesdomainnameL}) {
		this.setCreateInfo(${bussnesdomainnameL});
		return this.${daozhujiename}.insertObj(${bussnesdomainnameL});
	}

	@Override
	public DBObject updatePart(DBObject returnFields, ${bussnesdomainname} ${bussnesdomainnameL}) {

		DBObject toUpdate = makeUpdate(${bussnesdomainnameL});
		return this.${daozhujiename}.updateOneById(${bussnesdomainnameL}.get_id_str(),
				returnFields, toUpdate);
	}

	/****
	 * 转化对象为要更新的部分字段
	 * 
	 * @param update
	 * @return
	 */
	private DBObject makeUpdate(${bussnesdomainname} ${bussnesdomainnameL}) {

		DBObject update = new BasicDBObject();
		DBObject updateSet = new BasicDBObject();

		// updateSet.put("typename", ${bussnesdomainnameL}.getTypename());

		this.setModifyInfo(updateSet);
		update.put("$set", updateSet);

		logger.debug("更新的对象信息\n{}", update);
		return update;
	}

	@Override
	public DBObject RemoveOneById(String _id) {
		return this.${daozhujiename}.findAndRemoveOneById(_id);
	}

	@Override
	public DBObject RemoveOneByIdLogic(String _id) {

		DBObject updateSet = new BasicDBObject();
		this.setModifyInfo(updateSet);
		return this.${daozhujiename}.findAndRemoveOneByIdLogic(_id, updateSet);
	}
	
}
