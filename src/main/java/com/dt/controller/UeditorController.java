package com.dt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;

/**
 * Created by chenfeilong on 2017/10/18.
 */
@Controller
@RequestMapping("ueditor")
public class UeditorController {
    @RequestMapping("core")
    public String core(){
        return "ueditor/jsp/controller";
    }


}
