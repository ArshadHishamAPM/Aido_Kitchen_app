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

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Ravi Archi on 1/10/2017.
 */
public class AddVirtualWineManagerAct extends AppCompatActivity implements View.OnClickListener{

    private static final int CAMERA_REQUEST = 100;
    EditText edtvintage,
            edtwinery,
            edtwinename,
            edtstartdate,
            edtenddate,
            edtpaidbottle,
            edtstorelocation,
            edtstorewherepurchased,
            edtdate,
            edtEntertestingnotes,
            edtEntertestin;

    Button btnsaveVirtualwine;
    byte[] byteArrayImage;
    Spinner sprsize,
            sprtype;
    public String Strtvintage;
    public int id;
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
    public String[] sizespner, typespner;
    public DbHelper dbHelper;
    public String strtype, strsize;
    public String formattedDate;
    public int GALLERY_REQUEST = 200;
    public String realPath = "";
    public ImageView ivwineimage;
    public ArrayList<AddWineBottleModal> mArraylistbottle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvirtualwine);
        dbHelper = new DbHelper(AddVirtualWineManagerAct.this);
        edtvintage = (EditText) findViewById(R.id.edtvintage);
        edtwinery = (EditText) findViewById(R.id.edtwinery);
        edtwinename = (EditText) findViewById(R.id.edtwinename);
        edtstartdate = (EditText) findViewById(R.id.edtstartdate);
        edtenddate = (EditText) findViewById(R.id.edtenddate);
        edtpaidbottle = (EditText) findViewById(R.id.edtpaidbottle);
        edtstorelocation = (EditText) findViewById(R.id.edtstorelocation);
        edtstorewherepurchased = (EditText) findViewById(R.id.edtstorewherepurchased);
        edtdate = (EditText) findViewById(R.id.edtdate);
        edtEntertestingnotes = (EditText) findViewById(R.id.edtEntertestingnotes);
        edtEntertestin = (EditText) findViewById(R.id.edtEntertestin);
        ivwineimage = (ImageView) findViewById(R.id.ivwineimage);
        btnsaveVirtualwine = (Button) findViewById(R.id.btnsaveVirtualwine);
        init();


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

    }
    public void init() {


        btnsaveVirtualwine.setOnClickListener(this);
        ivwineimage.setOnClickListener(this);
        edtstartdate.setOnClickListener(this);
        edtenddate.setOnClickListener(this);
        edtdate.setOnClickListener(this);


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

//


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ivwineimage:
                selectImage();

                break;

            case R.id.edtstartdate:
                startDate();

                break;

            case R.id.edtenddate:
                endDate();

                break;

            case R.id.edtdate:
                Date();

                break;

            case R.id.btnsaveVirtualwine:

                strvintage = edtvintage.getText().toString();
                strwinery = edtwinery.getText().toString();
                strwinename = edtwinename.getText().toString();
                strstartdate = edtstartdate.getText().toString();
                strenddate = edtenddate.getText().toString();
                strdate = edtdate.getText().toString();
                strpaidbottle = edtpaidbottle.getText().toString();
                strstorelocation = edtstorelocation.getText().toString();
                strstorewherepurchased = edtstorewherepurchased.getText().toString();
                strEntertestingnotes = edtEntertestingnotes.getText().toString();
                strEntertestin = edtEntertestin.getText().toString();


                 dbHelper.addwine(new AddWineBottleModal( id,byteArrayImage, strvintage, strwinery, strwinename, strtype, strsize, strstartdate,
                        strenddate,strdate, strpaidbottle, strstorelocation, strstorewherepurchased, strEntertestingnotes, strEntertestin));
                Toast.makeText(AddVirtualWineManagerAct.this, "Success", Toast.LENGTH_SHORT).show();

               // onBackPressed();
               Intent intentHome = new Intent(AddVirtualWineManagerAct.this, BarTanderVitualWineAct.class);
                startActivity(intentHome);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST && data != null) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Uri uri = getImageUri(AddVirtualWineManagerAct.this, bitmap);
            realPath = getPathFromURI(uri);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            byteArrayImage = bytes.toByteArray();

            try {

                ivwineimage.setImageBitmap(bitmap);
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
                ivwineimage.setImageBitmap(newBitmap);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(AddVirtualWineManagerAct.this);
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
