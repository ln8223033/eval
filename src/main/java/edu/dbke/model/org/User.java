package edu.dbke.model.org;

/**
 * 用户表
 * Created by hp on 2016/12/10.
 */


public class User extends AbstractEntity{

    private String id ;
    private String username ;
    private String password ;
    private int loginNum ;

    //关联的v_personall视图字段
    private String org_id ;//对应的机构id

    public String getOrg_id() {
        return org_id;
    }
    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getLoginNum() {
        return loginNum;
    }
    public void setLoginNum(int loginNum) {
        this.loginNum = loginNum;
    }
}
