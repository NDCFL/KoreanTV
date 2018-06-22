package com.dt.service;

import com.dt.vo.BarrageVo;

import java.util.List;

public interface BarrageService extends BaseService<BarrageVo>{
    List<BarrageVo> barrageList(Long sectionId);
}
