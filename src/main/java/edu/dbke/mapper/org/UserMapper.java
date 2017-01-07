package edu.dbke.mapper.org;


import edu.dbke.model.org.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 定义的方法接口
 *
 * @author Administrator
 */
public interface UserMapper extends Mapper<User> {

    void create(User user);

    Integer getUserOrgIdByUserId(Integer id);

    User querySingle(String externalAccount);

    User findByName(String name);


}
