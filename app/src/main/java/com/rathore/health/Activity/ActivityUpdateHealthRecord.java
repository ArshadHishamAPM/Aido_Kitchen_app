package com.rathore.health.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by archi on 12/29/2016.
 */

public class ActivityUpdateHealthRecord extends AppCompatActivity implements View.OnClickListener {


    private Toolbar toolbar;
    private EditText etname, etDateOfBirth, etnormalbodytemp, etallergies, ethealthcondi, etsurgaryhistory, ethospitalhistory, etnotes;
    private RadioGroup rgroupgender;
    private RadioButton rbnmale, rbnfemale;
    private TextView tvShowHiddenContent;
    private EditText bloodtype;
    private CircleImageView imgprofile;
    private Button btnUpdate, btnDelete;
    private String image = "";
    String userChoosenTask;
    private LinearLayout llHiddenContent;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    public String imagePath;
    private String strId, gender;
    private DbHelper dbHelper;
    private String strSelectedItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_health_record);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(ActivityUpdateHealthRecord.this, R.color.white));

        etname = (EditText) findViewById(R.id.activity_update_health_record_edittxtname);
        imgprofile = (CircleImageView) findViewById(R.id.activity_update_health_record_update_imgprofile);
        rgroupgender = (RadioGroup) findViewById(R.id.activity_update_health_record_radiogroup);
        rbnmale = (RadioButton) findViewById(R.id.activity_update_health_record_rbtn_male);
        rbnfemale = (RadioButton) findViewById(R.id.activity_update_health_record_rbtn_female);
        llHiddenContent = (LinearLayout) findViewById(R.id.activity_update_health_update_ll_view_linear_layout);
       // tvShowHiddenContent = (TextView) findViewById(R.id.activity_update_health_record_show_hidden_content);
        etDateOfBirth = (EditText) findViewById(R.id.activity_update_health_record_et_date_of_birth);
        bloodtype = (EditText) findViewById(R.id.activity_update_health_record_edittxt_bloodtype);
        etnormalbodytemp = (EditText) findViewById(R.id.activity_update_health_record_edttxt_normal_body_temprature);
        etallergies = (EditText) findViewById(R.id.activity_update_health_record_edttxt_ellergies);
        ethealthcondi = (EditText) findViewById(R.id.activity_update_health_record_edttxt_condition);
        etsurgaryhistory = (EditText) findViewById(R.id.activity_update_health_record_surgerie_history);
        ethospitalhistory = (EditText) findViewById(R.id.activity_update_health_record_previous_hos_history);
        etnotes = (EditText) findViewById(R.id.activity_update_health_record_notes);
        btnDelete = (Button) findViewById(R.id.activity_update_healthrecord_btn_delete);
        btnUpdate = (Button) findViewById(R.id.activity_update_healthrecord_btn_update);
        init();
    }

    private void init() {
        dbHelper = new DbHelper(ActivityUpdateHealthRecord.this);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityUpdateHealthRecord.this, R.drawable.ic_toolbar_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (getIntent().getExtras() != null) {
            strId = getIntent().getExtras().getString("hr_id");
            etname.setText(getIntent().getExtras().getString("hr_name"));
            imagePath = getIntent().getExtras().getString("hr_profile_img");
            Bitmap bitmap = BitmapFactory.decodeFile(getIntent().getExtras().getString("hr_profile_img"));
            imgprofile.setImageBitmap(bitmap);
            gender = getIntent().getExtras().getString("hr_gender");
            if (gender.equalsIgnoreCase("male")) {
                rbnmale.setChecked(true);
            } else {
                rbnfemale.setChecked(true);

            }
            etDateOfBirth.setText(getIntent().getExtras().getString("hr_birthdate"));
            bloodtype.setText(getIntent().getExtras().getString("hr_bloodtype"));
            etnormalbodytemp.setText(getIntent().getExtras().getString("hr_nomal_body_temp"));
            etallergies.setText(getIntent().getExtras().getString("hr_allergies"));
            ethealthcondi.setText(getIntent().getExtras().getString("hr_health_condition"));
            etsurgaryhistory.setText(getIntent().getExtras().getString("hr_surgeries_his"));
            ethospitalhistory.setText(getIntent().getExtras().getString("hr_hospitalization_his"));
            etnotes.setText(getIntent().getExtras().getString("hr_notes"));

        }


        imgprofile.setOnClickListener(this);
        bloodtype.setOnClickListener(this);
        etDateOfBirth.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        llHiddenContent.setOnClickListener(this);
       // tvShowHiddenContent.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

//            case R.id.activity_update_health_record_show_hidden_content:
//
//                if (llHiddenContent.getVisibility() == View.VISIBLE) {
//                    llHiddenContent.setVisibility(View.GONE);
//                } else {
//                    llHiddenContent.setVisibility(View.VISIBLE);
//                }
//                break;


            case R.id.activity_update_health_record_edittxt_bloodtype:
                diaologSelectValue(getResources().getStringArray(R.array.bloodgp_array), bloodtype);
                break;

            case R.id.activity_update_health_record_et_date_of_birth:
                opencalender(etDateOfBirth);
                break;

            case R.id.activity_update_health_record_update_imgprofile:
                selectImage();
                break;

            case R.id.activity_update_healthrecord_btn_delete:
                deleteRecordComfirmation();
                break;


            case R.id.activity_update_healthrecord_btn_update:
                updateRecord();
                //Toast.makeText(ActivityUpdateHealthRecord.this, "update re record", Toast.LENGTH_SHORT).show();
                break;


        }

    }

    private void updateRecord() {
        String strName, strGender, strProfile, strBirthdate, strBloodtype, strNormalBodyTemprature, strAllergies, strHealthcondition, strSurgaryHostory, strHospitlHostory, strNotes;
        strName = etname.getText().toString();
        strGender = etDateOfBirth.getText().toString();
        strProfile = imagePath;


        strBirthdate = etDateOfBirth.getText().toString();
        strBloodtype = bloodtype.getText().toString();
        strNormalBodyTemprature = etnormalbodytemp.getText().toString();
        strAllergies = etallergies.getText().toString();
        strHealthcondition = ethealthcondi.getText().toString();
        strSurgaryHostory = ethealthcondi.getText().toString();
        strHospitlHostory = ethospitalhistory.getText().toString();
        strNotes = etnotes.getText().toString();
        dbHelper.updateHealthRecord(strId, strName, strGender, strProfile, strBirthdate, strBloodtype, strNormalBodyTemprature, strAllergies, strHealthcondition, strSurgaryHostory, strHospitlHostory, strNotes);
        Toast.makeText(ActivityUpdateHealthRecord.this, "Recored update", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    private void opencalender(final EditText etStartdate) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String strTime = dayOfMonth + "-" + (mMonth + 1) + "-" + year;
                        etStartdate.setText(strTime);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }


    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Gallery",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityUpdateHealthRecord.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(ActivityUpdateHealthRecord.this);
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


            imagePath=mypath.getPath();
            Picasso.with(getApplicationContext()).load(mypath).into(imgprofile);

        } catch (Exception e) {

            Log.e("SAVE_IMAGE", e.getMessage(), e);
        }

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
            Log.i("exception",e.toString());
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
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);

                if (cursor == null || cursor.getCount() < 1) {
                    return; // no cursor or no record. DO YOUR ERROR HANDLING
                }

                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                Log.i("imagepathh",imagePath);
                if (columnIndex < 0) // no column index
                    return; // DO YOUR ERROR HANDLING
                String picturePath = cursor.getString(columnIndex);
                Log.d("picturePath", picturePath);
                cursor.close(); // close cursor

                try {


                    Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
                    imgprofile.setVisibility(View.VISIBLE);
                    imgprofile.setImageBitmap(bmp);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
        }

    }


    private void diaologSelectValue(final String[] stringArray, final EditText etTypes) {
        final Dialog dialog = new Dialog(ActivityUpdateHealthRecord.this);
        dialog.setContentView(R.layout.dialog_list);
        ListView listView = (ListView) dialog.findViewById(R.id.dialog_list_listview);
        ArrayAdapter<String> selectedValue = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
        listView.setAdapter(selectedValue);
        final StringBuilder strBuilder = new StringBuilder();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strBuilder.append(stringArray[position].toString());
                // Toast.makeText(ActivityUpdateMedicine.this,selectedItem,Toast.LENGTH_SHORT).show();
                strSelectedItem = strBuilder.toString();
                etTypes.setText(strSelectedItem);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void deleteRecordComfirmation() {
        final AlertDialog.Builder aleBuilder = new AlertDialog.Builder(ActivityUpdateHealthRecord.this);
        aleBuilder.setMessage(getString(R.string.do_you_realy_want_to_delete));
        aleBuilder.setCancelable(false);
        aleBuilder.setTitle(getString(R.string.delete));
        aleBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.deleteHealthRecord(strId);
                onBackPressed();
            }
        });

        aleBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        aleBuilder.create();
        aleBuilder.show();
    }
}
