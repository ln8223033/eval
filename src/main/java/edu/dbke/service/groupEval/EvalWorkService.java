package edu.dbke.service.groupEval;

import edu.dbke.mapper.groupEval.EvalWorkMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2016/12/28.
 */
@Service
public class EvalWorkService {
    @Resource
    private EvalWorkMapper evalWorkMapper;
    /**
     * 通过id批量删除实体
     *
     * @param delList
     */
    public void deleteCTQ(List delList){
        evalWorkMapper.deleteCTQ(delList);
    }
}
