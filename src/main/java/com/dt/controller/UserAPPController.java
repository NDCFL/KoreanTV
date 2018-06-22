package com.dt.controller;

import com.dt.common.Message;
import com.dt.common.PageQuery;
import com.dt.common.PagingBean;
import com.dt.common.StatusQuery;
import com.dt.enums.ActiveStatusEnum;
import com.dt.service.AppSettingService;
import com.dt.service.AppUserService;
import com.dt.service.VerifcodeService;
import com.dt.vo.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("appUser")
public class UserAPPController {

    @Resource
    private AppUserService appUserService;
    @Resource
    private VerifcodeService verifcodeService;
    @Resource
    private AppSettingService appSettingService;
    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 10 * 10000000; // 超时时间
    private static final String CHARSET = "utf-8"; // 设置编码
    public static final String OPEN = "1";
    public static final String CLOSE = "0";
    @RequestMapping("checkLogin")
    @ResponseBody
    public Message checkLogin(String phone) {
        int cnt = appUserService.checkLogin(phone);
        if(cnt==0){
            return Message.success("账号不存在");
        }else {
            return Message.fail("账号已存在");
        }
    }
    @RequestMapping("register")
    @ResponseBody
    public Message register(String phone,String code,String passWord) {
        //查询改手机号在有效期5分钟之内是否还有未使用的短信，如果有则返回code如果没有则返回-1
        Verifcode verifcode = verifcodeService.queryByCode(phone);
        if(code==null || code.equals("")){
            return  Message.fail("你还未发送验证码!");
        }else{
            if(!verifcode.getCode().equals(code)){
                return  Message.fail("验证码输入错误!");
            }else{
                StatusQuery statusQuery = new StatusQuery();
                statusQuery.setStatus(1);
                statusQuery.setPhone(phone);
                //作废验证码
                verifcodeService.updateCodeStatus(statusQuery);
                AppUserVo appUserVo = new AppUserVo();
                appUserVo.setPhone(phone);
                appUserVo.setName(phone);
                appUserVo.setRealname(phone);
                appUserVo.setPassword(new Md5Hash(passWord).toString());
                appUserVo.setFaceImg("/upload/face.gif");
                appUserVo.setRemark("说点什么吧");
                appUserService.save(appUserVo);
                AppSettingVo appSettingVo = new AppSettingVo();
                appSettingVo.setUserId(appUserVo.getId());
                appSettingService.save(appSettingVo);
                return  Message.success("注册成功!");
            }
        }

    }
    @RequestMapping("findPwd")
    @ResponseBody
    public Message findPwd(String phone,String code,String passWord) {
       try{
           //查询改手机号在有效期5分钟之内是否还有未使用的短信，如果有则返回code如果没有则返回-1
           Verifcode verifcode = verifcodeService.queryByCode(phone);
           if(!verifcode.getCode().equals(code)){
               return  Message.fail("验证码输入错误!");
           }else{
               StatusQuery statusQuery = new StatusQuery();
               statusQuery.setStatus(1);
               statusQuery.setPhone(phone);
               //作废验证码
               verifcodeService.updateCodeStatus(statusQuery);
               appUserService.updatePassWord(phone,new Md5Hash(passWord).toString());
               return  Message.success("密码重置成功!");
           }
       }catch (Exception e){
           return  Message.fail("请求超时!");
       }
    }
    @RequestMapping("getInfo")
    @ResponseBody
    public Message getInfo(String phone,String passWord){
        try{
            AppUserVo appUserVo = appUserService.appLogin(phone, new Md5Hash(passWord).toString());
            if(appUserVo==null){
                return  Message.fail("账号或密码输入有误,或已被禁用");
            }else{
                return  Message.success(appUserVo.getId()+"");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("账号或密码输入有误,或已被禁用");
        }
    }
    @RequestMapping("updatePassword")
    @ResponseBody
    public Message updatePassword(String phone,String passWord) {
        try{
            appUserService.updatePassWord(phone, new Md5Hash(passWord).toString());
            return  Message.success("密码修改成功!");
        }catch (Exception e){
            return  Message.success("密码修改失败!");
        }
    }
    @RequestMapping("resetPwd")
    @ResponseBody
    public Message resetPwd(Long id) {
        try{
            appUserService.updatePwd(id, new Md5Hash("dt888888").toString());
            return  Message.success("重置密码成功!");
        }catch (Exception e){
            return  Message.success("重置密码失败!");
        }
    }
    @RequestMapping("updateFaceImg")
    @ResponseBody
    public Message updateFaceImg(Long id,String path) {
        try{
            appUserService.updateFaceImg(id,path);
            return  Message.success("头像修改成功!");
        }catch (Exception e){
            return  Message.success("头像修改失败!");
        }
    }
    @RequestMapping("updateName")
    @ResponseBody
    public Message updateName(Long id,String name) {
        try{
            appUserService.updateName(id,name);
            return  Message.success("昵称修改成功!");
        }catch (Exception e){
            return  Message.success("昵称修改失败!");
        }
    }
    @RequestMapping("updateRemark")
    @ResponseBody
    public Message updateRemark(Long id,String remark) {
        try{
            appUserService.updateRemark(id,remark);
            return  Message.success("签名修改成功!");
        }catch (Exception e){
            return  Message.success("签名修改失败!");
        }
    }
    @RequestMapping("appUserList")
    @ResponseBody
    public PagingBean appUserList(int pageSize, int pageIndex, String searchVal, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        pageQuery.setSearchVal(searchVal);
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(appUserService.count(pageQuery));
        pagingBean.setrows(appUserService.listPage(pageQuery));
        return pagingBean;
    }
    @RequestMapping("findAppUserList")
    @ResponseBody
    public PagingBean findAppUserList(int pageSize, int pageIndex, AppUserVo appUserVo, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        if(appUserVo.getSex().equals("")){
            appUserVo.setSex(null);
        }
        pagingBean.setTotal(appUserService.findAppUserListCount(pageQuery,appUserVo));
        pagingBean.setrows(appUserService.findAppUserList(pageQuery,appUserVo));
        return pagingBean;
    }
    @RequestMapping("/appUserAddSave")
    @ResponseBody
    public Message addSaveappUser(AppUserVo appUser, HttpSession session) throws  Exception {
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            appUser.setStatus(ActiveStatusEnum.ACTIVE.getValue());
            appUserService.save(appUser);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/appUserUpdateName")
    @ResponseBody
    public Message appUserUpdateName(Long id,String name) throws  Exception {
        try{
            appUserService.updateName(id,name);
            return  Message.success("修改昵称成功!");
        }catch (Exception E){
            return Message.fail("修改昵称失败!");
        }

    }
    @RequestMapping("/appUserUpdateRemark")
    @ResponseBody
    public Message appUserUpdateRemark(Long id,String remark) throws  Exception {
        try{
            appUserService.updateRemark(id,remark);
            return  Message.success("修改签名成功!");
        }catch (Exception E){
            return Message.fail("修改签名失败!");
        }

    }
    @ResponseBody
    @RequestMapping("getFenSi/{id}")
    public List<AppUserVo> getFenSi(@PathVariable("id") Long id){
        return appUserService.getFenSi(id);
    }
    @RequestMapping("/findAppUser")
    @ResponseBody
    public AppUserVo findappUser(long id){
        AppUserVo appUser = appUserService.getById(id);
        return appUser;
    }
    @RequestMapping("/getAppUserFaceImg")
    @ResponseBody
    public Message getAppUserFaceImg(Long id, @RequestParam String faceImg, HttpServletRequest request) throws Exception {
        try {
            if(faceImg == null || "".equals(faceImg)){
                return  Message.fail("数据为空");
            }
            String projectPath = request.getSession().getServletContext().getRealPath("/");
            String context = request.getContextPath();
            String imgFilePath ="/upload/images/";
            File uploadPathFile = new File(projectPath+imgFilePath);
            //创建父类文件
            if(!uploadPathFile.exists() && !uploadPathFile.isDirectory()){
                uploadPathFile.mkdirs();
            }
            File imgeFile = new File(projectPath+imgFilePath,new Date().getTime()+".jpg");
            if(!imgeFile.exists()){
                imgeFile.createNewFile();
            }
            //对base64进行解码
            byte[] result = decodeBase64(faceImg);
            //使用Apache提供的工具类将图片写到指定路径下
            FileUtils.writeByteArrayToFile(imgeFile,result);
            String path=imgFilePath+imgeFile.getName();
            appUserService.updateFaceImg(id,path);
            System.out.println(path+"========");
            return Message.success(path);
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("上传失败，系统异常");
        }
    }
    /**
     * Base64解码.
     */
    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input.getBytes());
    }
    private ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

