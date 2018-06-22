package com.dt.service.impl;

import com.dt.service.AbstractBaseService;
import org.springframework.stereotype.Service;
import com.dt.common.DozerMapperUtils;
import com.dt.dao.RoleDAO;
import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.service.RoleService;
import com.dt.vo.RoleVo;
import com.dt.vo.Select2Vo;

import javax.annotation.Resource;
import java.util.List;
/**
 * 角色Service实现类<br />
 * 创建于2017-09-11
 *
 * @author 陈飞龙
 * @version 1.0
 */
@Service
public class RoleServiceImpl extends AbstractBaseService implements RoleService {
    @Resource
    private RoleDAO roleDAO;

    @Override
    public List<RoleVo> listByAccount(String account) {
        try {
            List<RoleVo> roleDOList = roleDAO.listByAccount(account);
            return DozerMapperUtils.map(getDozerMapper(), roleDOList, RoleVo.class);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public List<Select2Vo> getRoleIdAndTitle() {
        return roleDAO.getRoleIdAndTitle();
    }

    @Override
    public RoleVo findByName(String title) {
        return roleDAO.findByName(title);
    }

    @Override
    public void save(RoleVo roleVo) {
        roleDAO.save(roleVo);
    }

    @Override
    public void remove(RoleVo roleVo) {
        roleDAO.remove(roleVo);
    }

    @Override
    public void removeById(Long id) {
        roleDAO.removeById(id);
    }

    @Override
    public void update(RoleVo roleVo) {
        roleDAO.update(roleVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        roleDAO.updateStatus(statusQuery);
    }

    @Override
    public RoleVo getById(Long id) {
        return roleDAO.getById(id);
    }

    @Override
    public List<RoleVo> listAll() {
        return roleDAO.listAll();
    }

    @Override
    public List<RoleVo> listPage(PageQuery pageQuery) {
        return roleDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return roleDAO.count(pageQuery);
    }

}
