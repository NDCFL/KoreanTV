package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.UserFriendDAO;
import com.dt.service.UserFriendService;
import com.dt.vo.UserFriendVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserFriendServiceImpl implements UserFriendService{
    @Resource
    private UserFriendDAO userFriendDAO;
    @Override
    public void save(UserFriendVo userFriendVo) {
        userFriendDAO.save(userFriendVo);
    }

    @Override
    public void remove(UserFriendVo userFriendVo) {
        userFriendDAO.remove(userFriendVo);
    }

    @Override
    public void removeById(Long id) {
        userFriendDAO.removeById(id);
    }

    @Override
    public void update(UserFriendVo userFriendVo) {
        userFriendDAO.update(userFriendVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        userFriendDAO.updateStatus(statusQuery);
    }

    @Override
    public UserFriendVo getById(Long id) {
        return userFriendDAO.getById(id);
    }

    @Override
    public List<UserFriendVo> listAll() {
        return userFriendDAO.listAll();
    }

    @Override
    public List<UserFriendVo> listPage(PageQuery pageQuery) {
        return userFriendDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return userFriendDAO.count(pageQuery);
    }

    @Override
    public List<UserFriendVo> getUserFriendList(Long userId) {
        return userFriendDAO.getUserFriendList(userId);
    }

    @Override
    public void delete(UserFriendVo userFriendVo) {
        userFriendDAO.delete(userFriendVo);
    }

    @Override
    public List<UserFriendVo> getMyFenList(Long userId) {
        return userFriendDAO.getMyFenList(userId);
    }
}
