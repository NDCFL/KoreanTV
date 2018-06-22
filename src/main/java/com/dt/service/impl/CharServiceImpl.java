package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.CharDAO;
import com.dt.service.CharService;
import com.dt.vo.Select2Vo;
import com.dt.vo.CharVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CharServiceImpl implements CharService{
    @Resource
    private CharDAO charDAO;
    @Override
    public void save(CharVo charVo) {
        charDAO.save(charVo);
    }

    @Override
    public void remove(CharVo charVo) {
        charDAO.remove(charVo);
    }

    @Override
    public void removeById(Long id) {
        charDAO.removeById(id);
    }

    @Override
    public void update(CharVo charVo) {
        charDAO.update(charVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        charDAO.updateStatus(statusQuery);
    }

    @Override
    public CharVo getById(Long id) {
        return charDAO.getById(id);
    }

    @Override
    public List<CharVo> listAll() {
        return charDAO.listAll();
    }

    @Override
    public List<CharVo> listPage(PageQuery pageQuery) {
        return charDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return charDAO.count(pageQuery);
    }

    @Override
    public List<CharVo> listPages(PageQuery pageQuery, CharVo charVo) {
        return charDAO.listPages(pageQuery, charVo);
    }

    @Override
    public Long counts(PageQuery pageQuery, CharVo charVo) {
        return charDAO.counts(pageQuery, charVo);
    }
}
