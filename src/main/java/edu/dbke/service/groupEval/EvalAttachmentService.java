package edu.dbke.service.groupEval;


import edu.dbke.mapper.groupEval.EvalAttachmentMapper;
import edu.dbke.model.groupEval.EvalAttachment;
import org.springframework.stereotype.Service;

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
