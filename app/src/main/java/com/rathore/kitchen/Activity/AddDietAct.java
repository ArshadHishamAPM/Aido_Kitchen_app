package com.rathore.kitchen.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.DietItemsUtility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ravi Archi on 12/26/2016.
 */
public class AddDietAct extends AppCompatActivity {

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    public Toolbar mToolbar;
    DatabaseHalper helper;
    public Button mButtonSave;
    private String userChoosenTask;
    public Spinner mSpinnerMealType;
    public LinearLayout mLayoutNatruents;
    public ImageView mImageViewAddNutrients, mImageViewIngredients,mImageViewp_image;
    public int flag = 0;
    public EditText mEditTextIngredients1, mEditTextIngredients2, mEditTextIngredients3, mEditTextIngredients4, mEditTextIngredients5, mEditTextIngredients6, mEditTextIngredients7,
            mEditTextIngredients8, mEditTextIngredients9, mEditTextIngredients10, mEditTextCalories, mEditTextTitle, mEditTextCarbohydrates, mEditTextNutrientsFat,
            mEditTextSaturatedfat, mEditTextProtein, mEditTextFiber, mEditTextSugars, mEditTextSodium, mEditTextedt_Note;
    public String selectMealType, sDate,mCurrentTime;
    public TextView mTextViewDate, mTextViewTime;
    public Date mDateTime;
    byte[] byteArray;
    public String DateFormate = "HH:mm:a";
    public ArrayList<DietItemsUtility> arraydietdata;

