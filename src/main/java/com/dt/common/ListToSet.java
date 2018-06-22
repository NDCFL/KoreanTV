package com.dt.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/29.
 */
public class ListToSet {
    public static <T>Set<T> listToSet(List<T> list){
        Set<T> set = new HashSet <T>();
        for(T t:list){
            set.add(t);
        }
        return set;
    }
}
