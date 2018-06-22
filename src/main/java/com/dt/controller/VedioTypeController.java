package com.dt.controller;

import com.dt.common.Message;
import com.dt.common.PageQuery;
import com.dt.common.PagingBean;
import com.dt.common.StatusQuery;
import com.dt.enums.ActiveStatusEnum;
import com.dt.service.VedioTypeService;
import com.dt.vo.Select2Vo;
import com.dt.vo.UserVo;
import com.dt.vo.VedioTypeVo;
import com.dt.vo.VedioVo;
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
@RequestMapping("vedioType")
public class VedioTypeController {

    @Resource
    private VedioTypeService vedioTypeService;
    @RequestMapping("vedioTypeList")
    @ResponseBody
    public PagingBean vedioTypeList(int pageSize, int pageIndex, String searchVal, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        pageQuery.setSearchVal(searchVal);
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(vedioTypeService.count(pageQuery));
        pagingBean.setrows(vedioTypeService.listPage(pageQuery));
        return pagingBean;
    }
    @RequestMapping("/vedioTypeAddSave")
    @ResponseBody
    public Message addSavevedioType(VedioTypeVo vedioType, HttpSession session) throws  Exception {
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            vedioType.setIsActive(ActiveStatusEnum.ACTIVE.getValue());
            vedioTypeService.save(vedioType);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/findVedioType/{id}")
    @ResponseBody
    public VedioTypeVo findvedioType(@PathVariable("id") long id){
        VedioTypeVo vedioType = vedioTypeService.getById(id);
        return vedioType;
    }
    @RequestMapping("/getVedioTypeList")
    @ResponseBody
    public List<Select2Vo> getVedioTypeList(){
        return vedioTypeService.getTypeList();
    }
    @RequestMapping("/vedioTypeUpdateSave")
    @ResponseBody
    public Message updatevedioType(VedioTypeVo vedioType) throws  Exception{
        try{
            vedioTypeService.update(vedioType);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyVedioType")
    @ResponseBody
    public Message deleteManyvedioType(@Param("manyId") String manyId,Integer status) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                vedioTypeService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteVedioType/{id}")
    @ResponseBody
    public Message deletevedioType(@PathVariable("id") long id) throws  Exception{
        try{
            vedioTypeService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/vedioTypePage")
    public String table() throws  Exception{
        return "vedio/vedioTypeList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            vedioTypeService.updateStatus(new StatusQuery(id,status));
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
