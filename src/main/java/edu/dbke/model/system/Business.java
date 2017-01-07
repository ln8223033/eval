package edu.dbke.model.system;

/**
 * 视图 v_businessorgsystem
 * 
 */
public class Business {
	private String businessId ;//业务id
	private String businessName ;//业务名称
	private String orgId ;//机构id
	private String appSystemId ;//应用系统id
	private String appSystemName ;//应用系统名称
	
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String bussinessName) {
		this.businessName = bussinessName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getAppSystemId() {
		return appSystemId;
	}
	public void setAppSystemId(String appSystemId) {
		this.appSystemId = appSystemId;
	}
	public String getAppSystemName() {
		return appSystemName;
	}
	public void setAppSystemName(String appSystemName) {
		this.appSystemName = appSystemName;
	}
}
