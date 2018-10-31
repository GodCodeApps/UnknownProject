package com.lib_common.entity;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/27  11:54
 * @description  序列化map供Bundle传递map使用
 */
public class SerializableMap implements Serializable {

    private HashMap<String,Object> map;

    public HashMap<String, Object> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Object> map) {
        this.map = map;
    }
}
