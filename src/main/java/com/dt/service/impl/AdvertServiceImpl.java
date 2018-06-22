package com.dt.service.impl;

import org.springframework.stereotype.Service;
import com.dt.dao.AdvertDAO;
import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.service.AdvertService;
import com.dt.vo.AdvertVo;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdvertServiceImpl implements AdvertService {
    @Resource
    private AdvertDAO advertDAO;
    @Override
    public void save(AdvertVo meiTuXiuTypeVo) {
        advertDAO.save(meiTuXiuTypeVo);
    }

    @Override
    public void remove(AdvertVo meiTuXiuTypeVo) {
        advertDAO.remove(meiTuXiuTypeVo);
    }

    @Override
    public void removeById(Long id) {
        advertDAO.removeById(id);
    }

    @Override
    public void update(AdvertVo meiTuXiuTypeVo) {
        advertDAO.update(meiTuXiuTypeVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        advertDAO.updateStatus(statusQuery);
    }

    @Override
    public AdvertVo getById(Long id) {
        return advertDAO.getById(id);
    }

    @Override
    public List<AdvertVo> listAll() {
        return advertDAO.listAll();
    }

    @Override
    public List<AdvertVo> listPage(PageQuery pageQuery) {
        return advertDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return advertDAO.count(pageQuery);
    }

    @Override
    public List<AdvertVo> listPages(String appName) {
        return advertDAO.listPages(appName);
    }
}
