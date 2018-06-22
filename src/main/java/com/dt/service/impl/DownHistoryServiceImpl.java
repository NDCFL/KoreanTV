package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.DownHistoryDAO;
import com.dt.service.DownHistoryService;
import com.dt.vo.DownHistoryVo;
import com.dt.vo.DownHistoryVo;
import com.dt.vo.VedioSectionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DownHistoryServiceImpl implements DownHistoryService{
    @Resource
    private DownHistoryDAO downHistoryDAO;
    @Override
    public void save(DownHistoryVo downHistoryVo) {
        downHistoryDAO.save(downHistoryVo);
    }

    @Override
    public void remove(DownHistoryVo downHistoryVo) {
        downHistoryDAO.remove(downHistoryVo);
    }

    @Override
    public void removeById(Long id) {
        downHistoryDAO.removeById(id);
    }

    @Override
    public void update(DownHistoryVo downHistoryVo) {
        downHistoryDAO.update(downHistoryVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        downHistoryDAO.updateStatus(statusQuery);
    }

    @Override
    public DownHistoryVo getById(Long id) {
        return downHistoryDAO.getById(id);
    }

    @Override
    public List<DownHistoryVo> listAll() {
        return downHistoryDAO.listAll();
    }

    @Override
    public List<DownHistoryVo> listPage(PageQuery pageQuery) {
        return downHistoryDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return downHistoryDAO.count(pageQuery);
    }

    @Override
    public List<DownHistoryVo> getList(DownHistoryVo downHistoryVo) {
        return downHistoryDAO.getList(downHistoryVo);
    }

    @Override
    public List<VedioSectionVo> getSectionList(DownHistoryVo downHistoryVo) {
        return downHistoryDAO.getSectionList(downHistoryVo);
    }

    @Override
    public List<VedioSectionVo> findDownHistoryList(PageQuery pageQuery, DownHistoryVo downHistoryVo) {
        return downHistoryDAO.findDownHistoryList(pageQuery, downHistoryVo);
    }

    @Override
    public Long findDownHistoryListCount(PageQuery pageQuery, DownHistoryVo downHistoryVo) {
        return downHistoryDAO.findDownHistoryListCount(pageQuery, downHistoryVo);
    }
}
