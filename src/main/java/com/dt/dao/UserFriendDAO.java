package com.dt.dao;

import com.dt.common.PageQuery;
import com.dt.vo.UserFriendVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFriendDAO extends BaseDAO<UserFriendVo>{
    List<UserFriendVo> getUserFriendList(Long userId);
    void delete(UserFriendVo userFriendVo);
    List<UserFriendVo> getMyFenList(Long userId);
}
