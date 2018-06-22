package com.dt.dao;

import com.dt.common.PageQuery;
import com.dt.common.UserAccountPasswordQuery;
import com.dt.vo.Select2Vo;
import com.dt.vo.UserVo;
import org.activiti.engine.identity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends BaseDAO<UserVo>{
    UserVo getByAccountPassword(UserAccountPasswordQuery userAccountPasswordQuery);
    int checkReg(String phone);
    int checkLogin(String account);
    void updatePwd(@Param("id") long id, @Param("pwd") String pwd);
    void updatePhone(@Param("id") long id,@Param("phone") String phone);
    String getPassword(long id);
    List<Select2Vo> getUser();
    UserVo findByPhone(String phone);
    UserVo appLogin(@Param("phone") String  phone,@Param("passWord")String passWord);
    void updatePassWord(@Param("phone") String  phone,@Param("passWord")String passWord);
    void updateFaceImg(@Param("id")long id,@Param("faceImg")String faceImg);
    void updateName(@Param("id")long id,@Param("name")String name);
    int checkName(String name);
    String getImg(Long id);
}
