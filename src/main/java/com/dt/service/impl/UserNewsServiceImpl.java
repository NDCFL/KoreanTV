package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.UserNewsDAO;
import com.dt.service.UserNewsService;
import com.dt.vo.UserNewsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserNewsServiceImpl implements UserNewsService{
    @Resource
    private UserNewsDAO userNewsDAO;
    @Override
    public void save(UserNewsVo userNewsVo) {
        userNewsDAO.save(userNewsVo);
    }

    @Override
    public void remove(UserNewsVo userNewsVo) {
        userNewsDAO.remove(userNewsVo);
    }

    @Override
    public void removeById(Long id) {
        userNewsDAO.removeById(id);
    }

    @Override
    public void update(UserNewsVo userNewsVo) {
        userNewsDAO.update(userNewsVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        userNewsDAO.updateStatus(statusQuery);
    }

    @Override
    public UserNewsVo getById(Long id) {
        return userNewsDAO.getById(id);
    }

    @Override
    public List<UserNewsVo> listAll() {
        return userNewsDAO.listAll();
    }

    @Override
    public List<UserNewsVo> listPage(PageQuery pageQuery) {
        return userNewsDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return userNewsDAO.count(pageQuery);
    }

    @Override
    public List<UserNewsVo> findUserNewsList(PageQuery pageQuery, UserNewsVo userNewsVo) {
        return userNewsDAO.findUserNewsList(pageQuery, userNewsVo);
    }

    @Override
    public Long findUserNewsListCount(PageQuery pageQuery, UserNewsVo userNewsVo) {
        return userNewsDAO.findUserNewsListCount(pageQuery, userNewsVo);
    }

    @Override
    public List<UserNewsVo> yourUserNewsList(PageQuery pageQuery,  UserNewsVo userNewsVo) {
        return userNewsDAO.yourUserNewsList(pageQuery, userNewsVo);
    }

    @Override
    public Long yourUserNewsCount(PageQuery pageQuery, UserNewsVo userNewsVo) {
        return userNewsDAO.yourUserNewsCount(pageQuery, userNewsVo);
    }
}
