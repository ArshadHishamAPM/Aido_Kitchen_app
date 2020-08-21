package com.rathore.kitchen.Utils;

/**
 * Created by Ravi Archi on 12/30/2016.
 */
public class StoreNameUtility {
    public int id;
    public String storename;

    public StoreNameUtility(String storeName) {
        this.storename = storeName;
    }

    public StoreNameUtility() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename)
    {
        this.storename = storename;
    }

    @Override
    public String toString() {
        return  storename;
    }
}
