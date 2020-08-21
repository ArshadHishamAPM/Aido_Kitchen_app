package com.rathore.kitchen.Model;

/**
 * Created by archi on 1/19/2017.
 */

public class WishListModel {

    private int id;
    private String productid;
    private String wlistname;
    private String wlistimg;

    public WishListModel(String drinkid, String wlistname, String wlistimg) {
        this.productid = drinkid;
        this.wlistname = wlistname;
        this.wlistimg = wlistimg;
    }


    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getWlistname() {
        return wlistname;
    }

    public void setWlistname(String wlistname) {
        this.wlistname = wlistname;
    }

    public String getWlistimg() {
        return wlistimg;
    }

    public void setWlistimg(String wlistimg) {
        this.wlistimg = wlistimg;
    }






}
