package edu.dbke.mapper.groupEval;


import edu.dbke.model.groupEval.GroupEvalQuestion;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface GroupEvalQuestionMapper extends Mapper<GroupEvalQuestion> {
    void BatchUpdate(GroupEvalQuestion g);
}