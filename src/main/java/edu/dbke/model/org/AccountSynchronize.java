package edu.dbke.model.org;

import java.util.Date;

/**
 * 
 * @author Xiao Xiong
 * ACL账号同步记录时间类
 *
 */
public class AccountSynchronize {
	private String id ;
	  
	private Date updateSuccessTime = null ;//账号更新成功时间

	public void setId(String id) {
		this.id = id;
	}

	
	public Date getUpdateSuccessTime() {
		return updateSuccessTime;
	}

	public void setUpdateSuccessTime(Date updateSuccessTime) {
		this.updateSuccessTime = updateSuccessTime;
	}
}