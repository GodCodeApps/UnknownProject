package com.lib_common.entity;

import java.util.List;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/27  17:45
 * @description
 */
public class BasePageBean<T> {
    private List<T> lists;
    private int totals;

    public List<T> getLists() {
        return lists;
    }

    public void setLists(List<T> lists) {
        this.lists = lists;
    }

    public int getTotals() {
        return totals;
    }

    public void setTotals(int totals) {
        this.totals = totals;
    }
}
