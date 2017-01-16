package edu.dbke.service.groupEval;


import edu.dbke.mapper.groupEval.GroupEvalMapper;
import edu.dbke.model.groupEval.GroupEval;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2016/12/24.
 */
@Service
public class GroupEvalService {

    @Resource
    private GroupEvalMapper groupEvalMapper;


    public List<GroupEval> selectAllT() {
        Example example = new Example(GroupEval.class);
        example.createCriteria()
                .andCondition("group_eval_question_id is not null")
                .andCondition("status = 1")
                .andCondition("teaching_class_id =");
        example.setOrderByClause("start_time desc, end_time desc");
        return groupEvalMapper.selectByExampleAndRowBounds(example, new RowBounds(1, 10));
    }

    public void insertGroupEval(GroupEval groupEval) {
        groupEvalMapper.insertSelective(groupEval);
    }

    public void deleteGroupEval(GroupEval g) {
        g.setStatus(GroupEval.DELETED);
        groupEvalMapper.updateByPrimaryKey(g);
    }

    public void deleteGroupEvalS(GroupEval g) {
        groupEvalMapper.BatchUpdate(g);
    }

    public List<String> selectSTimeByIdsBatch(List<Integer> Ids) {
        Map<String, Object> IdsParam = new HashMap<String, Object>();
        IdsParam.put("Ids", Ids);
        return groupEvalMapper.selectSTimeByIdsBatch(IdsParam);

    }
    public GroupEval selectByPrimaryKey(Integer id){
        return  groupEvalMapper.selectByPrimaryKey(id);
    }
    public int update(GroupEval g){
        return  groupEvalMapper.updateByPrimaryKey(g);
    }
}