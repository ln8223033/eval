package edu.dbke.service.babic;


import edu.dbke.mapper.basic.TeachingClassMapper;
import edu.dbke.model.basic.TeachingClass;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

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
    public List<TeachingClass> selectByTeacher(){
        TeachingClass teachingClass = new TeachingClass();
        Example example = new Example(TeachingClass.class);
        example.createCriteria()
                .andCondition("TeacherId",teachingClass.getTeacherId());
        return teachingClassMapper.selectByExample(example);
    }
}
