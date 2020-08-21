package com.rathore.kitchen.Utils;

/**
 * Created by Ravi Archi on 12/27/2016.
 */
public class DietItemsUtility {
    public String calories, title, ingredients1, ingredients2, ingredients3, ingredients4, ingredients5, ingredients6, ingredients7, ingredients8, ingredients9, ingredients10,
            carbohydrates, fat, saturatedfat, protein, fiber, sugars, sodium, note,selectMealType,sDate,mCurrentTime;
    public byte[]bytearray;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int id;

    public DietItemsUtility(String calories, String title, String ingredients1, String ingredients2, String ingredients3, String ingredients4, String ingredients5,
                            String ingredients6, String ingredients7, String ingredients8, String ingredients9,
                            String ingredients10, String carbohydrates, String fat, String saturatedfat, String protein,
                            String fiber, String sugars, String sodium, String note, String selectMealType, String sDate,
                            String mCurrentTime, byte[] byteArray) {

        this.calories = calories;
        this.title = title;
        this.ingredients1 = ingredients1;
        this.ingredients2 = ingredients2;
        this.ingredients3 = ingredients3;
        this.ingredients4 = ingredients4;
        this.ingredients5 = ingredients5;
        this.ingredients6 = ingredients6;
        this.ingredients7 = ingredients7;
        this.ingredients8 = ingredients8;
        this.ingredients9 = ingredients9;
        this.ingredients10 = ingredients10;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.saturatedfat = saturatedfat;
        this.protein = protein;
        this.fiber = fiber;
        this.sugars = sugars;
        this.sodium = sodium;
        this.note = note;
        this.selectMealType = selectMealType;
        this.sDate = sDate;
        this.mCurrentTime = mCurrentTime;
        this.bytearray =byteArray;
    }

    public DietItemsUtility() {

    }

    public DietItemsUtility(int id, String calories, String title, String ingredients1, String ingredients2, String ingredients3, String ingredients4,
                            String ingredients5, String ingredients6, String ingredients7, String ingredients8, String ingredients9, String ingredients10,
                            String carbohydrates, String fat, String saturatedfat, String protein, String fiber, String sugars,
                            String sodium, String note, String selectMealType, String sDate, String mCurrentTime, byte[] byteArray) {
       this.id = id;
        this.calories = calories;
        this.title = title;
        this.ingredients1 = ingredients1;
        this.ingredients2 = ingredients2;
        this.ingredients3 = ingredients3;
        this.ingredients4 = ingredients4;
        this.ingredients5 = ingredients5;
        this.ingredients6 = ingredients6;
        this.ingredients7 = ingredients7;
        this.ingredients8 = ingredients8;
        this.ingredients9 = ingredients9;
        this.ingredients10 = ingredients10;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.saturatedfat = saturatedfat;
        this.protein = protein;
        this.fiber = fiber;
        this.sugars = sugars;
        this.sodium = sodium;
        this.note = note;
        this.selectMealType = selectMealType;
        this.sDate = sDate;
        this.mCurrentTime = mCurrentTime;
        this.bytearray =byteArray;

    }


    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getIngredients1() {
        return ingredients1;
    }

    public void setIngredients1(String ingredients1) {
        this.ingredients1 = ingredients1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredients2() {
        return ingredients2;
    }

    public void setIngredients2(String ingredients2) {
        this.ingredients2 = ingredients2;
    }

    public String getIngredients3() {
        return ingredients3;
    }

    public void setIngredients3(String ingredients3) {
        this.ingredients3 = ingredients3;
    }

    public String getIngredients4() {
        return ingredients4;
    }

    public void setIngredients4(String ingredients4) {
        this.ingredients4 = ingredients4;
    }

    public String getIngredients5() {
        return ingredients5;
    }

    public void setIngredients5(String ingredients5) {
        this.ingredients5 = ingredients5;
    }

    public String getIngredients6() {
        return ingredients6;
    }

    public void setIngredients6(String ingredients6) {
        this.ingredients6 = ingredients6;
    }

    public String getIngredients7() {
        return ingredients7;
    }

    public void setIngredients7(String ingredients7) {
        this.ingredients7 = ingredients7;
    }

    public String getIngredients8() {
        return ingredients8;
    }

    public void setIngredients8(String ingredients8) {
        this.ingredients8 = ingredients8;
    }

    public String getIngredients9() {
        return ingredients9;
    }

    public void setIngredients9(String ingredients9) {
        this.ingredients9 = ingredients9;
    }

    public String getIngredients10() {
        return ingredients10;
    }

    public void setIngredients10(String ingredients10) {
        this.ingredients10 = ingredients10;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getSaturatedfat() {
        return saturatedfat;
    }

    public void setSaturatedfat(String saturatedfat) {
        this.saturatedfat = saturatedfat;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFiber() {
        return fiber;
    }

    public void setFiber(String fiber) {
        this.fiber = fiber;
    }

    public String getSugars() {
        return sugars;
    }

    public void setSugars(String sugars) {
        this.sugars = sugars;
    }

    public String getSodium() {
        return sodium;
    }

    public void setSodium(String sodium) {
        this.sodium = sodium;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSelectMealType() {
        return selectMealType;
    }

    public void setSelectMealType(String selectMealType) {
        this.selectMealType = selectMealType;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String getmCurrentTime() {
        return mCurrentTime;
    }

    public void setmCurrentTime(String mCurrentTime) {
        this.mCurrentTime = mCurrentTime;
    }

    public byte[] getBytearray() {
        return bytearray;
    }

    public void setBytearray(byte[] bytearray) {
        this.bytearray = bytearray;
    }




}
