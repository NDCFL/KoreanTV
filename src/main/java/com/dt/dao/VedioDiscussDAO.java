package com.dt.dao;

import com.dt.common.PageQuery;
import com.dt.vo.VedioDiscussVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VedioDiscussDAO extends BaseDAO<VedioDiscussVo>{
    List<VedioDiscussVo> findVedioList(Long vedioId);
    List<VedioDiscussVo> findVedioLists(Long vedioSectionId);
    List<VedioDiscussVo> findVedioDiscussList(@Param("pageQuery")PageQuery pageQuery,@Param("vedioDiscussVo")VedioDiscussVo vedioDiscussVo);
    Long findVedioDiscussCount(@Param("pageQuery")PageQuery pageQuery,@Param("vedioDiscussVo")VedioDiscussVo vedioDiscussVo);
}
