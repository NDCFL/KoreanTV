package com.dt.dao;

import com.dt.vo.Select2Vo;
import com.dt.vo.VedioModuleVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VedioModuleDAO extends BaseDAO<VedioModuleVo>{
    List<Select2Vo> getModuleList();
    List<VedioModuleVo> getList();
}
