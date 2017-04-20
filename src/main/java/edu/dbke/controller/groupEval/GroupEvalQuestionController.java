package edu.dbke.controller.groupEval;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.dbke.model.basic.Coursegroup;
import edu.dbke.model.groupEval.EvalCheckItem;
import edu.dbke.model.groupEval.GroupEvalQuestion;
import edu.dbke.service.babic.CoursegroupService;
import edu.dbke.service.files.FilesService;
import edu.dbke.service.groupEval.EvalAttachmentService;
import edu.dbke.service.groupEval.EvalCheckItemService;
import edu.dbke.service.groupEval.GroupEvalQuestionService;
import edu.dbke.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * Created by hp on 2017/1/5.
 */
@RequestMapping("/question")
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
    @Resource
    private CoursegroupService coursegroupService;




    @RequestMapping("/manage")
    public ModelAndView evalQuestionManage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("groupEval/teacher/evalQuestionList");
        return mav;
    }

    //获取该课程的的所有评测试题
//    @ResponseBody
//    @RequestMapping("/list")
//    public List<GroupEvalQuestion> list(HttpServletRequest request) {
//
//        GroupEvalQuestion groupEvalQuestion =new GroupEvalQuestion();
//        List<GroupEvalQuestion> groupEvalQuestions = new ArrayList<GroupEvalQuestion>();
//       if (request.getParameter("listType").equals("private")) {//私有题库
//            groupEvalQuestions = groupEvalQuestionService.selectAllPrivate(groupEvalQuestion);
//      } else if (request.getParameter("listType").equals("public")) {//公有题库
//          groupEvalQuestions = groupEvalQuestionService.selectAllPublic(groupEvalQuestion);
//       }
//
//        return groupEvalQuestions;
//    }
    @ResponseBody
    @RequestMapping("/list")
//    public R list(String roleName, Integer page, Integer limit) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("roleName", roleName);
//        map.put("offset", (page - 1) * limit);
//        map.put("limit", limit);
//        GroupEvalQuestion groupEvalQuestion = new GroupEvalQuestion();
//        //查询列表数据
//        List<GroupEvalQuestion> list = groupEvalQuestionService.queryList(map);
//        int total = groupEvalQuestionService.queryTotal();
//
//        PageUtils pageUtil = new PageUtils(list, total, limit, page);
//
//        return R.ok().put("page", pageUtil);
//    }

    public R list(@RequestParam(required = true) Integer page,
                  @RequestParam(required = false) Integer pageSize,
                  HttpServletRequest request
    ) {
        GroupEvalQuestion groupEvalQuestion = new GroupEvalQuestion();
        PageHelper.startPage(page, pageSize);
//        List<GroupEvalQuestion> list = new ArrayList<GroupEvalQuestion>();
//        if (request.getParameter("listType").equals("private")) {//私有题库
//            list = groupEvalQuestionService.selectAllPrivate(groupEvalQuestion);
//      } else if (request.getParameter("listType").equals("public")) {//公有题库
//            list = groupEvalQuestionService.selectAllPublic(groupEvalQuestion);
//       }
        List<GroupEvalQuestion> list = groupEvalQuestionService.selectAllPrivate(groupEvalQuestion);
        PageInfo<GroupEvalQuestion> p = new PageInfo<GroupEvalQuestion>(list);
        //System.out.println(p.getList());

        return R.ok().put("page", p);
    }

    @RequestMapping("/course")
    public ModelAndView coursegroupList(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Coursegroup> coursegroupList = coursegroupService.getCoursegroupList();
        //相当于request的setAttribute，在jsp页面中通过itemList来获取
        mv.addObject("coursegroupList", coursegroupList);
        //指定视图

        return evalQuestionManage();
    }

    //添加评测题目
    @RequestMapping("/create")

    public ModelAndView insertEvalQuestion(HttpServletRequest request) {
        GroupEvalQuestion groupEvalQuestion = new GroupEvalQuestion();
        groupEvalQuestion.setCourseGroupId(groupEvalQuestion.getCourseGroupId());
        groupEvalQuestion.setCreateTime(new Date());
        groupEvalQuestion.setOwnerId(groupEvalQuestion.getOwnerId());
        groupEvalQuestion.setPreviousQuestionId(null); //版本控制，此题的原始题目为本身
        groupEvalQuestion.setRootQuestionId(groupEvalQuestion.getId());
        groupEvalQuestion.setStatus(groupEvalQuestion.UNDELETED);
        groupEvalQuestion.setIsshare(groupEvalQuestion.UNSHARE);
        groupEvalQuestion.setTitleDetail(request.getParameter("evalTitle"));
        groupEvalQuestion.setTitle(request.getParameter("evalDetail"));
        groupEvalQuestionService.insertGroupEval(groupEvalQuestion);

        ModelAndView mav = new ModelAndView();
        mav.addObject("groupEvalQuestion", groupEvalQuestion);
        mav.setViewName("groupEval/teacher/addEvalQuestion");
        return mav;
    }


    //删除评测
    @ResponseBody
    @RequestMapping("/delete")
    public ModelAndView deleteEvalQuestion(GroupEvalQuestion groupEvalQuestion) {

        groupEvalQuestionService.update(groupEvalQuestion);
        return evalQuestionManage();
    }


    //
