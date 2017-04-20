package edu.dbke.model.groupEval;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "group_eval_question")
public class GroupEvalQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public static int DELETED = 1;
    public static int UNDELETED = 0;

    public static int SHARE = 1;
    public static int UNSHARE = 0;
    /**
     * 题目
     */
    private String title;

    /**
     * 题目的详细内容
     */
    @Column(name = "title_detail")
    private String titleDetail;

    /**
     * 出题人
     */
    @Column(name = "owner_id")
    private Integer ownerId;

    /**
     * 出题时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 状态，0为删除，1为未删除
     */
    private int status;

    /**
     * 是否分享，1为分享
     */
    @Column(name = "is_share")
    private int isShare;

    /**
     * 所属课程组id
     */
    @Column(name = " course_group_id")
    private Integer  courseGroupId;

    /**
     * 此题的上一个版本
     */
    @Column(name = "previous_question_id")
    private Integer previousQuestionId;

    /**
     * 根题目，此题的最原始版本
     */
    @Column(name = "root_question_id")
    private Integer rootQuestionId;

    /**
     * @return id
     */
    @Transient
    private List<EvalAttachment> evalAttachments =new ArrayList<EvalAttachment>();
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
     * 获取题目
     *
     * @return title - 题目
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置题目
     *
     * @param title 题目
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取题目的详细内容
     *
     * @return title_detail - 题目的详细内容
     */
    public String getTitleDetail() {
        return titleDetail;
    }

    /**
     * 设置题目的详细内容
     *
     * @param titleDetail 题目的详细内容
     */
    public void setTitleDetail(String titleDetail) {
        this.titleDetail = titleDetail;
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
     * 获取出题时间
     *
     * @return create_time - 出题时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置出题时间
     *
     * @param createTime 出题时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取状态，0为删除，1为未删除
     *
     * @return status - 状态，0为删除，1为未删除
     */
    public int getStatus() {
        return status;
    }

    /**
     * 设置状态，0为删除，1为未删除
     *
     * @param status 状态，0为删除，1为未删除
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 获取是否分享，1为分享
     *
     * @return isShare - 是否分享，1为分享
     */
    public int getIsshare() {
        return isShare;
    }

    /**
     * 设置是否分享，1为分享
     *
     * @param isshare 是否分享，1为分享
     */
    public void setIsshare(int isshare) {
        this.isShare = isshare;
    }



    /**
     * 获取所属课程id
     *
     * @return course_id - 所属课程id
     */
    public Integer getCourseGroupId() {
        return courseGroupId;
    }

    /**
     * 设置所属课程id
     *
     * @param courseGroupId 所属课程id
     */
    public void setCourseGroupId(Integer courseGroupId) {
        this.courseGroupId = courseGroupId;
    }

    /**
     * 获取此题的上一个版本
     *
     * @return previous_question_id - 此题的上一个版本
     */
    public Integer getPreviousQuestionId() {
        return previousQuestionId;
    }

    /**
     * 设置此题的上一个版本
     *
     * @param previousQuestionId 此题的上一个版本
     */
    public void setPreviousQuestionId(Integer previousQuestionId) {
        this.previousQuestionId = previousQuestionId;
    }

    /**
     * 获取根题目，此题的最原始版本
     *
     * @return root_question_id - 根题目，此题的最原始版本
     */
    public Integer getRootQuestionId() {
        return rootQuestionId;
    }

    /**
     * 设置根题目，此题的最原始版本
     *
     * @param rootQuestionId 根题目，此题的最原始版本
     */
    public void setRootQuestionId(Integer rootQuestionId) {
        this.rootQuestionId = rootQuestionId;
    }

    public List<EvalAttachment> getEvalAttachments() {
        return evalAttachments;
    }

    public void setEvalAttachments(List<EvalAttachment> evalAttachments) {
        this.evalAttachments = evalAttachments;
    }
}