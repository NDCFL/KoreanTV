package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.UserNewsPlDAO;
import com.dt.service.UserNewsPlService;
import com.dt.vo.UserNewsPlVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserNewsPlServiceImpl implements UserNewsPlService{
    @Resource
    private UserNewsPlDAO userNewsPlDAO;
    @Override
    public void save(UserNewsPlVo userNewsPlVo) {
        userNewsPlDAO.save(userNewsPlVo);
    }

    @Override
    public void remove(UserNewsPlVo userNewsPlVo) {
        userNewsPlDAO.remove(userNewsPlVo);
    }

    @Override
    public void removeById(Long id) {
        userNewsPlDAO.removeById(id);
    }

    @Override
    public void update(UserNewsPlVo userNewsPlVo) {
        userNewsPlDAO.update(userNewsPlVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        userNewsPlDAO.updateStatus(statusQuery);
    }

    @Override
    public UserNewsPlVo getById(Long id) {
        return userNewsPlDAO.getById(id);
    }

    @Override
    public List<UserNewsPlVo> listAll() {
        return userNewsPlDAO.listAll();
    }

    @Override
    public List<UserNewsPlVo> listPage(PageQuery pageQuery) {
        return userNewsPlDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return userNewsPlDAO.count(pageQuery);
    }

    @Override
    public List<UserNewsPlVo> findUserNewsPlList(PageQuery pageQuery, UserNewsPlVo userNewsPlVo) {
        return userNewsPlDAO.findUserNewsPlList(pageQuery, userNewsPlVo);
    }

    @Override
    public Long findUserNewsPlListCount(PageQuery pageQuery, UserNewsPlVo userNewsPlVo) {
        return userNewsPlDAO.findUserNewsPlListCount(pageQuery, userNewsPlVo);
    }
}
