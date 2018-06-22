package com.dt.dao;

import com.dt.common.PageQuery;
import com.dt.vo.Select2Vo;
import com.dt.vo.VedioSectionVo;
import com.dt.vo.VedioVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VedioSectionDAO extends BaseDAO<VedioSectionVo>{
    List<VedioSectionVo> getList(Long id);
    List<Select2Vo> getSectionList(Long vedioId);
    List<VedioSectionVo> findVedioSectionList(@Param("pageQuery") PageQuery pageQuery, @Param("vedioSectionVo") VedioSectionVo vedioSectionVo);
    Long findVedioSectionCount(@Param("pageQuery") PageQuery pageQuery, @Param("vedioSectionVo") VedioSectionVo vedioSectionVo);
    void updateVedio(VedioSectionVo vedioSectionVo);
}
