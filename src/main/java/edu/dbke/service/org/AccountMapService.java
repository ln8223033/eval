package edu.dbke.service.org;


import edu.dbke.mapper.org.AccountMapMapper;
import edu.dbke.model.org.AccountMap;
import edu.dbke.util.IdUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.List;

@Service
public class AccountMapService {

	@Resource
	private AccountMapMapper accountMapper ;


	public AccountMap getAccountMapByName(String name){
		return accountMapper.findByExternalName(name);
	}

	public AccountMap querySingle(String externalAccount){
		return accountMapper.querySingle(externalAccount);
	}

	public void create(AccountMap am){
		am.setId(IdUtil.getId());
		accountMapper.create(am);
	}
}
