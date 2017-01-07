package edu.dbke.service.groupEval;

import edu.dbke.mapper.groupEval.EvalGroupMapper;

import edu.dbke.model.groupEval.EvalGroup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Created by hp on 2016/12/28.
 */
@Service
public class EvalGroupService {
    @Resource
    private EvalGroupMapper evalGroupMapper;

    private void create(EvalGroup evalGroup){
        evalGroupMapper.insert(evalGroup);
    }




}
