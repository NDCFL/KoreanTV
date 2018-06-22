package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.service.RolePermissionService;
import com.dt.vo.RolePermissionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenfeilong on 2017/10/27.
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Resource
    private RolePermissionService rolePermissionService;
    @Override
    public void save(RolePermissionVo rolePermissionVo) {

    }

    @Override
    public void remove(RolePermissionVo rolePermissionVo) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void update(RolePermissionVo rolePermissionVo) {

    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {

    }

    @Override
    public RolePermissionVo getById(Long id) {
        return null;
    }

    @Override
    public List<RolePermissionVo> listAll() {
        return null;
    }

    @Override
    public List<RolePermissionVo> listPage(PageQuery pageQuery) {
        return null;
    }

    @Override
    public long count(PageQuery pageQuery) {
        return 0;
    }

    @Override
    public List<String> pageListByRoleId(String roleId) {
        return null;
    }
}
