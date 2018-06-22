package com.dt.service;

import com.dt.common.PageQuery;
import com.dt.vo.Select2Vo;
import com.dt.vo.VedioVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VedioService extends BaseService<VedioVo>{
    List<Select2Vo> getVedioList();
    List<VedioVo> findVedioList(VedioVo vedioVo,PageQuery pageQuery);
    Long findVedioCount(@Param("vedioVo") VedioVo vedioVo, @Param("pageQuery")PageQuery pageQuery);
}
