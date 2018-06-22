package com.dt.controller;

import com.dt.common.Message;
import com.dt.common.PageQuery;
import com.dt.common.PagingBean;
import com.dt.common.StatusQuery;
import com.dt.enums.ActiveStatusEnum;
import com.dt.service.BarrageService;
import com.dt.vo.UserVo;
import com.dt.vo.BarrageVo;
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
@RequestMapping("barrage")
public class BarrageController {

    @Resource
    private BarrageService barrageService;
    @RequestMapping("barrageList")
    @ResponseBody
    public PagingBean barrageList(int pageSize, int pageIndex, String searchVal, HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PageQuery pageQuery = new PageQuery();
        pageQuery.setSearchVal(searchVal);
        PagingBean pagingBean = new PagingBean();
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(barrageService.count(pageQuery));
        pagingBean.setrows(barrageService.listPage(pageQuery));
        return pagingBean;
    }
    @RequestMapping("/barrageAddSave")
    @ResponseBody
    public Message addSavebarrage(BarrageVo barrage, HttpSession session) throws  Exception {
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            barrage.setIsActive(ActiveStatusEnum.ACTIVE.getValue().byteValue());
            barrageService.save(barrage);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/getBarrageList")
    @ResponseBody
    public List<BarrageVo> getBarrageList(Long sectionId) throws  Exception {
        return  barrageService.barrageList(sectionId);
    }
    @RequestMapping("/findBarrage/{id}")
    @ResponseBody
    public BarrageVo findbarrage(@PathVariable("id") long id){
        BarrageVo barrage = barrageService.getById(id);
        return barrage;
    }
    @RequestMapping("/barrageUpdateSave")
    @ResponseBody
    public Message updatebarrage(BarrageVo barrage) throws  Exception{
        try{
            barrageService.update(barrage);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyBarrage")
    @ResponseBody
    public Message deleteManybarrage(@Param("manyId") String manyId,Integer status) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                barrageService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteBarrage/{id}")
    @ResponseBody
    public Message deletebarrage(@PathVariable("id") long id) throws  Exception{
        try{
            barrageService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/barragePage")
    public String table() throws  Exception{
        return "vedio/barrageList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            barrageService.updateStatus(new StatusQuery(id,status));
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
