package edu.dbke.mapper.system;


import edu.dbke.model.system.Business;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BusinessMapper extends Mapper<Business>{

}
