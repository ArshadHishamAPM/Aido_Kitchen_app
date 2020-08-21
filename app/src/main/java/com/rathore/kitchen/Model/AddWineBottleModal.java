package com.rathore.kitchen.Model;

/**
 * Created by Ravi Archi on 1/10/2017.
 */
public class AddWineBottleModal {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int id;
   public byte[] ByteImage;
    public String strivintage,strwinery,strwinename,strtype,strsize,strstartdate,strenddate,strpaidbottle,
            strstorelocation,strstorewherepurchased,strdate,strEntertestingnotes,strEntertestin;
    public AddWineBottleModal(){}
    public AddWineBottleModal(int id, byte[] byteArrayImage, String strvintage, String strwinery, String strwinename,
                              String strtype, String strsize, String strstartdate, String strenddate, String strpaidbottle,
                              String strstorelocation, String strstorewherepurchased, String strdate,
                              String strEntertestingnotes, String strEntertestin) {
        this.id=id;
        this.ByteImage =  byteArrayImage;
       this.strwinename = strwinename;
        this.strwinery = strwinery;
        this.strivintage = strvintage;
        this.strtype = strtype;
        this.strsize = strsize;
        this.strstartdate = strstartdate;
        this.strenddate = strenddate;
        this.strpaidbottle = strpaidbottle;
        this.strstorelocation = strstorelocation;
        this.strstorewherepurchased = strstorewherepurchased;
        this.strdate = strdate;
        this.strEntertestingnotes = strEntertestingnotes;
        this.strEntertestin = strEntertestin;
   }

    public byte[] getByteImage() {
        return ByteImage;
    }

    public void setByteImage(byte[] byteArrayImage)
    {
        this.ByteImage = byteArrayImage;
    }

    public String getStrivintage() {
        return strivintage;
    }

    public void setStrivintage(String strivintage)
    {
        this.strivintage = strivintage;
    }

    public String getStrwinery() {
        return strwinery;
    }

    public void setStrwinery(String strwinery) {
        this.strwinery = strwinery;
    }

    public String getStrwinename() {
        return strwinename;
    }

    public void setStrwinename(String strwinename) {
        this.strwinename = strwinename;
    }

    public String getStrtype() {
        return strtype;
    }

    public void setStrtype(String strtype) {
        this.strtype = strtype;
    }

    public String getStrsize() {
        return strsize;
    }

    public void setStrsize(String strsize) {
        this.strsize = strsize;
    }

    public String getStrstartdate() {
        return strstartdate;
    }

    public void setStrstartdate(String strstartdate) {
        this.strstartdate = strstartdate;
    }

    public String getStrenddate() {
        return strenddate;
    }

    public void setStrenddate(String strenddate) {
        this.strenddate = strenddate;
    }

    public String getStrpaidbottle() {
        return strpaidbottle;
    }

    public void setStrpaidbottle(String strpaidbottle) {
        this.strpaidbottle = strpaidbottle;
    }

    public String getStrstorelocation() {
        return strstorelocation;
    }

    public void setStrstorelocation(String strstorelocation) {
        this.strstorelocation = strstorelocation;
    }

    public String getStrstorewherepurchased() {
        return strstorewherepurchased;
    }

    public void setStrstorewherepurchased(String strstorewherepurchased) {
        this.strstorewherepurchased = strstorewherepurchased;
    }

    public String getStrdate() {
        return strdate;
    }

    public void setStrdate(String strdate) {
        this.strdate = strdate;
    }

    public String getStrEntertestingnotes() {
        return strEntertestingnotes;
    }

    public void setStrEntertestingnotes(String strEntertestingnotes) {
        this.strEntertestingnotes = strEntertestingnotes;
    }

    public String getStrEntertestin() {
        return strEntertestin;
    }

    public void setStrEntertestin(String strEntertestin) {
        this.strEntertestin = strEntertestin;
    }


}
