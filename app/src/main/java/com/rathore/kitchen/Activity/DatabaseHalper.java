package com.rathore.kitchen.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rathore.kitchen.Model.WishListModel;
import com.rathore.kitchen.Utils.AddItemToShopUtility;
import com.rathore.kitchen.Utils.DietItemsUtility;
import com.rathore.kitchen.Utils.GroceryAddItemUtility;
import com.rathore.kitchen.Utils.StoreNameUtility;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Ravi Archi on 12/27/2016.
 */
public class DatabaseHalper extends SQLiteOpenHelper {
    RequestQueue queue;
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "DietWhiz";
    // Contacts table name
    private static final String TABLE_CONTACTS = "DietItem";

    // Contacts Table Columns names
    private static byte[] arraybyte;
    private static final String KEY_ID = "id";

    private static final String CALORIES = "calories";
    private static final String TITLE = "title";
    private static final String INGREDIENTS1 = "ingredients1";
    private static final String INGREDIENTS2 = "ingredients2";
    private static final String INGREDIENTS3 = "ingredients3";
    private static final String INGREDIENTS4 = "ingredients4";
    private static final String INGREDIENTS5 = "ingredients5";
    private static final String INGREDIENTS6 = "ingredients6";
    private static final String INGREDIENTS7 = "ingredients7";
    private static final String INGREDIENTS8 = "ingredients8";
    private static final String INGREDIENTS9 = "ingredients9";
    private static final String INGREDIENTS10 = "ingredients10";
    private static final String CARBOHYDRATES = "carbohydrates";
    private static final String FAT = "fat";
    private static final String SATURATEDFAT = "saturatedfat";
    private static final String PROTEIN = "protein";
    private static final String FIBER = "fiber";
    private static final String SUGARS = "sugars";
    private static final String SODIUM = "sodium";
    private static final String NOTE = "note";
    private static final String MEALTYPE = "selectMealType";
    private static final String SDATE = "sDate";
    private static final String MTIME = "mCurrentTime";
    private static final String IMAGE = "image";
    //storenameList
    private static final String TABLE_STORENAME = "StorenameList";
    private static final String STORENAME_ID = "s_id";
    private static final String STORENAME = "storename";
    //add item to shop

    private static final String TABLE_SHOPITEM = "ShopAddItem";
    private static final String SHOPITEM_ID = "sI_id";
    private static final String ITEMIMAGE = "ItemImage";
    private static final String ITEMNAME = "ItemName";
    private static final String ITEMSELECT = "Itemselect";
    private static final String ITEMPRICE = "ItemPrice";
    private static final String ITEMWEIGHT = "ItemWeight";

    //additemto grocery

    private static final String TABLE_ADDITEMGROCERY = "AddItemGrocery";
    private static final String ADDGROCERYITEM_ID = "gi_id";
    private static final String ADDGROCERY = "Grocery";
    private static final String ADDGROCERYITEMIMAGE = "ItemImage";
    private static final String ADDGROCERYITEMNAME = "ItemName";
    private static final String ADDGROCERYITEMPRICE = "ItemPrice";
    private static final String ADDGROCERYITEMWEIGHT = "ItemWeight";

    // add to cart table

    private static final String TABLE_ADDTOCART = "AddItemCard";

    private static final String CARTTABLEID = "carttbl_id";
    private static final String PRODUCTID = "product_id";
    private static final String ADDCARDIMG = "card_img";
    private static final String ADDCARDNAME = "card_name";


    private DatabaseHalper mDbHelper;
    private SQLiteDatabase mDb;
    public Context context;
    String drinkid, img, name;

    public DatabaseHalper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + CALORIES + " TEXT,"
                + TITLE + " TEXT,"
                + INGREDIENTS1 + " TEXT,"
                + INGREDIENTS2 + " TEXT,"
                + INGREDIENTS3 + " TEXT,"
                + INGREDIENTS4 + " TEXT,"
                + INGREDIENTS5 + " TEXT,"
                + INGREDIENTS6 + " TEXT,"
                + INGREDIENTS7 + " TEXT,"
                + INGREDIENTS8 + " TEXT,"
                + INGREDIENTS9 + " TEXT,"
                + INGREDIENTS10 + " TEXT,"
                + CARBOHYDRATES + " TEXT,"
                + FAT + " TEXT,"
                + SATURATEDFAT + " TEXT,"
                + PROTEIN + " TEXT,"
                + FIBER + " TEXT,"
                + SUGARS + " TEXT,"
                + SODIUM + " TEXT,"
                + NOTE + " TEXT,"
                + MEALTYPE + " TEXT,"
                + SDATE + " TEXT,"
                + MTIME + " TEXT,"
                + IMAGE + " BLOB NOT NULL" + ")";

