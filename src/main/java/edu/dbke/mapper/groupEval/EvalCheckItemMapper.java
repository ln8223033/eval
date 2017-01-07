package edu.dbke.mapper.groupEval;


import edu.dbke.model.groupEval.EvalCheckItem;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface EvalCheckItemMapper extends Mapper<EvalCheckItem> {
    /**
     * 查询某个小组该子检查项的所有人得评分
     */
    List<EvalCheckItem> getChildItemQuery(Integer groupEvalQuestionId, Integer childItemId, Integer evalGroupId);
    /**
     * 得到某个子检查项的平均得分
     * @return
     */
    double getChildItemAvgScore(Integer groupEvalId, Integer childItemId, Integer evalGroupId);
    /**
     * 得到某个父检查项的平均得分(即其子选项的加分)
     * @return
     */
    double getParentItemAvgScore(Integer groupEvalQuestionId, Integer parentItemId, Integer evalGroupId);
    /**
     * 得到某个组在某次项目下的平均总得分
     * @return
     * @param groupEvalQuestionId
     * @param evalGroupId
     */
    double getTotalScore(Integer groupEvalQuestionId, Integer evalGroupId);



}