package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.VedioLookHistoryDAO;
import com.dt.service.VedioLookHistoryService;
import com.dt.vo.VedioLookHistoryVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VedioLookHistoryServiceImpl implements VedioLookHistoryService {
    @Resource
    private VedioLookHistoryDAO vedioLookHistoryDAO;
    @Override
    public void save(VedioLookHistoryVo vedioLookHistoryVo) {
        vedioLookHistoryDAO.save(vedioLookHistoryVo);
    }

    @Override
    public void remove(VedioLookHistoryVo vedioLookHistoryVo) {
        vedioLookHistoryDAO.remove(vedioLookHistoryVo);
    }

    @Override
    public void removeById(Long id) {
        vedioLookHistoryDAO.removeById(id);
    }

    @Override
    public void update(VedioLookHistoryVo vedioLookHistoryVo) {
        vedioLookHistoryDAO.update(vedioLookHistoryVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        vedioLookHistoryDAO.updateStatus(statusQuery);
    }

    @Override
    public VedioLookHistoryVo getById(Long id) {
        return vedioLookHistoryDAO.getById(id);
    }

    @Override
    public List<VedioLookHistoryVo> listAll() {
        return vedioLookHistoryDAO.listAll();
    }

    @Override
    public List<VedioLookHistoryVo> listPage(PageQuery pageQuery) {
        return vedioLookHistoryDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return vedioLookHistoryDAO.count(pageQuery);
    }

    @Override
    public List<VedioLookHistoryVo> findVedioLookHistory(VedioLookHistoryVo vedioLookHistoryVo) {
        return vedioLookHistoryDAO.findVedioLookHistory(vedioLookHistoryVo);
    }

    @Override
    public int findVedioLookHistoryCount(VedioLookHistoryVo vedioLookHistoryVo) {
        return vedioLookHistoryDAO.findVedioLookHistoryCount(vedioLookHistoryVo);
    }

    @Override
    public List<VedioLookHistoryVo> sevenVedioLookHistory(Long userId) {
        return vedioLookHistoryDAO.sevenVedioLookHistory(userId);
    }

    @Override
    public List<VedioLookHistoryVo> findVedioLookHistoryList(PageQuery pageQuery, VedioLookHistoryVo vedioLookHistoryVo) {
        return vedioLookHistoryDAO.findVedioLookHistoryList(pageQuery, vedioLookHistoryVo);
    }

    @Override
    public Long findVedioLookHistoryCounts(PageQuery pageQuery, VedioLookHistoryVo vedioLookHistoryVo) {
        return vedioLookHistoryDAO.findVedioLookHistoryCounts(pageQuery, vedioLookHistoryVo);
    }

    @Override
    public void delete(VedioLookHistoryVo vedioLookHistoryVo) {
        vedioLookHistoryDAO.delete(vedioLookHistoryVo);
    }
}
