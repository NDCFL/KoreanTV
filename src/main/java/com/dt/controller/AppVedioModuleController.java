package com.dt.controller;

import com.dt.common.Message;
import com.dt.common.PagingBean;
import com.dt.service.VedioModuleService;
import com.dt.vo.VedioModuleVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("appVedioModule")
public class AppVedioModuleController {
    @Resource
    private VedioModuleService vedioModuleService;
    @RequestMapping("getVedios")
    @ResponseBody
    private List<VedioModuleVo> getVedios(){
        return  vedioModuleService.getList();
    }
}
