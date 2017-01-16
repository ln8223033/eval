package edu.dbke.service.groupEval;


import edu.dbke.mapper.groupEval.GroupEvalQuestionMapper;
import edu.dbke.model.groupEval.GroupEval;
import edu.dbke.model.groupEval.GroupEvalQuestion;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2016/12/24.
 */
@Service
public class GroupEvalQuestionService {
    @Resource
    private GroupEvalQuestionMapper groupEvalQuestionMapper;

    public GroupEvalQuestion find(GroupEvalQuestion groupEvalQuestion){
        return groupEvalQuestionMapper.selectByPrimaryKey(groupEvalQuestion);
    }

    public List<GroupEvalQuestion> selectAllPrivate(GroupEvalQuestion ge){
        Example example = new Example(GroupEvalQuestion.class);
        example.createCriteria()
                .andCondition("course_Id =",ge.getCourseGroup())
                .andCondition("owner_Id=",ge.getOwner())
                .andCondition("status=0")
                .andCondition("is_share=0");
        example.setOrderByClause("create_time desc");
        return groupEvalQuestionMapper.selectByExampleAndRowBounds(example, new RowBounds(1, 10));
    }
    public List<GroupEvalQuestion> selectAllPublic(GroupEvalQuestion ge){
        Example example = new Example(GroupEvalQuestion.class);
        example.createCriteria()
                .andCondition("course_Id =",ge.getCourseGroup())
                .andCondition("owner_Id=",ge.getOwner())
                .andCondition("status=0")
                .andCondition("is_share=1");
        example.setOrderByClause("create_time desc");
        return groupEvalQuestionMapper.selectByExampleAndRowBounds(example, new RowBounds(1, 10));
    }

    public void insertGroupEval(GroupEvalQuestion groupEvalQuestion) {
        groupEvalQuestionMapper.insertSelective(groupEvalQuestion);
    }


    public void deleteEvalQuestion(GroupEvalQuestion g) {
        g.setStatus(GroupEval.DELETED);
       groupEvalQuestionMapper.updateByPrimaryKey(g);
    }

    public void deleteEvalQuestionS(GroupEvalQuestion g) {
        groupEvalQuestionMapper.BatchUpdate(g);
    }


    public int update(GroupEvalQuestion groupEvalQuestion) {
        return groupEvalQuestionMapper.updateByPrimaryKey(groupEvalQuestion);
    }
}

