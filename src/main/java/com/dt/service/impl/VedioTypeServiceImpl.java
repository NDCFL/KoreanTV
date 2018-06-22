package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.VedioTypeDAO;
import com.dt.service.VedioTypeService;
import com.dt.vo.Select2Vo;
import com.dt.vo.VedioTypeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VedioTypeServiceImpl implements VedioTypeService{
    @Resource
    private VedioTypeDAO vedioTypeDAO;
    @Override
    public void save(VedioTypeVo vedioTypeVo) {
        vedioTypeDAO.save(vedioTypeVo);
    }

    @Override
    public void remove(VedioTypeVo vedioTypeVo) {
        vedioTypeDAO.remove(vedioTypeVo);
    }

    @Override
    public void removeById(Long id) {
        vedioTypeDAO.removeById(id);
    }

    @Override
    public void update(VedioTypeVo vedioTypeVo) {
        vedioTypeDAO.update(vedioTypeVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        vedioTypeDAO.updateStatus(statusQuery);
    }

    @Override
    public VedioTypeVo getById(Long id) {
        return vedioTypeDAO.getById(id);
    }

    @Override
    public List<VedioTypeVo> listAll() {
        return vedioTypeDAO.listAll();
    }

    @Override
    public List<VedioTypeVo> listPage(PageQuery pageQuery) {
        return vedioTypeDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return vedioTypeDAO.count(pageQuery);
    }

    @Override
    public List<Select2Vo> getTypeList() {
        return vedioTypeDAO.getTypeList();
    }
}
