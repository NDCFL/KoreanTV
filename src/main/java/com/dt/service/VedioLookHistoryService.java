package com.dt.service;

import com.dt.common.PageQuery;
import com.dt.vo.VedioDiscussVo;
import com.dt.vo.VedioLookHistoryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VedioLookHistoryService extends BaseService<VedioLookHistoryVo>{
    List<VedioLookHistoryVo> findVedioLookHistory(VedioLookHistoryVo vedioLookHistoryVo);
    int findVedioLookHistoryCount(VedioLookHistoryVo vedioLookHistoryVo);
    List<VedioLookHistoryVo> sevenVedioLookHistory(Long userId);//近7天的观看历史记录
    List<VedioLookHistoryVo> findVedioLookHistoryList(PageQuery pageQuery, VedioLookHistoryVo vedioLookHistoryVo);
    Long findVedioLookHistoryCounts(PageQuery pageQuery,VedioLookHistoryVo vedioLookHistoryVo);
    void delete(VedioLookHistoryVo vedioLookHistoryVo);
}
