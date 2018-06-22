package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.common.UserAccountPasswordQuery;
import com.dt.dao.UserDAO;
import com.dt.service.UserService;
import com.dt.vo.Select2Vo;
import com.dt.vo.UserVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;
    public void save(UserVo userVo) {
        userDAO.save(userVo);
    }

    public void remove(UserVo userVo) {
        userDAO.remove(userVo);
    }

    public void removeById(Long id) {
        userDAO.removeById(id);
    }

    public void update(UserVo userVo) {
        userDAO.update(userVo);
    }

    public void updateStatus(StatusQuery statusQuery) {
        userDAO.updateStatus(statusQuery);
    }

    public UserVo getById(Long id) {
        return userDAO.getById(id);
    }

    public List<UserVo> listAll() {
        return userDAO.listAll();
    }

    public List<UserVo> listPage(PageQuery pageQuery) {
        return userDAO.listPage(pageQuery);
    }

    public long count(PageQuery pageQuery) {
        return userDAO.count(pageQuery);
    }

    public UserVo getByAccountPassword(UserAccountPasswordQuery userAccountPasswordQuery) {
        return userDAO.getByAccountPassword(userAccountPasswordQuery);
    }

    public int checkReg(String phone) {
        return userDAO.checkReg(phone);
    }

    public int checkLogin(String account) {
        return userDAO.checkLogin(account);
    }

    public void updatePwd(long id, String pwd) {
        userDAO.updatePwd(id, pwd);
    }

    public void updatePhone(long id, String phone) {
        userDAO.updatePhone(id, phone);
    }

    public String getPassword(long id) {
        return userDAO.getPassword(id);
    }

    public List<Select2Vo> getUser() {
        return userDAO.getUser();
    }

    public UserVo findByPhone(String phone) {
        return userDAO.findByPhone(phone);
    }

    @Override
    public UserVo appLogin(String phone, String passWord) {
        return userDAO.appLogin(phone, passWord);
    }

    @Override
    public void updatePassWord(String phone, String passWord) {
        userDAO.updatePassWord(phone, passWord);
    }

    @Override
    public void updateFaceImg(long id, String faceImg) {
        userDAO.updateFaceImg(id, faceImg);
    }

    @Override
    public int checkName(String name) {
        return userDAO.checkName(name);
    }

    @Override
    public String getImg(Long id) {
        return userDAO.getImg(id);
    }
}
