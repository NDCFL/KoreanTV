package com.dt.service;

import com.dt.vo.Select2Vo;
import com.dt.vo.VedioModuleVo;

import java.util.List;

public interface VedioModuleService extends BaseService<VedioModuleVo>{
    List<Select2Vo> getModuleList();
    List<VedioModuleVo> getList();
}
