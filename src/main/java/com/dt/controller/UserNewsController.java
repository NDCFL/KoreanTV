package com.dt.controller;

import com.dt.common.Message;
import com.dt.common.PageQuery;
import com.dt.common.PagingBean;
import com.dt.common.StatusQuery;
import com.dt.enums.ActiveStatusEnum;
import com.dt.service.UserNewsService;
import com.dt.vo.FileVo;
import com.dt.vo.UserNewsVo;
import com.dt.vo.UserVo;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenfeilong on 2017/10/21.
 */
@Controller
@RequestMapping("userNews")
public class UserNewsController {

    @Resource
    private UserNewsService userNewsService;
    @RequestMapping("userNewsList")
    @ResponseBody
    public PagingBean userNewsList(int pageSize, int pageIndex, String searchVal, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        pageQuery.setSearchVal(searchVal);
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(userNewsService.count(pageQuery));
        pagingBean.setrows(userNewsService.listPage(pageQuery));
        return pagingBean;
    }
    @RequestMapping("findUserNewsList")
    @ResponseBody
    public PagingBean findUserNewsList(int pageSize, int pageIndex, UserNewsVo userNewsVo, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(userNewsService.findUserNewsListCount(pageQuery,userNewsVo));
        pagingBean.setrows(userNewsService.findUserNewsList(pageQuery,userNewsVo));
        return pagingBean;
    }
    @RequestMapping("gzUserNewsList")
    @ResponseBody
    public PagingBean gzUserNewsList(int pageSize, int pageIndex, UserNewsVo userNewsVo, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(userNewsService.yourUserNewsCount(pageQuery,userNewsVo));
        pagingBean.setrows(userNewsService.yourUserNewsList(pageQuery,userNewsVo));
        return pagingBean;
    }
    @RequestMapping("/userNewsAddSave")
    @ResponseBody
    public Message addSaveuserNews(UserNewsVo userNews, HttpSession session, HttpServletRequest request) throws  Exception {
        String img = "";
        try{
            if(userNews.getImgUrl()==null || "".equals(userNews.getImgUrl())){
                userNews.setIsActive(ActiveStatusEnum.ACTIVE.getValue().byteValue());
                userNews.setImgUrl(img);
                userNewsService.save(userNews);
                return  Message.success("新增成功!");
            }else{
                String imgs[] = userNews.getImgUrl().split("\\s");
                if(imgs.length>=9){
                    return  Message.fail("上传失败，最多只能上传9张");
                }else{
                    for(int i=0;i<imgs.length;i++){
                        try {
                            if(imgs[i] == null || "".equals(imgs[i])){
                                continue;
                            }
                            String projectPath = request.getSession().getServletContext().getRealPath("/");
                            String context = request.getContextPath();
                            String imgFilePath ="/upload/news/";
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
                            byte[] result = decodeBase64(imgs[i]);
                            //使用Apache提供的工具类将图片写到指定路径下
                            FileUtils.writeByteArrayToFile(imgeFile,result);
                            String path=imgFilePath+imgeFile.getName();
                            System.out.println(path+"========");
                            if(i==imgs.length-1){
                                img = img + path;
                            }else{
                                img = img + path+";";
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            return  Message.fail("上传失败，系统异常");
                        }
                    }
                }
                userNews.setIsActive(ActiveStatusEnum.ACTIVE.getValue().byteValue());
                userNews.setImgUrl(img);
                userNewsService.save(userNews);
                return  Message.success("新增成功!");
            }
        }catch (Exception E){
            E.printStackTrace();
            return Message.fail("新增失败!");
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
    @RequestMapping("/findUserNews/{id}")
    @ResponseBody
    public UserNewsVo finduserNews(@PathVariable("id") long id){
        UserNewsVo userNews = userNewsService.getById(id);
        return userNews;
    }
    @RequestMapping("/userNewsUpdateSave")
    @ResponseBody
    public Message updateuserNews(UserNewsVo userNews) throws  Exception{
        try{
            userNewsService.update(userNews);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/dz/{id}")
    @ResponseBody
    public ModelAndView dz(@PathVariable("id") Long id) throws  Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/userNewsGoodList");
        modelAndView.addObject("id",id);
        return  modelAndView;
    }
    @RequestMapping("/pl/{id}")
    @ResponseBody
    public ModelAndView pl(@PathVariable("id") Long id) throws  Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/userNewsPlList");
        modelAndView.addObject("id",id);
        return  modelAndView;
    }
    @RequestMapping("/deleteManyUserNews")
    @ResponseBody
    public Message deleteManyuserNews(@Param("manyId") String manyId,Integer status) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                userNewsService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteUserNews/{id}")
    @ResponseBody
    public Message deleteuserNews(@PathVariable("id") long id) throws  Exception{
        try{
            userNewsService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Message delete(long id) throws  Exception{
        try{
            userNewsService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/userNewsPage")
    public String table() throws  Exception{
        return "user/userNewsList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            userNewsService.updateStatus(new StatusQuery(id,status));
            return Message.success("ok");
        }catch (Exception e){
            return  Message.fail("fail");
        }
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    //重命名文件名称
    private synchronized String getFileName(String filename) {
        int position = filename.lastIndexOf(".");
        String ext = filename.substring(position);
        return System.nanoTime() + ext;
    }
}
