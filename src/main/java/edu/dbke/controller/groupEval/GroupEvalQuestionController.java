package edu.dbke.controller.groupEval;

import edu.dbke.model.basic.Teacher;
import edu.dbke.model.files.Files;
import edu.dbke.model.groupEval.EvalAttachment;
import edu.dbke.model.groupEval.EvalCheckItem;
import edu.dbke.model.groupEval.GroupEvalQuestion;
import edu.dbke.model.org.User;
import edu.dbke.service.files.FilesService;
import edu.dbke.service.groupEval.EvalAttachmentService;
import edu.dbke.service.groupEval.EvalCheckItemService;
import edu.dbke.service.groupEval.GroupEvalQuestionService;
import edu.dbke.util.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.List;


/**
 * Created by hp on 2017/1/5.
 */
@Controller
public class GroupEvalQuestionController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private EvalCheckItemService evalCheckItemService;

    @Resource
    private GroupEvalQuestionService groupEvalQuestionService;

    @Resource
    private FilesService filesService;

    @Resource
    private EvalAttachmentService evalAttachmentService;

    GroupEvalQuestion groupEvalQuestion = new GroupEvalQuestion();

    //获取该课程的的所有评测试题
    @RequestMapping("/manage")
    public ModelAndView evalQuestinManage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (request.getParameter("listType").equals("private")) {//私有题库
            List<GroupEvalQuestion> groupEvalQuestions = groupEvalQuestionService.selectAllPrivate(groupEvalQuestion);
            mav.addObject("groupEvalQuestions", groupEvalQuestions);
        } else if (request.getParameter("listType").equals("public")) {//公有题库
            List<GroupEvalQuestion> groupEvalQuestions = groupEvalQuestionService.selectAllPublic(groupEvalQuestion);
            mav.addObject("groupEvalQuestions", groupEvalQuestions);
        }
        return mav;
    }

    //添加评测题目
    @RequestMapping("/insert")
    public ModelAndView insertEvalQuestion(HttpServletRequest request) {
        User user = new User();
        groupEvalQuestion.setCourse(groupEvalQuestion.getCourse());
        groupEvalQuestion.setCreateTime(new Date());
        groupEvalQuestion.setOwner((Teacher) user);
        groupEvalQuestion.setPreviousQuestion(null); //版本控制，此题的原始题目为本身
        groupEvalQuestion.setRootQuestion(null);
        groupEvalQuestion.setStatus(groupEvalQuestion.UNSHARE);
        groupEvalQuestion.setTitleDetail(groupEvalQuestion.getTitleDetail());
        groupEvalQuestion.setTitle(groupEvalQuestion.getTitle());
        groupEvalQuestion.setIsShare(groupEvalQuestion.UNSHARE);//默认私有
        groupEvalQuestionService.insertGroupEval(groupEvalQuestion);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("groupEval/teacher/addEvalQuestion");
        return mav;
    }

    //删除单个评测
    @RequestMapping("/delete")
    public ModelAndView deleteEvalQuestion(HttpServletRequest request) {
        groupEvalQuestionService.deleteEvalQuestion(groupEvalQuestion);
        return evalQuestinManage(request);
    }

    //批量删除评测
    @RequestMapping("/delete")
    public ModelAndView deleteEvalQuestionS(HttpServletRequest request) {
        groupEvalQuestionService.deleteEvalQuestionS(groupEvalQuestion);
        return evalQuestinManage(request);
    }

    //删除子检查项
    @RequestMapping("/delete")
    public ModelAndView deleteSubItem(HttpServletRequest request) {

        EvalCheckItem evalCheckItem = new EvalCheckItem();
        evalCheckItemService.delete(evalCheckItem);
        // 要修改父检查点的总分
        EvalCheckItem parentItem = evalCheckItem.getParentItem();
        if (parentItem != null) {
            try {
                double totalScore = 0;
                for (EvalCheckItem tempItem : parentItem.getChildItem()) {
                    totalScore += tempItem.getScore();
                }
                parentItem.setScore(totalScore);
                evalCheckItemService.update(parentItem);
            } catch (Exception e) {
                return null;
            }
        }
        return insertEvalQuestion(request);
    }



    public ModelAndView update(HttpServletRequest request) throws Exception {

        GroupEvalQuestion temp = this.groupEvalQuestionService.find(groupEvalQuestion); //上一个版本的题目

        temp.setTitle(groupEvalQuestion.getTitle());
        temp.setTitleDetail(groupEvalQuestion.getTitleDetail());
        groupEvalQuestionService.update(temp);

        return insertEvalQuestion(request);
    }




    /**
     * 复制检查项目和检查指标
     */
    public ModelAndView copyCheckItem(HttpServletRequest request) {
        GroupEvalQuestion groupEvalQuestion1 = groupEvalQuestionService.find(groupEvalQuestion);
       groupEvalQuestion = groupEvalQuestionService.find(groupEvalQuestion1);

        if (groupEvalQuestion == null) {
            return null;
        } else {
            List<EvalCheckItem> evalCheckItems = groupEvalQuestion.getEvalCheckItems();
            if (evalCheckItems != null) {
                //所有的大选择项
                for (int i = 0; i < evalCheckItems.size(); i++) {
                    EvalCheckItem tempItem = evalCheckItems.get(i);
                    EvalCheckItem targetItem = new EvalCheckItem();//检查项
                    targetItem.setGroupEvalQuestion(groupEvalQuestion);
                    targetItem.setDetail(tempItem.getDetail());
                    targetItem.setScore(tempItem.getScore());
                    evalCheckItemService.insert(targetItem);
                    for (EvalCheckItem childItem : tempItem.getChildItem()) {
                        //复制子项
                        EvalCheckItem targetChildCheckItem = new EvalCheckItem();
                        targetChildCheckItem.setDetail(childItem.getDetail());
                        targetChildCheckItem.setScore(childItem.getScore());
                        targetChildCheckItem.setParentItem(targetItem);
                        evalCheckItemService.insert(targetChildCheckItem);
                    }
                }
            }

        }
        return insertEvalQuestion(request);
    }

    /**
     * 创建副本
     */
    public ModelAndView copyEvalQuestion(HttpServletRequest request) {
        String type = request.getParameter("type");
        groupEvalQuestion = groupEvalQuestionService.find(groupEvalQuestion);

        GroupEvalQuestion  evalQuestioin1 = new GroupEvalQuestion();
        evalQuestioin1.setCourse(groupEvalQuestion.getCourse());
        evalQuestioin1.setCreateTime(new Date());
        evalQuestioin1.setTitleDetail(groupEvalQuestion.getTitleDetail());
        evalQuestioin1.setTitle(request.getParameter("Title"));
        evalQuestioin1.setOwner(groupEvalQuestion.getOwner());

        if (type.equals("private")) {
            groupEvalQuestion.setIsShare(groupEvalQuestion.UNSHARE);
            groupEvalQuestion.setStatus(groupEvalQuestion.UNDELETED);
        } else if (type.equals("public")) {
            groupEvalQuestion.setIsShare(groupEvalQuestion.UNSHARE);
            groupEvalQuestion.setStatus(groupEvalQuestion.UNDELETED);
        } else {
            System.out.print("创建副本出错!");
        }
        groupEvalQuestionService.insertGroupEval(groupEvalQuestion);
        //创建EvalAttachment
        List<EvalAttachment> list = groupEvalQuestion.getAttachment();
        for (int i = 0; i < list.size(); i++) {
            EvalAttachment eval = new EvalAttachment();
            EvalAttachment evaltempa = (EvalAttachment) list.get(i);
            eval.setFiles(evaltempa.getFiles());
            eval.setEvalQuestion(evalQuestioin);
            evalAttachmentService.create(eval);
        }

        //得分点复制
        List<EvalCheckItem> evalCheckItems = entity.getEvalCheckItems();
        EvalCheckItem fromItem = null, toItem = null;
        for (int i = 0; i < evalCheckItems.size(); i++) {
            fromItem = evalCheckItems.get(i);

            if (fromItem.getParentItem() != null)
                continue;

            toItem = new EvalCheckItem();
            toItem.setDetail(fromItem.getDetail());
            toItem.setScore(fromItem.getScore());
            toItem.setParentItem(null);
            toItem.setEvalQuestion(evalQuestioin);
            evalCheckItemService.create(toItem);//创建父项目
            //如果有子项
            if (fromItem.getChildItem().size() > 0) {
                EvalCheckItem item = null;

                for (int j = 0; j < fromItem.getChildItem().size(); j++) {
                    item = new EvalCheckItem();
                    item.setDetail(fromItem.getChildItem().get(j).getDetail());
                    item.setScore(fromItem.getChildItem().get(j).getScore());
                    item.setParentItem(toItem);
                    evalCheckItemService.create(item);
                }
            }
        }



        return null;
    }

    /**
     * 删除附件
     */
    public ModelAndView deleteAttachment(HttpServletRequest request, EvalAttachment evalAttachment) {

        groupEvalQuestion = groupEvalQuestionService.find(groupEvalQuestion);
        evalAttachmentService.deleteByPrimaryKey(evalAttachment);
        return insertEvalQuestion(request);
    }

    /**
     * 上传附件
     *
     * @return
     */
    public String addAttachment() {
        return null;
    }

    /**
     * 显示附件列表
     *
     * @return
     */
    public String listAttachment() {
        StringBuffer sb = new StringBuffer();
        groupEvalQuestion = groupEvalQuestionService.find(groupEvalQuestion);
        sb.append("{\"data\":");
        if (null != groupEvalQuestion) {
            if (groupEvalQuestion.getAttachment().size() > 0) {
                sb.append("[");
                for (EvalAttachment evalAttachment : groupEvalQuestion.getAttachment()) {
                    sb.append("{\"name\":\"")
                            .append(evalAttachment.getFiles().getName() + "."
                                    + evalAttachment.getFiles().getUploadExt() + "\",")
                            .append("\"id\":\"" + evalAttachment.getId() + "\"},");
                }
                sb.append("]");
                sb.deleteCharAt(sb.lastIndexOf(","));
            } else {
                sb.append("[]");
            }
            sb.append("}");
        } else {
            sb.append("[]}");
        }
        System.out.println(sb.toString());
        return null;
    }

    /**
     * 下载附件
     *
     * @return
     */
    public String downloadAttachment() {
        EvalAttachment evalAttachment = evalAttachmentService.find(a);
        if (null != evalAttachment && null != evalAttachment.getFiles()) {
            Files attachmentFiles = evalAttachment.getFiles();
            HttpServletResponse response = ServletActionContext.getResponse();
            //设置headers参数
            HttpServletRequest request = ServletActionContext.getRequest();
            String fileName = attachmentFiles.getName() + "." + attachmentFiles.getUploadext();
            ServletUtils.fileDownload(request, response, fileName, fileService.getFileById(attachmentFiles.getId()),
                    attachmentFiles.getContentType());
        }
        return null;
    }

    /**
     * 添加或者修改评测题目检查项
     *
     * @return
     */
    public String addItem() {
        String item = ServletActionContext.getRequest().getParameter("item");
        String evalQuestionId = ServletActionContext.getRequest().getParameter("id");

        if (itemId == null) { //增加
            EvalCheckItem checkItem = new EvalCheckItem();
            EvalQuestion evalQuestion = this.evalQuestionService.find(evalQuestionId);
            checkItem.setDetail(item);
            checkItem.setEvalQuestion(evalQuestion);

            this.evalCheckItemService.create(checkItem);
        } else {//修改
            EvalCheckItem checkItem = evalCheckItemService.find(itemId);
            checkItem.setDetail(item);
            evalCheckItemService.edit(checkItem);
        }

        Struts2Utils.renderText("true");

        return null;
    }

    /**
     * 添加subItem,修改
     */
    public String addSubItem() {
        EvalCheckItem subItem = null;
        if (subItemId == null || subItemId.length() == 0) {
            subItem = new EvalCheckItem();
            subItem.setScore(credit);
            subItem.setDetail(item);
            EvalCheckItem parentItem = evalCheckItemService.find(itemId);
            subItem.setParentItem(parentItem);
            evalCheckItemService.create(subItem);
        } else {
            subItem = evalCheckItemService.find(subItemId);
            subItem.setDetail(item);
            subItem.setScore(credit);
            evalCheckItemService.edit(subItem);
        }
        // 要修改父检查点的总分
        EvalCheckItem parentItem = subItem.getParentItem();
        double totalScore = 0;
        for (EvalCheckItem tempItem : parentItem.getChildItem()) {
            totalScore += tempItem.getScore();
        }
        parentItem.setScore(totalScore);
        evalCheckItemService.edit(parentItem);

        return null;
    }


}
