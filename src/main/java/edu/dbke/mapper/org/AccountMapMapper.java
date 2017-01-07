package edu.dbke.mapper.org;


import edu.dbke.model.org.AccountMap;

import edu.dbke.model.org.AccountSynchronize;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * 定义的方法接口
 *
 * @author Administrator
 */
@Repository
public interface AccountMapMapper extends Mapper<AccountMap> {

    AccountMap findByExternalName(String externalAccount);

    AccountMap querySingle(String name);

    void create(AccountMap entity);


}
