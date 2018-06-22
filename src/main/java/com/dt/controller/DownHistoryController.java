package com.dt.controller;

import com.dt.common.Message;
import com.dt.common.PageQuery;
import com.dt.common.PagingBean;
import com.dt.common.StatusQuery;
import com.dt.enums.ActiveStatusEnum;
import com.dt.service.DownHistoryService;
import com.dt.vo.Select2Vo;
import com.dt.vo.UserVo;
import com.dt.vo.DownHistoryVo;
import com.dt.vo.VedioSectionVo;
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
@RequestMapping("downHistory")
public class DownHistoryController {

    @Resource
    private DownHistoryService downHistoryService;
    @RequestMapping("downHistoryList")
    @ResponseBody
    public PagingBean downHistoryList(int pageSize, int pageIndex, String searchVal, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        pageQuery.setSearchVal(searchVal);
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(downHistoryService.count(pageQuery));
        pagingBean.setrows(downHistoryService.listPage(pageQuery));
        return pagingBean;
    }
    @RequestMapping("findDownHistoryList")
    @ResponseBody
    public PagingBean findDownHistoryList(int pageSize, int pageIndex, DownHistoryVo downHistoryVo, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(downHistoryService.findDownHistoryListCount(pageQuery,downHistoryVo));
        pagingBean.setrows(downHistoryService.findDownHistoryList(pageQuery,downHistoryVo));
        return pagingBean;
    }
    @RequestMapping("/downHistoryAddSave")
    @ResponseBody
    public Message addSavedownHistory(DownHistoryVo downHistory, HttpSession session) throws  Exception {
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            downHistory.setIsActive(ActiveStatusEnum.ACTIVE.getValue().byteValue());
            downHistoryService.save(downHistory);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/findDownHistory/{id}")
    @ResponseBody
    public DownHistoryVo finddownHistory(@PathVariable("id") long id){
        DownHistoryVo downHistory = downHistoryService.getById(id);
        return downHistory;
    }
    @RequestMapping("/getList")
    @ResponseBody
    public List<DownHistoryVo> getModuleList(DownHistoryVo downHistoryVo){
        return downHistoryService.getList(downHistoryVo);
    }
    @RequestMapping("/getSectionList")
    @ResponseBody
    public List<VedioSectionVo> getSectionList(DownHistoryVo downHistoryVo){
        return downHistoryService.getSectionList(downHistoryVo);
    }
    @RequestMapping("/downHistoryUpdateSave")
    @ResponseBody
    public Message updatedownHistory(DownHistoryVo downHistory) throws  Exception{
        try{
            downHistoryService.update(downHistory);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyDownHistory")
    @ResponseBody
    public Message deleteManydownHistory(@Param("manyId") String manyId,Integer status) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                downHistoryService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteDownHistory/{id}")
    @ResponseBody
    public Message deletedownHistory(@PathVariable("id") long id) throws  Exception{
        try{
            downHistoryService.removeById(id);
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
            downHistoryService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/downHistoryPage")
    public String table() throws  Exception{
        return "vedio/downHistoryList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            downHistoryService.updateStatus(new StatusQuery(id,status));
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
