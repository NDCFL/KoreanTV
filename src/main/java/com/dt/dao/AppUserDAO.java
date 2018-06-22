package com.dt.dao;

import com.dt.common.PageQuery;
import com.dt.vo.Select2Vo;
import com.dt.vo.AppUserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserDAO extends BaseDAO<AppUserVo>{
    int checkReg(String phone);
    int checkLogin(String phone);
    void updatePwd(@Param("id") long id, @Param("pwd") String pwd);
    void updatePhone(@Param("id") long id, @Param("phone") String phone);
    String getPassword(long id);
    List<Select2Vo> getAppUser();
    AppUserVo findByPhone(String phone);
    AppUserVo appLogin(@Param("phone") String phone, @Param("passWord") String passWord);
    void updatePassWord(@Param("phone") String phone, @Param("passWord") String passWord);
    void updateFaceImg(@Param("id") long id, @Param("faceImg") String faceImg);
    void updateName(@Param("id") long id, @Param("name") String name);
    void updateRemark(@Param("id") long id, @Param("remark") String remark);
    List<AppUserVo> findAppUserList(@Param("pageQuery") PageQuery pageQuery,@Param("appUserVo") AppUserVo appUserVo);
    Long findAppUserListCount(@Param("pageQuery") PageQuery pageQuery,@Param("appUserVo") AppUserVo appUserVo);
    List<AppUserVo> getFenSi(Long id);
    int cnts(int months);
}
