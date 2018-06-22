package com.dt.service;

import com.dt.common.PageQuery;
import com.dt.vo.UserNewsGoodVo;
import com.dt.vo.UserNewsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserNewsGoodService extends BaseService<UserNewsGoodVo>{
    List<UserNewsGoodVo> findUserNewsGoodList(PageQuery pageQuery, UserNewsGoodVo userNewsGoodVo);
    Long findUserNewsGoodListCount(PageQuery pageQuery,UserNewsGoodVo userNewsGoodVo);
    void delete(UserNewsGoodVo userNewsGoodVo);
}