        String CREATE_ADDITEMTOSHOP_TABLE = "CREATE TABLE " + TABLE_SHOPITEM + "("
                + SHOPITEM_ID + " INTEGER PRIMARY KEY,"
                + ITEMNAME + " TEXT,"
                + ITEMSELECT + " TEXT,"
                + ITEMPRICE + " TEXT,"
                + ITEMWEIGHT + " TEXT,"
                + ITEMIMAGE + " BLOB  " + ")";

        String CREATE_GROCERYADDITEM = "CREATE TABLE " + TABLE_ADDITEMGROCERY + "("
                + ADDGROCERYITEM_ID + " INTEGER PRIMARY KEY,"
                + ADDGROCERY + " TEXT,"
                + ADDGROCERYITEMNAME + " TEXT,"
                + ADDGROCERYITEMPRICE + " TEXT,"
                + ADDGROCERYITEMWEIGHT + " TEXT,"
                + ADDGROCERYITEMIMAGE + " BLOB NOT NULL " + ")";


        String CREATE_STORENAME_TABLE = "CREATE TABLE " + TABLE_STORENAME + "("
                + STORENAME_ID + " INTEGER PRIMARY KEY,"
                + STORENAME + " TEXT" + ")";


        String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_ADDTOCART + "("
                + CARTTABLEID + " INTEGER PRIMARY KEY,"
                + PRODUCTID + " TEXT ,"
                + ADDCARDIMG + " TEXT ,"
                + ADDCARDNAME + " TEXT" + ")";

