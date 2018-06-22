package com.dt.service;

import com.dt.common.PageQuery;
import com.dt.vo.Select2Vo;
import com.dt.vo.VedioSectionVo;
import com.dt.vo.VedioVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VedioSectionService extends BaseService<VedioSectionVo>{
    List<VedioSectionVo> getList(Long id);
    List<Select2Vo> getSectionList(Long vedioId);
    List<VedioSectionVo> findVedioSectionList(PageQuery pageQuery,VedioSectionVo vedioSectionVo);
    Long findVedioSectionCount(PageQuery pageQuery,VedioSectionVo vedioSectionVo);
    void updateVedio(VedioSectionVo vedioSectionVo);
}
