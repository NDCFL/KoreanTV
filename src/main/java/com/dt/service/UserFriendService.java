package com.dt.service;

import com.dt.vo.UserFriendVo;

import java.util.List;

public interface UserFriendService extends BaseService<UserFriendVo>{
    List<UserFriendVo> getUserFriendList(Long userId);
    void delete(UserFriendVo userFriendVo);
    List<UserFriendVo> getMyFenList(Long userId);
}
