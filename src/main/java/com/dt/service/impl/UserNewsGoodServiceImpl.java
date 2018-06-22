package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.UserNewsGoodDAO;
import com.dt.service.UserNewsGoodService;
import com.dt.vo.UserNewsGoodVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserNewsGoodServiceImpl implements UserNewsGoodService {

    @Resource
    private UserNewsGoodDAO userNewsGoodDAO;
    @Override
    public void save(UserNewsGoodVo userNewsGoodVo) {
        userNewsGoodDAO.save(userNewsGoodVo);
    }

    @Override
    public void remove(UserNewsGoodVo userNewsGoodVo) {
        userNewsGoodDAO.remove(userNewsGoodVo);
    }

    @Override
    public void removeById(Long id) {
        userNewsGoodDAO.removeById(id);
    }

    @Override
    public void update(UserNewsGoodVo userNewsGoodVo) {
        userNewsGoodDAO.update(userNewsGoodVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        userNewsGoodDAO.updateStatus(statusQuery);
    }

    @Override
    public UserNewsGoodVo getById(Long id) {
        return userNewsGoodDAO.getById(id);
    }

    @Override
    public List<UserNewsGoodVo> listAll() {
        return userNewsGoodDAO.listAll();
    }

    @Override
    public List<UserNewsGoodVo> listPage(PageQuery pageQuery) {
        return userNewsGoodDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return userNewsGoodDAO.count(pageQuery);
    }
    @Override
    public List<UserNewsGoodVo> findUserNewsGoodList(PageQuery pageQuery, UserNewsGoodVo userNewsGoodVo) {
        return userNewsGoodDAO.findUserNewsGoodList(pageQuery, userNewsGoodVo);
    }

    @Override
    public Long findUserNewsGoodListCount(PageQuery pageQuery, UserNewsGoodVo userNewsGoodVo) {
        return userNewsGoodDAO.findUserNewsGoodListCount(pageQuery, userNewsGoodVo);
    }

    @Override
    public void delete(UserNewsGoodVo userNewsGoodVo) {
        userNewsGoodDAO.delete(userNewsGoodVo);
    }
}
