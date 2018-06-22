package com.dt.service;

import com.dt.vo.AdvertVo;

import java.util.List;

public interface AdvertService extends BaseService<AdvertVo>{
    List<AdvertVo> listPages(String appName);
}
