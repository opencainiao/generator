package ${daopackagename};

import org.springframework.stereotype.Repository;

import com.bxb.modules.base.BaseDao;

/****
 * ${domainname}dao
 * 
 * @author NBQ
 *
 */
@Repository("${daozhujiename}")
public class ${daoname} extends BaseDao {

	@Override
	public String getCollectionName() {
		return "${collectionname}";
	}
}