    LinearLayout mEditTextIngredients11,mEditTextIngredients21,mEditTextIngredients31,mEditTextIngredients41,mEditTextIngredients51,mEditTextIngredients61,mEditTextIngredients71,mEditTextIngredients81,mEditTextIngredients91,mEditTextIngredients101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddiet);
        helper = new DatabaseHalper(this);

        mDateTime = new Date();
        SimpleDateFormat mDateFormate = new SimpleDateFormat(DateFormate);
        mCurrentTime = mDateFormate.format(mDateTime);
        mTextViewTime = (TextView) findViewById(R.id.txt_mealtime);
        mTextViewTime.setText(mCurrentTime);
        //mLayoutNatruents = (LinearLayout) findViewById(R.id.ly_Natruents);
        mButtonSave = (Button) findViewById(R.id.btn_Save);

        mEditTextCalories = (EditText) findViewById(R.id.edt_Calories);
        mEditTextTitle = (EditText) findViewById(R.id.edt_Title);
        mEditTextCarbohydrates = (EditText) findViewById(R.id.edt_Nutrients_Carbohydrates);
        mEditTextNutrientsFat = (EditText) findViewById(R.id.edt_Nutrients_Fat);
        mEditTextSaturatedfat = (EditText) findViewById(R.id.edt_Nutrients_Saturatedfat);
        mEditTextProtein = (EditText) findViewById(R.id.edt_Nutrients_Protein);
        mEditTextFiber = (EditText) findViewById(R.id.edt_Nutrients_Fiber);
        mEditTextSugars = (EditText) findViewById(R.id.edt_Nutrients_Sugars);
        mEditTextSodium = (EditText) findViewById(R.id.edt_Nutrients_Sodium);
        mEditTextedt_Note = (EditText) findViewById(R.id.edt_Note);
        mImageViewp_image = (ImageView)findViewById(R.id.img_UserImage);
       // mImageViewAddNutrients = (ImageView) findViewById(R.id.img_addNutrients);
        mImageViewIngredients = (ImageView) findViewById(R.id.img_add_Ingredients);
        mEditTextIngredients1 = (EditText) findViewById(R.id.edt_ingredients1);
        mEditTextIngredients2 = (EditText) findViewById(R.id.edt_ingredients2);
        mEditTextIngredients3 = (EditText) findViewById(R.id.edt_ingredients3);
        mEditTextIngredients4 = (EditText) findViewById(R.id.edt_ingredients4);
        mEditTextIngredients5 = (EditText) findViewById(R.id.edt_ingredients5);
        mEditTextIngredients6 = (EditText) findViewById(R.id.edt_ingredients6);
        mEditTextIngredients7 = (EditText) findViewById(R.id.edt_ingredients7);
        mEditTextIngredients8 = (EditText) findViewById(R.id.edt_ingredients8);
        mEditTextIngredients9 = (EditText) findViewById(R.id.edt_ingredients9);
        mEditTextIngredients10 = (EditText) findViewById(R.id.edt_ingredients10);

        mEditTextIngredients11 = (LinearLayout) findViewById(R.id.edt_ingredients11);
        mEditTextIngredients21 = (LinearLayout) findViewById(R.id.edt_ingredients21);
        mEditTextIngredients31 = (LinearLayout) findViewById(R.id.edt_ingredients31);
        mEditTextIngredients41 = (LinearLayout) findViewById(R.id.edt_ingredients41);
        mEditTextIngredients51 = (LinearLayout) findViewById(R.id.edt_ingredients51);
        mEditTextIngredients61 = (LinearLayout) findViewById(R.id.edt_ingredients61);
        mEditTextIngredients71 = (LinearLayout) findViewById(R.id.edt_ingredients71);
        mEditTextIngredients81 = (LinearLayout) findViewById(R.id.edt_ingredients81);
        mEditTextIngredients91 = (LinearLayout) findViewById(R.id.edt_ingredients91);
        mEditTextIngredients101 = (LinearLayout) findViewById(R.id.edt_ingredients101);


        Calendar c = Calendar.getInstance();
        sDate = c.get(Calendar.MONTH) + 1 + "/"
                + c.get(Calendar.DAY_OF_MONTH)
                + "/" + c.get(Calendar.YEAR);

        mTextViewDate = (TextView) findViewById(R.id.txt_mealdate);
        mTextViewDate.setText(sDate);
        mSpinnerMealType = (Spinner) findViewById(R.id.sp_MealTypeadd);
        String[] fiilliste = getResources().getStringArray(R.array.MealType);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddDietAct.this,
                android.R.layout.simple_dropdown_item_1line, fiilliste);
        mSpinnerMealType.setAdapter(arrayAdapter);
        mSpinnerMealType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectMealType = parent.getItemAtPosition(position).toString();
                Log.v("DepartmentItem", selectMealType);
                Toast.makeText(AddDietAct.this, selectMealType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
//        mImageViewAddNutrients.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mLayoutNatruents.setVisibility(View.VISIBLE);
//            }
//        });
        mImageViewp_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        mImageViewIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag == 0) {
                    mEditTextIngredients1.setVisibility(View.VISIBLE);
                    mEditTextIngredients11.setVisibility(View.VISIBLE);
                    flag++;
                } else if (flag == 1) {
                    mEditTextIngredients2.setVisibility(View.VISIBLE);
                    mEditTextIngredients21.setVisibility(View.VISIBLE);
                    flag++;
                } else if (flag == 2) {
                    mEditTextIngredients3.setVisibility(View.VISIBLE);
                    mEditTextIngredients31.setVisibility(View.VISIBLE);
                    flag++;
                } else if (flag == 3) {
                    mEditTextIngredients4.setVisibility(View.VISIBLE);
                    mEditTextIngredients41.setVisibility(View.VISIBLE);
                    flag++;
                } else if (flag == 4) {
                    mEditTextIngredients5.setVisibility(View.VISIBLE);
                    mEditTextIngredients51.setVisibility(View.VISIBLE);
                    flag++;
                } else if (flag == 5) {
                    mEditTextIngredients6.setVisibility(View.VISIBLE);
                    mEditTextIngredients61.setVisibility(View.VISIBLE);
                    flag++;
                } else if (flag == 6) {
                    mEditTextIngredients7.setVisibility(View.VISIBLE);
                    mEditTextIngredients71.setVisibility(View.VISIBLE);
                    flag++;
                } else if (flag == 7) {
                    mEditTextIngredients8.setVisibility(View.VISIBLE);
                    mEditTextIngredients81.setVisibility(View.VISIBLE);
                    flag++;
                } else if (flag == 8) {
                    mEditTextIngredients9.setVisibility(View.VISIBLE);
                    mEditTextIngredients91.setVisibility(View.VISIBLE);
                    flag++;
                } else if (flag == 9) {
                    mEditTextIngredients10.setVisibility(View.VISIBLE);
                    mEditTextIngredients101.setVisibility(View.VISIBLE);
                    flag++;
                }

            }
        });
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String calories, title, ingredients1, ingredients2, ingredients3, ingredients4, ingredients5, ingredients6, ingredients7, ingredients8, ingredients9, ingredients10,
                        carbohydrates, fat, saturatedfat, protein, fiber, sugars, sodium, note;

                if(byteArray!=null){




                calories = mEditTextCalories.getText().toString();
                title = mEditTextTitle.getText().toString();

                carbohydrates = mEditTextCarbohydrates.getText().toString();
                fat = mEditTextNutrientsFat.getText().toString();
                saturatedfat = mEditTextSaturatedfat.getText().toString();
                protein = mEditTextProtein.getText().toString();
                fiber = mEditTextFiber.getText().toString();
                sugars = mEditTextSugars.getText().toString();
                sodium = mEditTextSodium.getText().toString();
                note = mEditTextedt_Note.getText().toString();


                if(calories.equalsIgnoreCase(""))
                {
                    mEditTextCalories.setError("Enter Calories");
                }else if(title.equalsIgnoreCase("")){
                    mEditTextTitle.setError("Enter Title");
                }/*else if(carbohydrates.equalsIgnoreCase("")){
                    mEditTextCarbohydrates.setError("Enter Carbohydrates");
                }else if(fat.equalsIgnoreCase("")){
                    mEditTextNutrientsFat.setError("Enter Fat");
                }else if(saturatedfat.equalsIgnoreCase("")){
                    mEditTextSaturatedfat.setError("Enter Saturatedfat");
                }else if(protein.equalsIgnoreCase("")){
                    mEditTextProtein.setError("Enter Protein");
                }else if(fiber.equalsIgnoreCase("")){
                    mEditTextFiber.setError("Enter Faber");
                }else if(sugars.equalsIgnoreCase("")){
                    mEditTextSugars.setError("Enter Sugars");
                }else if(sodium.equalsIgnoreCase("")){
                    mEditTextSodium.setError("Enter Sodium");
                }else if(note.equalsIgnoreCase("")){
                    mEditTextedt_Note.setError("Enter Note");
                }*/else{
                    ingredients1 = mEditTextIngredients1.getText().toString();
                    ingredients2 = mEditTextIngredients2.getText().toString();
                    ingredients3 = mEditTextIngredients3.getText().toString();
                    ingredients4 = mEditTextIngredients4.getText().toString();
                    ingredients5 = mEditTextIngredients5.getText().toString();
                    ingredients6 = mEditTextIngredients6.getText().toString();
                    ingredients7 = mEditTextIngredients7.getText().toString();
                    ingredients8 = mEditTextIngredients8.getText().toString();
                    ingredients9 = mEditTextIngredients9.getText().toString();
                    ingredients10 = mEditTextIngredients10.getText().toString();
                    helper.InsertData(new DietItemsUtility(calories, title, ingredients1, ingredients2, ingredients3, ingredients4, ingredients5, ingredients6, ingredients7, ingredients8, ingredients9, ingredients10,
                            carbohydrates, fat, saturatedfat, protein, fiber, sugars, sodium, note,selectMealType,sDate,mCurrentTime,byteArray));
                    onBackPressed();
                }}
                else {


                Toast.makeText(getApplicationContext(),"please upload a photo", Toast.LENGTH_SHORT).show();
                }


            }
        });

        arraydietdata = helper.getAllRecords();

    }
    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Gallery",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(AddDietAct.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= true;

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Gallery")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }
    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        byteArray = bytes.toByteArray();

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        Uri tempUri = getImageUri(getApplicationContext(), thumbnail);
        String path = tempUri.toString();
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mImageViewp_image.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        //Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                byteArray = bytes.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mImageViewp_image.setImageBitmap(bm);
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}