        // Log.d("msg",CREATE_GROCERYADDITEM.toString());

        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_STORENAME_TABLE);
        db.execSQL(CREATE_ADDITEMTOSHOP_TABLE);
        db.execSQL(CREATE_GROCERYADDITEM);
        db.execSQL(CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORENAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDITEMGROCERY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDTOCART);

        onCreate(db);
    }

    public DatabaseHalper open() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void InsertData(DietItemsUtility dietItemsUtility) {
//        String ing1;
//        if(dietItemsUtility.getIngredients1().length()>0){
//            ing1 =dietItemsUtility.getIngredients1();
//        }else {
//            // ing1 =dietItemsUtility.getIngredients1();
//            ing1="null";
//        }
//        String ing2;
//        if(dietItemsUtility.getIngredients2().length()>0){
//ing2 = dietItemsUtility.getIngredients2();
//        }else {
//             //ing2 =dietItemsUtility.getIngredients2();
//            ing2="null";
//        }
//       String ing3 ;
//        if(dietItemsUtility.getIngredients3().length()>0){
//       ing3 = dietItemsUtility.getIngredients3();
//        }else {
//             //ing3 =dietItemsUtility.getIngredients3();
//            ing3="null";
//        }
//        String ing4 ;
//        if(dietItemsUtility.getIngredients4().length()>0){
//ing4 = dietItemsUtility.getIngredients4();
//        }else {
//            // ing4 =dietItemsUtility.getIngredients4();
//            ing4="null";
//        }
//        String ing5;
//        if(dietItemsUtility.getIngredients5().length()>0){
//ing5 =dietItemsUtility.getIngredients5();
//        }else {
//             //ing5 =dietItemsUtility.getIngredients5();
//            ing5="null";
//        }
//        String ing6 ="";
//        if(dietItemsUtility.getIngredients6().length()>0){
//ing6 = dietItemsUtility.getIngredients6();
//        }else {
//           //  ing6 =dietItemsUtility.getIngredients6();
//            ing6="no";
//        }
//        String ing7 = "";
//        if(dietItemsUtility.getIngredients7().length()>0){
//             ing7 = dietItemsUtility.getIngredients7();
//        }else {
//             //ing7 =dietItemsUtility.getIngredients7();
//            ing7="no";
//        }
//        String ing8;
//        if(dietItemsUtility.getIngredients8().length()>0){
//ing8 = dietItemsUtility.getIngredients8();
//        }else {
//            // ing8 =dietItemsUtility.getIngredients8();
//            ing8="no";
//        }
//        String ing9;
//        if(dietItemsUtility.getIngredients9().length()>0){
//            ing9 = dietItemsUtility.getIngredients9();
//        }else {
//             //ing9 =dietItemsUtility.getIngredients9();
//            ing9="no";
//        }
//        String ing10;
//        if(dietItemsUtility.getIngredients10().length()>0){
//ing10 = dietItemsUtility.getIngredients10();
//        }else {
//             //ing10 =dietItemsUtility.getIngredients10();
//            ing10="no";
//        }
//        String carbo;
//        if(dietItemsUtility.getCarbohydrates().length()>0){
//            carbo = dietItemsUtility.getCarbohydrates();
//
//        }
//        else {
//            carbo = "null";
//        }
//        String fat;
//        if(dietItemsUtility.getFat().length()>0){
//            fat = dietItemsUtility.getFat();
//
//        }
//        else {
//            fat = "null";
//        }
//        String satfat;
//        if(dietItemsUtility.getSaturatedfat().length()>0){
//            satfat = dietItemsUtility.getSaturatedfat();
//
//        }
//        else {
//            satfat = "null";
//        }
//        String protine;
//        if(dietItemsUtility.getProtein().length()>0){
//            protine = dietItemsUtility.getProtein();
//
//        }
//        else {
//            protine = "null";
//        }
//        String fiber;
//        if(dietItemsUtility.getFiber().length()>0){
//            fiber = dietItemsUtility.getFiber();
//
//        }
//        else {
//            fiber = "null";
//        }
//        String sugar;
//        if(dietItemsUtility.getSugars().length()>0){
//            sugar = dietItemsUtility.getSugars();
//
//        }
//        else {
//            sugar = "null";
//        }
//        String sodium;
//        if(dietItemsUtility.getSodium().length()>0){
//            sodium = dietItemsUtility.getSodium();
//
//        }
//        else {
//            sodium = "null";
//        }
//        String note;
//        if(dietItemsUtility.getNote().length()>0){
//            note = dietItemsUtility.getNote();
//
//        }
//        else {
//            note = "null";
//        }
//        Log.i("valuessining",ing1+ing2+ing3+ing4+ing5+ing6+ing7+ing8+ing9+ing10);




//        String url = "http://10.10.10.1/ApiKitchen.php?apicall=createtablecontacts&CALORIES="+dietItemsUtility.getCalories()+"&TITLE="+dietItemsUtility.getTitle()+"&INGREDIENTS1="+dietItemsUtility.getIngredients1()+"&INGREDIENTS2="+dietItemsUtility.getIngredients2()+"&INGREDIENTS3="+dietItemsUtility.getIngredients3()+"&INGREDIENTS4="+dietItemsUtility.getIngredients4()+"&INGREDIENTS5="+dietItemsUtility.getIngredients5()+"&INGREDIENTS6="+dietItemsUtility.getIngredients6()+"&INGREDIENTS7="+dietItemsUtility.getIngredients7()+"&INGREDIENTS8="+dietItemsUtility.getIngredients8()+"&INGREDIENTS9="+dietItemsUtility.getIngredients9()+"&INGREDIENTS10="+dietItemsUtility.getIngredients10()+"&CARBOHYDRATES="+dietItemsUtility.getCarbohydrates()+"&FAT="+dietItemsUtility.getFat()+"&SATURATEDFAT="+dietItemsUtility.getSaturatedfat()+"&PROTEIN="+dietItemsUtility.getProtein()+"&FIBER="+dietItemsUtility.getFiber()+"&SUGARS="+dietItemsUtility.getSugars()+"&SODIUM="+dietItemsUtility.getSodium()+"&NOTE="+dietItemsUtility.getNote()+"&MEALTYPE="+dietItemsUtility.getSelectMealType()+"&SDATE="+dietItemsUtility.getsDate()+"&MTIME="+dietItemsUtility.getmCurrentTime()+"&IMAGE="+dietItemsUtility.getBytearray();//dietItemsUtility.getBytearray();
//        url=url.replaceAll(" ", "%20");


        String calories= null,title =null,ing1 = null,encodedStringprice = null,encodedStringweight = null;
          String ing2=null,ing3=null,ing4=null,ing5=null,ing6=null,ing7=null,ing8=null;
        String ing9=null,ing10=null,carbo=null,satfat=null,mtimee=null,mealtype=null,sodium=null,sdate=null,sugar=null,note=null,fat = null,fiber=null,image=null,protein=null;
        try {
            calories= URLEncoder.encode(dietItemsUtility.getCalories(),"UTF-8");
            title = URLEncoder.encode(dietItemsUtility.getTitle(),"UTF-8");
            ing1 = URLEncoder.encode(dietItemsUtility.getIngredients1(),"UTF-8");
            ing2 = URLEncoder.encode(dietItemsUtility.getIngredients2(),"UTF-8");
            ing3 = URLEncoder.encode(dietItemsUtility.getIngredients3(),"UTF-8");
            ing4 = URLEncoder.encode(dietItemsUtility.getIngredients4(),"UTF-8");
            ing5 = URLEncoder.encode(dietItemsUtility.getIngredients5(),"UTF-8");
            ing6 = URLEncoder.encode(dietItemsUtility.getIngredients6(),"UTF-8");
            ing7 = URLEncoder.encode(dietItemsUtility.getIngredients7(),"UTF-8");
            ing8 = URLEncoder.encode(dietItemsUtility.getIngredients8(),"UTF-8");
            ing9 = URLEncoder.encode(dietItemsUtility.getIngredients9(),"UTF-8");
            ing10 = URLEncoder.encode(dietItemsUtility.getIngredients10(),"UTF-8");
            carbo = URLEncoder.encode(dietItemsUtility.getCarbohydrates(),"UTF-8");
            fat = URLEncoder.encode(dietItemsUtility.getFat(),"UTF-8");
            fiber = URLEncoder.encode(dietItemsUtility.getFiber(),"UTF-8");
            satfat = URLEncoder.encode(dietItemsUtility.getSaturatedfat(),"UTF-8");
            mtimee = URLEncoder.encode(dietItemsUtility.getmCurrentTime(),"UTF-8");
            mealtype = URLEncoder.encode(dietItemsUtility.getSelectMealType(),"UTF-8");
            sugar = URLEncoder.encode(dietItemsUtility.getSugars(),"UTF-8");
            sodium = URLEncoder.encode(dietItemsUtility.getSodium(),"UTF-8");
            note = URLEncoder.encode(dietItemsUtility.getNote(),"UTF-8");
            image  = URLEncoder.encode(dietItemsUtility.getBytearray().toString(),"UTF-8");
            sdate  = URLEncoder.encode(dietItemsUtility.getsDate(),"UTF-8");
            protein  = URLEncoder.encode(dietItemsUtility.getProtein(),"UTF-8");



        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = "http://10.10.10.1/ApiKitchen.php?apicall=createtablecontacts&CALORIES="+calories+"&TITLE="+title+"&INGREDIENTS1="+ing1+"&INGREDIENTS2="+ing2+"&INGREDIENTS3="+ing3+"&INGREDIENTS4="+ing4+"&INGREDIENTS5="+ing5+"&INGREDIENTS6="+ing6+"&INGREDIENTS7="+ing7+"&INGREDIENTS8="+ing8+"&INGREDIENTS9="+ing9+"&INGREDIENTS10="+ing10+"&CARBOHYDRATES="+carbo+"&FAT="+fat+"&SATURATEDFAT="+satfat+"&PROTEIN="+protein+"&FIBER="+fiber+"&SUGARS="+sugar+"&SODIUM="+sodium+"&NOTE="+note+"&MEALTYPE="+mealtype+"&SDATE="+sdate+"&MTIME="+mtimee+"&IMAGE="+image;//dietItemsUtility.getBytearray();
        url=url.replaceAll(" ", "%20");

        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                error.printStackTrace();
                Log.i("volley error",error.toString());
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CALORIES, dietItemsUtility.getCalories());
        cv.put(TITLE, dietItemsUtility.getTitle());
        cv.put(INGREDIENTS1, dietItemsUtility.getIngredients1());
        cv.put(INGREDIENTS2, dietItemsUtility.getIngredients2());
        cv.put(INGREDIENTS3, dietItemsUtility.getIngredients3());
        cv.put(INGREDIENTS4, dietItemsUtility.getIngredients4());
        cv.put(INGREDIENTS5, dietItemsUtility.getIngredients5());
        cv.put(INGREDIENTS6, dietItemsUtility.getIngredients6());
        cv.put(INGREDIENTS7, dietItemsUtility.getIngredients7());
        cv.put(INGREDIENTS8, dietItemsUtility.getIngredients8());
        cv.put(INGREDIENTS9, dietItemsUtility.getIngredients9());
        cv.put(INGREDIENTS10, dietItemsUtility.getIngredients10());
        cv.put(CARBOHYDRATES, dietItemsUtility.getCarbohydrates());
        cv.put(FAT, dietItemsUtility.getFat());
        cv.put(SATURATEDFAT, dietItemsUtility.getSaturatedfat());
        cv.put(PROTEIN, dietItemsUtility.getProtein());
        cv.put(FIBER, dietItemsUtility.getFiber());
        cv.put(SUGARS, dietItemsUtility.getSugars());
        cv.put(SODIUM, dietItemsUtility.getSodium());
        cv.put(NOTE, dietItemsUtility.getNote());
        cv.put(MEALTYPE, dietItemsUtility.getSelectMealType());
        cv.put(SDATE, dietItemsUtility.getsDate());
        cv.put(MTIME, dietItemsUtility.getmCurrentTime());
        cv.put(IMAGE, dietItemsUtility.getBytearray());
        db.insert(TABLE_CONTACTS, null, cv);
        db.close();
    }

    public ArrayList<DietItemsUtility> getAllRecords() {
        ArrayList<DietItemsUtility> arraydata = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DietItemsUtility contact = new DietItemsUtility();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setCalories(cursor.getString(1));
                contact.setTitle(cursor.getString(2));
                contact.setIngredients1(cursor.getString(3));
                contact.setIngredients2(cursor.getString(4));
                contact.setIngredients3(cursor.getString(5));
                contact.setIngredients4(cursor.getString(6));
                contact.setIngredients5(cursor.getString(7));
                contact.setIngredients6(cursor.getString(8));
                contact.setIngredients7(cursor.getString(9));
                contact.setIngredients8(cursor.getString(10));
                contact.setIngredients9(cursor.getString(11));
                contact.setIngredients10(cursor.getString(12));
                contact.setCarbohydrates(cursor.getString(13));
                contact.setFat(cursor.getString(14));
                contact.setSaturatedfat(cursor.getString(15));
                contact.setProtein(cursor.getString(16));
                contact.setFiber(cursor.getString(17));
                contact.setSugars(cursor.getString(18));
                contact.setSodium(cursor.getString(19));
                contact.setNote(cursor.getString(20));
                contact.setSelectMealType(cursor.getString(21));
                contact.setsDate(cursor.getString(22));
                contact.setmCurrentTime(cursor.getString(23));
                contact.setBytearray(cursor.getBlob(24));
                // Adding contact to list
                arraydata.add(contact);
            } while (cursor.moveToNext());
        }
        return arraydata;
    }

    public DietItemsUtility getRecords(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                        CALORIES, TITLE, INGREDIENTS1, INGREDIENTS2, INGREDIENTS3, INGREDIENTS4, INGREDIENTS5, INGREDIENTS6, INGREDIENTS7, INGREDIENTS8, INGREDIENTS9,
                        INGREDIENTS10, CARBOHYDRATES, FAT, SATURATEDFAT, PROTEIN, FIBER, SUGARS, SODIUM, NOTE, MEALTYPE, SDATE, MTIME, IMAGE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        DietItemsUtility contact = new DietItemsUtility(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getString(9),
                cursor.getString(10),
                cursor.getString(11),
                cursor.getString(12),
                cursor.getString(13),
                cursor.getString(14),
                cursor.getString(15),
                cursor.getString(16),
                cursor.getString(17),
                cursor.getString(18),
                cursor.getString(19),
                cursor.getString(20),
                cursor.getString(21),
                cursor.getString(22),
                cursor.getString(23),
                cursor.getBlob(24));
        // return contact
        return contact;
    }

    public void InsetStoreName(StoreNameUtility storeName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STORENAME, storeName.getStorename());
        long status = db.insert(TABLE_STORENAME, null, cv);
        Log.e("storename", "" + cv);
        String url = "http://10.10.10.1/ApiKitchen.php?apicall=createstorenamelist&STORENAME="+storeName.getStorename();
        url=url.replaceAll(" ", "%20");

        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);

    }

    public void deleteStoreName(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_STORENAME + " WHERE " + STORENAME_ID + "='" + id + "'");
        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        db.close();
    }


    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CONTACTS + " WHERE " + KEY_ID + "='" + id + "'");
        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        db.close();
    }

    public ArrayList<StoreNameUtility> getAllStoreName() {
        ArrayList<StoreNameUtility> arraystorenamedata = new ArrayList<StoreNameUtility>();
        String selectQuery = "SELECT  * FROM " + TABLE_STORENAME;
        Log.e("DATA", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                StoreNameUtility storenameutility = new StoreNameUtility();
                storenameutility.setId(Integer.parseInt(cursor.getString(0)));
                Log.d("DATAID", "" + Integer.parseInt(cursor.getString(0)));
                storenameutility.setStorename(cursor.getString(1));
                arraystorenamedata.add(storenameutility);
            } while (cursor.moveToNext());
        }
        return arraystorenamedata;
    }

    public void AddItemToShop(AddItemToShopUtility addItemToShopUtility) {

//        String url = "http://10.10.10.1/ApiKitchen.php?apicall=createadditemtoshop&ITEMSELECT="+addItemToShopUtility.getSelectitem()+"&ITEMIMAGE="+addItemToShopUtility.getImageinByte()+"&ITEMNAME="+addItemToShopUtility.getItemname()+"&ITEMPRICE="+addItemToShopUtility.getPrice()+"&ITEMWEIGHT="+addItemToShopUtility.getWeight();

//
        String selecteditem1 = addItemToShopUtility.getSelectitem();
        String image1 = addItemToShopUtility.getImageinByte();
        String itemname1 = addItemToShopUtility.getItemname();
        String price1 = addItemToShopUtility.getPrice();
        String weight1 = addItemToShopUtility.getWeight();
        String encodedStringselected = null,encodedStringimage =null,encodedStringname = null,encodedStringprice = null,encodedStringweight = null;

        try {
           if(addItemToShopUtility.getSelectitem()!=null){
            encodedStringselected = URLEncoder.encode(selecteditem1,"UTF-8");}
            else {
               encodedStringselected=   addItemToShopUtility.getSelectitem();
           }

            if(image1!=null){
            encodedStringimage = URLEncoder.encode(image1,"UTF-8");}
            else{
                encodedStringimage=image1;
            }
            encodedStringname = URLEncoder.encode(itemname1,"UTF-8");
            encodedStringprice = URLEncoder.encode(price1,"UTF-8");
            encodedStringweight = URLEncoder.encode(weight1,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://10.10.10.1/ApiKitchen.php?apicall=createadditemtoshop&ITEMSELECT="+encodedStringselected+"&ITEMIMAGE="+encodedStringimage+"&ITEMNAME="+encodedStringname+"&ITEMPRICE="+encodedStringprice+"&ITEMWEIGHT="+encodedStringweight;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // String select=   addItemToShopUtility.getSelectitem();
        cv.put(ITEMSELECT, addItemToShopUtility.getSelectitem());

       // Log.e("SELECTED ITEM", addItemToShopUtility.getSelectitem());
        cv.put(ITEMNAME, addItemToShopUtility.getItemname());
        cv.put(ITEMPRICE, addItemToShopUtility.getPrice());
        cv.put(ITEMWEIGHT, addItemToShopUtility.getWeight());
        cv.put(ITEMIMAGE, addItemToShopUtility.getImageinByte());
        Long ststus = db.insert(TABLE_SHOPITEM, null, cv);

        Log.e("status", "" + "" + ststus);
        db.close();


    }

    public void deleteCartData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ADDTOCART + " WHERE " + CARTTABLEID + "='" + id + "'");
        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        db.close();
    }


    public void AddToCart(WishListModel addItemToCart) {
        int count = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("QUERY== ", "SELECT * FROM " + TABLE_ADDTOCART + " WHERE " + PRODUCTID + "='" + addItemToCart.getProductid() + "'");
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_ADDTOCART + " where " + PRODUCTID + " = " + addItemToCart.getProductid(), null);

        if (c.moveToFirst()) {
            do {
                count++;
            } while (c.moveToNext());
        }
        if (count == 0) {
            ContentValues cv = new ContentValues();
            cv.put(PRODUCTID, addItemToCart.getProductid());
            cv.put(ADDCARDIMG, addItemToCart.getWlistimg());
            cv.put(ADDCARDNAME, addItemToCart.getWlistname());
            Long ststus = db.insert(TABLE_ADDTOCART, null, cv);
            db.close();
            String url = "http://10.10.10.1/ApiKitchen.php?apicall=createaddtocart&PRODUCTID="+addItemToCart.getProductid()+"&ADDCARDIMG="+addItemToCart.getWlistimg()+"&ADDCARDNAME="+addItemToCart.getWlistname();
            url=url.replaceAll(" ", "%20");
            queue = Volley.newRequestQueue(context);
            StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    // hide the progress dialog
                    Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
                }


            }) ;
