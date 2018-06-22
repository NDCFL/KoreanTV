package com.dt.service;

import org.dozer.Mapper;

import javax.annotation.Resource;

/**
 * 基础Service<br />
 * 创建于2017-09-11
 *
 * @author 陈飞龙
 * @version 1.0
 */
public abstract class AbstractBaseService {

    private Mapper dozerMapper;

    public Mapper getDozerMapper() {
        return dozerMapper;
    }

    @Resource
    public void setDozerMapper(Mapper dozerMapper) {
        this.dozerMapper = dozerMapper;
    }
}
