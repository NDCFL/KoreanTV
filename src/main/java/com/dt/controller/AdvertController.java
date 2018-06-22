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
import com.dt.service.AdvertService;
import com.dt.vo.AdvertVo;
import com.dt.vo.UserVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("advert")
public class AdvertController {
    @Resource
    private AdvertService advertService;

    @RequestMapping("advertList")
    @ResponseBody
    public PagingBean AdvertList(int pageSize, int pageIndex, String searchVal ,HttpSession session) throws Exception {
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        PagingBean pagingBean = new PagingBean();
        PageQuery pageQuery = new PageQuery();
        pageQuery.setSearchVal(searchVal);
        pagingBean.setPageSize(pageSize);
        pagingBean.setCurrentPage(pageIndex);
        pageQuery.setPageSize(pagingBean.getPageSize());
        pageQuery.setPageNo(pagingBean.getStartIndex());
        pagingBean.setTotal(advertService.count(pageQuery));
        pagingBean.setrows(advertService.listPage(pageQuery));
        return pagingBean;
    }

    @RequestMapping("/advertAddSave")
    @ResponseBody
    public Message addSaveAdvert(AdvertVo Advert, HttpSession session) throws Exception {
        try {
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            Advert.setIsActive(ActiveStatusEnum.ACTIVE.getValue().byteValue());
            advertService.save(Advert);
            return Message.success("新增成功!");
        } catch (Exception E) {
            return Message.fail("新增失败!");
        }

    }

    @RequestMapping("/findAdvert/{id}")
    @ResponseBody
    public AdvertVo findAdvert(@PathVariable("id") long id) {
        AdvertVo Advert = advertService.getById(id);
        return Advert;
    }

    @RequestMapping("/allAdvert")
    @ResponseBody
    public List<AdvertVo> allAdvert() {
        return advertService.listAll();
    }

    @RequestMapping("/advertUpdateSave")
    @ResponseBody
    public Message updateAdvert(AdvertVo advert, HttpServletRequest request) throws Exception {
        try {
            AdvertVo advertVo1 = advertService.getById(advert.getId());
            if(!advert.getUrl().equals(advertVo1.getUrl())){
                String rootPath = request.getSession().getServletContext().getRealPath("/");
                File file1 = new File(rootPath+ advertVo1.getUrl());
                file1.delete();//删除原来的就图片
            }
            advertService.update(advert);
            return Message.success("修改成功!");
        } catch (Exception e) {
            return Message.fail("修改失败!");
        }
    }

    @RequestMapping("/deleteManyAdvert")
    @ResponseBody
    public Message deleteManyAdvert(@Param("manyId") String manyId, Integer status) throws Exception {
        try {
            String str[] = manyId.split(",");
            for (String s : str) {
                advertService.updateStatus(new StatusQuery(Long.parseLong(s), status));
            }
            return Message.success("批量修改状态成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return Message.fail("批量修改状态失败!");
        }
    }

    @RequestMapping("/deleteAdvert/{id}")
    @ResponseBody
    public Message deleteAdvert(@PathVariable("id") long id) throws Exception {
        try {
            advertService.removeById(id);
            return Message.success("删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }

    @RequestMapping("/advertPage")
    public String table() throws Exception {
        return "advert/advertList";
    }

    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id, @PathVariable("status") int status) throws Exception {
        try {
            advertService.updateStatus(new StatusQuery(id, status));
            return Message.success("ok");
        } catch (Exception e) {
            return Message.fail("fail");
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}

