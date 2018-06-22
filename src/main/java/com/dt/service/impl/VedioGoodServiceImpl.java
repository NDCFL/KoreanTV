package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.VedioGoodDAO;
import com.dt.service.VedioGoodService;
import com.dt.vo.VedioGoodVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VedioGoodServiceImpl implements VedioGoodService {

    @Resource
    private VedioGoodDAO vedioGoodDAO;
    @Override
    public void save(VedioGoodVo vedioGoodVo) {
        vedioGoodDAO.save(vedioGoodVo);
    }

    @Override
    public void remove(VedioGoodVo vedioGoodVo) {
        vedioGoodDAO.remove(vedioGoodVo);
    }

    @Override
    public void removeById(Long id) {
        vedioGoodDAO.removeById(id);
    }

    @Override
    public void update(VedioGoodVo vedioGoodVo) {
        vedioGoodDAO.update(vedioGoodVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        vedioGoodDAO.updateStatus(statusQuery);
    }

    @Override
    public VedioGoodVo getById(Long id) {
        return vedioGoodDAO.getById(id);
    }

    @Override
    public List<VedioGoodVo> listAll() {
        return vedioGoodDAO.listAll();
    }

    @Override
    public List<VedioGoodVo> listPage(PageQuery pageQuery) {
        return vedioGoodDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return vedioGoodDAO.count(pageQuery);
    }
    @Override
    public List<VedioGoodVo> findVedioGoodList(PageQuery pageQuery, VedioGoodVo vedioGoodVo) {
        return vedioGoodDAO.findVedioGoodList(pageQuery, vedioGoodVo);
    }

    @Override
    public Long findVedioGoodListCount(PageQuery pageQuery, VedioGoodVo vedioGoodVo) {
        return vedioGoodDAO.findVedioGoodListCount(pageQuery, vedioGoodVo);
    }

    @Override
    public void delete(VedioGoodVo vedioGoodVo) {
        vedioGoodDAO.delete(vedioGoodVo);
    }
}
