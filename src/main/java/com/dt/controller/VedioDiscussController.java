package com.dt.controller;

import com.dt.common.Message;
import com.dt.common.PageQuery;
import com.dt.common.PagingBean;
import com.dt.common.StatusQuery;
import com.dt.enums.ActiveStatusEnum;
import com.dt.service.VedioDiscussService;
import com.dt.vo.Select2Vo;
import com.dt.vo.UserVo;
import com.dt.vo.VedioDiscussVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("vedioDiscuss")
public class VedioDiscussController {

    @Resource
    private VedioDiscussService vedioDiscussService;
    @RequestMapping("vedioDiscussList")
    @ResponseBody
    public PagingBean vedioDiscussList(int pageSize, int pageIndex, String searchVal, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        pageQuery.setSearchVal(searchVal);
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(vedioDiscussService.count(pageQuery));
        pagingBean.setrows(vedioDiscussService.listPage(pageQuery));
        return pagingBean;
    }
    @RequestMapping("findVedioDiscussList")
    @ResponseBody
    public PagingBean findVedioDiscussList(int pageSize, int pageIndex,VedioDiscussVo vedioDiscussVo,HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(vedioDiscussService.findVedioDiscussCount(pageQuery,vedioDiscussVo));
        pagingBean.setrows(vedioDiscussService.findVedioDiscussList(pageQuery,vedioDiscussVo));
        return pagingBean;
    }
    @RequestMapping("getVedioDiscussList")
    @ResponseBody
    public PagingBean getVedioDiscussList(int pageSize, int pageIndex, Long vedioId, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        pageQuery.setId(vedioId);
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(vedioDiscussService.count(pageQuery));
        pagingBean.setrows(vedioDiscussService.listPage(pageQuery));
        return pagingBean;
    }
    @RequestMapping("/vedioDiscussAddSave")
    @ResponseBody
    public Message addSavevedioDiscuss(VedioDiscussVo vedioDiscuss, HttpSession session) throws  Exception {
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            vedioDiscuss.setIsActive(ActiveStatusEnum.ACTIVE.getValue());
            vedioDiscussService.save(vedioDiscuss);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/findVedioDiscuss/{id}")
    @ResponseBody
    public VedioDiscussVo findvedioDiscuss(@PathVariable("id") long id){
        VedioDiscussVo vedioDiscuss = vedioDiscussService.getById(id);
        return vedioDiscuss;
    }
    @RequestMapping("/chars/{id}")
    @ResponseBody
    public ModelAndView chars(@PathVariable("id") long id){
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.addObject("id",id);
       modelAndView.setViewName("vedio/vedioCharList");
        return modelAndView;
    }
    @RequestMapping("/findVedioList")
    @ResponseBody
    public List<VedioDiscussVo> findVedioList(long vedioId){
        return vedioDiscussService.findVedioList(vedioId);
    }
    @RequestMapping("/findVedioLists")
    @ResponseBody
    public List<VedioDiscussVo> findVedioLists(long vedioSectionId){
        return vedioDiscussService.findVedioLists(vedioSectionId);
    }
    @RequestMapping("/vedioDiscussUpdateSave")
    @ResponseBody
    public Message updatevedioDiscuss(VedioDiscussVo vedioDiscuss) throws  Exception{
        try{
            vedioDiscussService.update(vedioDiscuss);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyVedioDiscuss")
    @ResponseBody
    public Message deleteManyvedioDiscuss(@Param("manyId") String manyId,Integer status) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                vedioDiscussService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteVedioDiscuss/{id}")
    @ResponseBody
    public Message deletevedioDiscuss(@PathVariable("id") long id) throws  Exception{
        try{
            vedioDiscussService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/vedioDiscussPage")
    public String table() throws  Exception{
        return "vedio/vedioDiscussList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            vedioDiscussService.updateStatus(new StatusQuery(id,status));
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
