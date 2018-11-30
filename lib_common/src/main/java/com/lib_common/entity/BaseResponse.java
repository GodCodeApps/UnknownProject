package com.lib_common.entity;

import java.util.List;

public class BaseResponse<T> {
    private List<T> results;

    public List<T> getResults() {
        return results;
    }
}
