package com.dt.dao;

import com.dt.vo.Select2Vo;
import com.dt.vo.VedioModuleVo;
import com.dt.vo.VedioTypeVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VedioTypeDAO extends BaseDAO<VedioTypeVo>{
    List<Select2Vo> getTypeList();
}
