package edu.dbke.mapper.groupEval;


import edu.dbke.model.groupEval.EvalWork;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface EvalWorkMapper extends Mapper<EvalWork> {
    void deleteCTQ(List delList);
}