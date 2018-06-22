package com.dt.dao;

import com.dt.vo.AdvertVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertDAO extends BaseDAO<AdvertVo>{
    List<AdvertVo> listPages(String appName);
}
