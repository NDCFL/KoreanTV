package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.BarrageDAO;
import com.dt.service.BarrageService;
import com.dt.vo.BarrageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class BarrageServiceImpl implements BarrageService {
    @Resource
    private BarrageDAO barrageDAO;
    @Override
    public void save(BarrageVo barrageVo) {
        barrageDAO.save(barrageVo);
    }

    @Override
    public void remove(BarrageVo barrageVo) {
        barrageDAO.remove(barrageVo);
    }

    @Override
    public void removeById(Long id) {
        barrageDAO.removeById(id);
    }

    @Override
    public void update(BarrageVo barrageVo) {
        barrageDAO.update(barrageVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        barrageDAO.updateStatus(statusQuery);
    }

    @Override
    public BarrageVo getById(Long id) {
        return barrageDAO.getById(id);
    }

    @Override
    public List<BarrageVo> listAll() {
        return barrageDAO.listAll();
    }

    @Override
    public List<BarrageVo> listPage(PageQuery pageQuery) {
        return barrageDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return barrageDAO.count(pageQuery);
    }

    @Override
    public List<BarrageVo> barrageList(Long sectionId) {
        return barrageDAO.barrageList(sectionId);
    }
}