        public String handleResponse(final HttpResponse response) throws IOException {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        }

    };
    @RequestMapping("/getAppUserList")
    @ResponseBody
    public List<Select2Vo> getAppUserList(){
        return appUserService.getAppUser();
    }
    @RequestMapping("/appUserUpdateSave")
    @ResponseBody
    public Message updateappUser(AppUserVo appUser) throws  Exception{
        try{
            appUserService.update(appUser);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyAppUser")
    @ResponseBody
    public Message deleteManyappUser(@Param("manyId") String manyId, Integer status) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                appUserService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteAppUser/{id}")
    @ResponseBody
    public Message deleteappUser(@PathVariable("id") long id) throws  Exception{
        try{
            appUserService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/appUserPage")
    public String table() throws  Exception{
        return "user/appUserList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            appUserService.updateStatus(new StatusQuery(id,status));
            return Message.success("ok");
        }catch (Exception e){
            return  Message.fail("fail");
        }
    }
    @RequestMapping("getCount")
    @ResponseBody
    public Integer[] getCount() throws  Exception{
        try{
            Integer cnt[] = new Integer[12];
           for(int i=0;i<=11;i++){
                cnt[i]=appUserService.cnts((i+1));
           }
           return cnt;
        }catch (Exception e){
            return  null;
        }
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
