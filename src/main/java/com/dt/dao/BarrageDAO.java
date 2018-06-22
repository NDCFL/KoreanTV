package com.dt.dao;

import com.dt.vo.BarrageVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarrageDAO extends BaseDAO<BarrageVo>{
    List<BarrageVo> barrageList(Long sectionId);
}
