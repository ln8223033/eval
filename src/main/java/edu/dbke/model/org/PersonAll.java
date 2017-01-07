package edu.dbke.model.org;

import java.util.Date;

public class PersonAll {
	private String id;
	private String buscode;
	private String gender;
	private Date birthday;
	private String fax;
	private String name;
	private String org_id;
	private String Org_Name;
	private String busUser;
	private String busUserId;
	private String appUserName;
	private String appUserId;
    private String title;
    private String type;
    private String mobile;
    //关联t_user的字段
    private String user_id ;//t_user的id
    
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAppUserName() {
		return appUserName;
	}
	public void setAppUserName(String appUserName) {
		this.appUserName = appUserName;
	}
	public String getAppUserId() {
		return appUserId;
	}
	public void setAppUserId(String appUserId) {
		this.appUserId = appUserId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBuscode() {
		return buscode;
	}
	public void setBuscode(String buscode) {
		this.buscode = buscode;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getOrg_Name() {
		return Org_Name;
	}
	public void setOrg_Name(String org_Name) {
		Org_Name = org_Name;
	}
	public String getBusUser() {
		return busUser;
	}
	public void setBusUser(String busUser) {
		this.busUser = busUser;
	}
	public String getBusUserId() {
		return busUserId;
	}
	public void setBusUserId(String busUserId) {
		this.busUserId = busUserId;
	}

}
