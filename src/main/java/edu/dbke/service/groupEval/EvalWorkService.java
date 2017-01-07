package edu.dbke.service.groupEval;

import com.alibaba.dubbo.config.annotation.Service;
import edu.dbke.mapper.groupEval.EvalWorkMapper;

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
