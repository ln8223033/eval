package edu.dbke.service.groupEval;

import edu.dbke.mapper.groupEval.EvalCommentReplyMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hp on 2016/12/28.
 */
@Service
public class EvalCommentReplyService {
    @Resource
    private EvalCommentReplyMapper evalCommentReplyMapper;

}
