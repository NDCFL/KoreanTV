package com.dt.dao;

import com.dt.common.PageQuery;
import com.dt.vo.VedioGoodVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VedioGoodDAO extends BaseDAO<VedioGoodVo>{
    List<VedioGoodVo> findVedioGoodList(@Param("pageQuery") PageQuery pageQuery, @Param("vedioGoodVo") VedioGoodVo vedioGoodVo);
    Long findVedioGoodListCount(@Param("pageQuery") PageQuery pageQuery, @Param("vedioGoodVo") VedioGoodVo vedioGoodVo);
    void delete(VedioGoodVo vedioGoodVo);
}
