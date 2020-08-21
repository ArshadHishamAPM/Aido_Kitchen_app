package com.rathore.kitchen.Model;

/**
 * Created by Ravi Archi on 1/4/2017.
 */
public class BarTanderModal
{
    public String strdrink,strdrinkthub,drinkid;
    public BarTanderModal(String strdrink, String strdrinkthumb, String drinkid) {

        this.strdrink = strdrink;
        this.strdrinkthub = strdrinkthumb;
        this.drinkid = drinkid;
    }

    public String getStrdrink() {
        return strdrink;
    }

    public void setStrdrink(String strdrink) {
        this.strdrink = strdrink;
    }

    public String getStrdrinkthub() {
        return strdrinkthub;
    }

    public void setStrdrinkthub(String strdrinkthub) {
        this.strdrinkthub = strdrinkthub;
    }

    public String getDrinkid() {
        return drinkid;
    }

    public void setDrinkid(String drinkid) {
        this.drinkid = drinkid;
    }
}
