package edu.dbke.model.groupEval;



import edu.dbke.model.basic.Course;
import edu.dbke.model.basic.Teacher;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 群体评测题
 * Created by hp on 2016/12/11.
 */
@Table(name = "group_eval_question")
public class GroupEvalQuestion  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public static int SHARE = 1;
    public static int UNSHARE = 0;
    public static int DELETED = 1;
    public static int UNDELETED = 0;
    private String title;//题目的名字
    @Column(name = "title_detail")
    private String titleDetail;//题目的详细内容
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Teacher owner;//出题人
    @Column(name = "create_time")
    private Date createTime;//出题时间
    @Column(name = "is_share")
    private int isShare;//是否共享
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;//对应的课程
    private int status = 0;//题目状态（删除，未删除）

    @OneToMany(mappedBy = "evalQuestion", cascade = { CascadeType.ALL })
    private List<EvalCheckItem> evalCheckItems = new ArrayList<EvalCheckItem>();//该题下的得分项

    @OneToMany(mappedBy = "evalQuestion")
    private List<EvalAttachment> attachment = new ArrayList<EvalAttachment>();//附件

    //版本控制
    @ManyToOne
    @JoinColumn(name = "previous_question_id")
    private GroupEvalQuestion previousQuestion;//此题的上一个版本

    @ManyToOne
    @JoinColumn(name = "root_question_id")
    private GroupEvalQuestion rootQuestion;//根题目，此题的最原始版本

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleDetail() {
        return titleDetail;
    }

    public void setTitleDetail(String titleDetail) {
        this.titleDetail = titleDetail;
    }

    public Teacher getOwner() {
        return owner;
    }

    public void setOwner(Teacher owner) {
        this.owner = owner;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getIsShare() {
        return isShare;
    }
    public String getIsShareDTO(){
        if( this.getIsShare() != GroupEvalQuestion.SHARE){
            return "<a href=\"javascript:void(0)\" onclick=\"isShare('" + id + "')\">共享</a>";
        }else{
            return "<a href=\"javascript:void(0)\" onclick=\"isShare('" + id + "')\">取消共享</a>";
        }
    }

    public void setIsShare(int isShare) {
        this.isShare = isShare;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public GroupEvalQuestion getPreviousQuestion() {
        return previousQuestion;
    }

    public void setPreviousQuestion(GroupEvalQuestion previousQuestion) {
        this.previousQuestion = previousQuestion;
    }

    public GroupEvalQuestion getRootQuestion() {
        return rootQuestion;
    }

    public void setRootQuestion(GroupEvalQuestion rootQuestion) {
        this.rootQuestion = rootQuestion;
    }

    public List<EvalCheckItem> getEvalCheckItems() {
        return evalCheckItems;
    }

    public void setEvalCheckItems(List<EvalCheckItem> evalCheckItems) {
        this.evalCheckItems = evalCheckItems;
    }

    public List<EvalAttachment> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<EvalAttachment> attachment) {
        this.attachment = attachment;
    }
}
