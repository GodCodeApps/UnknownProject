package com.lib_common.entity;


import com.contrarywind.interfaces.IPickerViewData;

/**
 *
 *@author ZhongWeiZhi
 *
 *@date 2018/8/31 20:15
 *
 *@description
 */

public class CardBean implements IPickerViewData {
    int id;
    String cardName;
    int price;
    public CardBean(int id, String cardName) {
        this.id = id;
        this.cardName = cardName;
    }

    public CardBean(int id, String cardName, int price) {
        this.id = id;
        this.cardName = cardName;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    @Override
    public String getPickerViewText() {
        return cardName;
    }
}

