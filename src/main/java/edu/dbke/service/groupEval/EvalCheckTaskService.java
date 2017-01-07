package edu.dbke.service.groupEval;

import com.alibaba.dubbo.config.annotation.Service;
import edu.dbke.mapper.groupEval.EvalCheckTaskMapper;

import javax.annotation.Resource;

/**
 * Created by hp on 2016/12/28.
 */
@Service
public class EvalCheckTaskService {
    @Resource
    private EvalCheckTaskMapper evalCheckTaskMapper;

}
