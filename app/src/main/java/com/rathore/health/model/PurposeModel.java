package com.rathore.health.model;

/**
 * Created by archi on 12/27/2016.
 */

public class PurposeModel {
    String strTitle,purpose,warning,keepAwayFromchild,dosage,sideEffect,strDescription;

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getWarnning() {
        return warning;
    }

    public void setWarnning(String warning) {
        this.warning = warning;
    }

    public String getKeepAwayFromchild() {
        return keepAwayFromchild;
    }

    public void setKeepAwayFromchild(String keepAwayFromchild) {
        this.keepAwayFromchild = keepAwayFromchild;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getSideEffect() {
        return sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }
}
