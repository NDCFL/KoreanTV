package com.dt.dao;

import com.dt.vo.UserCollectionVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCollectionDAO extends BaseDAO<UserCollectionVo> {
    void deleteById(UserCollectionVo userCollectionVo);
    List<UserCollectionVo> getList(UserCollectionVo userCollectionVo);
}
