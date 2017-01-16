package edu.dbke.service.babic;


import edu.dbke.mapper.basic.TeachingClassMapper;
import edu.dbke.model.basic.TeachingClass;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hp on 2016/12/30.
 */
@Service
public class TeachingClassService {
    @Resource
    private TeachingClassMapper teachingClassMapper;
    public TeachingClass find(TeachingClass teachingClass){
        return teachingClassMapper.selectByPrimaryKey(teachingClass);
    }
}
