package com.rathore.kitchen.Activity;

import android.annotation.TargetApi;
import android.content.ContentUris;
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
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.rathore.kitchen.Adapter.GroceryListItemAdapter;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.AddItemToShopUtility;
import com.rathore.kitchen.Utils.GroceryAddItemUtility;
import com.rathore.kitchen.Utils.StoreNameUtility;
import com.squareup.picasso.Picasso;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ravi Archi on 12/31/2016.
 */
public class AddItemToBuyAct extends AppCompatActivity {

    public Button mButtonItemtoBuy;
    public ListView mListView;
   // public ArrayList<StoreNameUtility> arraygrocerylist;
    DatabaseHalper helper;
    public Button mButtonAddItem;
    public EditText mEditTextItemName, mEditTextItemPrice, mEditTextItemWeight;
    public Spinner selectItem;
    public ImageView mImageViewItemPicture;
    private static final int CAMERA_REQUEST = 1;
    private static final int PICK_FROM_GALLERY = 2;
    public byte[] imageInByte;
    String userChoosenTask;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    public String imagePath;
    public String GroceryList;
    public ArrayList<AddItemToShopUtility> arraydietdata;
    private ArrayList<StoreNameUtility> arraystorename;
    public ArrayList<GroceryAddItemUtility> arraygrocerylist;
    GroceryListItemAdapter adapter;
    public ArrayList<GroceryAddItemUtility> array_grocery_Item;
    private MaterialBetterSpinner materialDesignSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additemtobuy);
        helper = new DatabaseHalper(this);
        mEditTextItemName = (EditText) findViewById(R.id.edt_ItemName);
        mEditTextItemPrice = (EditText) findViewById(R.id.edt_Price);
        mEditTextItemWeight = (EditText) findViewById(R.id.edt_weight);
        mButtonAddItem = (Button) findViewById(R.id.btn_AddItemToBuy);
        mImageViewItemPicture = (ImageView) findViewById(R.id.img_addtoitemproduct);
       // arraygrocerylist = helper.getAllStoreName();
       /* ArrayAdapter<StoreNameUtility> arrayAdapter = new ArrayAdapter<StoreNameUtility>(this,
                android.R.layout.simple_dropdown_item_1line, arraygrocerylist);
        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.sp_additemtobuy_grocerylist);
        materialDesignSpinner.setAdapter(arrayAdapter);
        materialDesignSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Grocerylist = parent.getItemAtPosition(position).toString();
                Log.v("DepartmentItem", Grocerylist);
                Toast.makeText(AddItemToBuyAct.this, Grocerylist, Toast.LENGTH_SHORT).show();
            }
        });*/
        mImageViewItemPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        mButtonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ItemName = mEditTextItemName.getText().toString();
                String Price = mEditTextItemPrice.getText().toString();
                String Weight = mEditTextItemWeight.getText().toString();
                helper.AddItemToShop(new AddItemToShopUtility(GroceryList,ItemName, Price, Weight,imagePath));
                onBackPressed();
            }
        });


        if (arraystorename == null) {

            arraystorename = helper.getAllStoreName();

            //Log.e("Arrayssbyyyys" ,"" + arraystorename.toString());


        }

        if (arraystorename != null) {

           /* mspSpinner = (Spinner) findViewById(R.id.sp_StoreName);
            ArrayAdapter<StoreNameUtility> arrayAdapter = new ArrayAdapter<StoreNameUtility>(GroceryAct.this,
                    android.R.layout.simple_dropdown_item_1line, arraystorename);
            mspSpinner.setAdapter(arrayAdapter);
            mspSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectMealType = parent.getItemAtPosition(position).toString();
                    Log.v("DepartmentItem", selectMealType);
                    arraygrocerylist = helper.getAllGroceryAddItem(selectMealType);
                    Toast.makeText(GroceryAct.this, selectMealType, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });*/

          //  Log.e("Arraysssssssss" ,"" + arraystorename.toString());
            ArrayAdapter<StoreNameUtility> arrayAdapter = new ArrayAdapter<StoreNameUtility>(AddItemToBuyAct.this,
                    android.R.layout.simple_dropdown_item_1line, arraystorename);
           materialDesignSpinner = (MaterialBetterSpinner)
                    findViewById(R.id.sp_GroceryAddtiem);
            materialDesignSpinner.setAdapter(arrayAdapter);
            materialDesignSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    GroceryList = parent.getItemAtPosition(position).toString();

                    if(GroceryList != null)
                    {
                        arraygrocerylist = helper.getAllGroceryAddItem(GroceryList);
                    }else{
                        Toast.makeText(AddItemToBuyAct.this,"Please select grocery", Toast.LENGTH_SHORT).show();
                    }

                    for(int i = 0; i < arraygrocerylist.size(); i++)
                    {
                        if(arraygrocerylist.get(i).grocery.equalsIgnoreCase(GroceryList)) {
                            array_grocery_Item = new ArrayList<GroceryAddItemUtility>();
                            String G_ItemName = arraygrocerylist.get(i).getItemname().toString();
                            String G_ItemPrice = arraygrocerylist.get(i).getItemprice().toString();
                            String G_ItemWeight = arraygrocerylist.get(i).getItemwieght().toString();
                            byte[] G_ItemPic = arraygrocerylist.get(i).getItemproductimage();

                            array_grocery_Item.add(new GroceryAddItemUtility(GroceryList, G_ItemName, G_ItemPrice, G_ItemWeight, G_ItemPic));
                            Log.d("list", "" + array_grocery_Item);
                        }

                    }
                    if(GroceryList != null)
                    {
                        if(array_grocery_Item != null)
                        {
                            adapter = new GroceryListItemAdapter(AddItemToBuyAct.this, R.layout.activity_groceryact_grocerylist_item,array_grocery_Item);
                            mListView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            Log.v("DepartmentItem", GroceryList);
                            Toast.makeText(AddItemToBuyAct.this, GroceryList, Toast.LENGTH_SHORT).show();
                        }else{
                          //  Toast.makeText(AddItemToBuyAct.this,"No Items",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(AddItemToBuyAct.this," select grocery", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            Toast.makeText(AddItemToBuyAct.this, "No Data", Toast.LENGTH_SHORT).show();
        }

    }



    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;


    }
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Gallery",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddItemToBuyAct.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = true;
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
//        intent.setType("image*//*");
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
            if (requestCode == SELECT_FILE) {
                imagePath=  getPath(AddItemToBuyAct.this,data.getData());

                Bitmap bmp = null;
                try {
                    bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(data.getData()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                mImageViewItemPicture.setVisibility(View.VISIBLE);
                mImageViewItemPicture.setImageBitmap(bmp);
            }

            else if (requestCode == REQUEST_CAMERA) {

                // onCaptureImageResult(data);
            }

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
            Picasso.with(getApplicationContext()).load(mypath).into(mImageViewItemPicture);
        } catch (Exception e) {
            Log.e("SAVE_IMAGE", e.getMessage(), e);
        }


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
                    mImageViewItemPicture.setVisibility(View.VISIBLE);
                    mImageViewItemPicture.setImageBitmap(bmp);

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


}
