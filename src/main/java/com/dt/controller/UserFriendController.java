package com.dt.controller;

import com.dt.common.Message;
import com.dt.common.PageQuery;
import com.dt.common.PagingBean;
import com.dt.common.StatusQuery;
import com.dt.enums.ActiveStatusEnum;
import com.dt.service.UserFriendService;
import com.dt.vo.UserFriendVo;
import com.dt.vo.UserVo;
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
@RequestMapping("userFriend")
public class UserFriendController {

    @Resource
    private UserFriendService userFriendService;
    @RequestMapping("userFriendList")
    @ResponseBody
    public PagingBean userFriendList(int pageSize, int pageIndex, Long id, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        pageQuery.setId(id);
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(userFriendService.count(pageQuery));
        pagingBean.setrows(userFriendService.listPage(pageQuery));
        return pagingBean;
    }
    @RequestMapping("/userFriendAddSave")
    @ResponseBody
    public Message addSaveuserFriend(UserFriendVo userFriend, HttpSession session) throws  Exception {
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            userFriend.setIsActive(ActiveStatusEnum.ACTIVE.getValue().byteValue());
            userFriendService.save(userFriend);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/getUserFriendList")
    @ResponseBody
    public List<UserFriendVo> getUserFriendList(Long userId) throws  Exception {
        return  userFriendService.getUserFriendList(userId);
    }
    @RequestMapping("/getMyFenList")
    @ResponseBody
    public List<UserFriendVo> getMyFenList(Long userId) throws  Exception {
        return  userFriendService.getMyFenList(userId);
    }
    @RequestMapping("/findUserFriend/{id}")
    @ResponseBody
    public UserFriendVo finduserFriend(@PathVariable("id") long id){
        UserFriendVo userFriend = userFriendService.getById(id);
        return userFriend;
    }
    @RequestMapping("/userFriendUpdateSave")
    @ResponseBody
    public Message updateuserFriend(UserFriendVo userFriend) throws  Exception{
        try{
            userFriendService.update(userFriend);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyUserFriend")
    @ResponseBody
    public Message deleteManyuserFriend(@Param("manyId") String manyId,Integer status) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                userFriendService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteUserFriend/{id}")
    @ResponseBody
    public Message deleteuserFriend(@PathVariable("id") long id) throws  Exception{
        try{
            userFriendService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Message delete(UserFriendVo userFriendVo) throws  Exception{
        try{
            userFriendService.delete(userFriendVo);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/userFriendPage")
    public String table() throws  Exception{
        return "user/userFriendList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            userFriendService.updateStatus(new StatusQuery(id,status));
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
