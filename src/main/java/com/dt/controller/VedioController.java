package com.dt.controller;

import com.dt.common.Message;
import com.dt.common.PageQuery;
import com.dt.common.PagingBean;
import com.dt.common.StatusQuery;
import com.dt.enums.ActiveStatusEnum;
import com.dt.service.AdvertService;
import com.dt.service.VedioSectionService;
import com.dt.service.VedioService;
import com.dt.vo.Select2Vo;
import com.dt.vo.UserVo;
import com.dt.vo.VedioVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenfeilong on 2017/10/21.
 */
@Controller
@RequestMapping("vedio")
public class VedioController {

    @Resource
    private VedioService vedioService;
    @Resource
    private VedioSectionService vedioSectionService;
    @Resource
    private AdvertService advertService;
    @RequestMapping("vedioList")
    @ResponseBody
    public PagingBean vedioList(int pageSize, int pageIndex, String searchVal, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        pageQuery.setSearchVal(searchVal);
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(vedioService.count(pageQuery));
        pagingBean.setrows(vedioService.listPage(pageQuery));
        return pagingBean;
    }
    @RequestMapping("findVedioList")
    @ResponseBody
    public PagingBean findVedioList(int pageSize, int pageIndex, VedioVo vedioVo, HttpSession session) throws  Exception{
        System.out.println(vedioVo.getVedioModuleId()+"=======>>>>>>>>>>");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(vedioService.findVedioCount(vedioVo,pageQuery));
        pagingBean.setrows(vedioService.findVedioList(vedioVo,pageQuery));
        return pagingBean;
    }
    @RequestMapping("/vedioAddSave")
    @ResponseBody
    public Message addSavevedio(VedioVo vedio, HttpSession session) throws  Exception {
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            vedio.setIsActive(ActiveStatusEnum.ACTIVE.getValue());
            vedio.setVedioTypeId(StringUtils.join(vedio.getTypeList(), ","));
            vedioService.save(vedio);
            return  Message.success("新增成功!");
        }catch (Exception E){
            E.printStackTrace();
            return Message.fail("新增失败!");
        }
    }
    @RequestMapping("/findVedio/{id}")
    @ResponseBody
    public VedioVo findvedio(@PathVariable("id") long id){
        VedioVo vedio = vedioService.getById(id);
        return vedio;
    }
    @RequestMapping("/getVedio/{id}")
    @ResponseBody
    public Map<Integer,Object> getVedio(@PathVariable("id") long id){
        try{
            Map<Integer,Object> map = new HashMap<>();
            map.put(0,vedioService.getById(id));
            map.put(1,vedioSectionService.getList(id));
            map.put(2,advertService.listAll());
            return map;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/getVedioList")
    @ResponseBody
    public List<Select2Vo> getVedioList(){
        return vedioService.getVedioList();
    }
    @RequestMapping("/vedioUpdateSave")
    @ResponseBody
    public Message updatevedio(VedioVo vedio) throws  Exception{
        try{
            System.out.println(StringUtils.join(vedio.getTypeList(), ","));
            vedio.setVedioTypeId(StringUtils.join(vedio.getTypeList(), ","));
            vedioService.update(vedio);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyVedio")
    @ResponseBody
    public Message deleteManyvedio(@Param("manyId") String manyId,Integer status) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                vedioService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteVedio/{id}")
    @ResponseBody
    public Message deletevedio(@PathVariable("id") long id) throws  Exception{
        try{
            vedioService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/vedioPage")
    public String table() throws  Exception{
        return "vedio/vedioList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            vedioService.updateStatus(new StatusQuery(id,status));
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
