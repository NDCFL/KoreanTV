package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.common.UserAccountPasswordQuery;
import com.dt.dao.AppUserDAO;
import com.dt.dao.UserDAO;
import com.dt.service.AppUserService;
import com.dt.service.UserService;
import com.dt.vo.AppUserVo;
import com.dt.vo.Select2Vo;
import com.dt.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Resource
    private AppUserDAO appUserDAO;

    @Override
    public int checkReg(String phone) {
        return appUserDAO.checkReg(phone);
    }

    @Override
    public int checkLogin(String phone) {
        return appUserDAO.checkLogin(phone);
    }

    @Override
    public void updatePwd(long id, String pwd) {
        appUserDAO.updatePwd(id, pwd);
    }

    @Override
    public void updatePhone(long id, String phone) {
        appUserDAO.updatePhone(id, phone);
    }

    @Override
    public String getPassword(long id) {
        return appUserDAO.getPassword(id);
    }

    @Override
    public List<Select2Vo> getAppUser() {
        return appUserDAO.getAppUser();
    }

    @Override
    public AppUserVo findByPhone(String phone) {
        return appUserDAO.findByPhone(phone);
    }

    @Override
    public AppUserVo appLogin(String phone, String passWord) {
        return appUserDAO.appLogin(phone, passWord);
    }

    @Override
    public void updatePassWord(String phone, String passWord) {
        appUserDAO.updatePassWord(phone, passWord);
    }

    @Override
    public void updateFaceImg(long id, String faceImg) {
        appUserDAO.updateFaceImg(id, faceImg);
    }

    @Override
    public void updateName(long id, String name) {
        appUserDAO.updateName(id, name);
    }

    @Override
    public void updateRemark(long id, String remark) {
        appUserDAO.updateRemark(id, remark);
    }

    @Override
    public List<AppUserVo> findAppUserList(PageQuery pageQuery, AppUserVo appUserVo) {
        return appUserDAO.findAppUserList(pageQuery, appUserVo);
    }

    @Override
    public Long findAppUserListCount(PageQuery pageQuery, AppUserVo appUserVo) {
        return appUserDAO.findAppUserListCount(pageQuery, appUserVo);
    }

    @Override
    public List<AppUserVo> getFenSi(Long id) {
        return appUserDAO.getFenSi(id);
    }

    @Override
    public int cnts(int months) {
        return appUserDAO.cnts(months);
    }

    @Override
    public void save(AppUserVo appUserVo) {
        appUserDAO.save(appUserVo);
    }

    @Override
    public void remove(AppUserVo appUserVo) {
        appUserDAO.remove(appUserVo);
    }

    @Override
    public void removeById(Long id) {
        appUserDAO.removeById(id);
    }

    @Override
    public void update(AppUserVo appUserVo) {
        appUserDAO.update(appUserVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        appUserDAO.updateStatus(statusQuery);
    }

    @Override
    public AppUserVo getById(Long id) {
        return appUserDAO.getById(id);
    }

    @Override
    public List<AppUserVo> listAll() {
        return appUserDAO.listAll();
    }

    @Override
    public List<AppUserVo> listPage(PageQuery pageQuery) {
        return appUserDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return appUserDAO.count(pageQuery);
    }
}
