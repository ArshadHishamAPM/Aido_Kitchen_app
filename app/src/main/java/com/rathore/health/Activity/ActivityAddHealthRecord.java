package com.rathore.health.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.archi.health.DbHelper.DbHelper;
//import com.example.archi.health.R;
//import com.example.archi.health.utilities.Utility;
//import com.pierfrancescosoffritti.aytplayersample.DbHelper.DbHelper;
//import com.pierfrancescosoffritti.aytplayersample.R;
//import com.pierfrancescosoffritti.aytplayersample.utilities.Utility;

import com.rathore.health.DbHelper.DbHelper;
import com.rathore.health.R;
import com.rathore.health.utilities.Utility;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by archi on 12/21/2016.
 */

public class ActivityAddHealthRecord extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private LinearLayout llHiddenContent;
    private TextView tvShowHiddenContent;
    private EditText etname, etDateOfBirth, etnormalbodytemp, etallergies, ethealthcondi, etsurgaryhistory, ethospitalhistory, etnotes;
    private RadioGroup rgroupgender;
    private RadioButton rbnmale, rbnfemale;
    private Spinner bloodtype;
    private CircleImageView imgprofile;
    private ImageView toolimg;
    private String image = "";
    private String picturePath, fileName, encodedString;
    private Bitmap bitmap;
    private File mFileTemp;
    private String base64Image = "";
    String userChoosenTask;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    public String imagePath;
    TextView tootlbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health_record);
        ArrayList bgArray = new ArrayList();
        bgArray.add("O");
        bgArray.add("AB+");
        bgArray.add("O-");
        bgArray.add("A");
        bgArray.add("B");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
       // toolimg = (ImageView) toolbar.findViewById(R.id.toolbar_save_iv);
         tootlbarTitle = (TextView)toolbar.findViewById(R.id.toolbar_title);
        tootlbarTitle.setText("SAVE");
        //toolimg.setVisibility(View.VISIBLE);
        llHiddenContent = (LinearLayout) findViewById(R.id.activity_add_health_ll_view_linear_layout);
        //tvShowHiddenContent = (TextView) findViewById(R.id.activity_add_health_record_show_hidden_content);
        etname = (EditText) findViewById(R.id.activity_add_health_record_edittxtname);
        imgprofile = (CircleImageView) findViewById(R.id.activity_add_health_record_imgprofile);
        rgroupgender = (RadioGroup) findViewById(R.id.activity_add_health_record_radiogroup);
       // rgroupgender.colo(getResources().getColor(R.color.white));
        etDateOfBirth = (EditText) findViewById(R.id.activity_add_health_record_et_date_of_birth);
        bloodtype = (Spinner) findViewById(R.id.activity_add_health_record_bloodgpspinner);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_view, bgArray);
       // bloodtype.setAdapter(adapter);
        bloodtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.white));
               // ((TextView) parent.getChildAt(position)).setTextColor(Color.WHITE);
               // ((TextView) parent.getChildAt(0)).setTextSize(5);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        etnormalbodytemp = (EditText) findViewById(R.id.activity_add_health_record_edttxt_normal_body_temprature);
        etallergies = (EditText) findViewById(R.id.activity_add_health_record_edttxt_ellergies);
        ethealthcondi = (EditText) findViewById(R.id.activity_add_health_record_edttxt_condition);
        etsurgaryhistory = (EditText) findViewById(R.id.activity_add_health_record_surgerie_history);
        ethospitalhistory = (EditText) findViewById(R.id.activity_add_health_record_previous_hos_history);
        etnotes = (EditText) findViewById(R.id.activity_add_health_record_notes);
        setSupportActionBar(toolbar);
        init();
    }

    private void init() {
        toolbar.setTitleTextColor(ContextCompat.getColor(ActivityAddHealthRecord.this, R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityAddHealthRecord.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
     //   tvShowHiddenContent.setOnClickListener(this);
        etDateOfBirth.setOnClickListener(this);
        tootlbarTitle.setOnClickListener(this);
        imgprofile.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.activity_add_health_record_show_hidden_content:
//
//                if (llHiddenContent.getVisibility() == View.VISIBLE) {
//                    llHiddenContent.setVisibility(View.GONE);
//                } else {
//                    llHiddenContent.setVisibility(View.VISIBLE);
//                }
//                break;

            case R.id.activity_add_health_record_et_date_of_birth:

                openDateOfBirth();
                break;


            case R.id.activity_add_health_record_imgprofile:
                selectImage();
                break;


            case R.id.toolbar_title:



                int selectedId = rgroupgender.getCheckedRadioButtonId();

                //  rgroupgender int selectedId = rgroupgender.getCheckedRadioButtonId();
                rbnmale = (RadioButton) findViewById(selectedId);

                if (!etname.getText().toString().equalsIgnoreCase(""))
                {
                    if(!etDateOfBirth.getText().toString().equalsIgnoreCase(""))
                    {

                        if (!etnormalbodytemp.getText().toString().equalsIgnoreCase(""))
                        {
                            if (!etallergies.getText().toString().equalsIgnoreCase(""))
                            {
                                if(!ethealthcondi.getText().toString().equalsIgnoreCase(""))
                                {
                                    if (!etsurgaryhistory.getText().toString().equalsIgnoreCase(""))
                                    {

                                        if (!ethospitalhistory.getText().toString().equalsIgnoreCase(""))
                                        {

                                            if (!etnotes.getText().toString().equalsIgnoreCase(""))
                                            {

                                                DbHelper helper = new DbHelper(getApplicationContext());

//                helper.test();
                                                helper.addHealthRecord(etname.getText().toString(),
                                                        rbnmale.getText().toString(),
                                                        imagePath,
                                                        etDateOfBirth.getText().toString(),
                                                        bloodtype.getSelectedItem().toString(),
                                                        etnormalbodytemp.getText().toString(),
                                                        etallergies.getText().toString(),
                                                        ethealthcondi.getText().toString(),
                                                        etsurgaryhistory.getText().toString(),
                                                        ethospitalhistory.getText().toString(),
                                                        etnotes.getText().toString());

                                                Toast.makeText(this, "health record Added Succesfully!!!", Toast.LENGTH_SHORT).show();
                                                onBackPressed();
                                                break;
                                            }
                                            else {
                                                Toast.makeText(ActivityAddHealthRecord.this, "please enter your notes", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else{
                                            Toast.makeText(ActivityAddHealthRecord.this, "please enter your hospitalhistory", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                    else {
                                        Toast.makeText(ActivityAddHealthRecord.this, "please enter your surgaryhistory", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(ActivityAddHealthRecord.this, "please enter your healthcondition", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(ActivityAddHealthRecord.this, "please enter your allergies", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(ActivityAddHealthRecord.this, "please enter Blood temprature", Toast.LENGTH_SHORT).show();
                        }
                }
                    else {
                        Toast.makeText(ActivityAddHealthRecord.this, "please selec birthdate", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ActivityAddHealthRecord.this, "please enter the name", Toast.LENGTH_SHORT).show();
                }

              /*  if (base64Image.length() > 10) {
                    Log.d("Hi", "hi");
                } else {
                    Log.d("Hi", "hiLow");
                }*/

        }
    }


    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Gallery",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAddHealthRecord.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(ActivityAddHealthRecord.this);
                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Gallery")) {
                    userChoosenTask = "Choose from Gallery";
                    if (result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    private void galleryIntent() {
        Intent intentPickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intentPickImage, SELECT_FILE);

//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(ACTION_GET_CONTENT);//
//        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_SHORT).show();
        }
    }


    private void onCaptureImageResult(Intent data) {
        Bitmap resizedbitmap = (Bitmap) data.getExtras().get("data");
        Log.e("CAMERA", ">>>> " + resizedbitmap);
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("profile", Context.MODE_PRIVATE);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File mypath = new File(directory, System.currentTimeMillis() + ".png");

        Matrix matrix = new Matrix();
        matrix.postRotate(getImageOrientation(mypath.getPath()));
        Bitmap rotatedBitmap = Bitmap.createBitmap(resizedbitmap, 0, 0, resizedbitmap.getWidth(),
                resizedbitmap.getHeight(), matrix, true);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
            Picasso.with(getApplicationContext()).load(mypath).into(imgprofile);
        } catch (Exception e) {
            Log.e("SAVE_IMAGE", e.getMessage(), e);
        }

//imgprofile.setImageBitmap(resizedbitmap);
//        ivImage.setImageBitmap(thumbnail);
    }

    // get Captured Image Rotation Degree
    public static int getImageOrientation(String imagePath) {
        int rotate = 0;
        try {

            File imageFile = new File(imagePath);
            ExifInterface exif = new ExifInterface(
                    imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotate;
    }

    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bitmap = null;
        Uri selectedImagePath = null;
        if (data != null) {
            try {

                Uri selectedImage = data.getData();
//                photoURI = selectedImage;
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);

                if (cursor == null || cursor.getCount() < 1) {

                    return; // no cursor or no record. DO YOUR ERROR HANDLING
                }

                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                if (columnIndex < 0) // no column index
                    return; // DO YOUR ERROR HANDLING

                String picturePath = cursor.getString(columnIndex);
                Log.d("picturePath",picturePath);
                cursor.close(); // close cursor

                try {


                    Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
                    imgprofile.setVisibility(View.VISIBLE);
                    imgprofile.setImageBitmap(bmp);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
//                selectedImagePath = data.getData();
//                Log.e("selectedImagePath", "GLRY " + selectedImagePath);
//                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
//                Log.e("BITMAP", ">>>>> " + bitmap);
//                if (bitmap != null) {
//                    // convert bitmap to base64
//                    String base64Image = Utils.getEncoded64ImageStringFromBitmap(bitmap);
//                    Picasso.with(getApplicationContext()).load(selectedImagePath).into(imgprofile);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
        }

    }


    private void openDateOfBirth() {
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
                        etDateOfBirth.setText(dayOfMonth + "-" + monthOfYear + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
