package com.dt.service.impl;

import org.springframework.stereotype.Service;
import com.dt.dao.UserRoleDAO;
import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.service.UserRoleService;
import com.dt.vo.UserRoleVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenfeilong on 2017/10/27.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleDAO userRoleDAO;
    @Override
    public void save(UserRoleVo userRoleVo) {
        userRoleDAO.save(userRoleVo);
    }

    @Override
    public void remove(UserRoleVo userRoleVo) {
        userRoleDAO.remove(userRoleVo);
    }

    @Override
    public void removeById(Long id) {
        userRoleDAO.removeById(id);
    }

    @Override
    public void update(UserRoleVo userRoleVo) {
        userRoleDAO.update(userRoleVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        userRoleDAO.updateStatus(statusQuery);
    }

    @Override
    public UserRoleVo getById(Long id) {
        return userRoleDAO.getById(id);
    }

    @Override
    public List<UserRoleVo> listAll() {
        return userRoleDAO.listAll();
    }

    @Override
    public List<UserRoleVo> listPage(PageQuery pageQuery) {
        return userRoleDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return userRoleDAO.count(pageQuery);
    }

    @Override
    public List<String> pageListByUserId(String userId) {
        return userRoleDAO.pageListByUserId(userId);
    }

    @Override
    public UserRoleVo getRole(Long userId) {
        return userRoleDAO.getRole(userId);
    }
}