//    //删除子检查项
//    @RequestMapping("/delete")
//    public ModelAndView deleteSubItem(HttpServletRequest request) {
//
//        EvalCheckItem evalCheckItem = new EvalCheckItem();
//        evalCheckItemService.delete(evalCheckItem);
//        // 要修改父检查点的总分
//        EvalCheckItem parentItem = evalCheckItem.getParentItem();
//        if (parentItem != null) {
//            try {
//                double totalScore = 0;
//                for (EvalCheckItem tempItem : parentItem.getChildItem()) {
//                    totalScore += tempItem.getScore();
//                }
//                parentItem.setScore(totalScore);
//                evalCheckItemService.update(parentItem);
//            } catch (Exception e) {
//                return null;
//            }
//        }
//        return insertEvalQuestion(request);
//    }
//
//
//
    @RequestMapping("/edit")
    public ModelAndView update(HttpServletRequest request) throws Exception {
        GroupEvalQuestion groupEvalQuestion = new GroupEvalQuestion();
        GroupEvalQuestion temp = this.groupEvalQuestionService.find(groupEvalQuestion); //上一个版本的题目

        temp.setTitle(groupEvalQuestion.getTitle());
        temp.setTitleDetail(groupEvalQuestion.getTitleDetail());
        groupEvalQuestionService.update(temp);

        return insertEvalQuestion(request);
    }


