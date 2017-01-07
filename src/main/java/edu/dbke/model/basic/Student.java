package edu.dbke.model.basic;

import edu.dbke.model.files.Files;
import edu.dbke.model.org.User;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by hp on 2016/12/10.
 */


@Table(name = "t_student")
public class Student  extends User{
    @Column(unique = true)
    private String studentNo;//学号
    private String name;//学生姓名
    private String sex;//性别
    private Date birthday;//生日
    private Files photo;//学生头像
    private String phoneNumber;//手机号码
    private String email;//邮箱
    private String homeAddress;//家庭住址


    @Transient
    private TeachingClass curTeachingClass;//当前所选课程

    @ManyToOne
    @JoinColumn(name = "classesId", nullable = false)
    private Classes classes;


    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
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

    public TeachingClass getCurTeachingClass() {
        return curTeachingClass;
    }

    public void setCurTeachingClass(TeachingClass curTeachingClass) {
        this.curTeachingClass = curTeachingClass;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }
}


