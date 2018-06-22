package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.VedioModuleDAO;
import com.dt.service.VedioModuleService;
import com.dt.vo.Select2Vo;
import com.dt.vo.VedioModuleVo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VedioModuleServiceImpl implements VedioModuleService{
    @Resource
    private VedioModuleDAO vedioModuleDAO;
    @Override
    public void save(VedioModuleVo vedioModuleVo) {
        vedioModuleDAO.save(vedioModuleVo);
    }

    @Override
    public void remove(VedioModuleVo vedioModuleVo) {
        vedioModuleDAO.remove(vedioModuleVo);
    }

    @Override
    public void removeById(Long id) {
        vedioModuleDAO.removeById(id);
    }

    @Override
    public void update(VedioModuleVo vedioModuleVo) {
        vedioModuleDAO.update(vedioModuleVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        vedioModuleDAO.updateStatus(statusQuery);
    }

    @Override
    public VedioModuleVo getById(Long id) {
        return vedioModuleDAO.getById(id);
    }

    @Override
    public List<VedioModuleVo> listAll() {
        return vedioModuleDAO.listAll();
    }

    @Override
    public List<VedioModuleVo> listPage(PageQuery pageQuery) {
        return vedioModuleDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return vedioModuleDAO.count(pageQuery);
    }

    @Override
    public List<Select2Vo> getModuleList() {
        return vedioModuleDAO.getModuleList();
    }

    @Override
    public List<VedioModuleVo> getList() {
        return vedioModuleDAO.getList();
    }
}