//
//
//    /**
//     * 复制检查项目和检查指标
//     */
//    public ModelAndView copyCheckItem(HttpServletRequest request) {
//        GroupEvalQuestion groupEvalQuestion1 = groupEvalQuestionService.find(groupEvalQuestion);
//       groupEvalQuestion = groupEvalQuestionService.find(groupEvalQuestion1);
//
//        if (groupEvalQuestion == null) {
//            return null;
//        } else {
//            List<EvalCheckItem> evalCheckItems = groupEvalQuestion.getEvalCheckItems();
//            if (evalCheckItems != null) {
//                //所有的大选择项
//                for (int i = 0; i < evalCheckItems.size(); i++) {
//                    EvalCheckItem tempItem = evalCheckItems.get(i);
//                    EvalCheckItem targetItem = new EvalCheckItem();//检查项
//                    targetItem.setGroupEvalQuestionId(groupEvalQuestion.getId());
//                    targetItem.setDetail(tempItem.getDetail());
//                    targetItem.setScore(tempItem.getScore());
//                    evalCheckItemService.insert(targetItem);
//                    for (EvalCheckItem childItem : tempItem.getChildItem()) {
//                        //复制子项
//                        EvalCheckItem targetChildCheckItem = new EvalCheckItem();
//                        targetChildCheckItem.setDetail(childItem.getDetail());
//                        targetChildCheckItem.setScore(childItem.getScore());
//                        targetChildCheckItem.setParentItem(targetItem);
//                        evalCheckItemService.insert(targetChildCheckItem);
//                    }
//                }
//            }
//
//        }
//        return insertEvalQuestion(request);
//    }
//
//
//    /**
//     * 创建副本
//     */
//    @RequestMapping("/copyEvalQuestion")
//    public ModelAndView copyEvalQuestion(HttpServletRequest request) {
//        String type = request.getParameter("type");
//        groupEvalQuestion = groupEvalQuestionService.find(groupEvalQuestion);
//
//        GroupEvalQuestion evalQuestioin1 = new GroupEvalQuestion();
//        evalQuestioin1.setCourseGroupId(groupEvalQuestion.getCourseGroupId());
//        evalQuestioin1.setCreateTime(new Date());
//        evalQuestioin1.setTitleDetail(groupEvalQuestion.getTitleDetail());
//        evalQuestioin1.setTitle(request.getParameter("Title"));
//        evalQuestioin1.setOwnerId(groupEvalQuestion.getOwnerId());
//
//        if (type.equals("private")) {
//            groupEvalQuestion.setIsShare(groupEvalQuestion.UNSHARE);
//            groupEvalQuestion.setStatus(groupEvalQuestion.UNDELETED);
//        } else if (type.equals("public")) {
//            groupEvalQuestion.setIsShare(groupEvalQuestion.UNSHARE);
//            groupEvalQuestion.setStatus(groupEvalQuestion.UNDELETED);
//        } else {
//            System.out.print("创建副本出错!");
//        }
//        groupEvalQuestionService.insertGroupEval(groupEvalQuestion);
//        //创建EvalAttachment
//        List<EvalAttachment> list = groupEvalQuestion.getAttachments();
//        for (int i = 0; i < list.size(); i++) {
//            EvalAttachment eval = new EvalAttachment();
//            EvalAttachment evaltempa = (EvalAttachment) list.get(i);
//            eval.setFiles(evaltempa.getFiles());
//            eval.setGroupEvalQuestionId(groupEvalQuestion.getId());
//            ;
//            evalAttachmentService.insert(eval);
//        }
//
//        //得分点复制
//        List<EvalCheckItem> evalCheckItems = groupEvalQuestion.getEvalCheckItems();
//        EvalCheckItem fromItem = null, toItem = null;
//        for (int i = 0; i < evalCheckItems.size(); i++) {
//            fromItem = evalCheckItems.get(i);
//
//            if (fromItem.getParentItem() != null)
//                continue;
//
//            toItem = new EvalCheckItem();
//            toItem.setDetail(fromItem.getDetail());
//            toItem.setScore(fromItem.getScore());
//            toItem.setParentItem(null);
//            toItem.setGroupEvalQuestionId(groupEvalQuestion.getId());
//            evalCheckItemService.insert(toItem);//创建父项目
//            //如果有子项
//            if (fromItem.getChildItem().size() > 0) {
//                EvalCheckItem item = null;
//
//                for (int j = 0; j < fromItem.getChildItem().size(); j++) {
//                    item = new EvalCheckItem();
//                    item.setDetail(fromItem.getChildItem().get(j).getDetail());
//                    item.setScore(fromItem.getChildItem().get(j).getScore());
//                    item.setParentItem(toItem);
//                    evalCheckItemService.insert(item);
//                }
//            }
//        }
//
//
//        return null;
//    }
}
//
//
//    /**
//     * 上传附件
//     *
//     * @return
//     */
//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    @ResponseBody
//    public String addAttachment(MultipartFile file, HttpServletRequest request) throws IOException {
//        String path = request.getSession().getServletContext().getRealPath("upload");
//        String fileName = file.getOriginalFilename();
//        File dir = new File(path, fileName);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        //MultipartFile自带的解析方法
//        file.transferTo(dir);
//        return "ok!";
//    }
//
//    /**
//     * 附件下载功能
//     */
//    @RequestMapping("/down")
//    public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{
//        //模拟文件，myfile.txt为需要下载的文件
//        String fileName = request.getSession().getServletContext().getRealPath("upload")+"/myfile.txt";
//        //获取输入流
//        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
//        //假如以中文名下载的话
//        String filename = "下载文件.txt";
//        //转码，免得文件名中文乱码
//        filename = URLEncoder.encode(filename,"UTF-8");
//        //设置文件下载头
//        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
//        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
//        response.setContentType("multipart/form-data");
//        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
//        int len = 0;
//        while((len = bis.read()) != -1){
//            out.write(len);
//            out.flush();
//        }
//        out.close();
//    }
//}
/**
 * 删除附件
 */
