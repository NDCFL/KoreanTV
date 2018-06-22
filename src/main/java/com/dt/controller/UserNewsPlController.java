package com.dt.controller;

import com.dt.common.Message;
import com.dt.common.PageQuery;
import com.dt.common.PagingBean;
import com.dt.common.StatusQuery;
import com.dt.enums.ActiveStatusEnum;
import com.dt.service.UserNewsPlService;
import com.dt.vo.UserNewsPlVo;
import com.dt.vo.UserVo;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenfeilong on 2017/10/21.
 */
@Controller
@RequestMapping("userNewsPl")
public class UserNewsPlController {

    @Resource
    private UserNewsPlService userNewsPlService;
    @RequestMapping("userNewsPlList")
    @ResponseBody
    public PagingBean userNewsPlList(int pageSize, int pageIndex, Long id, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        pageQuery.setId(id);
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(userNewsPlService.count(pageQuery));
        pagingBean.setrows(userNewsPlService.listPage(pageQuery));
        return pagingBean;
    }
    @RequestMapping("findUserNewsPlList")
    @ResponseBody
    public PagingBean findUserNewsPlList(int pageSize, int pageIndex, UserNewsPlVo userNewsPlVo, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pageQuery.setId(userNewsPlVo.getId());
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(userNewsPlService.findUserNewsPlListCount(pageQuery,userNewsPlVo));
        pagingBean.setrows(userNewsPlService.findUserNewsPlList(pageQuery,userNewsPlVo));
        return pagingBean;
    }
    @RequestMapping("/userNewsPlAddSave")
    @ResponseBody
    public Message addSaveuserNewsPl(UserNewsPlVo userNewsPl, HttpSession session, HttpServletRequest request) throws  Exception {
        try{
            userNewsPl.setIsActive(ActiveStatusEnum.ACTIVE.getValue().byteValue());
            userNewsPlService.save(userNewsPl);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }
    }
    @RequestMapping("/findUserNewsPl/{id}")
    @ResponseBody
    public UserNewsPlVo finduserNewsPl(@PathVariable("id") long id){
        UserNewsPlVo userNewsPl = userNewsPlService.getById(id);
        return userNewsPl;
    }
    @RequestMapping("/userNewsPlUpdateSave")
    @ResponseBody
    public Message updateuserNewsPl(UserNewsPlVo userNewsPl) throws  Exception{
        try{
            userNewsPlService.update(userNewsPl);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyUserNewsPl")
    @ResponseBody
    public Message deleteManyuserNewsPl(@Param("manyId") String manyId,Integer status) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                userNewsPlService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteUserNewsPl/{id}")
    @ResponseBody
    public Message deleteuserNewsPl(@PathVariable("id") long id) throws  Exception{
        try{
            userNewsPlService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/userNewsPlPage")
    public String table() throws  Exception{
        return "user/userNewsPlList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            userNewsPlService.updateStatus(new StatusQuery(id,status));
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
}
