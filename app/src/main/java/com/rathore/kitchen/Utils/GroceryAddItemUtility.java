package com.rathore.kitchen.Utils;

/**
 * Created by archirayan on 3/1/17.
 */
public class GroceryAddItemUtility {
    public String itemname,itemprice,itemwieght,grocery,g_itemname,g_itemprice,g_itemweight,itemselected;
    public byte[] itemproductimage;
    public byte[]g_itemppic;
    public int id;

    public GroceryAddItemUtility() {

    }

    public String getItemselected() {
        return itemselected;
    }

    public void setItemselected(String itemselected) {
        this.itemselected = itemselected;
    }

    public GroceryAddItemUtility(String itemselected , String itemname, String itemprice, String itemwieght, byte[] itemproductimage) {

        this.itemselected = itemselected;
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.itemwieght = itemwieght;
        this.grocery = grocery;
        this.itemproductimage = itemproductimage;
    }



    public String getG_itemname() {
        return g_itemname;
    }

    public void setG_itemname(String g_itemname) {
        this.g_itemname = g_itemname;
    }

    public String getG_itemprice() {
        return g_itemprice;
    }

    public void setG_itemprice(String g_itemprice) {
        this.g_itemprice = g_itemprice;
    }

    public String getG_itemweight() {
        return g_itemweight;
    }

    public void setG_itemweight(String g_itemweight) {
        this.g_itemweight = g_itemweight;
    }

    public byte[] getG_itemppic() {
        return g_itemppic;
    }

    public void setG_itemppic(byte[] g_itemppic) {
        this.g_itemppic = g_itemppic;
    }

    public String getGrocery() {
        return grocery;
    }

    public void setGrocery(String grocery) {
        this.grocery = grocery;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getItemwieght() {
        return itemwieght;
    }

    public void setItemwieght(String itemwieght) {
        this.itemwieght = itemwieght;
    }

    public byte[] getItemproductimage() {
        return itemproductimage;
    }

    public void setItemproductimage(byte[] itemproductimage) {
        this.itemproductimage = itemproductimage;
    }

    public void setID(int ID) {
        this.id = ID;
    }
}
