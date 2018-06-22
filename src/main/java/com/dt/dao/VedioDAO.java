package com.dt.dao;

import com.dt.common.PageQuery;
import com.dt.vo.Select2Vo;
import com.dt.vo.VedioVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VedioDAO extends BaseDAO<VedioVo>{
    List<Select2Vo> getVedioList();
    List<VedioVo> findVedioList(@Param("vedioVo") VedioVo vedioVo, @Param("pageQuery")PageQuery pageQuery);
    Long findVedioCount(@Param("vedioVo") VedioVo vedioVo, @Param("pageQuery")PageQuery pageQuery);

}
