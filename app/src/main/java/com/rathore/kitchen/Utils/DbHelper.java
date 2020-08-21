package com.rathore.kitchen.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
import com.rathore.kitchen.Model.AddWineBottleModal;
import com.rathore.kitchen.Model.BookmarkRecipesModel;
import com.rathore.kitchen.Model.DrinkAllDetailModal;
import com.rathore.kitchen.Model.VirtualWineModel;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by archirayan on 27/12/16.
 */

public class DbHelper extends SQLiteOpenHelper {


    // All Static variables
    // Database Version
    RequestQueue queue;


    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "recipes";

    // Contacts table name
    private static final String TABLE_RECIPES = "recipesdetails";

    // Contacts Table Columns names
    private static final String TABLE_CONTACTS = "DietItem";
    //  private static final String KEY_ID = "id";
    private static final String KEY_RECIPES_NAME = "name";
    private static final String KEY_RECIPES_IMAGE = "image";
    private static final String KEY_INGREDIENT = "ingredient";
    private static final String KEY_RATING = "rating";
    private static final String KEY_RECIPES_SOURCE_URL = "sourceurl";
    //timetask
   /* private static final String TABLE_TIMETASK = "timewizard";
    private static final String TIMETASK_ID = "tI_id";
    private static final String TIMETASK_TASKTYPE = "TaskType";
    private static final String  TIMETASK_TASKNAME= "TaskName";
    private static final String TIMETASK_MINUT = "TaskMinut";
    private static final String TIMETASK_STATUS = "TaskStatus";*/
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
    private static final String TABLE_SHOPITEM = "ShopAddItem";
    //add item to shop
    private static final String SHOPITEM_ID = "sI_id";
    private static final String ITEMIMAGE = "ItemImage";
    private static final String ITEMNAME = "ItemName";
    private static final String ITEMPRICE = "ItemPrice";
    private static final String ITEMWEIGHT = "ItemWeight";
    //timetask
    private static final String TABLE_TIMETASK = "timewizard";
    private static final String TIMETASK_ID = "tI_id";
    private static final String TIMETASK_TASKTYPE = "TaskType";
    private static final String TIMETASK_TASKNAME = "TaskName";
    private static final String TIMETASK_MINUT = "TaskMinut";
    private static final String TIMETASK_STATUS = "TaskStatus";
    private static final String Virtual_WINE = "vertualwine";


    // virtual wine manager
    private static final String KEY_TABLE_WINE_VINTAGE = "vintage";
    private static final String KEY_WINE_ID = "id";
    private static final String KEY_WINE_VINTAGE = "vintage";
    private static final String KEY_WINE_IMAGE = "image";
    private static final String KEY_WINE_PRODUCER = "producer";
    private static final String KEY_WINE_NAME = "name";
    private static final String KEY_WINE_TYPE = "type";
    private static final String KEY_WINE_SIZE = "size";
    private static final String KEY_WINE_SDATE = "sdate";
    private static final String KEY_WINE_EDATE = "edate";
    private static final String KEY_WINE_PRICE = "price";
    private static final String KEY_WINE_STORED = "stored";
    private static final String KEY_WINE_PURCHASED = "parchased";
    private static final String KEY_WINE_DATE = "date";
    private static final String KEY_WINE_NOTE = "note";
    private static final String KEY_WINE_TASTED = "tasted";


    //AllDrink
    private static final String TABLE_DRINK = "Drink";
    private static final String DRINK_ID = "dI_id";
    private static final String DRINK_IDDRINK = "idDrink";
    private static final String DRINK_STRDRINK = "strDrink";
    private static final String DRINK_STRCATEGORY = "strCategory";
    private static final String DRINK_STRALCOHOLIC = "strAlcoholic";
    private static final String DRINK_STRGLASS = "strGlass";
    private static final String DRINK_STRINSTRUCTION = "strInstructions";
    private static final String DRINK_DRINKTHUMB = "strDrinkThumb";
    private static final String DRINK_INGREDIENT1 = "strIngredient1";
    private static final String DRINK_INGREDIENT2 = "strIngredient2";
    private static final String DRINK_INGREDIENT3 = "strIngredient3";
    private static final String DRINK_INGREDIENT4 = "strIngredient4";
    private static final String DRINK_INGREDIENT5 = "strIngredient5";
    private static final String DRINK_INGREDIENT6 = "strIngredient6";
    private static final String DRINK_INGREDIENT7 = "strIngredient7";
    private static final String DRINK_INGREDIENT8 = "strIngredient8";
    private static final String DRINK_INGREDIENT9 = "strIngredient9";
    private static final String DRINK_INGREDIENT10 = "strIngredient10";
    private static final String DRINK_INGREDIENT11 = "strIngredient11";
    private static final String DRINK_INGREDIENT12 = "strIngredient12";
    private static final String DRINK_INGREDIENT13 = "strIngredient13";
    private static final String DRINK_INGREDIENT14 = "strIngredient14";
    private static final String DRINK_INGREDIENT15 = "strIngredient15";
    private static final String DRINK_STRMASUARE1 = "strMeasure1";
    private static final String DRINK_STRMASUARE2 = "strMeasure2";
    private static final String DRINK_STRMASUARE3 = "strMeasure3";
    private static final String DRINK_STRMASUARE4 = "strMeasure4";
    private static final String DRINK_STRMASUARE5 = "strMeasure5";
    private static final String DRINK_STRMASUARE6 = "strMeasure6";
    private static final String DRINK_STRMASUARE7 = "strMeasure7";
    private static final String DRINK_STRMASUARE8 = "strMeasure8";
    private static final String DRINK_STRMASUARE9 = "strMeasure9";
    private static final String DRINK_STRMASUARE10 = "strMeasure10";
    private static final String DRINK_STRMASUARE11 = "strMeasure11";
    private static final String DRINK_STRMASUARE12 = "strMeasure12";
    private static final String DRINK_STRMASUARE13 = "strMeasure13";
    private static final String DRINK_STRMASUARE14 = "strMeasure14";
    private static final String DRINK_STRMASUARE15 = "strMeasure15";
    private static final String DRINK_STRDATEMODIFIELD = "dateModified";
    private static final String DRINK_STATUS = "status";


    public Context context;

