package edu.dbke.service.babic;


import edu.dbke.mapper.basic.ClassMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hp on 2016/12/24.
 */
@Service
public class ClassService {
    @Resource
    private ClassMapper classMapper;

}

