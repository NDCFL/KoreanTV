package com.dt.controller;

import com.dt.common.Message;
import com.dt.common.PageQuery;
import com.dt.common.PagingBean;
import com.dt.common.StatusQuery;
import com.dt.enums.ActiveStatusEnum;
import com.dt.service.UserCollectionService;
import com.dt.vo.Select2Vo;
import com.dt.vo.UserVo;
import com.dt.vo.UserCollectionVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by chenfeilong on 2017/10/21.
 */
@Controller
@RequestMapping("userCollection")
public class UserCollectionController {

    @Resource
    private UserCollectionService userCollectionService;
    @RequestMapping("userCollectionList")
    @ResponseBody
    public PagingBean userCollectionList(int pageSize, int pageIndex, String searchVal, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        pageQuery.setSearchVal(searchVal);
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(userCollectionService.count(pageQuery));
        pagingBean.setrows(userCollectionService.listPage(pageQuery));
        return pagingBean;
    }
    @RequestMapping("/userCollectionAddSave")
    @ResponseBody
    public Message addSaveuserCollection(UserCollectionVo userCollection, HttpSession session) throws  Exception {
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            userCollection.setIsActive(ActiveStatusEnum.ACTIVE.getValue().byteValue());
            userCollectionService.save(userCollection);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/findUserCollection/{id}")
    @ResponseBody
    public UserCollectionVo finduserCollection(@PathVariable("id") long id){
        UserCollectionVo userCollection = userCollectionService.getById(id);
        return userCollection;
    }
    @RequestMapping("/getList")
    @ResponseBody
    public List<UserCollectionVo> getList(UserCollectionVo userCollectionVo){
        return userCollectionService.getList(userCollectionVo);
    }
    @RequestMapping("/userCollectionUpdateSave")
    @ResponseBody
    public Message updateuserCollection(UserCollectionVo userCollection) throws  Exception{
        try{
            userCollectionService.update(userCollection);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyUserCollection")
    @ResponseBody
    public Message deleteManyuserCollection(@Param("manyId") String manyId,Integer status) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                userCollectionService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteUserCollection/{id}")
    @ResponseBody
    public Message deleteuserCollection(@PathVariable("id") long id) throws  Exception{
        try{
            userCollectionService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Message delete(UserCollectionVo userCollectionVo) throws  Exception{
        try{
            userCollectionService.deleteById(userCollectionVo);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/userCollectionPage")
    public String table() throws  Exception{
        return "vedio/userCollectionList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            userCollectionService.updateStatus(new StatusQuery(id,status));
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
