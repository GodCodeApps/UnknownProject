package com.lib_common.bus;

/**
 * author hechao
 * date 2018/9/13 0013
 */
public class BusEvent {

    public int what;
    public Object data;

    public BusEvent(int what) {
        this.what = what;
    }

    public BusEvent(int what, Object data) {
        this.what = what;
        this.data = data;
    }

    @Override
    public String toString() {
        return "BusEvent{" +
                "what=" + what +
                ", data=" + data +
                '}';
    }
}
