package com.rathore.kitchen.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rathore.kitchen.Adapter.RecipeItemAdapter;
import com.rathore.kitchen.Model.BookmarkRecipesModel;
import com.rathore.kitchen.R;
import com.rathore.kitchen.Utils.DbHelper;

import java.util.ArrayList;
import java.util.List;


public class RecipesItemAct extends AppCompatActivity implements View.OnClickListener {

    public ArrayList<String> itemArray;
    public List<BookmarkRecipesModel> bookmarkRecipesModels;
    RecyclerView rv;
    EditText edtMenu;
    Button btnAdd, btnBookmarkList, btnSearch;
    private DbHelper dbHelper;
    private RecipeItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_item);
        rv = (RecyclerView) findViewById(R.id.recycler_view);
        edtMenu = (EditText) findViewById(R.id.edtMenu);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnBookmarkList = (Button) findViewById(R.id.btnBookmarklist);
        btnBookmarkList.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnSearch = (Button) findViewById(R.id.btnRecipesSearch);
        btnSearch.setOnClickListener(this);
        itemArray = new ArrayList<>();
        dbHelper = new DbHelper(RecipesItemAct.this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (bookmarkRecipesModels == null) {

        } else {
            bookmarkRecipesModels.clear();
        }


        bookmarkRecipesModels = dbHelper.getRecipeList();
        Log.i("bookmarkreceipelist",String.valueOf(bookmarkRecipesModels.size()));
        mAdapter = new RecipeItemAdapter(RecipesItemAct.this, bookmarkRecipesModels);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        bookmarkRecipesModels = dbHelper.getRecipeList();
        Log.i("bookmarkreceipelist",String.valueOf(bookmarkRecipesModels.size()));
        mAdapter = new RecipeItemAdapter(RecipesItemAct.this, bookmarkRecipesModels);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnAdd:

                String menu = edtMenu.getText().toString();
                itemArray.add(menu);
                String imageUri = "drawable://" + R.mipmap.ic_image_not_fount;
                BookmarkRecipesModel bookmarkRecipesModelAdd = new BookmarkRecipesModel();
                bookmarkRecipesModelAdd.setTitle(menu);
                bookmarkRecipesModelAdd.setIngredient("");
                bookmarkRecipesModelAdd.setRank("0");
                bookmarkRecipesModelAdd.setImage(imageUri);
                bookmarkRecipesModelAdd.setOrignalurl("");
                bookmarkRecipesModelAdd.setId("0");
                bookmarkRecipesModels.add(bookmarkRecipesModelAdd);
                dbHelper.addRecipes(menu, "0", "", imageUri, "");
                mAdapter = new RecipeItemAdapter(RecipesItemAct.this, bookmarkRecipesModels);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                rv.setLayoutManager(mLayoutManager);
                rv.setItemAnimator(new DefaultItemAnimator());
                rv.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                break;

            case R.id.btnBookmarklist:
                Intent intentBookmark = new Intent(RecipesItemAct.this, BookmarkRecipesList.class);
                startActivity(intentBookmark);
                break;

            case R.id.btnRecipesSearch:
                Intent intentsearch = new Intent(RecipesItemAct.this,RecipesList.class);
                startActivity(intentsearch);
                break;

        }
    }
}

