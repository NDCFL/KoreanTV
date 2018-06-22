package com.dt.service;

import com.dt.common.PageQuery;
import com.dt.vo.CharVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CharService extends BaseService<CharVo> {
    List<CharVo> listPages(PageQuery pageQuery,CharVo charVo);
    Long counts(PageQuery pageQuery,CharVo charVo);
}
