package com.dt.service;

import com.dt.common.PageQuery;
import com.dt.vo.DownHistoryVo;
import com.dt.vo.VedioSectionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DownHistoryService extends BaseService<DownHistoryVo>{
    List<DownHistoryVo> getList(DownHistoryVo downHistoryVo);
    List<VedioSectionVo> getSectionList(DownHistoryVo downHistoryVo);
    List<VedioSectionVo> findDownHistoryList(PageQuery pageQuery,DownHistoryVo downHistoryVo);
    Long findDownHistoryListCount(PageQuery pageQuery,DownHistoryVo downHistoryVo);

}