//    public ModelAndView deleteAttachment(HttpServletRequest request, EvalAttachment evalAttachment) {
//        GroupEvalQuestion groupEvalQuestion=new GroupEvalQuestion();
//        groupEvalQuestion = groupEvalQuestionService.find(groupEvalQuestion);
//        evalAttachmentService.deleteByPrimaryKey(evalAttachment);
//        return insertEvalQuestion(request);
//    }
//
//    /**
//     * 显示附件列表
//     *
//     * @return
//     */
//    public String listAttachment() {
//        StringBuffer sb = new StringBuffer();
//        groupEvalQuestion = groupEvalQuestionService.find(groupEvalQuestion);
//        sb.append("{\"data\":");
//        if (null != groupEvalQuestion) {
//            if (groupEvalQuestion.getAttachments().size() > 0) {
//                sb.append("[");
//                for (EvalAttachment evalAttachment : groupEvalQuestion.getAttachments()) {
//                    sb.append("{\"name\":\"")
//                            .append(evalAttachment.getFiles().getName() + "."
//                                    + evalAttachment.getFiles().getUploadExt() + "\",")
//                            .append("\"id\":\"" + evalAttachment.getId() + "\"},");
//                }
//                sb.append("]");
//                sb.deleteCharAt(sb.lastIndexOf(","));
//            } else {
//                sb.append("[]");
//            }
//            sb.append("}");
//        } else {
//            sb.append("[]}");
//        }
//        System.out.println(sb.toString());
//        return null;
//    }
//
//    /**
//     * 下载附件
//     *
//     * @return
//     */
//    public String downloadAttachment() {
//        EvalAttachment evalAttachment = evalAttachmentService.find(e);
//        if (null != evalAttachment && null != evalAttachment.getFiles()) {
//            Files attachmentFiles = evalAttachment.getFiles();
//            HttpServletResponse response = Ses.getResponse();
//            //设置headers参数
//            HttpServletRequest request = ServletActionContext.getRequest();
//            String fileName = attachmentFiles.getName() + "." + attachmentFiles.getUploadext();
//            ServletUtils.fileDownload(request, response, fileName, fileService.getFileById(attachmentFiles.getId()),
//                    attachmentFiles.getContentType());
//        }
//        return null;
//    }
//
//    /**
//     * 添加或者修改评测题目检查项
//     *
//     * @return
//     */
//    public String addItem() {
//        String item = ServletActionContext.getRequest().getParameter("item");
//        String evalQuestionId = ServletActionContext.getRequest().getParameter("id");
//
//        if (itemId == null) { //增加
//            EvalCheckItem checkItem = new EvalCheckItem();
//            EvalQuestion evalQuestion = this.evalQuestionService.find(evalQuestionId);
//            checkItem.setDetail(item);
//            checkItem.setEvalQuestion(evalQuestion);
//
//            this.evalCheckItemService.create(checkItem);
//        } else {//修改
//            EvalCheckItem checkItem = evalCheckItemService.find(itemId);
//            checkItem.setDetail(item);
//            evalCheckItemService.edit(checkItem);
//        }
//
//        Struts2Utils.renderText("true");
//
//        return null;
//    }
//
//    /**
//     * 添加subItem,修改
//     */
//    public String addSubItem() {
//        EvalCheckItem subItem = null;
//        if (subItemId == null || subItemId.length() == 0) {
//            subItem = new EvalCheckItem();
//            subItem.setScore(credit);
//            subItem.setDetail(item);
//            EvalCheckItem parentItem = evalCheckItemService.find(itemId);
//            subItem.setParentItem(parentItem);
//            evalCheckItemService.create(subItem);
//        } else {
//            subItem = evalCheckItemService.find(subItemId);
//            subItem.setDetail(item);
//            subItem.setScore(credit);
//            evalCheckItemService.edit(subItem);
//        }
//        // 要修改父检查点的总分
//        EvalCheckItem parentItem = subItem.getParentItem();
//        double totalScore = 0;
//        for (EvalCheckItem tempItem : parentItem.getChildItem()) {
//            totalScore += tempItem.getScore();
//        }
//        parentItem.setScore(totalScore);
//        evalCheckItemService.edit(parentItem);
//
//        return null;
//    }
//
//        return null;
//    }

