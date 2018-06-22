package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.VedioDAO;
import com.dt.service.VedioService;
import com.dt.vo.Select2Vo;
import com.dt.vo.VedioVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VedioServiceImpl implements VedioService{
    @Resource
    private VedioDAO vedioDAO;
    @Override
    public void save(VedioVo vedioVo) {
        vedioDAO.save(vedioVo);
    }

    @Override
    public void remove(VedioVo vedioVo) {
        vedioDAO.remove(vedioVo);
    }

    @Override
    public void removeById(Long id) {
        vedioDAO.removeById(id);
    }

    @Override
    public void update(VedioVo vedioVo) {
        vedioDAO.update(vedioVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        vedioDAO.updateStatus(statusQuery);
    }

    @Override
    public VedioVo getById(Long id) {
        return vedioDAO.getById(id);
    }

    @Override
    public List<VedioVo> listAll() {
        return vedioDAO.listAll();
    }

    @Override
    public List<VedioVo> listPage(PageQuery pageQuery) {
        return vedioDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return vedioDAO.count(pageQuery);
    }

    @Override
    public List<Select2Vo> getVedioList() {
        return vedioDAO.getVedioList();
    }

    @Override
    public List<VedioVo> findVedioList(VedioVo vedioVo, PageQuery pageQuery) {
        return vedioDAO.findVedioList(vedioVo, pageQuery);
    }

    @Override
    public Long findVedioCount(VedioVo vedioVo, PageQuery pageQuery) {
        return vedioDAO.findVedioCount(vedioVo, pageQuery);
    }
}
