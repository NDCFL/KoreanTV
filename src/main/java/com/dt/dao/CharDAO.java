package com.dt.dao;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.dt.common.PageQuery;
import com.dt.vo.CharVo;
import com.dt.vo.Select2Vo;
import com.dt.vo.VedioModuleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharDAO extends BaseDAO<CharVo>{
    List<CharVo> listPages(@Param("pageQuery") PageQuery pageQuery, @Param("charVo") CharVo charVo);
    Long counts(@Param("pageQuery") PageQuery pageQuery,@Param("charVo") CharVo charVo);

}
