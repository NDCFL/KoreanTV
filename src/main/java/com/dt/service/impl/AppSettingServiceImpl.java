package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.AppSettingDAO;
import com.dt.service.AppSettingService;
import com.dt.vo.AppSettingVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppSettingServiceImpl implements AppSettingService {
    @Resource
    private AppSettingDAO appSettingDAO;
    @Override
    public void save(AppSettingVo appSettingVo) {
        appSettingDAO.save(appSettingVo);
    }

    @Override
    public void remove(AppSettingVo appSettingVo) {
        appSettingDAO.remove(appSettingVo);
    }

    @Override
    public void removeById(Long id) {
        appSettingDAO.removeById(id);
    }

    @Override
    public void update(AppSettingVo appSettingVo) {
        appSettingDAO.update(appSettingVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        appSettingDAO.updateStatus(statusQuery);
    }

    @Override
    public AppSettingVo getById(Long id) {
        return appSettingDAO.getById(id);
    }

    @Override
    public List<AppSettingVo> listAll() {
        return appSettingDAO.listAll();
    }

    @Override
    public List<AppSettingVo> listPage(PageQuery pageQuery) {
        return appSettingDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return appSettingDAO.count(pageQuery);
    }
}
