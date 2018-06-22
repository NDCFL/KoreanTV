package com.dt.controller;

import com.dt.common.Message;
import com.dt.common.PageQuery;
import com.dt.common.PagingBean;
import com.dt.common.StatusQuery;
import com.dt.enums.ActiveStatusEnum;
import com.dt.service.VedioModuleService;
import com.dt.vo.Select2Vo;
import com.dt.vo.VedioModuleVo;
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
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by chenfeilong on 2017/10/21.
 */
@Controller
@RequestMapping("vedioModule")
public class VedioModuleController {

    @Resource
    private VedioModuleService vedioModuleService;
    @RequestMapping("vedioModuleList")
    @ResponseBody
    public PagingBean vedioModuleList(int pageSize, int pageIndex, String searchVal, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        pageQuery.setSearchVal(searchVal);
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(vedioModuleService.count(pageQuery));
        pagingBean.setrows(vedioModuleService.listPage(pageQuery));
        return pagingBean;
    }
    @RequestMapping("/vedioModuleAddSave")
    @ResponseBody
    public Message addSavevedioModule(VedioModuleVo vedioModule, HttpSession session) throws  Exception {
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            vedioModule.setIsActive(ActiveStatusEnum.ACTIVE.getValue());
            vedioModuleService.save(vedioModule);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/findVedioModule/{id}")
    @ResponseBody
    public VedioModuleVo findvedioModule(@PathVariable("id") long id){
        VedioModuleVo vedioModule = vedioModuleService.getById(id);
        return vedioModule;
    }
    @RequestMapping("/getModuleList")
    @ResponseBody
    public List<Select2Vo> getModuleList(){
        return vedioModuleService.getModuleList();
    }
    @RequestMapping("/vedioModuleUpdateSave")
    @ResponseBody
    public Message updatevedioModule(VedioModuleVo vedioModule, HttpServletRequest request) throws  Exception{
        try{
            VedioModuleVo vedioModuleVo = vedioModuleService.getById(vedioModule.getId());
            String rootPath = request.getSession().getServletContext().getRealPath("/");
            if(vedioModuleVo.getImgUrl()==null || vedioModuleVo.getImgUrl().equals("")){
                vedioModuleService.update(vedioModule);
            }else{
                if(vedioModuleVo.getImgUrl().indexOf("http")==-1){
                    if(!vedioModule.getImgUrl().equals(vedioModuleVo.getImgUrl())){
                        File file1 = new File(rootPath+ vedioModuleVo.getImgUrl());
                        file1.delete();//删除原来的就图片
                    }
                }
                vedioModuleService.update(vedioModule);
            }
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyVedioModule")
    @ResponseBody
    public Message deleteManyvedioModule(@Param("manyId") String manyId,Integer status) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                vedioModuleService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteVedioModule/{id}")
    @ResponseBody
    public Message deletevedioModule(@PathVariable("id") long id) throws  Exception{
        try{
            vedioModuleService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/vedioModulePage")
    public String table() throws  Exception{
        return "vedio/vedioModuleList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            vedioModuleService.updateStatus(new StatusQuery(id,status));
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
