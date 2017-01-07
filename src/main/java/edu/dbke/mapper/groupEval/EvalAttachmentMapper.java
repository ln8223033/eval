package edu.dbke.mapper.groupEval;


import edu.dbke.model.groupEval.EvalAttachment;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface EvalAttachmentMapper extends Mapper<EvalAttachment> {

    void deleteCTQ(List delList);

}