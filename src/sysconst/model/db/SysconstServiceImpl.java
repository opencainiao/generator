package sysconst.model.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import sysconst.model.SysConst;
import sysconst.model.SysConstType;

@Service("sysconstService")
public class SysconstServiceImpl implements ISysconstService {

	private static final Logger logger = LogManager.getLogger(SysconstServiceImpl.class);

	@Resource(name = "dataSource")
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		if (this.jdbcTemplate == null) {
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}

		return this.jdbcTemplate;
	}

	/****
	 * 查询所有常量类型
	 */
	@Override
	public List<SysConstType> findBatch() {

		List<SysConstType> rtnList = null;

		String sql = "SELECT * FROM sysconsttype order by typecode ";

		rtnList = getJdbcTemplate().query(sql, new SysConstTypeMapper());

		logger.debug("sql = {}", sql);
		logger.debug(rtnList);

		return rtnList;
	}

	/****
	 * SysConstType行转换器
	 * 
	 * @author NBQ
	 *
	 */
	public class SysConstTypeMapper implements RowMapper<SysConstType> {

		@Override
		public SysConstType mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysConstType sct = new SysConstType();

			sct.set_id(rs.getObject("_id"));
			sct.setDelflg(rs.getString("delflg"));
			sct.setUseflg(rs.getString("useflg"));
			sct.setTypecode(rs.getString("typecode"));
			sct.setTypename(rs.getString("typename"));

			return sct;
		}
	}

	public static void main(String[] args) {
		System.out.println("2015-07-03 17:32:44:999".length());
	}

	@Override
	public List<SysConst> findSysconsts(String typecode) {
		List<SysConst> rtnList = null;

		String sql = "SELECT * FROM sysconst WHERE typecod=? order by valordernum,val ";

		Object[] args = new Object[] { typecode };

		rtnList = getJdbcTemplate().query(sql, args, new SysConstMapper());

		logger.debug("sql = {}", sql);
		logger.debug("params {}", args);
		logger.debug(rtnList);

		return rtnList;
	}

	/****
	 * SysConst行转换器
	 * 
	 * @author NBQ
	 *
	 */
	public class SysConstMapper implements RowMapper<SysConst> {

		@Override
		public SysConst mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysConst sc = new SysConst();
			
			sc.set_id(rs.getObject("_id"));
			sc.setDelflg(rs.getString("delflg"));
			sc.setUseflg(rs.getString("useflg"));
			sc.setTypecode(rs.getString("typecode"));
			sc.setTypename(rs.getString("typename"));
			sc.setVal(rs.getString("val"));
			sc.setDspval(rs.getString("dspval"));
			sc.setValordernum(rs.getString("valordernum"));

			return sc;
		}
	}

}
