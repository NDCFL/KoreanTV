package com.dt.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dt.common.Message;
import com.dt.common.PagingBean;
import com.dt.enums.ActiveStatusEnum;
import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.service.RoleService;
import com.dt.vo.RoleVo;
import com.dt.vo.Select2Vo;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by chenfeilong on 2017/10/29.
 */
@RequestMapping("role")
@Controller
public class RoleController {

    @Resource
    private RoleService roleService;
    @RequestMapping("roleList")
    @ResponseBody
    public PagingBean roleList(int pageSize, int pageIndex,String searchVal) throws  Exception{
        PagingBean pagingBean = new PagingBean();
        pagingBean.setTotal(roleService.count(new PageQuery(searchVal)));
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pagingBean.setrows(roleService.listPage(new PageQuery(pagingBean.getStartIndex(),pagingBean.getPageSize())));
        return pagingBean;
    }
    @RequestMapping("/roleAddSave")
    @ResponseBody
    public Message addSaveRole(RoleVo role) throws  Exception {
        try{
            role.setIsActive(ActiveStatusEnum.ACTIVE.getValue().byteValue());
            roleService.save(role);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/findRole/{id}")
    @ResponseBody
    public RoleVo findrole(@PathVariable("id") long id){
        RoleVo role = roleService.getById(id);
        return role;
    }
    @RequestMapping("/roleUpdateSave")
    @ResponseBody
    public Message updaterole(RoleVo role) throws  Exception{
        try{
            roleService.update(role);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyRole")
    @ResponseBody
    public Message deleteManyrole(@Param("manyId") String manyId,Integer status) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                roleService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteRole/{id}")
    @ResponseBody
    public Message deleteRole(@PathVariable("id") long id) throws  Exception{
        try{
            roleService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/rolePage")
    public String table() throws  Exception{
        return "role/roleList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            roleService.updateStatus(new StatusQuery(id,status));
            return Message.success("ok");
        }catch (Exception e){
            return  Message.fail("fail");
        }
    }
    @RequestMapping("getRoleIdAndTitle")
    @ResponseBody
    public List<Select2Vo> getRoleIdAndTitle(){
        List<Select2Vo> select2Vos = roleService.getRoleIdAndTitle();
        return select2Vos;
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
