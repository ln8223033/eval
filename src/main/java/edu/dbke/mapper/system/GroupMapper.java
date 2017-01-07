package edu.dbke.mapper.system;


import edu.dbke.model.system.Group;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface GroupMapper extends Mapper<Group>{
	public List<Group> findNodeById(String id);
	public Group findByName(String name);
	
	
	public int insert(Group entity);//增加分组
	public int update(Group entity);//更新分组
	public void removeByIds(String ids);
	public Group findById(String id);
	public int getTotal();
	
	
	public Group delete(String id);
	public int selectCount(Map<String, Object> map);
	public List<Group> selectByPage(Map<String, Object> map);
	public List<Group> findNodeByParentId(String parentId);
	public List<Group> selectByOrgnazationId(String orgId);//查询顶级分类
	public List<Group> selectByBussinessId(String bId);//查询顶级分类
	public int getChildNodeCount(String id);//获取子节点数量
	
	public List<Group> selectByExample(Group group);//查询顶级分类。通过多重条件查询
}