    private static byte[] arraybyte;
    private final ArrayList<VirtualWineModel> wineModels = new ArrayList<>();


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_DRINK_TABLE = "CREATE TABLE " + TABLE_DRINK + "("
                + DRINK_ID + " INTEGER PRIMARY KEY,"
                + DRINK_IDDRINK + " TEXT,"
                + DRINK_STRDRINK + " TEXT,"
                + DRINK_STRCATEGORY + " TEXT,"
                + DRINK_STRALCOHOLIC + " TEXT,"
                + DRINK_STRGLASS + " TEXT,"
                + DRINK_STRINSTRUCTION + " TEXT,"
                + DRINK_INGREDIENT1 + " TEXT,"
                + DRINK_INGREDIENT2 + " TEXT,"
                + DRINK_INGREDIENT3 + " TEXT,"
                + DRINK_INGREDIENT4 + " TEXT,"
                + DRINK_INGREDIENT5 + " TEXT,"
                + DRINK_INGREDIENT6 + " TEXT,"
                + DRINK_INGREDIENT7 + " TEXT,"
                + DRINK_INGREDIENT8 + " TEXT,"
                + DRINK_INGREDIENT9 + " TEXT,"
                + DRINK_INGREDIENT10 + " TEXT,"
                + DRINK_INGREDIENT11 + " TEXT,"
                + DRINK_INGREDIENT12 + " TEXT,"
                + DRINK_INGREDIENT13 + " TEXT,"
                + DRINK_INGREDIENT14 + " TEXT,"
                + DRINK_INGREDIENT15 + " TEXT,"
                + DRINK_STRMASUARE1 + " TEXT,"
                + DRINK_STRMASUARE2 + " TEXT,"
                + DRINK_STRMASUARE3 + " TEXT,"
                + DRINK_STRMASUARE4 + " TEXT,"
                + DRINK_STRMASUARE5 + " TEXT,"
                + DRINK_STRMASUARE6 + " TEXT,"
                + DRINK_STRMASUARE7 + " TEXT,"
                + DRINK_STRMASUARE8 + " TEXT,"
                + DRINK_STRMASUARE9 + " TEXT,"
                + DRINK_STRMASUARE10 + " TEXT,"
                + DRINK_STRMASUARE11 + " TEXT,"
                + DRINK_STRMASUARE12 + " TEXT,"
                + DRINK_STRMASUARE13 + " TEXT,"
                + DRINK_STRMASUARE14 + " TEXT,"
                + DRINK_STRMASUARE15 + " TEXT,"
                + DRINK_STRDATEMODIFIELD + " TEXT,"
                + DRINK_DRINKTHUMB + " BLOB,"
                + DRINK_STATUS + " TEXT " + ")";


        String CREATE_TABLE_VIRTUALWINE = "CREATE TABLE " + KEY_TABLE_WINE_VINTAGE + "("
                + KEY_WINE_ID + " INTEGER PRIMARY KEY,"
                + KEY_WINE_IMAGE + " BLOB,"
                + KEY_WINE_VINTAGE + " INTEGER,"
                + KEY_WINE_PRODUCER + " TEXT,"
                + KEY_WINE_NAME + " TEXT,"
                + KEY_WINE_TYPE + " TEXT,"
                + KEY_WINE_SIZE + " INTEGER,"
                + KEY_WINE_SDATE + " INTEGER,"
                + KEY_WINE_EDATE + " INTEGER,"
                + KEY_WINE_PRICE + " INTEGER,"
                + KEY_WINE_STORED + " TEXT,"
                + KEY_WINE_PURCHASED + " TEXT,"
                + KEY_WINE_DATE + " INTEGER,"
                + KEY_WINE_NOTE + " TEXT,"
                + KEY_WINE_TASTED + " TEXT" + ")";


        String CREATE_TABLE_CHILD = "CREATE TABLE " + TABLE_RECIPES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_RECIPES_NAME + " TEXT,"
                + KEY_RECIPES_IMAGE + " TEXT,"
                + KEY_RECIPES_SOURCE_URL + " TEXT,"
                + KEY_INGREDIENT + " INTEGER,"
                + KEY_RATING + " TEXT" + ")";


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
                + IMAGE + " BLOB " + ")";
        String CREATE_ADDITEMTOSHOP_TABLE = "CREATE TABLE " + TABLE_SHOPITEM + "("
                + SHOPITEM_ID + " INTEGER PRIMARY KEY,"
                + ITEMNAME + " TEXT,"
                + ITEMPRICE + " TEXT,"
                + ITEMWEIGHT + " TEXT,"
                + ITEMIMAGE + " BLOB " + ")";


        String CREATE_STORENAME_TABLE = "CREATE TABLE " + TABLE_STORENAME + "("
                + STORENAME_ID + " INTEGER PRIMARY KEY,"
                + STORENAME + " TEXT" + ")";

        String CREATE_TIMETASK = "CREATE TABLE " + TABLE_TIMETASK + "("
                + TIMETASK_ID + " INTEGER PRIMARY KEY,"
                + TIMETASK_TASKTYPE + " TEXT,"
                + TIMETASK_TASKNAME + " TEXT,"
                + TIMETASK_MINUT + " TEXT,"
                + TIMETASK_STATUS + " TEXT " + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_STORENAME_TABLE);
        db.execSQL(CREATE_ADDITEMTOSHOP_TABLE);
        db.execSQL(CREATE_TIMETASK);
        db.execSQL(CREATE_TABLE_VIRTUALWINE);
        db.execSQL(CREATE_DRINK_TABLE);
        db.execSQL(CREATE_TABLE_CHILD);

        Log.d("db", "oncreate ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + KEY_TABLE_WINE_VINTAGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMETASK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORENAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRINK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);

        Log.d("db", " onUpgrade ");
    }


    public void addRecipes(String title, String rating, String ingredient, String image, String sourcecUrl) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RECIPES_NAME, title); // Contact Name
        values.put(KEY_RECIPES_IMAGE, image); // Contact Phone Number
        values.put(KEY_INGREDIENT, ingredient);
        values.put(KEY_RATING, rating);
        values.put(KEY_RECIPES_SOURCE_URL, sourcecUrl);
        Log.i("name",title);
        Log.i("image",image);
        Log.i("ingredient",ingredient);
        Log.i("rating",rating);
        Log.i("sourcecUrl",sourcecUrl);


