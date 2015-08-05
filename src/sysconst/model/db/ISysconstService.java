package sysconst.model.db;

import java.util.List;

import sysconst.model.SysConst;
import sysconst.model.SysConstType;

public interface ISysconstService {

	public List<SysConstType> findBatch();

	public List<SysConst> findSysconsts(String typecode);
}
