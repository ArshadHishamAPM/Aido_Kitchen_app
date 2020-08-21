package com.rathore.kitchen.Model;

import java.io.Serializable;

/**
 * Created by archirayan on 29/12/16.
 */

public class BookmarkRecipesModel implements Serializable {

    String title, ingredient, image, orignalurl, rank, id;





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getOrignalurl() {
        return orignalurl;
    }

    public void setOrignalurl(String orignalurl) {
        this.orignalurl = orignalurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
