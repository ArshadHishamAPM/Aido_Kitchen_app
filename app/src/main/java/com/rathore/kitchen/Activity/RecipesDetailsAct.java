package com.rathore.kitchen.Activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
//import com.archirayan.kitchen.Model.BookmarkRecipesModel;
//import com.archirayan.kitchen.R;
//import com.archirayan.kitchen.Utils.DbHelper;
import com.rathore.kitchen.Utils.Utils;
import com.bumptech.glide.Glide;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.rathore.kitchen.Model.BookmarkRecipesModel;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.DbHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RecipesDetailsAct extends AppCompatActivity implements View.OnClickListener {


    public LinearLayout liIngredients;
    public TextView tvName, tvRating;
    public ImageView ivRecipes;
    public String rid = "";
    Bitmap newBitmap;

    private String bookmarkUrl = "",imageurl="";
    private String REGISTER_URL = "http://api.diffbot.com/v3/extract?";
    private TextView tvSourceUrl;
    private ImageView ivsave1,ivsave;
    private BookmarkRecipesModel bookmarkRecipesModel;
    public BookmarkRecipesModel bookmarkRecipesModelIntent;
    private int CAMERA_REQUEST = 100;
    private int GALLERY_REQUEST = 200;
    private String realPath = "";
    private DbHelper dbHelper;
    private String recipeEditName;
    private NotificationCompat.Builder builder;
    private long timeRemaining = 0;
    private ImageView ivTime,ivTime1, ivDelete,ivDelete1, ivEdit,ivEdit1;
    private String status;
    public String sourceurl;
    //  private String REGISTER_URL = "http://api.embed.ly/1/extract?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipes_details);
        dbHelper = new DbHelper(RecipesDetailsAct.this);
        Intent intent=getIntent();
        String sourceurl=intent.getStringExtra("bookmarkurl");
       //Log.i("urll1",sourceurl);
        tvSourceUrl = (TextView) findViewById(R.id.tvsourceurl);
//        tvSourceUrl.setText("sourceurl");
        ivTime = (ImageView) findViewById(R.id.img_setTime);
        ivTime1 = (ImageView) findViewById(R.id.img_setTime1);
        ivEdit = (ImageView) findViewById(R.id.iveditRecipedetail);
        ivEdit1 = (ImageView) findViewById(R.id.iveditRecipedetail1);
        ivsave = (ImageView) findViewById(R.id.activity_detail_ivsave);
        ivsave1 = (ImageView) findViewById(R.id.activity_detail_ivsave1);
        ivEdit.setOnClickListener(this);
        ivsave.setOnClickListener(this);
        ivDelete = (ImageView) findViewById(R.id.ivDeleteRecipeDetail);
        ivDelete1 = (ImageView) findViewById(R.id.ivDeleteRecipeDetail1);
        ivDelete.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            Log.i("status", bundle.toString());

            if (bundle != null) {
                Log.i("status", bundle.toString());
                status = bundle.getString("status");
            }


            if (status.equalsIgnoreCase("0")) {
                ivEdit.setVisibility(View.GONE);
                ivEdit1.setVisibility(View.GONE);
                ivsave.setVisibility(View.VISIBLE);
                ivsave1.setVisibility(View.VISIBLE);
                ivDelete.setVisibility(View.GONE);
                ivDelete1.setVisibility(View.GONE);

            }


            if (status.equalsIgnoreCase("1")) {
                ivEdit.setVisibility(View.VISIBLE);
                ivEdit1.setVisibility(View.VISIBLE);
                ivsave.setVisibility(View.GONE);
                ivsave1.setVisibility(View.GONE);
            }
        }

        tvSourceUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(RecipesDetailsAct.this, "TEXT", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RecipesDetailsAct.this, ActivityWebview.class);
                intent.putExtra("url", tvSourceUrl.getText().toString());
                // Log.e("url",bookmarkRecipesModelIntent.getOrignalurl());
                startActivity(intent);
            }
        });


