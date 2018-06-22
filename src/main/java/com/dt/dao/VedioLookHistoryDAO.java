package com.dt.dao;

import com.dt.common.PageQuery;
import com.dt.vo.VedioDiscussVo;
import com.dt.vo.VedioLookHistoryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VedioLookHistoryDAO extends BaseDAO<VedioLookHistoryVo>{
    List<VedioLookHistoryVo> findVedioLookHistory(VedioLookHistoryVo vedioLookHistoryVo);
    int findVedioLookHistoryCount(VedioLookHistoryVo vedioLookHistoryVo);
    List<VedioLookHistoryVo> sevenVedioLookHistory(Long userId);//近7天的观看历史记录
    List<VedioLookHistoryVo> findVedioLookHistoryList(@Param("pageQuery")PageQuery pageQuery, @Param("vedioLookHistoryVo")VedioLookHistoryVo vedioLookHistoryVo);
    Long findVedioLookHistoryCounts(@Param("pageQuery")PageQuery pageQuery,@Param("vedioLookHistoryVo")VedioLookHistoryVo vedioLookHistoryVo);
    void delete(VedioLookHistoryVo vedioLookHistoryVo);
}
