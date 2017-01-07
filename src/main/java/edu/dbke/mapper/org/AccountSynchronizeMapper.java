package edu.dbke.mapper.org;


import edu.dbke.model.org.AccountSynchronize;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AccountSynchronizeMapper extends Mapper<AccountSynchronize> {
     void edit(AccountSynchronize as);//更新时间
}
