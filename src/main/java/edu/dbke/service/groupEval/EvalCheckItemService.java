package edu.dbke.service.groupEval;

import com.alibaba.dubbo.config.annotation.Service;
import edu.dbke.mapper.groupEval.EvalCheckItemMapper;
import edu.dbke.model.groupEval.EvalCheckItem;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2016/12/24.
 */
@Service
public class EvalCheckItemService {
    @Resource
    private EvalCheckItemMapper evalCheckItemMapper;
    /**
     * 查询某个小组该子检查项的所有人得评分
     */
    public List<EvalCheckItem> getChildItemQuery(Integer groupEvalQuestionId, Integer childItemId, Integer evalGroupId) {
        return evalCheckItemMapper.getChildItemQuery(groupEvalQuestionId, childItemId, evalGroupId);
    }
    /**
     * 得到某个子检查项的平均得分
     * @return
     */
    public double getChildItemAvgScore(Integer groupEvalQuestionId, Integer childItemId, Integer evalGroupId) {
        return evalCheckItemMapper.getChildItemAvgScore(groupEvalQuestionId,childItemId,evalGroupId);
    }
    /**
     * 得到某个父检查项的平均得分(即其子选项的加分)
     * @return
     */
    public double getParentItemAvgScore(Integer groupEvalQuestionId, Integer parentItemId, Integer evalGroupId) {
        return evalCheckItemMapper.getParentItemAvgScore(groupEvalQuestionId,parentItemId,evalGroupId);
    }
    /**
     * 得到某个组在某次项目下的平均总得分
     * @return
     * @param groupEvalQuestionId
     * @param evalGroupId
     */
    public double getTotalScore(Integer groupEvalQuestionId, Integer evalGroupId){
        return evalCheckItemMapper.getTotalScore(groupEvalQuestionId,evalGroupId);
    }

    public void delete(EvalCheckItem evalCheckItem){
        evalCheckItemMapper.deleteByPrimaryKey(evalCheckItem);
    }

    public int update(EvalCheckItem evalCheckItem){

        return  evalCheckItemMapper.updateByPrimaryKey(evalCheckItem);
    }

    public int insert(EvalCheckItem evalCheckItem){
        return  evalCheckItemMapper.insertSelective(evalCheckItem);
    }
}