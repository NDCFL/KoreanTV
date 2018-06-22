package com.dt.service;


import com.dt.dao.BaseDAO;
import com.dt.vo.UserRoleVo;

import java.util.List;

/**
 * Created by chenfeilong on 2017/10/27.
 */
public interface UserRoleService extends BaseDAO<UserRoleVo> {
    List<String> pageListByUserId(String userId);
    UserRoleVo getRole(Long userId);
}
