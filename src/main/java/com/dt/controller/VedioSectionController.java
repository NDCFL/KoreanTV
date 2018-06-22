package com.dt.controller;

import com.dt.common.Message;
import com.dt.common.PageQuery;
import com.dt.common.PagingBean;
import com.dt.common.StatusQuery;
import com.dt.enums.ActiveStatusEnum;
import com.dt.service.VedioSectionService;
import com.dt.vo.Select2Vo;
import com.dt.vo.UserVo;
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
@RequestMapping("vedioSection")
public class VedioSectionController {

    @Resource
    private VedioSectionService vedioSectionService;
    @RequestMapping("vedioSectionList")
    @ResponseBody
    public PagingBean vedioSectionList(int pageSize, int pageIndex, Long vedioId, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        pageQuery.setId(vedioId);
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(vedioSectionService.count(pageQuery));
        pagingBean.setrows(vedioSectionService.listPage(pageQuery));
        return pagingBean;
    }
    @RequestMapping("findVedioSectionLists")
    @ResponseBody
    public PagingBean findVedioSectionLists(int pageSize, int pageIndex, VedioSectionVo vedioSectionVo, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(vedioSectionService.findVedioSectionCount(pageQuery,vedioSectionVo));
        pagingBean.setrows(vedioSectionService.findVedioSectionList(pageQuery,vedioSectionVo));
        return pagingBean;
    }
    @RequestMapping("/vedioSectionAddSave")
    @ResponseBody
    public Message addSavevedioSection(VedioSectionVo vedioSection, HttpSession session) throws  Exception {
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            vedioSection.setIsActive(ActiveStatusEnum.ACTIVE.getValue().byteValue());
            vedioSectionService.save(vedioSection);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/updateVedio")
    @ResponseBody
    public Message updateVedio(VedioSectionVo vedioSection) throws  Exception {
        try{
            vedioSectionService.updateVedio(vedioSection);
            return  Message.success("视频修改成功!");
        }catch (Exception E){
            return Message.fail("视频修改失败!");
        }

    }
    @RequestMapping("/findVedioSection/{id}")
    @ResponseBody
    public VedioSectionVo findvedioSection(@PathVariable("id") long id){
        VedioSectionVo vedioSection = vedioSectionService.getById(id);
        return vedioSection;
    }
    @RequestMapping("/getSectionList/{id}")
    @ResponseBody
    public List<Select2Vo> getSectionList(@PathVariable("id") long id){
        return vedioSectionService.getSectionList(id);
    }
    @RequestMapping("/findVedioSectionList")
    @ResponseBody
    public List<VedioSectionVo> findVedioSectionList(long id){
        return vedioSectionService.getList(id);
    }
    @RequestMapping("/vedioSectionUpdateSave")
    @ResponseBody
    public Message updatevedioSection(VedioSectionVo vedioSection) throws  Exception{
        try{
            vedioSectionService.update(vedioSection);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyVedioSection")
    @ResponseBody
    public Message deleteManyvedioSection(@Param("manyId") String manyId,Integer status) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                vedioSectionService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteVedioSection/{id}")
    @ResponseBody
    public Message deletevedioSection(@PathVariable("id") long id) throws  Exception{
        try{
            vedioSectionService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/vedioSectionPage")
    public String table() throws  Exception{
        return "vedio/vedioSectionList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            vedioSectionService.updateStatus(new StatusQuery(id,status));
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
