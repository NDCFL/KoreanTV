package com.dt.controller;

import com.dt.common.UserAccountPasswordQuery;
import com.dt.service.UserRoleService;
import com.dt.service.UserService;
import com.dt.vo.UserRoleVo;
import com.dt.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 用于显示主页的控制器<br />
 * 创建于2017-08-28
 *
 * @author 陈飞龙
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    @RequestMapping("index")
    public String index(HttpSession session) {
        try{
            UserVo user = (UserVo) session.getAttribute("userVo");
            Subject subject = SecurityUtils.getSubject();
            UserVo userVo = userService.getByAccountPassword(new UserAccountPasswordQuery(user.getPhone(), user.getPassword()));
            subject.login(new UsernamePasswordToken(user.getPhone(), user.getPassword()));
            UserRoleVo userRoleVo = userRoleService.getRole(user.getId());
            session.setAttribute("userRole",userRoleVo);
            return "index";
        }catch (Exception e){

            return "login/loginPage";
        }
    }

}
