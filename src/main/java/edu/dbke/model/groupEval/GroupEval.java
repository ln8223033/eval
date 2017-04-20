package edu.dbke.model.groupEval;

import javax.persistence.*;
import java.util.Date;

@Table(name = "group_eval")
public class GroupEval {

    public static final int DELETED = 1;
    public static final int UNDELETED = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 是否匿名评论
     */
    @Column(name = "anonymous_comment")
    private Boolean anonymousComment;

    /**
     * 发布
     */
    @Column(name = "auto_publish")
    private Boolean autoPublish;

    /**
     * 评论开关
     */
    @Column(name = "bs_witch")
    private Boolean bsWitch;

    /**
     * 作品结束上传时间
     */
    @Column(name = "`upload _time`")
    private Date uploadTime;

    /**
     * 评测结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 分组方式,0为还没设置,1为教师指定，2为学生自己分组(默认教师指定)
     */
    @Column(name = "group_method")
    private Integer groupMethod;

    /**
     * 每组人数(默认1人)
     */
    @Column(name = "group_size")
    private Integer groupSize;

    /**
     * 是否可以多次评论
     */
    @Column(name = "multi_comment")
    private Boolean multiComment;

    /**
     * 评测题目
     */
    @Column(name = "eval_title")
    private String evalTitle;

    /**
     * 是否公布结果
     */
    @Column(name = "publish_result")
    private Boolean publishResult;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 出题人
     */
    @Column(name = "owner_id")
    private Integer ownerId;

    /**
     * 教学班级id
     */
    @Column(name = "teaching_class_id")
    private Integer teachingClassId;

    /**
     * 群体评测题目id
     */
    @Column(name = "group_eval_question_id")
    private Integer groupEvalQuestionId;

    /**
     * 评测的详细内容
     */
    @Column(name = "eval_detail")
    private String evalDetail;

    /**
     * 分组结束时间
     */
    @Column(name = "group_time")
    private Date groupTime;

    /**
     * 评论任务所需评论组数，默认为3组
     */
    @Column(name = "check_task_group_count")
    private Integer checkTaskGroupCount;

    /**
     * 是否已分组
     */
    @Column(name = "assign_state")
    private Integer assignState;

    /**
     * 状态
     */
    private Integer status;

    /**
     * @return id
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
     * 获取是否匿名评论
     *
     * @return anonymous_comment - 是否匿名评论
     */
    public Boolean getAnonymousComment() {
        return anonymousComment;
    }

    /**
     * 设置是否匿名评论
     *
     * @param anonymousComment 是否匿名评论
     */
    public void setAnonymousComment(Boolean anonymousComment) {
        this.anonymousComment = anonymousComment;
    }

    /**
     * 获取发布
     *
     * @return auto_publish - 发布
     */
    public Boolean getAutoPublish() {
        return autoPublish;
    }

    /**
     * 设置发布
     *
     * @param autoPublish 发布
     */
    public void setAutoPublish(Boolean autoPublish) {
        this.autoPublish = autoPublish;
    }

    /**
     * 获取评论开关
     *
     * @return bs_witch - 评论开关
     */
    public Boolean getBsWitch() {
        return bsWitch;
    }

    /**
     * 设置评论开关
     *
     * @param bsWitch 评论开关
     */
    public void setBsWitch(Boolean bsWitch) {
        this.bsWitch = bsWitch;
    }

    /**
     * 获取作品结束上传时间
     *
     * @return upload _time - 作品结束上传时间
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * 设置作品结束上传时间
     *
     * @param uploadTime 作品结束上传时间
     */
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * 获取评测结束时间
     *
     * @return end_time - 评测结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置评测结束时间
     *
     * @param endTime 评测结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取分组方式,0为还没设置,1为教师指定，2为学生自己分组(默认教师指定)
     *
     * @return group_method - 分组方式,0为还没设置,1为教师指定，2为学生自己分组(默认教师指定)
     */
    public Integer getGroupMethod() {
        return groupMethod;
    }

    /**
     * 设置分组方式,0为还没设置,1为教师指定，2为学生自己分组(默认教师指定)
     *
     * @param groupMethod 分组方式,0为还没设置,1为教师指定，2为学生自己分组(默认教师指定)
     */
    public void setGroupMethod(Integer groupMethod) {
        this.groupMethod = groupMethod;
    }

