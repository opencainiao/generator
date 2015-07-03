package generator.db;

import generator.model.EntityModel;
import generator.model.Field;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mou.common.DateUtil;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("iTableService")
public class TableServiceImpl implements ITableService {

	private static final Logger logger = LogManager
			.getLogger(TableServiceImpl.class);

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
	 * 保存model
	 * 
	 * @param model
	 */
	public void savaTable(final EntityModel model) {

		final String classmodule = model.getClassmodule();
		final String classname = model.getClassname();

		Object[] removeparams = new Object[] { classmodule, classname };
		// 1.删除原class
		String sqlStrClass = "delete from class where classmodule = ? and classname = ?";
		int result = getJdbcTemplate().update(sqlStrClass, removeparams);
		logger.debug("removed class 【?】", result);

		// 2.删除原Fields
		String sqlStrFields = "delete from fields where classmodule = ? and classname = ?";
		result = getJdbcTemplate().update(sqlStrFields, removeparams);
		logger.debug("removed fields 【?】", result);

		// 3.写入class
		Object[] insertparams = new Object[] { classmodule, classname,
				model.getClassrmk() };
		String insertSqlClass = "INSERT INTO class (classmodule, classname, classrmk) VALUES (?, ?, ?)";
		result = getJdbcTemplate().update(insertSqlClass, insertparams);
		logger.debug("insert class 【?】", result);

		// 4.写入新Fields
		String insertSqlFields = "INSERT INTO fields (classmodule, classname, coltitle, colorder, colname, coltype, classrmk, ctime) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		final List<Field> fields = model.getFields();
		getJdbcTemplate().batchUpdate(insertSqlFields,
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {

						String coltitle = fields.get(i).getColtitle();
						String colname = fields.get(i).getColname();
						int colorder = fields.get(i).getColorder();
						String coltype = fields.get(i).getColtype();

						// classmodule, classname, coltitle, colorder, colname,
						// coltype, classrmk, ctime

						String ctime = DateUtil.getCurrentTimsmp();

						ps.setString(1, classmodule);
						ps.setString(2, classname);
						ps.setString(3, coltitle);
						ps.setInt(4, colorder);
						ps.setString(5, colname);
						ps.setString(6, coltype);
						ps.setString(7, model.getClassrmk());
						ps.setString(8, ctime);
					}

					public int getBatchSize() {
						return fields.size();
					}
				});

		logger.debug("=========================");
		logger.debug("保存数据库信息完毕!");
	}
	
	public static void main(String[] args) {
		System.out.println("2015-07-03 17:32:44:999".length());
	}
}
