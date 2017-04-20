package edu.dbke.model.basic;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_schoolcampus")
public class Schoolcampus {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 校区名字
     */
    @Column(name = "Name")
    private String name;

    /**
     * 校区代码或者编码
     */
    @Column(name = "Number")
    private String number;

    @Column(name = "Address")
    private String address;

    @Column(name = "Email")
    private String email;

    @Column(name = "TelNumber")
    private String telnumber;

    @Column(name = "InDate")
    private Date indate;

    @Column(name = "InUser")
    private String inuser;

    @Column(name = "LastEditDate")
    private Date lasteditdate;

    @Column(name = "LastEditUser")
    private String lastedituser;

    /**
     * @return Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取校区名字
     *
     * @return Name - 校区名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置校区名字
     *
     * @param name 校区名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取校区代码或者编码
     *
     * @return Number - 校区代码或者编码
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置校区代码或者编码
     *
     * @param number 校区代码或者编码
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return Address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return TelNumber
     */
    public String getTelnumber() {
        return telnumber;
    }

    /**
     * @param telnumber
     */
    public void setTelnumber(String telnumber) {
        this.telnumber = telnumber;
    }

    /**
     * @return InDate
     */
    public Date getIndate() {
        return indate;
    }

    /**
     * @param indate
     */
    public void setIndate(Date indate) {
        this.indate = indate;
    }

    /**
     * @return InUser
     */
    public String getInuser() {
        return inuser;
    }

    /**
     * @param inuser
     */
    public void setInuser(String inuser) {
        this.inuser = inuser;
    }

    /**
     * @return LastEditDate
     */
    public Date getLasteditdate() {
        return lasteditdate;
    }

    /**
     * @param lasteditdate
     */
    public void setLasteditdate(Date lasteditdate) {
        this.lasteditdate = lasteditdate;
    }

    /**
     * @return LastEditUser
     */
    public String getLastedituser() {
        return lastedituser;
    }

    /**
     * @param lastedituser
     */
    public void setLastedituser(String lastedituser) {
        this.lastedituser = lastedituser;
    }
}