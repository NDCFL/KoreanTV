package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.VedioSectionDAO;
import com.dt.service.VedioSectionService;
import com.dt.vo.Select2Vo;
import com.dt.vo.VedioSectionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class  VedioSectionServiceImpl implements VedioSectionService {
    @Resource
    private VedioSectionDAO vedioSectionDAO;
    @Override
    public void save(VedioSectionVo vedioVo) {
        vedioSectionDAO.save(vedioVo);
    }

    @Override
    public void remove(VedioSectionVo vedioVo) {
        vedioSectionDAO.remove(vedioVo);
    }

    @Override
    public void removeById(Long id) {
        vedioSectionDAO.removeById(id);
    }

    @Override
    public void update(VedioSectionVo vedioVo) {
        vedioSectionDAO.update(vedioVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        vedioSectionDAO.updateStatus(statusQuery);
    }

    @Override
    public VedioSectionVo getById(Long id) {
        return vedioSectionDAO.getById(id);
    }

    @Override
    public List<VedioSectionVo> listAll() {
        return vedioSectionDAO.listAll();
    }

    @Override
    public List<VedioSectionVo> listPage(PageQuery pageQuery) {
        return vedioSectionDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return vedioSectionDAO.count(pageQuery);
    }

    @Override
    public List<VedioSectionVo> getList(Long id) {
        return vedioSectionDAO.getList(id);
    }

    @Override
    public List<Select2Vo> getSectionList(Long vedioId) {
        return vedioSectionDAO.getSectionList(vedioId);
    }

    @Override
    public List<VedioSectionVo> findVedioSectionList(PageQuery pageQuery, VedioSectionVo vedioSectionVo) {
        return vedioSectionDAO.findVedioSectionList(pageQuery, vedioSectionVo);
    }

    @Override
    public Long findVedioSectionCount(PageQuery pageQuery, VedioSectionVo vedioSectionVo) {
        return vedioSectionDAO.findVedioSectionCount(pageQuery, vedioSectionVo);
    }

    @Override
    public void updateVedio(VedioSectionVo vedioSectionVo) {
        vedioSectionDAO.updateVedio(vedioSectionVo);
    }
}
