package com.dt.service;

import com.dt.vo.Select2Vo;
import com.dt.vo.VedioModuleVo;
import com.dt.vo.VedioTypeVo;

import java.util.List;

public interface VedioTypeService extends BaseService<VedioTypeVo>{
    List<Select2Vo> getTypeList();

}
