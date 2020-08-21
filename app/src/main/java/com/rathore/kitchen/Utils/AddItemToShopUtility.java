package com.rathore.kitchen.Utils;

/**
 * Created by Ravi Archi on 1/2/2017.
 */
public class AddItemToShopUtility {

    public String itemname,price,weight,selectitem;
    public String imagePath;
    public int id;


    public AddItemToShopUtility() {

    }

    public AddItemToShopUtility(String selectitem, String itemName, String price, String weight, String imagePath) {


        this.selectitem = selectitem;
        this.itemname = itemName;
        this.price = price;
        this.weight = weight;
        this.imagePath = imagePath;
    }

    public String getSelectitem() {
        return selectitem;
    }

    public void setSelectitem(String selectitem) {
        this.selectitem = selectitem;
    }



    public String getImageinByte() {
        return imagePath;
    }

    public void setImageinByte(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setID(int ID) {
        this.id = ID;
    }
}
