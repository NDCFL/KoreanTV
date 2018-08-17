package com.dt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/soa")
@Api(value="test1",description="测试接口描述")
public class TestController1 {
    /*
     * @ApiOperation(value = "接口说明", httpMethod ="接口请求方式", response ="接口返回参数类型", notes ="接口发布说明"
     *
     * @ApiParam(required = "是否必须参数", name ="参数名称", value ="参数具体描述"
     */
    @RequestMapping("test")
    @ApiOperation(value="接口说明(测试)",httpMethod="GET",notes="在没有会话、没有签名的情况下，进入方法体")
    public void test(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().write("ignoreAll");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}