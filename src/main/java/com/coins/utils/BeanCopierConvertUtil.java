package com.coins.utils;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//import lombok.extern.slf4j.Slf4j;

/**
 * Created by seba on 17/3/15.
 */
//@Slf4j
public class BeanCopierConvertUtil{
    private static Logger log = LoggerFactory.getLogger(BeanCopierConvertUtil.class);
    private static Lock initLock = new ReentrantLock();

    private static Map<String, BeanCopier> beanCopierMap = new HashMap<String, BeanCopier>();
    private static WddConverter wddConverter = new WddConverter();

    /**
     * 初始化 BeanCopier
     *
     * @param source
     * @param target
     * @return
     */
    private static BeanCopier initCopier(String key, Class source, Class target, boolean useConverter) {
        initLock.lock();
        BeanCopier find = beanCopierMap.get(key);
        if (find != null) {
            initLock.unlock();
            return find;
        }
        BeanCopier beanCopier = BeanCopier.create(source, target, useConverter);
        beanCopierMap.put(key, beanCopier);
        initLock.unlock();
        return beanCopier;
    }

    private static String getKey(Class source, Class target, boolean useConverter) {
        return Joiner.on("_").join(source.getName(),target.getName(),(useConverter ? 1 : 0));
    }

    /**
     * 获取BeanCopier
     *
     * @param source
     * @param target
     * @return
     */
    private static BeanCopier getBeanCopier(Class source, Class target, boolean useConverter) {

        String key = getKey(source, target, useConverter);
        BeanCopier beanCopier = beanCopierMap.get(key);
        if (beanCopier != null) {
            return beanCopier;
        }
        return initCopier(key, source, target, useConverter);
    }


    /**
     * Pojo 类型转换（浅复制，字段名&类型相同则被复制）
     *
     * @param source
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T convert(Object source, Class<T> targetClass) {
        return convert(source, targetClass, false);
    }

    /**
     * Pojo 类型转换（浅复制，字段名&类型相同则被复制）
     *
     * @param source
     * @param targetClass
     * @param useConverter 是否使用转换器
     * @param <T>
     * @return
     */
    public static <T> T convert(Object source, Class<T> targetClass, boolean useConverter) {
        if (source == null) {
            return null;
        }
        BeanCopier beanCopier = getBeanCopier(source.getClass(), targetClass, useConverter);
        try {
            T target = targetClass.newInstance();

            beanCopier.copy(source, target, (useConverter ? wddConverter : null));
            return target;

        } catch (Exception e) {
            log.error("对象拷贝失败,{}", e);
            throw new RuntimeException("对象拷贝失败" + source + "_" + targetClass);
        }
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
        return convertList(source, targetClass, false);
    }

    /**
     * Pojo List 类型转换（浅复制，字段名&类型相同则被复制）
     *
     * @param source
     * @param targetClass
     * @param useConverter
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> List<T> convertList(final Collection<F> source, Class<T> targetClass, boolean useConverter) {
        if (source == null) {
            return null;
        }
        try {
            List<T> result = Lists.newArrayList();
            if (source.isEmpty()) {
                return result;
            }

            for (Object each : source) {
                result.add(convert(each, targetClass, useConverter));
            }
            return result;
        } catch (Exception e) {
            log.error("对象拷贝失败,{}", e);
            throw new RuntimeException("对象拷贝失败" + source + "_" + targetClass);
        }
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
        return convertPageInfo(source, targetClass, false);
    }

    /**
     * Pojo PageInfo 类型转换（浅复制，字段名&类型相同则被复制）
     *
     * @param source
     * @param targetClass
     * @param useConverter
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> PageInfo<T> convertPageInfo(PageInfo<F> source, Class<T> targetClass, boolean useConverter) {
        if (source == null) {
            return null;
        }
        PageInfo<T> pageInfo = convert(source, PageInfo.class, useConverter);

        pageInfo.setList(convertList(source.getList(), targetClass, useConverter));

        return pageInfo;
    }

    static class WddConverter implements Converter {


        @SuppressWarnings("rawtypes")
        @Override
        public Object convert(Object value, Class target, Object context) {
            if (value instanceof Boolean) {
                return (Boolean) value;
            } else if (value instanceof Character) {
                return (Character) value;
            } else if (value instanceof Byte) {
                return (Byte) value;
            } else if (value instanceof Short) {
                return (Short) value;
            } else if (value instanceof Integer) {
                return (Integer) value;
            } else if (value instanceof Long) {
                return (Long) value;
            } else if (value instanceof Float) {
                return (Float) value;
            } else if (value instanceof Double) {
                return (Double) value;
            }
            return value;
        }

    }
}



