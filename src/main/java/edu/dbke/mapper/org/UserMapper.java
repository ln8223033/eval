package edu.dbke.mapper.org;


import edu.dbke.model.org.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;



/**
 * 定义的方法接口
 *
 * @author Administrator
 */
@Repository
public interface UserMapper extends Mapper<User> {

    void create(User user);

    Integer getUserOrgIdByUserId(Integer id);

    User querySingle(String externalAccount);

    User findByName(String name);


}
