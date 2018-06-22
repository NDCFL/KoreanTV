package com.dt.controller;

import com.dt.common.Message;
import com.dt.common.PageQuery;
import com.dt.common.PagingBean;
import com.dt.common.StatusQuery;
import com.dt.enums.ActiveStatusEnum;
import com.dt.service.VedioGoodService;
import com.dt.vo.VedioGoodVo;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenfeilong on 2017/10/21.
 */
@Controller
@RequestMapping("vedioGood")
public class VedioGoodController {

    @Resource
    private VedioGoodService vedioGoodService;
    @RequestMapping("vedioGoodList")
    @ResponseBody
    public PagingBean vedioGoodList(int pageSize, int pageIndex, Long id, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        PagingBean pagingBean = new PagingBean();
        pageQuery.setId(id);
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(vedioGoodService.count(pageQuery));
        pagingBean.setrows(vedioGoodService.listPage(pageQuery));
        return pagingBean;
    }
    @RequestMapping("findVedioGoodList")
    @ResponseBody
    public PagingBean findVedioGoodList(int pageSize, int pageIndex, VedioGoodVo vedioGoodVo, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setId(vedioGoodVo.getId());
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(vedioGoodService.findVedioGoodListCount(pageQuery,vedioGoodVo));
        pagingBean.setrows(vedioGoodService.findVedioGoodList(pageQuery,vedioGoodVo));
        return pagingBean;
    }
    @RequestMapping("/vedioGoodAddSave")
    @ResponseBody
    public Message addSavevedioGood(VedioGoodVo vedioGood, HttpSession session, HttpServletRequest request) throws  Exception {
        try{
            vedioGood.setIsActive(ActiveStatusEnum.ACTIVE.getValue().byteValue());
            vedioGoodService.save(vedioGood);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }
    }

    @RequestMapping("/findVedioGood/{id}")
    @ResponseBody
    public VedioGoodVo findvedioGood(@PathVariable("id") long id){
        VedioGoodVo vedioGood = vedioGoodService.getById(id);
        return vedioGood;
    }
    @RequestMapping("/vedioGoodUpdateSave")
    @ResponseBody
    public Message updatevedioGood(VedioGoodVo vedioGood) throws  Exception{
        try{
            vedioGoodService.update(vedioGood);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyVedioGood")
    @ResponseBody
    public Message deleteManyvedioGood(@Param("manyId") String manyId,Integer status) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                vedioGoodService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteVedioGood/{id}")
    @ResponseBody
    public Message deletevedioGood(@PathVariable("id") long id) throws  Exception{
        try{
            vedioGoodService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Message deletevedioGood(VedioGoodVo vedioGoodVo) throws  Exception{
        try{
            vedioGoodService.delete(vedioGoodVo);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/vedioGoodPage")
    public String table() throws  Exception{
        return "user/vedioGoodList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            vedioGoodService.updateStatus(new StatusQuery(id,status));
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
