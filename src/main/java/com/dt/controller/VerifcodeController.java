package com.dt.controller;


import com.dt.common.Message;
import com.dt.common.StatusQuery;
import com.dt.service.AppUserService;
import com.dt.service.UserService;
import com.dt.service.VerifcodeService;
import com.dt.vo.AppUserVo;
import com.dt.vo.UserVo;
import com.dt.vo.Verifcode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;

@Controller
@RequestMapping("code")
public class VerifcodeController {

    @Resource
    private VerifcodeService verifcodeService;
    @Resource
    private UserService userService;
    @Resource
    private AppUserService appUserService;
    /**
     * 发送手机短信验证码
     * @param verifcode
     * @return
     */
    @RequestMapping("sendCode")
    @ResponseBody
    public Message addCode(Verifcode verifcode){
        try{
            //查询改手机号在有效期5分钟之内是否还有未使用的短信，如果有则返回code如果没有则返回-1
            Verifcode verifcode1  = verifcodeService.queryByCode(verifcode.getPhone());
            if(verifcode1==null){
                //生成6位数的验证码
                int code = new Random().nextInt(899999)+100000;
                //执行注册发送的验证码
                if(verifcode.getCodeType().equals("register")){
                    //保存到数据库中并且发送到手机上
                    verifcode.setCode(code+"");
                    verifcode.setMsg("【西瓜韩剧】注册验证码，你的验证码是"+code+ "。如非本人操作，请忽略本短信。该验证码将在30分钟后失效");
                    System.out.println(code+"====注册发送的验证码==>>>");
                }else if(verifcode.getCodeType().equals("findPwd")){
                    UserVo userVo = userService.findByPhone(verifcode.getPhone());
                    if(userVo==null){
                        return  Message.fail("账号不存在!");
                    }else{
                        //保存到数据库中并且发送到手机上
                        verifcode.setCode(code+"");
                        verifcode.setMsg("【西瓜韩剧】找回密码，你的验证码是"+code+ "。如非本人操作，请忽略本短信。该验证码将在30分钟后失效");
                        System.out.println(code+"====找回密码注册发送的验证码==>>>");
                    }
                }
                verifcodeService.save(verifcode);
            }else{
                //发送数据库原来就有的验证码dbcode
                //模拟接收验证码
                System.out.println(verifcode1.getCode()+"==后台==来自于数据库的验证码====>>>");
                verifcode.setMsg(verifcode1.getMsg());
            }
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("Account", "18370940755"));
            formparams.add(new BasicNameValuePair("Pwd", "79ede916aaad87f0d057b35ec"));
            formparams.add(new BasicNameValuePair("Content", verifcode.getMsg()));
            formparams.add(new BasicNameValuePair("Mobile", verifcode.getPhone()));
            formparams.add(new BasicNameValuePair("SignId", "41686"));
            send(formparams);
            return  Message.success("验证码发送成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.success("验证码发送失败!");
        }
    }
    @RequestMapping("sendCodeApp")
    @ResponseBody
    public Message sendCodeApp(Verifcode verifcode){
        try{
            //查询改手机号在有效期5分钟之内是否还有未使用的短信，如果有则返回code如果没有则返回-1
            Verifcode verifcode1 = verifcodeService.queryByCode(verifcode.getPhone());
            if(verifcode1==null){
                //生成6位数的验证码
                int code = new Random().nextInt(899999)+100000;
                //执行注册发送的验证码
                if(verifcode.getCodeType().equals("register")){
                    //保存到数据库中并且发送到手机上
                    verifcode.setCode(code+"");
                    verifcode.setMsg("【西瓜韩剧】注册验证码，你的验证码是"+code+ "。如非本人操作，请忽略本短信。该验证码将在30分钟后失效");
                    System.out.println(code+"====注册发送的验证码==>>>");
                }else if(verifcode.getCodeType().equals("findPwd")){
                    AppUserVo appUserVo = appUserService.findByPhone(verifcode.getPhone());
                    if(appUserVo==null){
                        return  Message.fail("账号不存在!");
                    }else{
                        //保存到数据库中并且发送到手机上
                        verifcode.setCode(code+"");
                        verifcode.setMsg("【西瓜韩剧】找回密码，你的验证码是"+code+ "。如非本人操作，请忽略本短信。该验证码将在30分钟后失效");
                        System.out.println(code+"====找回密码注册发送的验证码==>>>");
                    }
                }
                verifcodeService.save(verifcode);
            }else{
                //发送数据库原来就有的验证码dbcode
                //模拟接收验证码
                System.out.println(verifcode1.getPhone()+"==APP==来自于数据库的验证码====>>>");
                verifcode.setMsg(verifcode1.getMsg());
            }
            System.out.println("=========开始发送验证码");
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("Account", "18370940755"));
            formparams.add(new BasicNameValuePair("Pwd", "79ede916aaad87f0d057b35ec"));
            formparams.add(new BasicNameValuePair("Content", verifcode.getMsg()));
            formparams.add(new BasicNameValuePair("Mobile", verifcode.getPhone()));
            formparams.add(new BasicNameValuePair("SignId", "41686"));
            send(formparams);
            return  Message.success("验证码发送成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.success("验证码发送失败!");
        }
    }
    /**
     * 验证手机验证码是否输入正确
     * @param verifcode
     * @return
     *
     */
    @RequestMapping("validateCode")
    @ResponseBody
    public Message validateCode(Verifcode verifcode){
        try{
            //查询改手机号在有效期5分钟之内是否还有未使用的短信，如果有则返回code如果没有则返回-1
            Verifcode verifcode1 = verifcodeService.queryByCode(verifcode.getPhone());
            System.out.println(verifcode.getCode()+"==========>>>");
            if(!verifcode1.getCode().equals(verifcode.getCode())){
                return  Message.fail("验证码输入错误!");
            }else{
                System.out.println("==================");
                StatusQuery statusQuery = new StatusQuery();
                statusQuery.setId(1l);
                statusQuery.setPhone(verifcode.getPhone());
                verifcodeService.updateCodeStatus(statusQuery);
                return  Message.success("验证码输入正确!");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  Message.success("验证码已失效!");
        }
    }
    @RequestMapping("updateCodeStatus")
    @ResponseBody
    public Message updateCodeStatus(Verifcode verifcode){
        try{
            StatusQuery statusQuery = new StatusQuery();
            statusQuery.setStatus(1);
            statusQuery.setPhone(verifcode.getPhone());
            //状态为1代表使用过的或者已经作废的，0代表的是未使用过
            verifcodeService.updateCodeStatus(statusQuery);
            return  Message.success("验证码校验成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.success("验证码校验失败!");
        }
    }
    public void send(List<NameValuePair> formparams) throws Exception {
        CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
        httpClient.start();
        HttpPost requestPost = new HttpPost("http://api.feige.ee/SmsService/Send");
        requestPost.setEntity(new UrlEncodedFormEntity(formparams, "utf-8"));
        httpClient.execute(requestPost, new FutureCallback<HttpResponse>() {
            public void failed(Exception arg0) {
                System.out.println("Exception: " + arg0.getMessage());
            }
            public void completed(HttpResponse arg0) {
                System.out.println("Response: " + arg0.getStatusLine());
                try {
                    InputStream stram = arg0.getEntity().getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stram));
                    System.out.println(reader.readLine());
                } catch (UnsupportedOperationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void cancelled() {
                // TODO Auto-generated method stub
            }
        }).get();
        System.out.println("Done");
    }
}