// {
//
//
//        };

            queue.add(jsonObjReq);
            Toast.makeText(context, "Added in Cart", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Already in Cart", Toast.LENGTH_SHORT).show();
        }

    }


    public ArrayList<WishListModel> getCartData() {

        ArrayList<WishListModel> arraycartdata = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_ADDTOCART;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                WishListModel Item = new WishListModel(drinkid, name, img);
                Item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CARTTABLEID))));
                Item.setWlistname(cursor.getString(cursor.getColumnIndex(ADDCARDNAME)));
                Item.setWlistimg(cursor.getString(cursor.getColumnIndex(ADDCARDIMG)));
                // Adding contact to list
                arraycartdata.add(Item);

            } while (cursor.moveToNext());
        }
        return arraycartdata;
    }


    public ArrayList<AddItemToShopUtility> getAllRecordsAddItem() {

        ArrayList<AddItemToShopUtility> arraystorenamedata = new ArrayList<AddItemToShopUtility>();

        String selectQuery = "SELECT  * FROM " + TABLE_SHOPITEM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AddItemToShopUtility Item = new AddItemToShopUtility();
                Item.setID(Integer.parseInt(cursor.getString(0)));
                Item.setItemname(cursor.getString(cursor.getColumnIndex(ITEMNAME)));
                Item.setPrice(cursor.getString(cursor.getColumnIndex(ITEMPRICE)));
                Item.setSelectitem(cursor.getString(cursor.getColumnIndex(ITEMSELECT)));
                Item.setWeight(cursor.getString(cursor.getColumnIndex(ITEMWEIGHT)));
                Item.setImageinByte(cursor.getString(cursor.getColumnIndex(ITEMIMAGE)));
                // Adding contact to list
                arraystorenamedata.add(Item);

            } while (cursor.moveToNext());
        }
        return arraystorenamedata;
    }

    public void InsertItemToGroceryList(GroceryAddItemUtility groceryAddItemUtility) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ADDGROCERY, groceryAddItemUtility.getGrocery());
        cv.put(ADDGROCERYITEMNAME, groceryAddItemUtility.getItemname());
        cv.put(ADDGROCERYITEMPRICE, groceryAddItemUtility.getItemprice());
        cv.put(ADDGROCERYITEMWEIGHT, groceryAddItemUtility.getItemwieght());
        cv.put(ADDGROCERYITEMIMAGE, groceryAddItemUtility.getItemproductimage());
        long ss = db.insert(TABLE_ADDITEMGROCERY, null, cv);
        Log.d("insert", "nn " + ss);
        db.close();

    }

    public ArrayList<GroceryAddItemUtility> getAllGroceryAddItem(String selectMealType) {

        ArrayList<GroceryAddItemUtility> arraygroceryadditemdata = new ArrayList<GroceryAddItemUtility>();
        String[] columns = {"Grocery"};

        String selectQuery = "SELECT  * FROM " + TABLE_ADDITEMGROCERY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GroceryAddItemUtility Item = new GroceryAddItemUtility();

                Item.setID(Integer.parseInt(cursor.getString(0)));
                Item.setItemselected(cursor.getString(1));
                Log.e("THIS ITEM", cursor.getString(1));
                Item.setItemname(cursor.getString(2));
                Item.setItemprice(cursor.getString(3));
                Item.setItemwieght(cursor.getString(4));
                Item.setItemproductimage(cursor.getBlob(5));
                // Adding contact to list
                arraygroceryadditemdata.add(Item);
            } while (cursor.moveToNext());

        }
        return arraygroceryadditemdata;
    }


}
