package edu.dbke.model.system;

import java.util.Date;

/**
 * 用户组	t_group
 * @author Administrator
 *
 */
public class Group {
	private String id ; //主键
	private String name;//组的名称
	private Date createTime;//创建时间
	private String applicationSystemId;//所属应用
	private String organization_id;//所属机构
	private String business_id;//所属业务
	private String group_id ; //组id
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getApplicationSystemId() {
		return applicationSystemId;
	}
	public void setApplicationSystemId(String applicationSystemId) {
		this.applicationSystemId = applicationSystemId;
	}
	public String getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}
	public String getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
}
