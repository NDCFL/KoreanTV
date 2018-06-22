package com.dt.dao;

import com.dt.common.PageQuery;
import com.dt.vo.DownHistoryVo;
import com.dt.vo.VedioSectionVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DownHistoryDAO extends BaseDAO<DownHistoryVo>{
    List<DownHistoryVo> getList(DownHistoryVo downHistoryVo);
    List<VedioSectionVo> getSectionList(DownHistoryVo downHistoryVo);
    List<VedioSectionVo> findDownHistoryList(@Param("pageQuery")PageQuery pageQuery,@Param("downHistoryVo") DownHistoryVo downHistoryVo);
    Long findDownHistoryListCount(@Param("pageQuery")PageQuery pageQuery,@Param("downHistoryVo") DownHistoryVo downHistoryVo);
}
