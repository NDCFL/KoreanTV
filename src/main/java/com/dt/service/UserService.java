package com.dt.service;

import com.dt.common.UserAccountPasswordQuery;
import com.dt.vo.Select2Vo;
import com.dt.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService extends BaseService<UserVo>{
    UserVo getByAccountPassword(UserAccountPasswordQuery userAccountPasswordQuery);
    int checkReg(String phone);
    int checkLogin(String account);
    void updatePwd(@Param("id") long id, @Param("pwd") String pwd);
    void updatePhone(@Param("id") long id,@Param("phone") String phone);
    String getPassword(long id);
    List<Select2Vo> getUser();
    UserVo findByPhone(String phone);
    UserVo appLogin(String  phone,String passWord);
    void updatePassWord(String  phone,String passWord);
    void updateFaceImg(long id,String faceImg);
    int checkName(String name);
    String getImg(Long id);
}
