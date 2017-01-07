package edu.dbke.model.org;

/**
 * 封装分页，搜索，排序所需字段。供flexgrid使用。如果需要，则继承。
 * @author Administrator
 *
 */
public class AbstractEntity {
	/**
	 * 搜索参数
	 */
	private String query ;
	private String qtype ;
	/**
	 * 页参数
	 */
	private int start ;//开始记录
	private int size ; //每页大小
	/**
	 * 排序参数
	 */
	private String sortname;
	private String sortorder ;
	
	private String applicationSystemId ;//所属应用系统
	private String organization_id ;//所属机构
	private String group_id ;//所属分组
	private String business_id ;//业务
	
	public String getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
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
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getQtype() {
		return qtype;
	}
	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getSortname() {
		return sortname;
	}
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}
	public String getSortorder() {
		return sortorder;
	}
	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}