package edu.dbke.service.org;


import edu.dbke.mapper.org.UserMapper;
import edu.dbke.model.org.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2016/12/10.
 */
@Service
public class UserService  {

    @Resource
    private UserMapper userMapper ;
    public UserService() {

    }
   public List<User> selectAll(){
        return userMapper.selectAll();
    }


    public User querySingle(String externalAccount) {
        return userMapper.querySingle(externalAccount);
    }

    public void create(User u) {
    }

    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    public Integer getUserOrgIdByUserId(Integer id) {
        return userMapper.getUserOrgIdByUserId(id);
    }

    public User selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
