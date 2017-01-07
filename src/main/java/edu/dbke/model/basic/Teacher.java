package edu.dbke.model.basic;

import edu.dbke.model.files.Files;
import edu.dbke.model.org.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by hp on 2016/12/10.
 */


@Table(name = "t_teacher")
public class Teacher extends User {
    @Column(unique = true)
    private String loginAccount;//工号
    private String profession;//专业
    private String name;//教师姓名
    private String sex;//性别
    private Date birthday;//生日
    private Files photo;//头像
    private String phoneNumber;//手机号码
    private String email;//邮箱
    private String homeAddress;//家庭住址
    private String introduction;//介绍
    private Date hireDate;//入校时间
    //教学班
    @OneToMany
    private List<TeachingClass> teachingClasses = new ArrayList<TeachingClass>();
   @Transient
    private TeachingClass curTeachingClass;//当前教学班

    public TeachingClass getCurTeachingClass() {
        return curTeachingClass;
    }

    public void setCurTeachingClass(TeachingClass curTeachingClass) {
        this.curTeachingClass = curTeachingClass;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }



    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Files getPhoto() {
        return photo;
    }

    public void setPhoto(Files photo) {
        if (photo != null) {
            this.photo.getSavePath();
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public List<TeachingClass> getTeachingClasses() {
        return teachingClasses;
    }

    public void setTeachingClasses(List<TeachingClass> teachingClasses) {
        this.teachingClasses = teachingClasses;
    }
}