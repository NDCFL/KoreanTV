package com.dt.service.impl;

import com.dt.common.PageQuery;
import com.dt.common.StatusQuery;
import com.dt.dao.VerifcodeDAO;
import com.dt.service.VerifcodeService;
import com.dt.vo.Verifcode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VerifcodeServiceImpl implements VerifcodeService {
    @Resource
    private VerifcodeDAO verifcodeDAO;

    @Override
    public Verifcode queryByCode(String phone) {
        return verifcodeDAO.queryByCode(phone);
    }

    @Override
    public void updateCodeStatus(StatusQuery statusQuery) {
        verifcodeDAO.updateCodeStatus(statusQuery);
    }

    @Override
    public void save(Verifcode verifcode) {
        verifcodeDAO.save(verifcode);
    }

    @Override
    public void remove(Verifcode verifcode) {
        verifcodeDAO.remove(verifcode);
    }

    @Override
    public void removeById(Long id) {
        verifcodeDAO.removeById(id);
    }

    @Override
    public void update(Verifcode verifcode) {
        verifcodeDAO.update(verifcode);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        verifcodeDAO.updateStatus(statusQuery);
    }

    @Override
    public Verifcode getById(Long id) {
        return verifcodeDAO.getById(id);
    }

    @Override
    public List<Verifcode> listAll() {
        return verifcodeDAO.listAll();
    }

    @Override
    public List<Verifcode> listPage(PageQuery pageQuery) {
        return verifcodeDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return verifcodeDAO.count(pageQuery);
    }
}
