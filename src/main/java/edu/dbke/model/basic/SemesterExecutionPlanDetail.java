package edu.dbke.model.basic;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_tts_semester_execution_plan_detail")
public class SemesterExecutionPlanDetail {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 人才培养方案ID
     */
    @Column(name = "TTSMasterId")
    private Integer ttsmasterid;

    /**
     * 大纲ID
     */
    @Column(name = "SyllabusId")
    private Integer syllabusid;

    /**
     * 课程代码
     */
    @Column(name = "CourseCode")
    private String coursecode;

    /**
     * 课程负责学院ID
     */
    @Column(name = "InChargeOfDeptId")
    private Integer inchargeofdeptid;

    /**
     * 模块ID
     */
    @Column(name = "MoudleId")
    private Integer moudleid;

    /**
     * 类别ID
     */
    @Column(name = "CategroyId")
    private Integer categroyid;

    /**
     * 课程性质
     */
    @Column(name = "CourseNatureld")
    private Integer coursenatureld;

    /**
     * 先修课程
     */
    @Column(name = "PreCourse")
    private String precourse;

    /**
     * 平时成绩比例
     */
    @Column(name = "InspectionProportion")
    private Integer inspectionproportion;

    /**
     * 执行学期
     */
    @Column(name = "PerformSemester")
    private String performsemester;

    /**
     * 备注
     */
    @Column(name = "Memo")
    private String memo;

    @Column(name = "InDate")
    private Date indate;

    @Column(name = "InUser")
    private String inuser;

    @Column(name = "LastEditDate")
    private Date lasteditdate;

    @Column(name = "LastEditUser")
    private String lastedituser;

    /**
     * 是否是 原始计划
     */
    @Column(name = "IsCopy")
    private Boolean iscopy;

    @Column(name = "IsUpdate")
    private Boolean isupdate;

    /**
     * 用于数据逻辑删除
     */
    @Column(name = "Status")
    private Integer status;

    /**
     * 教学任务审核状态
     */
    @Column(name = "ApproveStatus")
    private Integer approvestatus;

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
     * 获取人才培养方案ID
     *
     * @return TTSMasterId - 人才培养方案ID
     */
    public Integer getTtsmasterid() {
        return ttsmasterid;
    }

    /**
     * 设置人才培养方案ID
     *
     * @param ttsmasterid 人才培养方案ID
     */
    public void setTtsmasterid(Integer ttsmasterid) {
        this.ttsmasterid = ttsmasterid;
    }

    /**
     * 获取大纲ID
     *
     * @return SyllabusId - 大纲ID
     */
    public Integer getSyllabusid() {
        return syllabusid;
    }

    /**
     * 设置大纲ID
     *
     * @param syllabusid 大纲ID
     */
    public void setSyllabusid(Integer syllabusid) {
        this.syllabusid = syllabusid;
    }

    /**
     * 获取课程代码
     *
     * @return CourseCode - 课程代码
     */
    public String getCoursecode() {
        return coursecode;
    }

    /**
     * 设置课程代码
     *
     * @param coursecode 课程代码
     */
    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    /**
     * 获取课程负责学院ID
     *
     * @return InChargeOfDeptId - 课程负责学院ID
     */
    public Integer getInchargeofdeptid() {
        return inchargeofdeptid;
    }

    /**
     * 设置课程负责学院ID
     *
     * @param inchargeofdeptid 课程负责学院ID
     */
    public void setInchargeofdeptid(Integer inchargeofdeptid) {
        this.inchargeofdeptid = inchargeofdeptid;
    }

    /**
     * 获取模块ID
     *
     * @return MoudleId - 模块ID
     */
    public Integer getMoudleid() {
        return moudleid;
    }

    /**
     * 设置模块ID
     *
     * @param moudleid 模块ID
     */
    public void setMoudleid(Integer moudleid) {
        this.moudleid = moudleid;
    }

    /**
     * 获取类别ID
     *
     * @return CategroyId - 类别ID
     */
    public Integer getCategroyid() {
        return categroyid;
    }

    /**
     * 设置类别ID
     *
     * @param categroyid 类别ID
     */
    public void setCategroyid(Integer categroyid) {
        this.categroyid = categroyid;
    }

    /**
     * 获取课程性质
     *
     * @return CourseNatureld - 课程性质
     */
    public Integer getCoursenatureld() {
        return coursenatureld;
    }

    /**
     * 设置课程性质
     *
     * @param coursenatureld 课程性质
     */
    public void setCoursenatureld(Integer coursenatureld) {
        this.coursenatureld = coursenatureld;
    }

    /**
     * 获取先修课程
     *
     * @return PreCourse - 先修课程
     */
    public String getPrecourse() {
        return precourse;
    }

    /**
     * 设置先修课程
     *
     * @param precourse 先修课程
     */
    public void setPrecourse(String precourse) {
        this.precourse = precourse;
    }

    /**
     * 获取平时成绩比例
     *
     * @return InspectionProportion - 平时成绩比例
     */
    public Integer getInspectionproportion() {
        return inspectionproportion;
    }

    /**
     * 设置平时成绩比例
     *
     * @param inspectionproportion 平时成绩比例
     */
    public void setInspectionproportion(Integer inspectionproportion) {
        this.inspectionproportion = inspectionproportion;
    }

    /**
     * 获取执行学期
     *
     * @return PerformSemester - 执行学期
     */
    public String getPerformsemester() {
        return performsemester;
    }

    /**
     * 设置执行学期
     *
     * @param performsemester 执行学期
     */
    public void setPerformsemester(String performsemester) {
        this.performsemester = performsemester;
    }

    /**
     * 获取备注
     *
     * @return Memo - 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置备注
     *
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
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

    /**
     * 获取是否是 原始计划
     *
     * @return IsCopy - 是否是 原始计划
     */
    public Boolean getIscopy() {
        return iscopy;
    }

    /**
     * 设置是否是 原始计划
     *
     * @param iscopy 是否是 原始计划
     */
    public void setIscopy(Boolean iscopy) {
        this.iscopy = iscopy;
    }

    /**
     * @return IsUpdate
     */
    public Boolean getIsupdate() {
        return isupdate;
    }

    /**
     * @param isupdate
     */
    public void setIsupdate(Boolean isupdate) {
        this.isupdate = isupdate;
    }

    /**
     * 获取用于数据逻辑删除
     *
     * @return Status - 用于数据逻辑删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置用于数据逻辑删除
     *
     * @param status 用于数据逻辑删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取教学任务审核状态
     *
     * @return ApproveStatus - 教学任务审核状态
     */
    public Integer getApprovestatus() {
        return approvestatus;
    }

    /**
     * 设置教学任务审核状态
     *
     * @param approvestatus 教学任务审核状态
     */
    public void setApprovestatus(Integer approvestatus) {
        this.approvestatus = approvestatus;
    }
}