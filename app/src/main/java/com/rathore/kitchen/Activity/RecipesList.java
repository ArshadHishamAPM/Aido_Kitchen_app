package com.rathore.kitchen.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import com.archirayan.kitchen.Adapter.RecipesListAdapter;
//import com.archirayan.kitchen.Model.RecipesListModel;
//import com.archirayan.kitchen.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.rathore.kitchen.Adapter.RecipesListAdapter;
import com.rathore.kitchen.Model.RecipesListModel;
import com.rathore.kitchen.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipesList extends AppCompatActivity implements View.OnClickListener {


    public ArrayList<RecipesListModel> recipesListModelsArray;
    public EditText edtSearch;

    public Button btnSearch;
    public RecyclerView rv;
    private String recipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);

        edtSearch = (EditText) findViewById(R.id.edtSearch);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        recipesListModelsArray = new ArrayList<>();
        rv = (RecyclerView) findViewById(R.id.recycler_view);
       // edtSearch.setText("Braised lamb shanks with crushed herb potatoes");
        recipeName = "";
        btnSearch.setOnClickListener(this);


        getRecipesList();
    }


    public void getRecipesList() {


        final ProgressDialog progressDialog = new ProgressDialog(RecipesList.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        recipesListModelsArray = new ArrayList<>();

        Log.d("searchParameter",recipeName);

        Ion.with(RecipesList.this)
                .load("http://food2fork.com/api/search?")
                .progressDialog(progressDialog)
                .setMultipartParameter("key","152a5660c9c4a9a8495ba74999e4b4d4")
                .setMultipartParameter("q", recipeName)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {


                        try {


                            Log.d("msg","recipes list res "+result);
                            Log.i("searchresult",result);
                            progressDialog.dismiss();
                            if(result!=null){
                            JSONObject jsonObjectMain = new JSONObject(result);
                            JSONArray jsonArrayRecipes = jsonObjectMain.getJSONArray("recipes");
                            for (int i = 0; i < jsonArrayRecipes.length(); i++) {

                                JSONObject jsonObjectRecipes = jsonArrayRecipes.getJSONObject(i);
                                RecipesListModel recipesListModel = new RecipesListModel();
                                recipesListModel.setPublisher(jsonObjectRecipes.getString("publisher"));
                                recipesListModel.setF2f_url(jsonObjectRecipes.getString("f2f_url"));
                                recipesListModel.setTitle(jsonObjectRecipes.getString("title"));
                                recipesListModel.setSource_url(jsonObjectRecipes.getString("source_url"));
                                recipesListModel.setRecipe_id(jsonObjectRecipes.getString("recipe_id"));
                                recipesListModel.setImage_url(jsonObjectRecipes.getString("image_url"));
                                recipesListModel.setSocial_rank(jsonObjectRecipes.getString("social_rank"));
                                recipesListModel.setPublisher_url(jsonObjectRecipes.getString("publisher_url"));


                                recipesListModelsArray.add(recipesListModel);
                                Log.i("receipebysearch",jsonObjectRecipes.toString());


                            }


                            RecipesListAdapter mAdapter = new RecipesListAdapter(RecipesList.this, recipesListModelsArray);
                                                                            //Linearlayputmanager
                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),3);
                            //rv.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                            rv.setLayoutManager(mLayoutManager);
                            rv.setItemAnimator(new DefaultItemAnimator());
                            rv.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();}


                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }


                    }
                });


    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btnSearch:


                recipeName = edtSearch.getText().toString();



                getRecipesList();
                break;
        }
    }
}
