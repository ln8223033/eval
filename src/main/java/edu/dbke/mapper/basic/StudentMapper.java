package edu.dbke.mapper.basic;


import edu.dbke.model.basic.Student;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by hp on 2016/12/15.
 */
@Repository
public interface StudentMapper extends Mapper<Student>{
}
