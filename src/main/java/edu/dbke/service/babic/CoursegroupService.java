package edu.dbke.service.babic;


import edu.dbke.mapper.basic.CoursegroupMapper;
import edu.dbke.model.basic.Coursegroup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2017/1/19.
 */
@Service
public class CoursegroupService {
    @Resource
    private CoursegroupMapper coursegroupMapper;

    public List<Coursegroup> getCoursegroupList() {
        return coursegroupMapper.selectAll();

    }
}
