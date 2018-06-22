package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.UserCollectionDAO;
import com.dt.service.UserCollectionService;
import com.dt.vo.Select2Vo;
import com.dt.vo.UserCollectionVo;
import com.dt.vo.UserCollectionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserCollectionServiceImpl implements UserCollectionService{
    @Resource
    private UserCollectionDAO userCollectionDAO;
    @Override
    public void save(UserCollectionVo userCollectionVo) {
        userCollectionDAO.save(userCollectionVo);
    }

    @Override
    public void remove(UserCollectionVo userCollectionVo) {
        userCollectionDAO.remove(userCollectionVo);
    }

    @Override
    public void removeById(Long id) {
        userCollectionDAO.removeById(id);
    }

    @Override
    public void update(UserCollectionVo userCollectionVo) {
        userCollectionDAO.update(userCollectionVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        userCollectionDAO.updateStatus(statusQuery);
    }

    @Override
    public UserCollectionVo getById(Long id) {
        return userCollectionDAO.getById(id);
    }

    @Override
    public List<UserCollectionVo> listAll() {
        return userCollectionDAO.listAll();
    }

    @Override
    public List<UserCollectionVo> listPage(PageQuery pageQuery) {
        return userCollectionDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return userCollectionDAO.count(pageQuery);
    }

    @Override
    public void deleteById(UserCollectionVo userCollectionVo) {
        userCollectionDAO.deleteById(userCollectionVo);
    }

    @Override
    public List<UserCollectionVo> getList(UserCollectionVo userCollectionVo) {
        return userCollectionDAO.getList(userCollectionVo);
    }
}
