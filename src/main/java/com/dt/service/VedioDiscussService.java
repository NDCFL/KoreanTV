package com.dt.service;

import com.dt.common.PageQuery;
import com.dt.vo.VedioDiscussVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VedioDiscussService extends BaseService<VedioDiscussVo>{
    List<VedioDiscussVo> findVedioList(Long vedioId);
    List<VedioDiscussVo> findVedioLists(Long vedioSectionId);
    List<VedioDiscussVo> findVedioDiscussList(PageQuery pageQuery,VedioDiscussVo vedioDiscussVo);
    Long findVedioDiscussCount(PageQuery pageQuery,VedioDiscussVo vedioDiscussVo);

}
