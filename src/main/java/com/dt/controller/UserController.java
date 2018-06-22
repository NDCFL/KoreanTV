package com.dt.controller;

import com.dt.common.*;
import com.dt.controller.websocket.MessageHandler;
import com.dt.enums.ActiveStatusEnum;
import com.dt.service.UserService;
import com.dt.vo.FileVo;
import com.dt.vo.Select2Vo;
import com.dt.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController  {

    /**
     *
     */
    @Resource
    private UserService userService;
    @Autowired
    private MessageHandler messageHandler;
    @RequestMapping("loginPage")
    public String loginPage(){
        return  "login/loginPage";
    }
    @RequestMapping("getInfo")
    @ResponseBody
    public Message getInfo(String phone,String password){
        try{
            UserVo userVo = userService.getByAccountPassword(new UserAccountPasswordQuery(phone, new Md5Hash(password).toString()));
            if((userVo.getPhone().equals(phone) || userVo.getRealname().equals(phone) || userVo.getName().equals(phone) ) && userVo.getPassword().equals(new Md5Hash(password).toString())){
                Subject subject = SecurityUtils.getSubject();
                subject.login(new UsernamePasswordToken(phone, new Md5Hash(password).toString()));
                Session session = subject.getSession();
                session.setAttribute("userVo", userVo);
                return  Message.success("验证成功");
            }else{
                return  Message.fail("账号或密码输入有误,或已被禁用");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("账号或密码输入有误,或已被禁用");
        }
    }
    @ResponseBody
    @RequestMapping("getImgCode/{code}")
    public Message getImgCode(@PathVariable("code") String code, HttpSession session){
        String realCode = (String) session.getAttribute("rand");
        if(!code.equals(realCode)){
            return  Message.fail("验证码输入错误");
        }else{
            return  Message.success("验证码输入成功");
        }
    }
    @ResponseBody
    @RequestMapping("getImg")
    public String getImg(HttpSession session){
        UserVo userVo =(UserVo) session.getAttribute("userVo");
        return userService.getImg(userVo.getId());
    }
    @RequestMapping("main")
    public String main(){
        return "main";
    }
    @RequestMapping("exit")
    public String exit(HttpSession session) {
        session.invalidate();
        return "login/loginPage";
    }
    @RequestMapping("checkLogin")
    public Message checkLogin(String account) {
        int cnt = userService.checkLogin(account);
        if(cnt==0){
            return Message.success("账号不存在");
        }else {
            return Message.success("账号已存在");
        }
    }
    @RequestMapping("bossInfoPage")
    public String bossInfoPage() {
        return "user/bossInfoPage";
    }
    @RequestMapping("bossInfo")
    @ResponseBody
    public UserVo bossInfo(HttpSession session) {
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        return userService.getById(userVo.getId());
    }
    @RequestMapping("updateBossInfo")
    @ResponseBody
    public Message updateBossInfo(UserVo userVo) {
        try{
            int cnt= userService.checkName(userVo.getName());
            if(cnt>0){
                return Message.success("昵称已被占用！");
            }else{
                userService.update(userVo);
                return Message.success("资料修改成功！");
            }
        }catch (Exception e){
            return Message.success("资料修改失败！");
        }
    }
    @RequestMapping("updateImg")
    @ResponseBody
    public Message updateImg(UserVo userVo,HttpSession session) {
        try{
            UserVo userVo1 = (UserVo) session.getAttribute("userVo");
            userService.updateFaceImg(userVo.getId(),userVo.getFaceImg());
            return Message.success("头像修改成功！");
        }catch (Exception e){
            return Message.success("头像修改失败！");
        }
    }
    @RequestMapping("checkPwd")
    @ResponseBody
    public Map<String, Boolean> checkPwd(String password, HttpSession session) {
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        Map<String, Boolean> result = new HashMap<String, Boolean>();
        try {
            Subject subject = SecurityUtils.getSubject();
            String pwd = userService.getPassword(userVo.getId());
            if (pwd.equals(new Md5Hash(password).toString())) {
                result.put("valid", true);
            } else {
                result.put("valid", false);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("valid", false);
            return result;
        }
    }

    @RequestMapping("updatePassword")
    @ResponseBody
    public Message updatePassword(HttpSession session, String newpassword) {
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            userService.updatePwd(userVo.getId(), new Md5Hash(newpassword).toString());
            return  Message.success("密码修改成功!");
        }catch (Exception e){
            return  Message.success("密码修改失败!");
        }
    }
    @RequestMapping("resetPassword")
    @ResponseBody
    public Message resetPassword(HttpSession session, UserVo userVo) {
        try{
            userService.updatePwd(userVo.getId(), new Md5Hash(userVo.getPassword()).toString());
            return  Message.success("重置密码成功!");
        }catch (Exception e){
            return  Message.success("重置密码失败!");
        }
    }
    @RequestMapping("changePhone")
    public Message changePhone(HttpSession session, String phone) {
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            userService.updatePhone(userVo.getId(), phone);
            return  Message.success("修改手机绑定成功!");
        }catch (Exception e){
            return  Message.success("修改手机绑定失败!");
        }
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id, @PathVariable("status") int status) throws Exception {
        try {
            userService.updateStatus(new StatusQuery(id, status));
            return Message.success("ok");
        } catch (Exception e) {
            return Message.fail("fail");
        }
    }
    @RequestMapping("/deleteManyUser")
    @ResponseBody
    public Message deleteManycashSubject(@Param("manyId") String manyId) throws Exception {
        try {
            String str[] = manyId.split(",");
            for (String s : str) {
                userService.removeById(Long.parseLong(s));
            }
            return Message.success("删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }

    @RequestMapping("/deleteUser/{id}")
    @ResponseBody
    public Message deleteUser(@PathVariable("id") long id) throws Exception {
        try {
            userService.removeById(id);
            return Message.success("删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }

    @RequestMapping("userList")
    @ResponseBody
    public PagingBean userList(int pageSize, int pageIndex, String searchVal, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        pageQuery.setSearchVal(searchVal);
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(userService.count(pageQuery));
        pagingBean.setrows(userService.listPage(pageQuery));
        return pagingBean;
    }
    @RequestMapping("/userAddSave")
    @ResponseBody
    public Message addSaveUser(UserVo user, HttpSession session) throws  Exception {
        try{
            int cnt = userService.checkReg(user.getPhone());
            if (cnt>0) {
                return  Message.fail("账号已存在!");
            }else{
                user.setName(user.getPhone());
                user.setPassword(new Md5Hash(user.getPassword()).toString());
                user.setStatus(ActiveStatusEnum.ACTIVE.getValue());
                user.setSex("0");
                user.setFaceImg("/upload/face.gif");
                userService.save(user);
                return  Message.success("新增成功!");
            }
        }catch (Exception E){
            E.printStackTrace();
            return Message.fail("新增失败!");
        }
    }
    @RequestMapping("/getUserList")
    @ResponseBody
    public List<Select2Vo> getUserList() throws  Exception {
        return  userService.getUser();
    }
    @RequestMapping("/findUser/{id}")
    @ResponseBody
    public UserVo findUser(@PathVariable("id") long id){
        UserVo user = userService.getById(id);
        return user;
    }
    @RequestMapping("/userUpdateSave")
    @ResponseBody
    public Message updateUser(UserVo user) throws  Exception{
        try{
            userService.update(user);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/userPage")
    public String table() throws  Exception{
        return "user/userList";
    }
    @RequestMapping("up")
    @ResponseBody
    public FileVo fileUp(MultipartFile file, HttpServletRequest request,HttpSession session){
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        UserVo u = userService.getById(userVo.getId());
        FileVo fileVo = new FileVo();
        String path = request.getSession().getServletContext().getRealPath("upload/userImg/");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        File file1 = new File(rootPath+u.getFaceImg());
        file1.delete();//删除原来的就头像
        try{
            //使用原始文件名称
            String fileName = getFileName(file.getOriginalFilename());
            //重新格式化文件名称
            //String fileName = getFileName(file.getOriginalFilename());
            File dir = new File(path,fileName);
            if(!dir.exists()){
                dir.mkdirs();
            }
            file.transferTo(dir);
            Map<String,String> data = new HashMap<String, String>();
            data.put("src","/upload/userImg/"+fileName);
            fileVo.setData(data);
            System.out.println("保存到数据库的图片地址:/upload/userImg/"+fileName);
            fileVo.setCode(0);
            userService.updateFaceImg(userVo.getId(),"/upload/userImg/"+fileName);
        }catch (Exception e){
            e.printStackTrace();
            fileVo.setCode(1);
        }
        fileVo.setMsg("上传成功!");
        return  fileVo;
    }
    @RequestMapping("sendMsg")
    @ResponseBody
    //测试消息推送
    public Message pushMessage(UserVo userVo){
       try{
           //发送消息给特定用户
           messageHandler.sendMessageToUser(userVo.getId()+"","你好！");
           return Message.success("推送成功!");
       }catch (Exception e){
           e.printStackTrace();
           return Message.fail("推送失败!");
       }

    }


    //重命名文件名称
    private synchronized String getFileName(String filename) {
        int position = filename.lastIndexOf(".");
        String ext = filename.substring(position);
        return System.nanoTime() + ext;
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
