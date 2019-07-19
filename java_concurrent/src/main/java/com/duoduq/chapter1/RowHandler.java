package com.duoduq.chapter1;

import java.sql.ResultSet;

/**
 * @Auther: Jack
 * @Date: 2019-07-19 16:25
 * @Description:策略模式在Thread的应用
 */
public interface RowHandler<T> {
    T handle(ResultSet rs);
}
