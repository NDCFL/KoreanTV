package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.VedioDiscussDAO;
import com.dt.service.VedioDiscussService;
import com.dt.vo.VedioDiscussVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class VedioDiscussServiceImpl implements VedioDiscussService {
    @Resource
    private VedioDiscussDAO vedioDiscussDAO;
    @Override
    public void save(VedioDiscussVo vedioDiscussVo) {
        vedioDiscussDAO.save(vedioDiscussVo);
    }

    @Override
    public void remove(VedioDiscussVo vedioDiscussVo) {
        vedioDiscussDAO.remove(vedioDiscussVo);
    }

    @Override
    public void removeById(Long id) {
        vedioDiscussDAO.removeById(id);
    }

    @Override
    public void update(VedioDiscussVo vedioDiscussVo) {
        vedioDiscussDAO.update(vedioDiscussVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        vedioDiscussDAO.updateStatus(statusQuery);
    }

    @Override
    public VedioDiscussVo getById(Long id) {
        return vedioDiscussDAO.getById(id);
    }

    @Override
    public List<VedioDiscussVo> listAll() {
        return vedioDiscussDAO.listAll();
    }

    @Override
    public List<VedioDiscussVo> listPage(PageQuery pageQuery) {
        return vedioDiscussDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return vedioDiscussDAO.count(pageQuery);
    }

    @Override
    public List<VedioDiscussVo> findVedioList(Long vedioId) {
        return vedioDiscussDAO.findVedioList(vedioId);
    }

    @Override
    public List<VedioDiscussVo> findVedioLists(Long vedioSectionId) {
        return vedioDiscussDAO.findVedioLists(vedioSectionId);
    }

    @Override
    public List<VedioDiscussVo> findVedioDiscussList(PageQuery pageQuery, VedioDiscussVo vedioDiscussVo) {
        return vedioDiscussDAO.findVedioDiscussList(pageQuery, vedioDiscussVo);
    }

    @Override
    public Long findVedioDiscussCount(PageQuery pageQuery, VedioDiscussVo vedioDiscussVo) {
        return vedioDiscussDAO.findVedioDiscussCount(pageQuery, vedioDiscussVo);
    }
}
