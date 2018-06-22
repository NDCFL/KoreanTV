package com.dt.service;

import com.dt.common.PageQuery;
import com.dt.vo.VedioGoodVo;

import java.util.List;

public interface VedioGoodService extends BaseService<VedioGoodVo>{
    List<VedioGoodVo> findVedioGoodList(PageQuery pageQuery, VedioGoodVo vedioGoodVo);
    Long findVedioGoodListCount(PageQuery pageQuery, VedioGoodVo vedioGoodVo);
    void delete(VedioGoodVo vedioGoodVo);
}
