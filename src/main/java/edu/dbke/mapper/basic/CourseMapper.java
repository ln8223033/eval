package edu.dbke.mapper.basic;


import edu.dbke.model.basic.Course;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


/**
 * Created by hp on 2016/12/15.
 */
@Repository
public interface CourseMapper extends Mapper<Course> {
}
