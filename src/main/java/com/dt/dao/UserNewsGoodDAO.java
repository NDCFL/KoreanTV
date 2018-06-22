package com.dt.dao;

import com.dt.common.PageQuery;
import com.dt.vo.UserNewsGoodVo;
import com.dt.vo.UserNewsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNewsGoodDAO extends BaseDAO<UserNewsGoodVo>{
    List<UserNewsGoodVo> findUserNewsGoodList(@Param("pageQuery") PageQuery pageQuery, @Param("userNewsGoodVo") UserNewsGoodVo userNewsGoodVo);
    Long findUserNewsGoodListCount(@Param("pageQuery") PageQuery pageQuery, @Param("userNewsGoodVo") UserNewsGoodVo userNewsGoodVo);
    void delete(UserNewsGoodVo userNewsGoodVo);
}
