package com.rathore.health.model;

/**
 * Created by archi on 1/3/2017.
 */

public class IllnessData


{
    boolean isSelected;
    String strId,strName,strCategory,sexFilter;
    public String getStrId() {
        return strId;
    }
    public void setStrId(String strId) {
        this.strId = strId;
    }
    public String getStrName() {
        return strName;
    }
    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getSexFilter() {
        return sexFilter;
    }

    public void setSexFilter(String sexFilter) {
        this.sexFilter = sexFilter;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
