package edu.dbke.service.groupEval;


import edu.dbke.mapper.groupEval.EvalScoreItemMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hp on 2016/12/28.
 */
@Service
public class EvalScoreItemService {
    @Resource
    private EvalScoreItemMapper evalScoreItemMapper;
}
