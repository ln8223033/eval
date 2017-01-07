package edu.dbke.service.babic;

import com.alibaba.dubbo.config.annotation.Service;
import edu.dbke.mapper.basic.ClassesMapper;

import javax.annotation.Resource;

/**
 * Created by hp on 2016/12/24.
 */
@Service
public class ClassesService {
    @Resource
    private ClassesMapper classesMapper;

}

