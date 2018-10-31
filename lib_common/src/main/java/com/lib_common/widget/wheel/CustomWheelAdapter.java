package com.lib_common.widget.wheel;


import com.contrarywind.adapter.WheelAdapter;
import com.lib_common.entity.CardBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Peng YanMing on 2018\9\4 0004
 */
public class CustomWheelAdapter implements WheelAdapter<CardBean> {
    List<CardBean> beans=new ArrayList<>();
    public CustomWheelAdapter(List<CardBean> cardBeans) {
        beans=cardBeans;
    }

    @Override
    public int getItemsCount() {
        return beans.size();
    }

    @Override
    public CardBean getItem(int index) {
        return beans.get(index);
    }

    @Override
    public int indexOf(CardBean o) {
        return o.getId();
    }
}