    /**
     * 获取每组人数(默认1人)
     *
     * @return group_size - 每组人数(默认1人)
     */
    public Integer getGroupSize() {
        return groupSize;
    }

    /**
     * 设置每组人数(默认1人)
     *
     * @param groupSize 每组人数(默认1人)
     */
    public void setGroupSize(Integer groupSize) {
        this.groupSize = groupSize;
    }

    /**
     * 获取是否可以多次评论
     *
     * @return multi_comment - 是否可以多次评论
     */
    public Boolean getMultiComment() {
        return multiComment;
    }

    /**
     * 设置是否可以多次评论
     *
     * @param multiComment 是否可以多次评论
     */
    public void setMultiComment(Boolean multiComment) {
        this.multiComment = multiComment;
    }

    /**
     * 获取评测题目
     *
     * @return eval_title - 评测题目
     */
    public String getEvalTitle() {
        return evalTitle;
    }

    /**
     * 设置评测题目
     *
     * @param evalTitle 评测题目
     */
    public void setEvalTitle(String evalTitle) {
        this.evalTitle = evalTitle;
    }

    /**
     * 获取是否公布结果
     *
     * @return publish_result - 是否公布结果
     */
    public Boolean getPublishResult() {
        return publishResult;
    }

    /**
     * 设置是否公布结果
     *
     * @param publishResult 是否公布结果
     */
    public void setPublishResult(Boolean publishResult) {
        this.publishResult = publishResult;
    }

    /**
     * 获取开始时间
     *
     * @return start_time - 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取出题人
     *
     * @return owner_id - 出题人
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * 设置出题人
     *
     * @param ownerId 出题人
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 获取教学班级id
     *
     * @return teaching_class_id - 教学班级id
     */
    public Integer getTeachingClassId() {
        return teachingClassId;
    }

    /**
     * 设置教学班级id
     *
     * @param teachingClassId 教学班级id
     */
    public void setTeachingClassId(Integer teachingClassId) {
        this.teachingClassId = teachingClassId;
    }

    /**
     * 获取群体评测题目id
     *
     * @return group_eval_question_id - 群体评测题目id
     */
    public Integer getGroupEvalQuestionId() {
        return groupEvalQuestionId;
    }

    /**
     * 设置群体评测题目id
     *
     * @param groupEvalQuestionId 群体评测题目id
     */
    public void setGroupEvalQuestionId(Integer groupEvalQuestionId) {
        this.groupEvalQuestionId = groupEvalQuestionId;
    }

    /**
     * 获取评测的详细内容
     *
     * @return eval_detail - 评测的详细内容
     */
    public String getEvalDetail() {
        return evalDetail;
    }

    /**
     * 设置评测的详细内容
     *
     * @param evalDetail 评测的详细内容
     */
    public void setEvalDetail(String evalDetail) {
        this.evalDetail = evalDetail;
    }

    /**
     * 获取分组结束时间
     *
     * @return group_time - 分组结束时间
     */
    public Date getGroupTime() {
        return groupTime;
    }

    /**
     * 设置分组结束时间
     *
     * @param groupTime 分组结束时间
     */
    public void setGroupTime(Date groupTime) {
        this.groupTime = groupTime;
    }

    /**
     * 获取评论任务所需评论组数，默认为3组
     *
     * @return check_task_group_count - 评论任务所需评论组数，默认为3组
     */
    public Integer getCheckTaskGroupCount() {
        return checkTaskGroupCount;
    }

    /**
     * 设置评论任务所需评论组数，默认为3组
     *
     * @param checkTaskGroupCount 评论任务所需评论组数，默认为3组
     */
    public void setCheckTaskGroupCount(Integer checkTaskGroupCount) {
        this.checkTaskGroupCount = checkTaskGroupCount;
    }

    /**
     * 获取是否已分组
     *
     * @return assign_state - 是否已分组
     */
    public Integer getAssignState() {
        return assignState;
    }

    /**
     * 设置是否已分组
     *
     * @param assignState 是否已分组
     */
    public void setAssignState(Integer assignState) {
        this.assignState = assignState;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}