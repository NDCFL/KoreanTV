package com.dt.service;

import com.dt.vo.UserCollectionVo;

import java.util.List;

public interface UserCollectionService extends BaseService<UserCollectionVo>{
    void deleteById(UserCollectionVo userCollectionVo);
    List<UserCollectionVo> getList(UserCollectionVo userCollectionVo);
}
