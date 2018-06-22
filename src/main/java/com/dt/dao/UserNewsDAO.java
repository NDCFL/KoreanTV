package com.dt.dao;

import com.dt.common.PageQuery;
import com.dt.vo.UserNewsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNewsDAO extends BaseDAO<UserNewsVo>{
    List<UserNewsVo> findUserNewsList(@Param("pageQuery") PageQuery pageQuery, @Param("userNewsVo") UserNewsVo userNewsVo);
    Long findUserNewsListCount(@Param("pageQuery") PageQuery pageQuery, @Param("userNewsVo") UserNewsVo userNewsVo);
    List<UserNewsVo> yourUserNewsList(@Param("pageQuery") PageQuery pageQuery, @Param("userNewsVo") UserNewsVo userNewsVo);
    Long yourUserNewsCount(@Param("pageQuery") PageQuery pageQuery, @Param("userNewsVo") UserNewsVo userNewsVo);
}
