package com.dt.service;

import com.dt.common.PageQuery;
import com.dt.vo.UserNewsPlVo;

import java.util.List;

public interface UserNewsPlService extends BaseService<UserNewsPlVo>{
    List<UserNewsPlVo> findUserNewsPlList(PageQuery pageQuery, UserNewsPlVo userNewsVo);
    Long findUserNewsPlListCount(PageQuery pageQuery, UserNewsPlVo userNewsVo);
}