//        if(title.length()>0){
//
//        }
//        else {
//            title = "null";
//        }
//        if(image.length()>0){
//
//        }
//        else {
//            image = "null";
//        }
//        if(ingredient.length()>0){
//            ingredient.concat(" ");
//
//        }
//        else {
//            ingredient = "null";
//        }
//        if(rating.length()>0){
//
//        }
//        else {
//            rating = "null";
//        }
//        if(sourcecUrl.length()>0){
//
//        }
//        else {
//            sourcecUrl = "null";
//        }

       // String url = "http://10.10.10.1/ApiKitchen.php?apicall=createtablereceipe&KEY_RECIPES_NAME="+title+"&KEY_RECIPES_IMAGE="+image+"&KEY_RECIPES_SOURCE_URL="+sourcecUrl+"&KEY_INGREDIENT="+ingredient+"&KEY_RATING="+rating;

       // url=url.replaceAll(" ", "%20");
        //url=url.replaceAll(",", "%20");
       // Log.i("url",url);
        String name = title;
        String image1 = image;
        String surl = sourcecUrl;
        String ratingg = rating;
        String ingredientss = ingredient;
        String encodedStringname = null,encodedStringimage =null,encodedStringurl = null,encodedStringing = null,encodedStringrating = null;
        try {
            encodedStringname = URLEncoder.encode(name,"UTF-8");
            if(image1!=null){
            encodedStringimage = URLEncoder.encode(image1,"UTF-8");}
            else {
                encodedStringimage=image;
            }

            encodedStringurl = URLEncoder.encode(surl,"UTF-8");
            encodedStringing = URLEncoder.encode(ingredientss,"UTF-8");
            encodedStringrating = URLEncoder.encode(ratingg,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://10.10.10.1/ApiKitchen.php?apicall=createtablereceipe&KEY_RECIPES_NAME="+encodedStringname+"&KEY_RECIPES_IMAGE="+encodedStringimage+"&KEY_RECIPES_SOURCE_URL="+encodedStringurl+"&KEY_INGREDIENT="+encodedStringing+"&KEY_RATING="+encodedStringrating;

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


        // Inserting Row
        long statusInsert = db.insert(TABLE_RECIPES, null, values);
        Log.d("msg", "insert status " + statusInsert);
        db.close(); // Closing database connection
    }


    public int InsertDetailsDrink(DrinkAllDetailModal drinkAllDetailModal) {
        String drinkiddrink= null,thumb =null,modified = null,status = null,alchoholic = null;
        String category=null,glass=null,ing4=null,ing5=null,ing6=null,ing7=null,ing8=null,ing1=null,ing2=null,ing3=null;
        String ing9=null,ing10=null,ing11=null,ing12=null,ing13=null,ing14=null,ing15=null,msr1=null,msr2=null,msr3=null,msr4 = null,msr5=null,msr6=null,msr7=null;
        String msr8=null,msr9=null,msr10=null,msr11=null,msr12=null,msr13=null,msr14=null,msr15=null,strdrink=null,instruction=null;
        try {
            drinkiddrink= URLEncoder.encode(drinkAllDetailModal.getIdDrink(),"UTF-8");
            thumb = URLEncoder.encode(drinkAllDetailModal.getByteImage(),"UTF-8");
            modified = URLEncoder.encode(drinkAllDetailModal.getDateModified(),"UTF-8");
            status = URLEncoder.encode(drinkAllDetailModal.getStatus(),"UTF-8");
            alchoholic = URLEncoder.encode(drinkAllDetailModal.getStrAlcoholic(),"UTF-8");
            category = URLEncoder.encode(drinkAllDetailModal.getStrCategory(),"UTF-8");
            glass = URLEncoder.encode(drinkAllDetailModal.getStrGlass(),"UTF-8");
            ing6 = URLEncoder.encode(drinkAllDetailModal.getStrIngredient6(),"UTF-8");
            ing7 = URLEncoder.encode(drinkAllDetailModal.getStrIngredient7(),"UTF-8");
            ing8 = URLEncoder.encode(drinkAllDetailModal.getStrIngredient8(),"UTF-8");
            ing9 = URLEncoder.encode(drinkAllDetailModal.getStrIngredient9(),"UTF-8");
            ing10 = URLEncoder.encode(drinkAllDetailModal.getStrIngredient10(),"UTF-8");
            ing1 = URLEncoder.encode(drinkAllDetailModal.getStrIngredient1(),"UTF-8");
            ing2 = URLEncoder.encode(drinkAllDetailModal.getStrIngredient2(),"UTF-8");
            ing3 = URLEncoder.encode(drinkAllDetailModal.getStrIngredient3(),"UTF-8");
            ing4 = URLEncoder.encode(drinkAllDetailModal.getStrIngredient4(),"UTF-8");
            ing5 = URLEncoder.encode(drinkAllDetailModal.getStrIngredient5(),"UTF-8");
            ing11 = URLEncoder.encode(drinkAllDetailModal.getStrIngredient11(),"UTF-8");
            ing12 = URLEncoder.encode(drinkAllDetailModal.getStrIngredient12(),"UTF-8");
            ing13 = URLEncoder.encode(drinkAllDetailModal.getStrIngredient13(),"UTF-8");
            ing14 = URLEncoder.encode(drinkAllDetailModal.getStrIngredient14(),"UTF-8");
            ing15 = URLEncoder.encode(drinkAllDetailModal.getStrIngredient15(),"UTF-8");

            msr1 = URLEncoder.encode(drinkAllDetailModal.getStrMeasure1(),"UTF-8");
            msr2 = URLEncoder.encode(drinkAllDetailModal.getStrMeasure2(),"UTF-8");
            msr3 = URLEncoder.encode(drinkAllDetailModal.getStrMeasure3(),"UTF-8");
            msr4 = URLEncoder.encode(drinkAllDetailModal.getStrMeasure4(),"UTF-8");
            msr5 = URLEncoder.encode(drinkAllDetailModal.getStrMeasure5(),"UTF-8");
            msr6 = URLEncoder.encode(drinkAllDetailModal.getStrMeasure6(),"UTF-8");
            msr7 = URLEncoder.encode(drinkAllDetailModal.getStrMeasure7(),"UTF-8");
            msr8 = URLEncoder.encode(drinkAllDetailModal.getStrMeasure8(),"UTF-8");
            msr9 = URLEncoder.encode(drinkAllDetailModal.getStrMeasure9(),"UTF-8");
            msr10 = URLEncoder.encode(drinkAllDetailModal.getStrMeasure10(),"UTF-8");
            msr11 = URLEncoder.encode(drinkAllDetailModal.getStrMeasure11(),"UTF-8");
            msr12 = URLEncoder.encode(drinkAllDetailModal.getStrMeasure12(),"UTF-8");
            msr13 = URLEncoder.encode(drinkAllDetailModal.getStrMeasure13(),"UTF-8");
            msr14 = URLEncoder.encode(drinkAllDetailModal.getStrMeasure14(),"UTF-8");
            msr15 = URLEncoder.encode(drinkAllDetailModal.getStrMeasure15(),"UTF-8");



            strdrink = URLEncoder.encode(drinkAllDetailModal.getStrDrink(),"UTF-8");
            instruction = URLEncoder.encode(drinkAllDetailModal.getStrInstructions(),"UTF-8");




        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = "http://10.10.10.1/ApiKitchen.php?apicall=createalldrinks&drinkid_drink="+drinkiddrink+"&drink_strdrink="+strdrink+"&drink_strcategory="+category+"&drink_stralchohol="+alchoholic+"&drink_strglass="+ glass+"&drink_strinstruction="+instruction+"&drink_drinkthumb="+thumb+"&drink_ing1="+ing1+"&drink_ing2="+ing2+"&drink_ing3="+ing3+"&drink_ing4="+ing4+"&drink_ing5="+ing5+"&drink_ing6="+ing6+"&drink_ing7="+ing7+"&drink_ing8="+ing8+"&drink_ing9="+ing9+"&drink_ing10="+ing10 +"&drink_ing11="+ing11+"&drink_ing12="+ing12+"&drink_ing13="+ing13+"&drink_ing14="+ing14+"&drink_ing15="+ing15+"&drink_strmesure1="+msr1+"&drink_strmesure2="+msr2+"&drink_strmesure3="+msr3+"&drink_strmesure4="+msr4+"&drink_strmesure5="+msr5+"&drink_strmesure6=" +msr6+"&drink_strmesure7="+msr7+"&drink_strmesure8="+msr8+"&drink_strmesure9="+msr9+"&drink_strmesure10="+msr10+"&drink_strmesure11="+msr11+"&drink_strmesure12="+msr12+"&drink_strmesure13="+msr13+"&drink_strmesure14="+msr14+"&drink_strmesure15="+msr15+"&drink_strdatemodified="+modified+"&drink_status="+status ;
        url=url.replaceAll(" ", "%20");
Log.i("url",url.toString());

        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
Log.i("erroe",error.toString());
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
        cv.put(DRINK_IDDRINK, drinkAllDetailModal.getIdDrink());
        cv.put(DRINK_STRDRINK, drinkAllDetailModal.getStrDrink());
        cv.put(DRINK_STRCATEGORY, drinkAllDetailModal.getStrCategory());
        cv.put(DRINK_STRALCOHOLIC, drinkAllDetailModal.getStrAlcoholic());
        cv.put(DRINK_STRGLASS, drinkAllDetailModal.getStrGlass());
        cv.put(DRINK_STRINSTRUCTION, drinkAllDetailModal.getStrInstructions());
        cv.put(DRINK_INGREDIENT1, drinkAllDetailModal.getStrIngredient1());
        cv.put(DRINK_INGREDIENT2, drinkAllDetailModal.getStrIngredient2());
        cv.put(DRINK_INGREDIENT3, drinkAllDetailModal.getStrIngredient3());
        cv.put(DRINK_INGREDIENT4, drinkAllDetailModal.getStrIngredient4());
        cv.put(DRINK_INGREDIENT5, drinkAllDetailModal.getStrIngredient5());
        cv.put(DRINK_INGREDIENT6, drinkAllDetailModal.getStrIngredient6());
        cv.put(DRINK_INGREDIENT7, drinkAllDetailModal.getStrIngredient7());
        cv.put(DRINK_INGREDIENT8, drinkAllDetailModal.getStrIngredient8());
        cv.put(DRINK_INGREDIENT9, drinkAllDetailModal.getStrIngredient9());
        cv.put(DRINK_INGREDIENT10, drinkAllDetailModal.getStrIngredient10());
        cv.put(DRINK_INGREDIENT11, drinkAllDetailModal.getStrIngredient11());
        cv.put(DRINK_INGREDIENT12, drinkAllDetailModal.getStrIngredient12());
        cv.put(DRINK_INGREDIENT13, drinkAllDetailModal.getStrIngredient13());
        cv.put(DRINK_INGREDIENT14, drinkAllDetailModal.getStrIngredient14());
        cv.put(DRINK_INGREDIENT15, drinkAllDetailModal.getStrIngredient15());
        cv.put(DRINK_STRMASUARE1, drinkAllDetailModal.getStrMeasure1());
        cv.put(DRINK_STRMASUARE2, drinkAllDetailModal.getStrMeasure2());
        cv.put(DRINK_STRMASUARE3, drinkAllDetailModal.getStrMeasure3());
        cv.put(DRINK_STRMASUARE4, drinkAllDetailModal.getStrMeasure4());
        cv.put(DRINK_STRMASUARE5, drinkAllDetailModal.getStrMeasure5());
        cv.put(DRINK_STRMASUARE6, drinkAllDetailModal.getStrMeasure6());
        cv.put(DRINK_STRMASUARE7, drinkAllDetailModal.getStrMeasure7());
        cv.put(DRINK_STRMASUARE8, drinkAllDetailModal.getStrMeasure8());
        cv.put(DRINK_STRMASUARE9, drinkAllDetailModal.getStrMeasure9());
        cv.put(DRINK_STRMASUARE10, drinkAllDetailModal.getStrMeasure10());
        cv.put(DRINK_STRMASUARE11, drinkAllDetailModal.getStrMeasure11());
        cv.put(DRINK_STRMASUARE12, drinkAllDetailModal.getStrMeasure12());
        cv.put(DRINK_STRMASUARE13, drinkAllDetailModal.getStrMeasure13());
        cv.put(DRINK_STRMASUARE14, drinkAllDetailModal.getStrMeasure14());
        cv.put(DRINK_STRMASUARE15, drinkAllDetailModal.getStrMeasure15());
        cv.put(DRINK_STRDATEMODIFIELD, drinkAllDetailModal.getDateModified());
        cv.put(DRINK_DRINKTHUMB, drinkAllDetailModal.getByteImage());
        cv.put(DRINK_STATUS, "0");
        long statusInsertdrink = db.insert(TABLE_DRINK, null, cv);
        Log.d("msg", "insert status drink " + statusInsertdrink);
        db.close();
        return (int) statusInsertdrink;
    }


    public void updatedDetailDrinkStatus(int id) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DRINK_STATUS, "1");


        // updating row
        long idstatus = db.update(TABLE_DRINK, values, DRINK_ID + " = ?",
                new String[]{String.valueOf(id)});

        Log.d("msg", "status update " + idstatus);

    }
    public void updateWineBottle(AddWineBottleModal winebottle) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
       // values.put(KEY_WINE_ID, winebottle.getId());
        values.put(KEY_WINE_IMAGE, winebottle.getByteImage());
        values.put(KEY_WINE_VINTAGE, winebottle.getStrivintage());
        values.put(KEY_WINE_PRODUCER, winebottle.getStrwinery());
        values.put(KEY_WINE_NAME, winebottle.getStrwinename());
        values.put(KEY_WINE_TYPE, winebottle.getStrtype());
        values.put(KEY_WINE_SIZE, winebottle.getStrsize());
        values.put(KEY_WINE_SDATE, winebottle.getStrstartdate());
        values.put(KEY_WINE_EDATE, winebottle.getStrenddate());
        values.put(KEY_WINE_PRICE, winebottle.getStrstorelocation());
        values.put(KEY_WINE_STORED, winebottle.getStrstorewherepurchased());
        values.put(KEY_WINE_PURCHASED, winebottle.getStrdate());
        values.put(KEY_WINE_DATE, winebottle.getStrpaidbottle());
        values.put(KEY_WINE_NOTE, winebottle.getStrEntertestingnotes());
        values.put(KEY_WINE_TASTED, winebottle.getStrEntertestin());

        db.update(KEY_TABLE_WINE_VINTAGE, values, KEY_WINE_ID + " = ?", new String[]
                {String.valueOf(winebottle.getId())});

        Log.e("UpdatedDATA", "" +values);

    }



    public void addwine(AddWineBottleModal winebottle) {

//        String tasted;
//        if (winebottle.getStrEntertestin().length()>0){
//            tasted = winebottle.getStrEntertestin();
//        }
//
//        else {
//            tasted= "null";
//        }
//        String vintage;
//        if (winebottle.getStrivintage().length()>0){
//            vintage = winebottle.getStrivintage();
//        }
//
//        else {
//            vintage= "null";
//        }
//        String notes;
//        if (winebottle.getStrEntertestingnotes().length()>0){
//            notes = winebottle.getStrEntertestingnotes();
//        }
//
//        else {
//            notes= "null";
//        }
//        String stdate;
//        if (winebottle.getStrdate().length()>0){
//            stdate = winebottle.getStrdate();
//        }
//
//        else {
//            stdate= "null";
//        }
//        String endate;
//        if (winebottle.getStrenddate().length()>0){
//            endate = winebottle.getStrenddate();
//        }
//
//        else {
//            endate= "null";
//        }
//        String paid;
//        if (winebottle.getStrpaidbottle().length()>0){
//            paid = winebottle.getStrpaidbottle();
//        }
//
//        else {
//            paid= "null";
//        }
//        String location;
//        if (winebottle.getStrstorelocation().length()>0){
//            location = winebottle.getStrstorelocation();
//        }
//
//        else {
//            location= "null";
//        }
//
//        String purchased;
//        if (winebottle.getStrstorewherepurchased().length()>0){
//            purchased = winebottle.getStrstorewherepurchased();
//        }
//
//        else {
//            purchased= "null";
//        }
//        String name;
//        if (winebottle.getStrwinename().length()>0){
//            name = winebottle.getStrwinename();
//        }
//
//        else {
//            name= "null";
//        }
//        String producer;
//        if (winebottle.getStrwinery().length()>0){
//            producer = winebottle.getStrwinery();
//        }
//
//        else {
//            producer= "null";
//        }
//        String date;
//        if (winebottle.getStrdate().length()>0){
//            date = winebottle.getStrdate();
//        }
//
//        else {
//            date= "null";
//        }
        String name= null,strdate =null,endate = null,image = null,tested = null;
        String notes=null,vintage=null,paid=null,wherepurchased=null,size=null,strstrdate=null,type=null;
        String producer=null,location=null;
        try {
            name= URLEncoder.encode(winebottle.getStrwinename(),"UTF-8");
            strdate = URLEncoder.encode(winebottle.getStrdate(),"UTF-8");
            endate = URLEncoder.encode(winebottle.getStrenddate(),"UTF-8");
            if(image!=null){
            image = URLEncoder.encode(winebottle.getByteImage().toString(),"UTF-8");}
            else {
                image="null";
            }
            tested = URLEncoder.encode(winebottle.getStrEntertestin(),"UTF-8");
            notes = URLEncoder.encode(winebottle.getStrEntertestingnotes(),"UTF-8");
            vintage = URLEncoder.encode(winebottle.getStrivintage(),"UTF-8");
            paid = URLEncoder.encode(winebottle.getStrpaidbottle(),"UTF-8");
            wherepurchased = URLEncoder.encode(winebottle.getStrstorewherepurchased(),"UTF-8");
            size = URLEncoder.encode(winebottle.getStrsize(),"UTF-8");
            strstrdate = URLEncoder.encode(winebottle.getStrstartdate(),"UTF-8");
            type = URLEncoder.encode(winebottle.getStrtype(),"UTF-8");
            producer = URLEncoder.encode(winebottle.getStrwinery(),"UTF-8");
            location = URLEncoder.encode(winebottle.getStrstorelocation(),"UTF-8");






        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }





        String url = "http://10.10.10.1/ApiKitchen.php?apicall=createvirtualwinemanager&WINE_VINTAGE="+vintage+"&WINE_IMAGE="+image+"&WINE_PRODUCER="+ producer+"&WINE_NAME="+name+"&WINE_TYPE="+ type+"&WINE_SIZE="+size+"&WINE_SDATE="+strstrdate+"&WINE_EDATE="+endate+"&WINE_PRICE="+paid+"&WINE_STORED="+wherepurchased+"&WINE_PURCHASED="+location+"&WINE_DATE="+strdate+"&WINE_NOTE="+notes+"&WINE_TASTED="+tested;

        url=url.replaceAll(" ", "%20");

        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

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

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_WINE_IMAGE, winebottle.getByteImage());
        values.put(KEY_WINE_VINTAGE, winebottle.getStrivintage());
        values.put(KEY_WINE_PRODUCER, winebottle.getStrwinery());
        values.put(KEY_WINE_NAME, winebottle.getStrwinename());
        values.put(KEY_WINE_TYPE, winebottle.getStrtype());
        values.put(KEY_WINE_SIZE, winebottle.getStrsize());
        values.put(KEY_WINE_SDATE, winebottle.getStrstartdate());
        values.put(KEY_WINE_EDATE, winebottle.getStrenddate());
        values.put(KEY_WINE_PRICE, winebottle.getStrstorelocation());
        values.put(KEY_WINE_STORED, winebottle.getStrstorewherepurchased());
        values.put(KEY_WINE_PURCHASED, winebottle.getStrdate());
        values.put(KEY_WINE_DATE, winebottle.getStrpaidbottle());
        values.put(KEY_WINE_NOTE, winebottle.getStrEntertestingnotes());
        values.put(KEY_WINE_TASTED, winebottle.getStrEntertestin());
        db.insert(KEY_TABLE_WINE_VINTAGE, null, values);
        Log.d("DATAS", "" + values);
        db.close(); // Closing database connection

        //  return statusInsert;
    }

    public ArrayList<AddWineBottleModal> getVineBottelList() {
        ArrayList<AddWineBottleModal> arraystorenamedata = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + KEY_TABLE_WINE_VINTAGE;
        Log.e("DATA", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AddWineBottleModal arrayvinebottle = new AddWineBottleModal();
                arrayvinebottle.setId(cursor.getInt(cursor.getColumnIndex(KEY_WINE_ID)));
                arrayvinebottle.setStrwinename(cursor.getString(cursor.getColumnIndex(KEY_WINE_NAME)));
                arrayvinebottle.setByteImage(cursor.getBlob(cursor.getColumnIndex(KEY_WINE_IMAGE)));
                arrayvinebottle.setStrivintage(cursor.getString(cursor.getColumnIndex(KEY_WINE_VINTAGE)));
                arrayvinebottle.setStrwinery(cursor.getString(cursor.getColumnIndex(KEY_WINE_PRODUCER)));
                arrayvinebottle.setStrtype(cursor.getString(cursor.getColumnIndex(KEY_WINE_TYPE)));
                arrayvinebottle.setStrsize(cursor.getString(cursor.getColumnIndex(KEY_WINE_SIZE)));
                arrayvinebottle.setStrstartdate(cursor.getString(cursor.getColumnIndex(KEY_WINE_SDATE)));
                arrayvinebottle.setStrenddate(cursor.getString(cursor.getColumnIndex(KEY_WINE_EDATE)));
                arrayvinebottle.setStrstorelocation(cursor.getString(cursor.getColumnIndex(KEY_WINE_PRICE)));
                arrayvinebottle.setStrstorewherepurchased(cursor.getString(cursor.getColumnIndex(KEY_WINE_STORED)));
                arrayvinebottle.setStrdate(cursor.getString(cursor.getColumnIndex(KEY_WINE_PURCHASED)));
                arrayvinebottle.setStrpaidbottle(cursor.getString(cursor.getColumnIndex(KEY_WINE_DATE)));
                arrayvinebottle.setStrEntertestingnotes(cursor.getString(cursor.getColumnIndex(KEY_WINE_NOTE)));
                arrayvinebottle.setStrEntertestin(cursor.getString(cursor.getColumnIndex(KEY_WINE_TASTED)));
                arraystorenamedata.add(arrayvinebottle);
            } while (cursor.moveToNext());
        }
        return arraystorenamedata;
    }




    // Updating single contact
    public int updateContact(BookmarkRecipesModel bookmarkRecipesModel, String realpath, String newtitle) {

        Log.i("imagechangeddb","called");
        SQLiteDatabase db = this.getWritableDatabase();
        String title = bookmarkRecipesModel.getTitle();
        Log.d("msg", "title " + title);
        ContentValues values = new ContentValues();

        if (newtitle.equalsIgnoreCase("")) {
            values.put(KEY_RECIPES_NAME, bookmarkRecipesModel.getTitle());
        } else {

            values.put(KEY_RECIPES_NAME, newtitle);
        }


        values.put(KEY_INGREDIENT, bookmarkRecipesModel.getIngredient());
        values.put(KEY_RATING, bookmarkRecipesModel.getRank());
        values.put(KEY_RECIPES_SOURCE_URL, bookmarkRecipesModel.getOrignalurl());


        if (realpath.equalsIgnoreCase("")) {
            values.put(KEY_RECIPES_IMAGE, bookmarkRecipesModel.getImage());

        } else {
            values.put(KEY_RECIPES_IMAGE, realpath);
        }

        // updating row
        return db.update(TABLE_RECIPES, values, KEY_RECIPES_NAME + " = ?",
                new String[]{String.valueOf(bookmarkRecipesModel.getTitle())});

    }


    public ArrayList<DrinkAllDetailModal> getSingleRecordsdrink(int code) {
        ArrayList<DrinkAllDetailModal> arraytimetdrink = new ArrayList<DrinkAllDetailModal>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DRINK, new String[]{DRINK_ID, DRINK_IDDRINK, DRINK_STRDRINK, DRINK_STRCATEGORY, DRINK_STRALCOHOLIC, DRINK_STRGLASS, DRINK_STRINSTRUCTION,
                        DRINK_INGREDIENT1, DRINK_INGREDIENT2, DRINK_INGREDIENT3, DRINK_INGREDIENT4, DRINK_INGREDIENT5, DRINK_INGREDIENT6, DRINK_INGREDIENT7, DRINK_INGREDIENT8,
                        DRINK_INGREDIENT9, DRINK_INGREDIENT10, DRINK_INGREDIENT11, DRINK_INGREDIENT12, DRINK_INGREDIENT13, DRINK_INGREDIENT14, DRINK_INGREDIENT15, DRINK_STRMASUARE1,
                        DRINK_STRMASUARE2, DRINK_STRMASUARE3, DRINK_STRMASUARE4, DRINK_STRMASUARE5, DRINK_STRMASUARE6, DRINK_STRMASUARE7, DRINK_STRMASUARE8, DRINK_STRMASUARE9,
                        DRINK_STRMASUARE10, DRINK_STRMASUARE11, DRINK_STRMASUARE12, DRINK_STRMASUARE13, DRINK_STRMASUARE14, DRINK_STRMASUARE15, DRINK_STRDATEMODIFIELD, DRINK_DRINKTHUMB},
                DRINK_ID + "=?", new String[]{String.valueOf(code)}, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                DrinkAllDetailModal Item = new DrinkAllDetailModal();
                Item.setID(Integer.parseInt(cursor.getString(0)));
                Item.setIdDrink(cursor.getString(1));
                Item.setStrDrink(cursor.getString(2));
                Item.setStrCategory(cursor.getString(3));
                Item.setStrAlcoholic(cursor.getString(4));
                Item.setStrGlass(cursor.getString(5));
                Item.setStrInstructions(cursor.getString(6));
                Item.setStrIngredient1(cursor.getString(7));
                Item.setStrIngredient2(cursor.getString(8));
                Item.setStrIngredient3(cursor.getString(9));
                Item.setStrIngredient4(cursor.getString(10));
                Item.setStrIngredient5(cursor.getString(11));
                Item.setStrIngredient6(cursor.getString(12));
                Item.setStrIngredient7(cursor.getString(13));
                Item.setStrIngredient8(cursor.getString(14));
                Item.setStrIngredient9(cursor.getString(15));
                Item.setStrIngredient10(cursor.getString(16));
                Item.setStrIngredient11(cursor.getString(17));
                Item.setStrIngredient12(cursor.getString(18));
                Item.setStrIngredient13(cursor.getString(19));
                Item.setStrIngredient14(cursor.getString(20));
                Item.setStrIngredient15(cursor.getString(21));
                Item.setStrMeasure1(cursor.getString(22));
                Item.setStrMeasure2(cursor.getString(23));
                Item.setStrMeasure3(cursor.getString(24));
                Item.setStrMeasure4(cursor.getString(25));
                Item.setStrMeasure5(cursor.getString(26));
                Item.setStrMeasure6(cursor.getString(27));
                Item.setStrMeasure7(cursor.getString(28));
                Item.setStrMeasure8(cursor.getString(29));
                Item.setStrMeasure9(cursor.getString(30));
                Item.setStrMeasure10(cursor.getString(31));
                Item.setStrMeasure11(cursor.getString(32));
                Item.setStrMeasure12(cursor.getString(33));
                Item.setStrMeasure13(cursor.getString(34));
                Item.setStrMeasure14(cursor.getString(35));
                Item.setStrMeasure15(cursor.getString(36));
                Item.setDateModified(cursor.getString(37));
                Item.setByteImage(cursor.getString(38));
                // Adding contact to list
                arraytimetdrink.add(Item);
            } while (cursor.moveToNext());

        }
        return arraytimetdrink;

    }


    public ArrayList<DrinkAllDetailModal> getAllRcordofDrink() {
        ArrayList<DrinkAllDetailModal> arraytimetask = new ArrayList<DrinkAllDetailModal>();

        String selectQuery = "SELECT  * FROM " + TABLE_DRINK;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DrinkAllDetailModal Item = new DrinkAllDetailModal();
                Item.setID(Integer.parseInt(cursor.getString(0)));

                Item.setIdDrink(cursor.getString(1));
                Item.setStrDrink(cursor.getString(2));
                Item.setStrCategory(cursor.getString(3));
                Item.setStrAlcoholic(cursor.getString(4));
                Item.setStrGlass(cursor.getString(5));
                Item.setStrInstructions(cursor.getString(6));
                Item.setStrIngredient1(cursor.getString(7));
                Item.setStrIngredient2(cursor.getString(8));
                Item.setStrIngredient3(cursor.getString(9));
                Item.setStrIngredient4(cursor.getString(10));
                Item.setStrIngredient5(cursor.getString(11));
                Item.setStrIngredient6(cursor.getString(12));
                Item.setStrIngredient7(cursor.getString(13));
                Item.setStrIngredient8(cursor.getString(14));
                Item.setStrIngredient9(cursor.getString(15));
                Item.setStrIngredient10(cursor.getString(16));
                Item.setStrIngredient11(cursor.getString(17));
                Item.setStrIngredient12(cursor.getString(18));
                Item.setStrIngredient13(cursor.getString(19));
                Item.setStrIngredient14(cursor.getString(20));
                Item.setStrIngredient15(cursor.getString(21));
                Item.setStrMeasure1(cursor.getString(22));
                Item.setStrMeasure2(cursor.getString(23));
                Item.setStrMeasure3(cursor.getString(24));
                Item.setStrMeasure4(cursor.getString(25));
                Item.setStrMeasure5(cursor.getString(26));
                Item.setStrMeasure6(cursor.getString(27));
                Item.setStrMeasure7(cursor.getString(28));
                Item.setStrMeasure8(cursor.getString(29));
                Item.setStrMeasure9(cursor.getString(30));
                Item.setStrMeasure10(cursor.getString(31));
                Item.setStrMeasure11(cursor.getString(32));
                Item.setStrMeasure12(cursor.getString(33));
                Item.setStrMeasure13(cursor.getString(34));
                Item.setStrMeasure14(cursor.getString(35));
                Item.setStrMeasure15(cursor.getString(36));
                Item.setDateModified(cursor.getString(37));
                Item.setByteImage(cursor.getString(38));
                Item.setStatus(cursor.getString(cursor.getColumnIndex(DRINK_STATUS)));
                // Adding contact to list
                arraytimetask.add(Item);
            } while (cursor.moveToNext());

        }

        return arraytimetask;
    }


    public List<BookmarkRecipesModel> getRecipeList() {

        String selectQuery = "SELECT  * FROM " + TABLE_RECIPES;
        List<BookmarkRecipesModel> contactList = new ArrayList<BookmarkRecipesModel>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                BookmarkRecipesModel recipesListModel = new BookmarkRecipesModel();

                recipesListModel.setId(cursor.getString(cursor.getColumnIndex(KEY_ID)));
                recipesListModel.setTitle(cursor.getString(cursor.getColumnIndex(KEY_RECIPES_NAME)));
                recipesListModel.setIngredient(cursor.getString(cursor.getColumnIndex(KEY_INGREDIENT)));
                recipesListModel.setImage(cursor.getString(cursor.getColumnIndex(KEY_RECIPES_IMAGE)));
                recipesListModel.setOrignalurl(cursor.getString(cursor.getColumnIndex(KEY_RECIPES_SOURCE_URL)));
                recipesListModel.setRank(cursor.getString(cursor.getColumnIndex(KEY_RATING)));
                Log.i("dbdata",cursor.getString(cursor.getColumnIndex(KEY_RECIPES_NAME)));

                // Adding contact to list
                contactList.add(recipesListModel);
            } while (cursor.moveToNext());
        }

        // return contact
        return contactList;

    }


    public ArrayList<VirtualWineModel> getBartendarList() {


        String selectQuery = "SELECT  * FROM " + KEY_TABLE_WINE_VINTAGE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                VirtualWineModel virtualWineModel = new VirtualWineModel();
                virtualWineModel.setStrvintage(cursor.getString(cursor.getColumnIndex(KEY_WINE_VINTAGE)));
                virtualWineModel.setStrimg(cursor.getString(cursor.getColumnIndex(KEY_RECIPES_IMAGE)));
                virtualWineModel.setStrwinery(cursor.getString(cursor.getColumnIndex(KEY_WINE_PRODUCER)));
                virtualWineModel.setStrwinename(cursor.getString(cursor.getColumnIndex(KEY_WINE_NAME)));
                virtualWineModel.setStrtype(cursor.getString(cursor.getColumnIndex(KEY_WINE_TYPE)));
                virtualWineModel.setStrsize(cursor.getString(cursor.getColumnIndex(KEY_WINE_SIZE)));
                virtualWineModel.setStrstartdate(cursor.getString(cursor.getColumnIndex(KEY_WINE_SDATE)));
                virtualWineModel.setStrenddate(cursor.getString(cursor.getColumnIndex(KEY_WINE_EDATE)));
                virtualWineModel.setStrpaidbottle(cursor.getString(cursor.getColumnIndex(KEY_WINE_PRICE)));
                virtualWineModel.setStrstorelocation(cursor.getString(cursor.getColumnIndex(KEY_WINE_STORED)));
                virtualWineModel.setStrstorewherepurchased(cursor.getString(cursor.getColumnIndex(KEY_WINE_PURCHASED)));
                virtualWineModel.setFormattedDate(cursor.getString(cursor.getColumnIndex(KEY_WINE_DATE)));
                virtualWineModel.setStrEntertestingnotes(cursor.getString(cursor.getColumnIndex(KEY_WINE_NOTE)));
                virtualWineModel.setStrEntertestin(cursor.getString(cursor.getColumnIndex(KEY_WINE_TASTED)));

                wineModels.add(virtualWineModel);
            } while (cursor.moveToNext());
        }
        return wineModels;

    }


    public int deleteContact(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletestatus = db.delete(TABLE_RECIPES, KEY_RECIPES_NAME + " = ?",
                new String[]{name});
        Log.d("msg", "deletewhitwicon status " + deletestatus);
        db.close();
        return deletestatus;
    }

    public void InsertData(DietItemsUtility dietItemsUtility) {
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
                storenameutility.setStorename(cursor.getString(1));
                arraystorenamedata.add(storenameutility);
            } while (cursor.moveToNext());
        }
        return arraystorenamedata;
    }

    public void AddItemToShop(AddItemToShopUtility addItemToShopUtility) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ITEMNAME, addItemToShopUtility.getItemname());
        cv.put(ITEMPRICE, addItemToShopUtility.getPrice());
        cv.put(ITEMWEIGHT, addItemToShopUtility.getWeight());
        cv.put(ITEMIMAGE, addItemToShopUtility.getImageinByte());
        db.insert(TABLE_SHOPITEM, null, cv);
        db.close();
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
                Item.setItemname(cursor.getString(1));
                Item.setPrice(cursor.getString(2));
                Item.setWeight(cursor.getString(3));
                Item.setImageinByte(cursor.getBlob(4).toString());
                // Adding contact to list
                arraystorenamedata.add(Item);
            } while (cursor.moveToNext());

        }
        return arraystorenamedata;
    }

    public void insert(TimeTaskUtility timeTaskUtility) {

// {
//
//
//        };


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TIMETASK_TASKTYPE, timeTaskUtility.getTimetaskresukt());
        cv.put(TIMETASK_TASKNAME, timeTaskUtility.getTaskname());
        cv.put(TIMETASK_MINUT, timeTaskUtility.getMinut());
        cv.put(TIMETASK_STATUS, timeTaskUtility.getStatus());
        String url = "http://10.10.10.1/ApiKitchen.php?apicall=createtimetask&TIMETASK_TASKTYPE="+timeTaskUtility.getTimetaskresukt()+"&TIMETASK_TASKNAME="+timeTaskUtility.getTaskname()+"&TIMETASK_MINUT="+timeTaskUtility.getMinut()+"&TIMETASK_STATUS="+timeTaskUtility.getStatus();
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

        long ss = db.insert(TABLE_TIMETASK, null, cv);
        Log.d("insert", "nn " + ss);
        db.close();
    }

    public void deletWineBottle(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + KEY_TABLE_WINE_VINTAGE + " WHERE " + KEY_WINE_ID + "='" + id + "'");
        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        db.close();
    }


    public int DeleteMyShelfItem(int rid) {

        SQLiteDatabase db = this.getWritableDatabase();
        int response = db.delete(TABLE_DRINK, DRINK_ID + " = ?",
                new String[]{String.valueOf(rid)});
        Log.d("Delete....", "nn " + response);
        db.close();
        return response;

    }

    public int DeleteDrinkItem(int rid) {
        SQLiteDatabase db = this.getWritableDatabase();
        int response = db.delete(TABLE_DRINK, DRINK_ID + " = ?",
                new String[]{String.valueOf(rid)});
        Log.d("Delete....", "nn " + response);
        db.close();
        return response;
    }

    public int DeletetimeTask(int rid) {
        SQLiteDatabase db = this.getWritableDatabase();
        int response = db.delete(TABLE_TIMETASK, TIMETASK_ID + " = ?",
                new String[]{String.valueOf(rid)});
        Log.d("Delete....", "nn " + response);
        db.close();
        return response;
    }


    public ArrayList<TimeTaskUtility> getAllTimeTaskRecord() {
        ArrayList<TimeTaskUtility> arraytimetask = new ArrayList<TimeTaskUtility>();

        String selectQuery = "SELECT  * FROM " + TABLE_TIMETASK;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TimeTaskUtility Item = new TimeTaskUtility();

                Item.setID(Integer.parseInt(cursor.getString(0)));
                Item.setTimetaskresukt(cursor.getString(1));
                Item.setTaskname(cursor.getString(2));
                Item.setMinut(cursor.getString(3));
                Item.setStatus(cursor.getString(4));
                // Adding contact to list
                arraytimetask.add(Item);
            } while (cursor.moveToNext());

        }
        return arraytimetask;
    }

    public void UpdateStatus(int lastid, String str) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TIMETASK_STATUS, str);
        db.update(TABLE_TIMETASK, cv, TIMETASK_ID + " = ?",
                new String[]{String.valueOf(lastid)});
        // updating row
    }

    /*public ArrayList<AddWineBottleModal> getAllRecordsAddBottel() {
        ArrayList<AddWineBottleModal> bootlelist = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + KEY_TABLE_WINE_VINTAGE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AddWineBottleModal contact = new AddWineBottleModal();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setByteImage(cursor.getBlob(1));
                contact.setByteImage(cursor.getBlob(2));
                contact.setStrwinery(cursor.getString(3));
                contact.setStrwinename(cursor.getString(4));
                contact.setStrtype(cursor.getString(5));
                contact.setStrenddate(cursor.getString(6));
                contact.setStrdate(cursor.getString(7));
                contact.setStrenddate(cursor.getString(8));
                contact.setStrpaidbottle(cursor.getString(9));
                contact.setStrstorelocation(cursor.getString(10));
                contact.setStrstorewherepurchased(cursor.getString(11));
                contact.setStrsize(cursor.getString(12));
                contact.setStrstartdate(cursor.getString(13));
                contact.setStrEntertestingnotes(cursor.getString(14));
               // contact.setStrEntertestin(cursor.getString(15));
                // Adding contact to list
                bootlelist.add(contact);
            } while (cursor.moveToNext());

        }
        return bootlelist;
    }*/
}
