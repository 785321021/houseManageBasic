package com.coins.utils;

import com.github.pagehelper.PageInfo;

import java.util.Collection;
import java.util.List;

public class ObjectConvertUtil {
    /**
     * Pojo 类型转换（浅复制，字段名&类型相同则被复制）
     *
     * @param source
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T convert(Object source, Class<T> targetClass) {
        return BeanCopierConvertUtil.convert(source, targetClass);
    }

    /**
     * Pojo List 类型转换（浅复制，字段名&类型相同则被复制）
     * @param source
     * @param targetClass
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> List<T> convertList(Collection<F> source, Class<T> targetClass) {
        return BeanCopierConvertUtil.convertList(source, targetClass);
    }

    /**
     * Pojo PageInfo 类型转换（浅复制，字段名&类型相同则被复制）
     *
     * @param source
     * @param targetClass
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> PageInfo<T> convertPageInfo(PageInfo<F> source, Class<T> targetClass) {
        return BeanCopierConvertUtil.convertPageInfo(source, targetClass);
    }

}
