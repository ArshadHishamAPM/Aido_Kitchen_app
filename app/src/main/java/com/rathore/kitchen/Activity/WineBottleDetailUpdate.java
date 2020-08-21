package com.rathore.kitchen.Activity;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.rathore.kitchen.Model.AddWineBottleModal;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.DbHelper;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class WineBottleDetailUpdate extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 100;
    private int GALLERY_REQUEST = 200;

    public EditText edtBottelVintage, edtwineproducer, edtcomplitwinename, edttype, edtsize,
            edtstartdate, edtenddate, edtprice, edtstored, edtpurchased, edtdate, edttestingnote, edttestedname;
    public ImageView imgbottel;
    public String BottelVintage, wineproducer, complitwinename, type, sizes,
            startdate, enddate, pricep, stored, purchased, dates, testingnote, testedname;
    ;
    public DbHelper dbHelper;
    public Button btnupdate;
    public ArrayList<AddWineBottleModal> arrayaddbottel;
    public AddWineBottleModal addWineBottleModal;
    public int id;
   // public byte[] img;
   public String realPath = "";
    public byte[] byteArrayImage;
    public String[] sizespner, typespner;
    public String strtype, strsize;
    public String formattedDate;
    public ImageView ivwineimage;
    public Button btnsaveVirtualwine;
    public Spinner sprsize, sprtype;
    public String Strtvintage;
    public String strwinery;
    public String strwinename;
    public String strstartdate;
    public String strenddate;
    public String strpaidbottle;
    public String strstorelocation;
    public String strstorewherepurchased;
    public String strdate;
    public String strEntertestingnotes;
    public String strEntertestin;
    public String strvintage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_bottle_detail_update);
        dbHelper = new DbHelper(this);

        if (getIntent().getExtras() != null) {

            addWineBottleModal = new AddWineBottleModal();
            Gson gson = new Gson();
            String strObj = getIntent().getExtras().getString("obj");
            addWineBottleModal = gson.fromJson(strObj, AddWineBottleModal.class);

            id = addWineBottleModal.getId();
            BottelVintage = addWineBottleModal.getStrivintage();
            wineproducer = addWineBottleModal.getStrwinery();
            complitwinename = addWineBottleModal.getStrwinename();
            type = addWineBottleModal.getStrtype();
            sizes = addWineBottleModal.getStrsize();
            startdate = addWineBottleModal.getStrstartdate();
            enddate = addWineBottleModal.getStrenddate();
            pricep = addWineBottleModal.getStrstorelocation();
            stored = addWineBottleModal.getStrstorewherepurchased();
            purchased = addWineBottleModal.getStrdate();
            dates = addWineBottleModal.getStrpaidbottle();
            testingnote = addWineBottleModal.getStrEntertestingnotes();
            testedname = addWineBottleModal.getStrEntertestin();
            byteArrayImage = addWineBottleModal.getByteImage();


        }

        btnupdate = (Button) findViewById(R.id.btnupdateVirtualwine);
        edtBottelVintage = (EditText) findViewById(R.id.activity_wine_bottel_detail_update_edit_bottle_vintage);
        edtwineproducer = (EditText) findViewById(R.id.activity_wine_bottel_detail_update_edit_wine_producer);
        edtcomplitwinename = (EditText) findViewById(R.id.activity_wine_bottel_detail_update_edit_wine_name);
        edtstartdate = (EditText) findViewById(R.id.activity_wine_bottel_detail_update_edit_startdate);
        edtenddate = (EditText) findViewById(R.id.activity_wine_bottel_detail_update_edit_enddate);
        edtprice = (EditText) findViewById(R.id.activity_wine_bottel_detail_update_edit_bottleprice);
        edtstored = (EditText) findViewById(R.id.activity_wine_bottel_detail_update_edit_bottlestored);
        edtpurchased = (EditText) findViewById(R.id.activity_wine_bottel_detail_update_edit_bottlepurchased);
        edtdate = (EditText) findViewById(R.id.activity_wine_bottel_detail_update_edit_date);
        edttestingnote = (EditText) findViewById(R.id.activity_wine_bottel_detail_update_edit_testnote);
        edttestedname = (EditText) findViewById(R.id.activity_wine_bottel_detail_update_edit_other_testedyou);
        imgbottel = (ImageView) findViewById(R.id.activity_wine_bottel_detail_update_imgdata);


        edtBottelVintage.setText(BottelVintage);
        edtwineproducer.setText(wineproducer);
        edtcomplitwinename.setText(complitwinename);
       /* edttype.setText(type);
        edtsize.setText(sizes);*/
        edtstartdate.setText(startdate);
        edtenddate.setText(enddate);
        edtprice.setText(pricep);
        edtstored.setText(stored);
        edtpurchased.setText(purchased);
        edtdate.setText(dates);
        edttestingnote.setText(testingnote);
        edttestedname.setText(testedname);

        Glide.with(getApplicationContext()).load(byteArrayImage).into(imgbottel);


        sprsize = (Spinner) findViewById(R.id.sprsize);
        sprtype = (Spinner) findViewById(R.id.sprtype);
        ArrayAdapter<CharSequence> TypeAdapter = ArrayAdapter
                .createFromResource(this, R.array.size,
                        R.layout.customspinnerlist);
        TypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sprsize.setAdapter(TypeAdapter);
        ArrayAdapter<CharSequence> TypeAdapter1 = ArrayAdapter
                .createFromResource(this, R.array.type,
                        R.layout.customspinnerlist);
        TypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sprtype.setAdapter(TypeAdapter1);



        formattedDate = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        edtdate.setText(formattedDate);

        sizespner = getResources().getStringArray(R.array.size);
        typespner = getResources().getStringArray(R.array.type);

        sprsize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                strsize = sizespner[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sprtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                strtype = typespner[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        edtstartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDate();
            }
        });
        edtenddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endDate();

            }
        });
        edtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date();
            }
        });


        imgbottel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strvintage = edtwineproducer.getText().toString().trim();
                strwinery = edtBottelVintage.getText().toString().trim();
                strwinename = edtcomplitwinename.getText().toString().trim();
                strstartdate = edtstartdate.getText().toString().trim();
                strenddate = edtenddate.getText().toString().trim();
                strdate = edtdate.getText().toString().trim();
                strpaidbottle = edtprice.getText().toString().trim();
                strstorelocation = edtstored.getText().toString().trim();
                strstorewherepurchased = edtpurchased.getText().toString().trim();
                strEntertestingnotes = edttestingnote.getText().toString().trim();
                strEntertestin = edttestedname.getText().toString().trim();
                //if (ivwineimage != null) {
               //     Glide.with(getApplicationContext()).load(img).into(imgbottel);
              //  } else {

                    //ivwineimage.setImageBitmap);
               // }


                dbHelper.updateWineBottle(new AddWineBottleModal(id, byteArrayImage, strvintage, strwinery, strwinename, strtype, strsize, strstartdate,
                        strenddate, strdate, strpaidbottle, strstorelocation, strstorewherepurchased, strEntertestingnotes, strEntertestin));
                Toast.makeText(WineBottleDetailUpdate.this, "Success", Toast.LENGTH_SHORT).show();
                // onBackPressed();
                Intent intentHome = new Intent(WineBottleDetailUpdate.this, BarTanderVitualWineAct.class);
                startActivity(intentHome);


            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST && data != null) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Uri uri = getImageUri(WineBottleDetailUpdate.this, bitmap);
            realPath = getPathFromURI(uri);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            byteArrayImage = bytes.toByteArray();

            try {

                imgbottel.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else if (resultCode == Activity.RESULT_OK && requestCode == GALLERY_REQUEST && data != null) {

            Uri imageUri = data.getData();

            try {


                realPath = getPathFromURI(imageUri);
                Bitmap newBitmap = BitmapFactory.decodeFile(realPath);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                newBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                imgbottel.setImageBitmap(newBitmap);
                byteArrayImage = bytes.toByteArray();

            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            Log.d("msg", "fail ");
        }


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


    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Gallery",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(WineBottleDetailUpdate.this);
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


    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    public void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST);

    }


    private void startDate() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = 1990;
        int mMonth = 01;
        int mDay = 01;
        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String year1 = String.valueOf(year);
                        String month1 = String.valueOf(monthOfYear + 1);
                        String day1 = String.valueOf(dayOfMonth);

                        edtstartdate.setText(day1 + "-" + month1 + "-" + year1);


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();


    }

    private void endDate() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = 1990;
        int mMonth = 01;
        int mDay = 01;
        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String year1 = String.valueOf(year);
                        String month1 = String.valueOf(monthOfYear + 1);
                        String day1 = String.valueOf(dayOfMonth);

                        edtenddate.setText(day1 + "-" + month1 + "-" + year1);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();


    }

    private void Date() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = 1990;
        int mMonth = 01;
        int mDay = 01;
        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String year1 = String.valueOf(year);
                        String month1 = String.valueOf(monthOfYear + 1);
                        String day1 = String.valueOf(dayOfMonth);

                        edtdate.setText(day1 + "-" + month1 + "-" + year1);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();


    }


}
