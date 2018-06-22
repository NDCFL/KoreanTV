package com.dt.controller;

import com.dt.common.Message;
import com.dt.common.PageQuery;
import com.dt.common.PagingBean;
import com.dt.common.StatusQuery;
import com.dt.enums.ActiveStatusEnum;
import com.dt.service.VedioLookHistoryService;
import com.dt.vo.UserVo;
import com.dt.vo.VedioLookHistoryVo;
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
@RequestMapping("vedioLookHistory")
public class VedioLookHistoryController {

    @Resource
    private VedioLookHistoryService vedioLookHistoryService;
    @RequestMapping("vedioLookHistoryList")
    @ResponseBody
    public PagingBean vedioLookHistoryList(int pageSize, int pageIndex, String searchVal, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        pageQuery.setSearchVal(searchVal);
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(vedioLookHistoryService.count(pageQuery));
        pagingBean.setrows(vedioLookHistoryService.listPage(pageQuery));
        return pagingBean;
    }
    @RequestMapping("findVedioLookHistoryByList")
    @ResponseBody
    public PagingBean findVedioLookHistoryByList(int pageSize, int pageIndex, VedioLookHistoryVo vedioLookHistoryVo, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(vedioLookHistoryService.findVedioLookHistoryCounts(pageQuery,vedioLookHistoryVo));
        pagingBean.setrows(vedioLookHistoryService.findVedioLookHistoryList(pageQuery,vedioLookHistoryVo));
        return pagingBean;
    }
    @RequestMapping("/vedioLookHistoryAddSave")
    @ResponseBody
    public Message addSavevedioLookHistory(VedioLookHistoryVo vedioLookHistory, HttpSession session) throws  Exception {
        try{
            int cnt = vedioLookHistoryService.findVedioLookHistoryCount(vedioLookHistory);
            if(cnt==0){
                vedioLookHistory.setIsActive(ActiveStatusEnum.ACTIVE.getValue());
                vedioLookHistoryService.save(vedioLookHistory);
            }else{
                vedioLookHistoryService.update(vedioLookHistory);
            }
            return  Message.success(vedioLookHistory.getId()+"");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }
    }
    @RequestMapping("/findVedioLookHistoryList")
    @ResponseBody
    public List<VedioLookHistoryVo> findVedioLookHistoryList(VedioLookHistoryVo vedioLookHistory) throws  Exception {
        return  vedioLookHistoryService.findVedioLookHistory(vedioLookHistory);
    }
    @RequestMapping("/sevenVedioLookHistoryList")
    @ResponseBody
    public List<VedioLookHistoryVo> sevenVedioLookHistoryList(Long userId) throws  Exception {
        return  vedioLookHistoryService.sevenVedioLookHistory(userId);
    }
    @RequestMapping("/findVedioLookHistory/{id}")
    @ResponseBody
    public VedioLookHistoryVo findvedioLookHistory(@PathVariable("id") long id){
        VedioLookHistoryVo vedioLookHistory = vedioLookHistoryService.getById(id);
        return vedioLookHistory;
    }
    @RequestMapping("/vedioLookHistoryUpdateSave")
    @ResponseBody
    public Message updatevedioLookHistory(VedioLookHistoryVo vedioLookHistory) throws  Exception{
        try{
            vedioLookHistoryService.update(vedioLookHistory);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyVedioLookHistory")
    @ResponseBody
    public Message deleteManyvedioLookHistory(@Param("manyId") String manyId,Integer status) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                vedioLookHistoryService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteVedioLookHistory/{id}")
    @ResponseBody
    public Message deletevedioLookHistory(@PathVariable("id") long id) throws  Exception{
        try{
            vedioLookHistoryService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Message delete(VedioLookHistoryVo vedioLookHistoryVo) throws  Exception{
        try{
            vedioLookHistoryService.delete(vedioLookHistoryVo);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/vedioLookHistoryPage")
    public String table() throws  Exception{
        return "vedio/vedioLookHistoryList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            vedioLookHistoryService.updateStatus(new StatusQuery(id,status));
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
