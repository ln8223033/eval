package edu.dbke.service.org;


import edu.dbke.mapper.org.AccountSynchronizeMapper;
import edu.dbke.model.org.AccountSynchronize;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service
public class AccountSynchronizeService {
	@Resource
	public AccountSynchronizeMapper accountSynchronizeMapper;

	/**
	 * 获取上次账号同步更新时间
	 *
	 * @return
	 */
	public String getLastSucceedTimeString(String id) {
		return null;
	}

	public AccountSynchronize find(String id) {
		return accountSynchronizeMapper.selectByPrimaryKey(id);
	}

	public void edit(AccountSynchronize as){
		accountSynchronizeMapper.edit(as);
	}
}