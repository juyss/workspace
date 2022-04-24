package com.github.tangyi.common.core.utils;

import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.core.model.ResponseBean;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

public abstract class ResponseBeanUtils {

    private ResponseBeanUtils() {}

    public static <T> ResponseBean<T> queryOne(@Nullable T data) {
        if (data != null)
            return ResponseBean.success(data);
        else
            return ResponseBean.notFound().build();
    }

    public static <T extends Collection<E>, E> ResponseBean<T> queryMany(@Nullable T data) {
        if (!CollectionUtils.isEmpty(data))
            return ResponseBean.success(data);
        else
            return ResponseBean.notFound().build();
    }

    public static <T extends PageInfo<E>, E> ResponseBean<T> queryPageInfo(@Nullable T data) {
        if (data != null) {
            if (!CollectionUtils.isEmpty(data.getList()))
                return ResponseBean.success(data);
            else
                return ResponseBean.empty().data(data);
        } else {
            return ResponseBean.notFound().build();
        }
    }

    public static <T extends Page<E>, E> ResponseBean<T> queryPage(@Nullable T data) {
        if (data != null) {
            if (!CollectionUtils.isEmpty(data.getContent()))
                return ResponseBean.success(data);
            else
                return ResponseBean.empty().data(data);
        } else {
            return ResponseBean.notFound().build();
        }
    }

    public static <ID> ResponseBean<ID> addNewData(@Nullable ID newId) {
        return newId != null
                ? ResponseBean.success(newId)
                : ResponseBean.error().build();
    }

    public static ResponseBean<Boolean> updateData(@Nullable Integer rows) {
        return rows != null && rows > 0
                ? ResponseBean.success(Boolean.TRUE)
                : ResponseBean.fail().build();
    }

    public static ResponseBean<Boolean> deleteData(@Nullable Integer rows) {
        return updateData(rows);
    }

    public static ResponseBean<Boolean> operate(@Nullable Boolean operateResult) {
        if (Boolean.TRUE.equals(operateResult))
            return ResponseBean.success(Boolean.TRUE);
        else
            return ResponseBean.fail().data(Boolean.FALSE);
    }
}
