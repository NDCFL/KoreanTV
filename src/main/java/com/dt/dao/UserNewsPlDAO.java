package com.dt.dao;

import com.dt.common.PageQuery;
import com.dt.vo.UserNewsPlVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNewsPlDAO extends BaseDAO<UserNewsPlVo>{
    List<UserNewsPlVo> findUserNewsPlList(@Param("pageQuery") PageQuery pageQuery, @Param("userNewsPlVo") UserNewsPlVo userNewsPlVo);
    Long findUserNewsPlListCount(@Param("pageQuery") PageQuery pageQuery, @Param("userNewsPlVo") UserNewsPlVo userNewsPlVo);
}
