package edu.dbke.model.flexgrid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public abstract class FlexGridActionSupport<T> {
	protected Integer ids;//记录主键id
	protected String sortname;//排序名称
	protected String sortorder;//排序方式
	protected String qtype;//查询字段
	protected String query;//查询条件
	public int rp ;
	protected int pageSize;


	public int page = 0 ;
	

	public FlexGridJsonData data = new FlexGridJsonData();
	public Map<String, Integer> pageMap = new HashMap<String, Integer>();//分页使用，保存当前页和页内数量。

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public Integer getIds() {
		return ids;
	}

	public void setIds(Integer ids) {
		this.ids = ids;
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

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
