package com.dt.service;

import com.dt.common.PageQuery;
import com.dt.common.UserAccountPasswordQuery;
import com.dt.vo.AppUserVo;
import com.dt.vo.Select2Vo;
import com.dt.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppUserService extends BaseService<AppUserVo>{
    int checkReg(String phone);
    int checkLogin(String phone);
    void updatePwd(@Param("id") long id, @Param("pwd") String pwd);
    void updatePhone(@Param("id") long id, @Param("phone") String phone);
    String getPassword(long id);
    List<Select2Vo> getAppUser();
    AppUserVo findByPhone(String phone);
    AppUserVo appLogin(String phone, String passWord);
    void updatePassWord(String phone, String passWord);
    void updateFaceImg(long id,String faceImg);
    void updateName(long id,String name);
    void updateRemark(long id,String remark);
    List<AppUserVo> findAppUserList(PageQuery pageQuery, AppUserVo appUserVo);
    Long findAppUserListCount(PageQuery pageQuery,AppUserVo appUserVo);
    List<AppUserVo> getFenSi(Long id);
    int cnts(int months);
}
