package edu.dbke.model.org;

import javax.persistence.*;

/**
 * 用户表
 * Created by hp on 2016/12/10.
 */

@Table(name = "t_user")
public class User {

    public static String Teacher = "teacher";
    public static String Student = "student";
    public static String Admin = "admin";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Column(unique = true)
    private String userName;//登录用户名
    private String password;//密码
    private Integer org_id ;//对应的机构id
    private String userType;//用户类型

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