//        ivTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder alert = new AlertDialog.Builder(RecipesDetailsAct.this);
//                LayoutInflater inflater = getLayoutInflater();
//                final View alertLayout = inflater.inflate(R.layout.customtime, null);
//                alert.setTitle("Add Time");
//                // this is set the view from XML inside AlertDialog
//                alert.setView(alertLayout);
//                // disallow cancel of AlertDialog on click of back button and outside touch
//                alert.setCancelable(false);
//                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        // Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //EditText mEditTextHour = (EditText) alertLayout.findViewById(R.id.edt_hour);
//                        EditText mEditTextMinit = (EditText) alertLayout.findViewById(R.id.edt_minit);
//                        //String strhour = mEditTextHour.getText().toString();
//                        String strminit = mEditTextMinit.getText().toString();
//                        //int hour = Integer.parseInt(strhour);
//                        long min = Integer.parseInt(strminit);
//
//                        //Log.d("hour", strhour);
//
//                        //setTimer(minit);
//                        long mm = min * 60 * 1000;
//                        // int hh = hour * 3600 * 100;
//                        final CounterClass timer = new CounterClass(mm, 1000);
//                        timer.start();
//                        //setTimer(hour, minit);
//
//                    }
//                });
//                AlertDialog dialog = alert.create();
//                dialog.show();
//            }
//        });

        Bundle bundlee = getIntent().getExtras();
        if (bundle != null) {
            if (bundlee.containsKey("rid")) {

                rid = bundlee.getString("rid");
                Log.i("rid",rid);

            }

            if (bundle.containsKey("bookmarkurl")) {
                bookmarkUrl = bundle.getString("bookmarkurl");
                imageurl = bundle.getString("imageurl");
                Log.i("urll",bookmarkUrl);
                //tvSourceUrl.setText(bookmarkUrl);
                //ivRecipes = (ImageView) findViewById(R.id.ivRecipesRecipesDetails);
                //Glide.with(RecipesDetailsAct.this)
                      //  .load(imageurl).into(ivRecipes);


            }

            if (bundle.containsKey("recipedetails")) {
                bookmarkRecipesModelIntent = (BookmarkRecipesModel) bundle.getSerializable("recipedetails");

            }

        }

        Log.d("msg", "recipe url " + bookmarkUrl);

        liIngredients = (LinearLayout) findViewById(R.id.liIngredients);
        liIngredients.setOnClickListener(this);
        tvName = (TextView) findViewById(R.id.tvnameRecipesDetails);
        tvName.setOnClickListener(this);

        tvSourceUrl = (TextView) findViewById(R.id.tvsourceurl);
        tvRating = (TextView) findViewById(R.id.tvRatingRecipesDetails);
        ivRecipes = (ImageView) findViewById(R.id.ivRecipesRecipesDetails);
        ivRecipes.setOnClickListener(this);
        /*ivsave = (ImageView) findViewById(R.id.ivsave);*/

       /* ivsave.setOnClickListener(this);*/


        //    BookmarkUrlExtractData();

        //   new GetData().execute();

        //BookmarkUrlExtractDataa();

        if (!bookmarkUrl.equalsIgnoreCase("")) {

            BookmarkUrlExtractDataa1();
        } else if (!rid.equalsIgnoreCase("")) {

            RecipesDetails();
        }


        if (bookmarkRecipesModelIntent != null) {


            tvName.setText(bookmarkRecipesModelIntent.getTitle());

            tvSourceUrl.setText(bookmarkRecipesModelIntent.getOrignalurl());
            tvRating.setText("Rank : " + bookmarkRecipesModelIntent.getRank());

            String img = bookmarkRecipesModelIntent.getImage();

            String ingredietn = bookmarkRecipesModelIntent.getIngredient();
            List<String> items = new ArrayList<String>(Arrays.asList(ingredietn.split(",")));

            if (!img.equalsIgnoreCase("")) {
                Glide.with(RecipesDetailsAct.this)
                        .load(img).into(ivRecipes);
            }


            for (int j = 0; j < items.size(); j++) {

                String ingredients = (String) items.get(j);
                Log.d("msg", "ingredients " + ingredients);

                // Create LinearLayout
                LinearLayout ll = new LinearLayout(RecipesDetailsAct.this);
                ll.setOrientation(LinearLayout.VERTICAL);

                // Create TextView
                TextView product = new TextView(RecipesDetailsAct.this);
                product.setTextSize(35);
                product.setTextColor(getResources().getColor(R.color.white));
                product.setText(j + 1 + " )    " + ingredients);
                ll.addView(product);



                View view = new View(RecipesDetailsAct.this);
                //view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));


                liIngredients.addView(ll);
                liIngredients.addView(view);


            }


        }

        // registerUser();
    }

    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            //textViewTime.setText("Completed.");
            addNotificationEnd();
        }

        @Override
        public void onTick(long millisUntilFinished) {

            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            //textViewTime.setText(hms);
            addNotification(hms);
        }
    }

    public void RecipesDetails() {

/*
        http://food2fork.com/api/get?key=152a5660c9c4a9a8495ba74999e4b4d4&rId=47899*/


        final ProgressDialog progressDialog = new ProgressDialog(RecipesDetailsAct.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Ion.with(RecipesDetailsAct.this)
                .load("http://food2fork.com/api/get?")
                .progressDialog(progressDialog)
                .setMultipartParameter("key", "152a5660c9c4a9a8495ba74999e4b4d4")
                .setMultipartParameter("rId", rid)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {

                        try {

                            Log.d("msg", "recipes list detail  res " + result);
                            progressDialog.dismiss();

                            JSONObject jsonObjectMain = new JSONObject(result);
                            JSONObject jsonObjectRecipes = jsonObjectMain.getJSONObject("recipe");

                            JSONArray jsonArrayingredients = jsonObjectRecipes.getJSONArray("ingredients");
                            String imgUrl = jsonObjectRecipes.getString("image_url");
                            String social_rank = jsonObjectRecipes.getString("social_rank");
                            String title = jsonObjectRecipes.getString("title");
                            sourceurl = jsonObjectRecipes.getString("source_url");


                            tvName.setText(title);

                            tvRating.setText("Rating : " + social_rank);
                            tvSourceUrl.setText(sourceurl);


                            bookmarkRecipesModel = new BookmarkRecipesModel();
                            bookmarkRecipesModel.setTitle(title);

                            bookmarkRecipesModel.setOrignalurl(sourceurl);
                            bookmarkRecipesModel.setImage(imgUrl);
                            bookmarkRecipesModel.setRank(social_rank);


                            if (!imgUrl.equalsIgnoreCase(""))

                            {
                                Glide.with(RecipesDetailsAct.this).load(imgUrl).placeholder(R.drawable.image_loader).into(ivRecipes);
                            }

                            StringBuffer stringBuffer = new StringBuffer();
                            for (int j = 0; j < jsonArrayingredients.length(); j++) {

                                String ingredients = (String) jsonArrayingredients.get(j);
                                Log.d("msg", "ingredients " + ingredients);
                                stringBuffer.append(ingredients);
                                stringBuffer.append(",");
                                // Create LinearLayout
                                LinearLayout ll = new LinearLayout(RecipesDetailsAct.this);
                                ll.setOrientation(LinearLayout.VERTICAL);

                                // Create TextView
                                TextView product = new TextView(RecipesDetailsAct.this);
                                product.setText(j + 1 + " )    " + ingredients);
                                ll.addView(product);


                                liIngredients.addView(ll);


                            }
                            bookmarkRecipesModel.setIngredient(stringBuffer.toString());
                            Log.i("title",bookmarkRecipesModel.getTitle());


                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }


                    }
                });


    }

    public void BookmarkUrlExtractData() {


    /*    http://api.embed.ly/1/extract?
         url=http://www.epicurious.com/recipes/food/views/our-favorite-french-onion-soup-51248680&maxwidth=500
         &key=20dff8688c3d4a4fa3e50ee33e4351f5
    */


     /* final ProgressDialog progressDialog = new ProgressDialog(RecipesDetailsAct.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Ion.with(RecipesDetailsAct.this)
                .load("http://api.embed.ly/1/extract?\n" +
                        "         url=http://www.epicurious.com/recipes/food/views/our-favorite-french-onion-soup-51248680&maxwidth=500\n" +
                        "         &key=20dff8688c3d4a4fa3e50ee33e4351f5")
                .
                .progressDialog(progressDialog)

                .asString()
                .setCallback(new FutureCallback<String>() {
                    public String url;

                    @Override
                    public void onCompleted(Exception e, String result) {

                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }


                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            String des = jsonObject.getString("description");
                            String title = jsonObject.getString("title");
                            String orignalurl = jsonObject.getString("url");
                            JSONArray jsonArrayImage = jsonObject.getJSONArray("images");

                            for (int i = 0; i < 1; i++) {
                                JSONObject jsonObjectImage = jsonArrayImage.getJSONObject(i);
                                url = jsonObjectImage.getString("url");
                                Log.d("msg", " url " + url + "  title  " + title + " des " + des);
                            }


                            BookmarkRecipesModel bookmarkRecipesModel = new BookmarkRecipesModel();
                            bookmarkRecipesModel.setTitle(title);
                            bookmarkRecipesModel.setBookmark(des);
                            bookmarkRecipesModel.setOrignalurl(orignalurl);
                            bookmarkRecipesModel.setImage(url);

                            tvName.setText(title);

                            if (!url.equalsIgnoreCase("")) {
                                Glide.with(RecipesDetailsAct.this)
                                        .load(url).placeholder(R.drawable.image_loader).into(ivRecipes);
                            }


                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                    }
                });*/


    }

    private void registerUser() {


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("url", "http://food.ndtv.com/recipe-whole-wheat-pasta-in-mushroom-sauce-218185");
        hashMap.put("key", "20dff8688c3d4a4fa3e50ee33e4351f5");
        hashMap.put("maxwidth", "500");


        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, REGISTER_URL, new JSONObject(hashMap),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(RecipesDetailsAct.this, "" + response.toString(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RecipesDetailsAct.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

// add the request object to the queue to be executed
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(req);


    }


    private void registerUserr() throws UnsupportedEncodingException {

        String query = URLEncoder.encode("http://food.ndtv.com/recipe-whole-wheat-pasta-in-mushroom-sauce-218185", "utf-8");

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("url", query);
        hashMap.put("token", "9e4ab311416c8931f4a9a696489be1a3");


        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, REGISTER_URL, new JSONObject(hashMap),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(RecipesDetailsAct.this, "" + response.toString(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RecipesDetailsAct.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

// add the request object to the queue to be executed
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(req);


    }

    public void BookmarkUrlExtractDataa() {


        final ProgressDialog progressDialog = new ProgressDialog(RecipesDetailsAct.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Ion.with(RecipesDetailsAct.this)
                .load("http://api.diffbot.com/v3/extract?")
                .setMultipartParameter("url", "http%3A%2F%2Ffood2fork.com%2Fview%2FPerfect_Iced_Coffee%2F47024")
                .setMultipartParameter("token", "ab311416c8931f4a9a696489be1a3")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    public String url;

                    @Override
                    public void onCompleted(Exception e, String result) {

                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }
                        Log.d("msg", "new res " + result);
/*

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            String des = jsonObject.getString("description");
                            String title = jsonObject.getString("title");
                            String orignalurl = jsonObject.getString("url");
                            JSONArray jsonArrayImage = jsonObject.getJSONArray("images");

                            for (int i = 0; i < 1; i++) {
                                JSONObject jsonObjectImage = jsonArrayImage.getJSONObject(i);
                                url = jsonObjectImage.getString("url");
                                Log.d("msg", " url " + url + "  title  " + title + " des " + des);
                            }

*/
/*

                            BookmarkRecipesModel bookmarkRecipesModel = new BookmarkRecipesModel();
                            bookmarkRecipesModel.setTitle(title);
                            bookmarkRecipesModel.setBookmark(des);
                            bookmarkRecipesModel.setOrignalurl(orignalurl);
                            bookmarkRecipesModel.setImage(url);
*//*


                            tvName.setText(title);

                            if (!url.equalsIgnoreCase("")) {
                                Glide.with(RecipesDetailsAct.this)
                                        .load(url).placeholder(R.drawable.image_loader).into(ivRecipes);
                            }


                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
*/

                    }
                });


    }


    public void BookmarkUrlExtractDataa1() {

//http://web-medico.com/web2/kitchen/api/get_kitchen_data.php?
//        Ion.with(RecipesDetailsAct.this).load(bookmarkUrl)
//                //.setMultipartParameter("url", bookmarkUrl)
//                //.setMultipartContentType("json")
//                .asJsonObject()
//                .setCallback(new FutureCallback<JsonObject>() {
//            @Override
//            public void onCompleted(Exception e, JsonObject result) {
//               // String status =  result.get("status").toString();
//                Log.i("resut",result.toString());
//
//            }
//        });










        Ion.with(RecipesDetailsAct.this)
                .load("http://web-medico.com/web2/kitchen/api/get_kitchen_data.php?")
                .setMultipartParameter("url", bookmarkUrl)

                .asString()


                .setCallback(new FutureCallback<String>() {
                    public String rank;
                    public String url;

                    @Override
                    public void onCompleted(Exception e, String result) {
                        String imageLink = null;

                        Log.d("msg", "new res " + result);
                        Log.i("res",result);
//




                        try {

                            JSONObject jsonObject = new JSONObject(result);
                            String status = jsonObject.getString("status");
                            Log.i("statuss",jsonObject.toString());


                            if (status.equalsIgnoreCase("true")) {

                                JSONObject jsonObjectdata = jsonObject.getJSONObject("data");
                                String des = jsonObjectdata.getString("ingredients");
                                String title = jsonObjectdata.getString("title");
                                String url = jsonObjectdata.getString("url");
                                Log.i("jsonresult",des +"  "+ title);


                                List<String> items = new ArrayList<String>(Arrays.asList(des.split(",")));
                                Log.d("msg", "size ingredient " + items.toString());

                                String img = jsonObjectdata.getString("image");


                                Log.d("msg", " url " + url + "  title  " + title + " des " + des + " img  " + img);

                                bookmarkRecipesModel = new BookmarkRecipesModel();
                                bookmarkRecipesModel.setTitle(title);
                                bookmarkRecipesModel.setIngredient(des);
                                bookmarkRecipesModel.setOrignalurl(url);
                                bookmarkRecipesModel.setImage(img);
                                bookmarkRecipesModel.setRank(rank);


                                tvName.setText(title);

                                tvSourceUrl.setText(url);
                                tvRating.setText("Rank : " + rank);

                                if (!img.equalsIgnoreCase("")) {
                                    Glide.with(RecipesDetailsAct.this)
                                            .load(img).placeholder(R.drawable.pizza).into(ivRecipes);
                                }


                                for (int j = 0; j < items.size(); j++) {

                                    String ingredients = (String) items.get(j);
                                    Log.d("msg", "ingredients " + ingredients);

                                    // Create LinearLayout
                                    LinearLayout ll = new LinearLayout(RecipesDetailsAct.this);
                                    ll.setOrientation(LinearLayout.VERTICAL);

                                    // Create TextView
                                    TextView product = new TextView(RecipesDetailsAct.this);
                                    product.setText(j + 1 + " )    " + ingredients);
                                    ll.addView(product);

                                    View view = new View(RecipesDetailsAct.this);
                                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));


                                    liIngredients.addView(ll);
                                    liIngredients.addView(view);


                                }


                            }


                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }


                    }
                });


    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.activity_detail_ivsave:

                Toast.makeText(RecipesDetailsAct.this, "save", Toast.LENGTH_SHORT).show();


                List<BookmarkRecipesModel> bookmarkRecipesModels = dbHelper.getRecipeList();
                int size=bookmarkRecipesModels.size();


                for (int i = 0; i < bookmarkRecipesModels.size(); i++) {

                    BookmarkRecipesModel bookmarkRecipesModell = bookmarkRecipesModels.get(i);
                    Log.i("title new " ,bookmarkRecipesModell.getTitle());


                }


                boolean isrecipe = true;

                for (int i = 0; i < bookmarkRecipesModels.size(); i++) {

                    BookmarkRecipesModel bookmarkRecipesModell = bookmarkRecipesModels.get(i);

                    Log.d("msg", "all data   " + bookmarkRecipesModell.toString());

                    String title = bookmarkRecipesModell.getTitle();

                    String titlenew = bookmarkRecipesModel.getTitle();

                    Log.d("msg", "title " + title + "  new title  " + titlenew);

                    if (title.equalsIgnoreCase(titlenew)) {

                        isrecipe = false;
                        Log.d("msg", " flag " + isrecipe);

                    } else {

                        Log.d("msg", " flag  else " + isrecipe);
                        isrecipe = true;

                    }


                }


                Log.d("msg", " is available " + isrecipe);


                if (isrecipe) {
                    dbHelper.addRecipes(bookmarkRecipesModel.getTitle(), bookmarkRecipesModel.getRank(), bookmarkRecipesModel.getIngredient(), bookmarkRecipesModel.getImage(), bookmarkRecipesModel.getOrignalurl());

                    Toast.makeText(RecipesDetailsAct.this, "save", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RecipesDetailsAct.this, "Already exist", Toast.LENGTH_SHORT).show();

                }


                break;


            case R.id.ivRecipesRecipesDetails:
                if(status.equalsIgnoreCase("0")){
                    Log.i("editimage","cannot edit");}
                else {
                    selectImage();
                }

                break;

            case R.id.tvnameRecipesDetails:
                if(status.equalsIgnoreCase("0")){
                Log.i("editname","cannot edit");}
                else {
                    editNameDialog("name");
                }

                break;


            case R.id.ivDeleteRecipeDetail:


                int status = dbHelper.deleteContact(bookmarkRecipesModelIntent.getTitle());
                if (status == 1)

                {
                    Toast.makeText(RecipesDetailsAct.this, "deletewhitwicon recipe", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RecipesDetailsAct.this, "try again", Toast.LENGTH_SHORT).show();

                }


                break;

            case R.id.iveditRecipedetail:


                editNameDialog("ingredient");

                break;

        }

    }

    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    public void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST);

    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Gallery",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(RecipesDetailsAct.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public String userChoosenTask;

            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = true;

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        openCamera();

                } else if (items[item].equals("Choose from Gallery")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        openGallery();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int hight=ivRecipes.getHeight();
        int weight=ivRecipes.getWidth();
        Log.i("imageviewsize", String.valueOf(hight)+"  "+ String.valueOf(weight));


        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST && data != null) {

        Log.i("camera","called");
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Uri uri = getImageUri(RecipesDetailsAct.this, bitmap);
            realPath = getPathFromURI(uri);
           Bitmap newBitmap1 = BitmapFactory.decodeFile(realPath);


            try {

                ivRecipes.setImageBitmap(newBitmap1);
            } catch (Exception e) {
                e.printStackTrace();
            }



//            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//            thumbnail.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
//           // byteArray = bytes.toByteArray();
//
//            File destination = new File(Environment.getExternalStorageDirectory(),
//                    System.currentTimeMillis() + ".jpg");
//            Uri tempUri = getImageUri(getApplicationContext(), thumbnail);
//            String path = tempUri.toString();
//            FileOutputStream fo;
//            try {
//                destination.createNewFile();
//                fo = new FileOutputStream(destination);
//                fo.write(bytes.toByteArray());
//                fo.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            //ivRecipes.setImageBitmap(thumbnail);

        } else if (resultCode == Activity.RESULT_OK && requestCode == GALLERY_REQUEST && data != null) {

            Uri imageUri = data.getData();

            try {


                realPath = getPathFromURI(imageUri);
                 newBitmap = BitmapFactory.decodeFile(realPath);
               Bitmap b= newBitmap.createScaledBitmap(newBitmap,450,650,true);
//                ImageView iv  = (ImageView)waypointListView.findViewById(R.id.waypoint_picker_photo);
//                Bitmap d = new BitmapDrawable(ctx.getResources() , w.photo.getAbsolutePath()).getBitmap();
//                int nh = (int) ( d.getHeight() * (512.0 / d.getWidth()) );
//                Bitmap scaled = Bitmap.createScaledBitmap(d, 512, nh, true);
//                iv.setImageBitmap(scaled);
                ivRecipes.setImageBitmap(b);
                Log.i("imagechanged","called");
            } catch (Exception e) {

                e.printStackTrace();
            }


        } else {
            Log.d("msg", "fail ");
        }


        if (realPath != null) {
if(bookmarkRecipesModelIntent!=null){
            DbHelper dbHelper = new DbHelper(RecipesDetailsAct.this);
            long updateStatus = dbHelper.updateContact(bookmarkRecipesModelIntent, realPath, "");
            Log.d("msg", "update status " + updateStatus);

        }}


    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getPathFromURI(Uri contentUri) {

        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(proj[0]);
            res = cursor.getString(columnIndex);
        }

        cursor.close();
        return res;


    }

    public void editNameDialog(final String status) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(RecipesDetailsAct.this);
        alertDialog.setTitle("Edit");
        alertDialog.setMessage("Enter " + status);

        final EditText input = new EditText(RecipesDetailsAct.this);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input); // uncomment this line
        if (status.equalsIgnoreCase("ingredient")) {
            input.setText(bookmarkRecipesModelIntent.getIngredient());
        }

        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        if (status.equalsIgnoreCase("ingredient")) {

                            recipeEditName = input.getText().toString();

                            bookmarkRecipesModelIntent.setIngredient(recipeEditName);
                            long uddateStatus = dbHelper.updateContact(bookmarkRecipesModelIntent, realPath, bookmarkRecipesModelIntent.getTitle());
                            Log.d("msg", "update name status " + uddateStatus);


                        } else {
                            recipeEditName = input.getText().toString();
                            tvName.setText(recipeEditName);

                            long uddateStatus = dbHelper.updateContact(bookmarkRecipesModelIntent, realPath, recipeEditName);
                            Log.d("msg", "update name status " + uddateStatus);
                        }
                        onBackPressed();

                        //recreate();
                       /* Intent i=new Intent(RecipesDetailsAct.this,RecipesDetailsAct.class);
                        startActivity(i);*/
                    }
                });


        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();


    }


   /* private void setTimer(int hr, int min) {
        CountDownTimer timer;
        long millisInFuture = 60000; //60 seconds = 1 minit
        long countDownInterval = 1000; //1 second

       *//* long hour = hr * 60;
        long minut = min + hour;
        long second = minut * 60;*//*

        long minit = 1;
        timer = new CountDownTimer(millisInFuture, countDownInterval) {
            public void onTick(long millisUntilFinished) {
                //1 second = 1000 milliseconds

                long tt = millisUntilFinished / 1000;

                //  Toast.makeText(RecipesDetailsAct.this, ""+tt, Toast.LENGTH_SHORT).show();
                timeRemaining = millisUntilFinished;
                addNotification(tt);

            }

            @Override
            public void onFinish() {
                addNotificationEnd();
            }
        }.start();

    }*/

    private void addNotificationEnd() {
        builder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplication())
                .setSmallIcon(R.drawable.clock)
                .setContentTitle("Time")
                .setContentText("Done");
        Intent notificationIntent = new Intent(this, RecipesDetailsAct.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    private void addNotification(String tt) {

        builder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplication())
                .setSmallIcon(R.drawable.clock)
                .setContentTitle("Time")
                .setContentText(tt);
        Intent notificationIntent = new Intent(this, RecipesDetailsAct.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }


    class GetData extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(Void... params) {


            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("url", "http://food.ndtv.com/recipe-whole-wheat-pasta-in-mushroom-sauce-218185");
            hashMap.put("key", "20dff8688c3d4a4fa3e50ee33e4351f5");
            hashMap.put("maxwidth", "500");

            return new Utils().getResponseofPost("http://api.embed.ly/1/extract?", hashMap);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("msg", " res " + s);

        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("restart","called");
       // ivRecipes.setImageBitmap(newBitmap);
//

    }
}
