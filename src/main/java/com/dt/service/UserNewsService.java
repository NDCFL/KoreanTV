package com.dt.service;

import com.dt.common.PageQuery;
import com.dt.vo.UserNewsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserNewsService extends BaseService<UserNewsVo>{
    List<UserNewsVo> findUserNewsList(PageQuery pageQuery, UserNewsVo userNewsVo);
    Long findUserNewsListCount(PageQuery pageQuery, UserNewsVo userNewsVo);
    List<UserNewsVo> yourUserNewsList(PageQuery pageQuery, UserNewsVo userNewsVo);
    Long yourUserNewsCount(PageQuery pageQuery, UserNewsVo userNewsVo);
}
