package generator.db;

import java.util.List;

import generator.model.ClassTable;
import generator.model.EntityModel;

public interface ITableService {

	public void savaTable(final EntityModel model);
	
	public List<ClassTable> findBatch(String module, String classname);
}
