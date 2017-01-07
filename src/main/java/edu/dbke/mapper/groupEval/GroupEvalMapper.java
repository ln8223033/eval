package edu.dbke.mapper.groupEval;


import edu.dbke.model.groupEval.GroupEval;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


@Repository
public interface GroupEvalMapper extends Mapper<GroupEval> {

    void BatchUpdate(GroupEval groupEval);

    List<String> selectSTimeByIdsBatch(Map<String, Object> IdsParam);





}