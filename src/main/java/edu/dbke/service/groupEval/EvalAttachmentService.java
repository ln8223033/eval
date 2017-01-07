package edu.dbke.service.groupEval;

import com.alibaba.dubbo.config.annotation.Service;
import edu.dbke.mapper.groupEval.EvalAttachmentMapper;
import edu.dbke.model.groupEval.EvalAttachment;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2016/12/24.
 */
@Service
public class EvalAttachmentService {
    @Resource
    private EvalAttachmentMapper evalAttachmentMapper;
    /**
     * 通过id批量删除实体
     *
     * @param delList
     */
    public void deleteCTQ(List delList){
        evalAttachmentMapper.deleteCTQ(delList);
    }

    public void deleteByPrimaryKey(EvalAttachment evalAttachment){
        evalAttachmentMapper.deleteByPrimaryKey(evalAttachment);
    }
}
