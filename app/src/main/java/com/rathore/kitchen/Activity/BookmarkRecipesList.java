package com.rathore.kitchen.Activity;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Browser;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

//import com.archirayan.kitchen.Adapter.BookmarkItemListAdapter;
//import com.archirayan.kitchen.R;
import com.rathore.kitchen.Adapter.BookmarkItemListAdapter;
import com.rathore.kitchen.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

//import com.android.volley.toolbox.HttpResponse;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;

//import android.provider.Browser;
//import android.provider.Browser.BookmarkColumns;
//import com.google.zxing.client.android.Intents;


public class BookmarkRecipesList extends AppCompatActivity {


    public RecyclerView rv;
    public ArrayList<String> titleArray, urlArray;
    public ArrayList<Bitmap> bitmapImages;
    private Bitmap icon;
    HttpGet httpget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_recipes_list);


        titleArray = new ArrayList<>();
        bitmapImages = new ArrayList<>();
        rv = (RecyclerView) findViewById(R.id.rvBookmark);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
       // mLayoutManager.onAttachedToWindow(rv);
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());

        urlArray = new ArrayList<>();

        getBrowserHist();



       for(int i=0;i<urlArray.size();i++) {
           new HIstorydetails().execute(urlArray.get(i));
       }
    }


    public void getBrowserHist() {

        /*Cursor mCur = managedQuery(Browser.BOOKMARKS_URI,
                Browser.HISTORY_PROJECTION, null, null, null);
        mCur.moveToFirst();
        if (mCur.moveToFirst() && mCur.getCount() > 0) {
            while (mCur.isAfterLast() == false) {

                titleArray.add(mCur
                        .getString(Browser.HISTORY_PROJECTION_TITLE_INDEX));
                urlArray.add(mCur
                        .getString(Browser.HISTORY_PROJECTION_URL_INDEX));


                mCur.moveToNext();
            }
        }*/


/*
        http://api.embed.ly/1/extract?url=http%3A%2F%2Ffood.ndtv.com%2Frecipe-aloo-tamatar-ka-jhol-322237&maxwidth=500*/

//        final String[] HISTORY_PROJECTION = new String[]{
//                "_id", // 0
//                "url", // 1
//                "visits", // 2
//                "date", // 3
//                "bookmark", // 4
//                "title", // 5
//                "favicon", // 6
//                "thumbnail", // 7
//                "touch_icon", // 8
//                "user_entered", // 9
//        };
//        final int HISTORY_PROJECTION_TITLE_INDEX = 5;
//        final int HISTORY_PROJECTION_URL_INDEX = 1;
//
//        String[] proj = new String[]{Browser.BookmarkColumns.TITLE, Browser.BookmarkColumns.URL};
//        Uri uriCustom = Uri.parse("content://com.android.chrome.browser/bookmarks");
//        String sel = Browser.BookmarkColumns.BOOKMARK + " = 1"; // 0 = history, 1 = bookmark
//        Cursor mCur = getContentResolver().query(uriCustom, proj, sel, null, null);
//        mCur.moveToFirst();
//        @SuppressWarnings("unused")
//        String title = "";
//        @SuppressWarnings("unused")
//        String url = "";
//
//        if (mCur.moveToFirst() && mCur.getCount() > 0) {
//            boolean cont = true;
//            while (mCur.isAfterLast() == false && cont) {
//                title = mCur.getString(mCur.getColumnIndex(Browser.BookmarkColumns.TITLE));
//                url = mCur.getString(mCur.getColumnIndex(Browser.BookmarkColumns.URL));
//
//                Log.i("brwser history",url.toString());
//                Log.d("msg", " d  " + title + "  d " + url);
//
//                if (url.contains("food") || url.contains("recipes")) {
//                    Log.i("brwser history","called");
//
//                    urlArray.add(url);
//
//                    titleArray.add(title);
//                    Log.i("brwser history1",urlArray.toString());
//                }
//
//                // Do something with title and url
//                mCur.moveToNext();
//
//
//            }
//
//        }

       /* final String[] columns = { Browser.BookmarkColumns.TITLE, Browser.BookmarkColumns.URL };
        Cursor query = getContentResolver().query(Browser.BOOKMARKS_URI,columns, null, null, null);
        query.moveToFirst();
        while (query.moveToNext()) {
            String title = query.getString(query.getColumnIndex(Browser.BookmarkColumns.TITLE));
            String url = query.getString(query.getColumnIndex(Browser.BookmarkColumns.URL));
            Log.d("msg"," d  "+title+"  d "+url);
            urlArray.add(url);
        }*/
    }


    class HIstorydetails extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(BookmarkRecipesList.this);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            //StringBuilder sb;

            if (dialog != null) {
                dialog.dismiss();
            }

            Log.i("params", params[0]);
            HttpClient httpclient = new DefaultHttpClient(); // Create HTTP Client
            //if(urlArray.size()>0){
//             httpget = new HttpGet(urlArray.get(0));
            httpget = new HttpGet(params[0]);
//}// Set the action you want to do
            HttpResponse response = null; // Executeit
            try {
                response = httpclient.execute(httpget);
                Log.i("response", response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpEntity entity = ((HttpResponse) response).getEntity();
            InputStream is = null; // Create an InputStream with the response
            try {
                is = entity.getContent();


            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            StringBuilder sb = new StringBuilder();
            String line = null;

            try {
                while ((line = reader.readLine()) != null) // Read line by line
                    sb.append(line + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

            String resString = sb.toString(); // Result is here
            Log.i("response1", resString);


            try {
                is.close(); // Close the stream
            } catch (IOException e) {
                e.printStackTrace();
            }
//
            return resString;




           // Utils.getResponseofGet()






// new changes
//            URL url = null;
//            try {
//                url = new URL(params[0]);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//            HttpURLConnection conn = null;
//            try {
//                conn = (HttpURLConnection) url.openConnection();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            // conn.setReadTimeout(15000 /* milliseconds */);
            //conn.setConnectTimeout(15000 /* milliseconds */);
//            try {
//               // conn.setRequestMethod("GET");
//            } catch (ProtocolException e) {
//                e.printStackTrace();
//            }
            // conn.setDoInput(true);
            //conn.setDoOutput(true);
            // String s="response";

            //  OutputStream os = null;
//            try {
//                os = conn.getOutputStream();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//            BufferedWriter writer = null;
//            try {
//                writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//            } catch (UnsupportedEncodingException e1) {
//                e1.printStackTrace();
//            }
            //   writer.write(getPostDataString(postDataParams));

//            try {
//                writer.flush();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//            try {
//                writer.close();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//            try {
//                os.close();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }

//            int responseCode= 0;
//            try {
//                responseCode = conn.getResponseCode();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//
//            if (responseCode == HttpsURLConnection.HTTP_OK) {
//
//                BufferedReader in= null;
//                try {
//                    in = new BufferedReader(
//                            new InputStreamReader(
//                                    conn.getInputStream()));
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//                 sb = new StringBuilder("");
//                String line="";
//
//                try {
//                    while((line = in.readLine()) != null) {
//
//                        sb.append(line);
//                        break;
//                    }
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//
////                try {
////                    in.close();
////                } catch (IOException e1) {
////                    e1.printStackTrace();
////                }
//                Log.i("reponsev",sb.toString());
//                return sb.toString();
//
//            }
//            else {
//                return new String("false : "+responseCode);
//            }
//
//        }
////        catch(Exception e){
////            return new String("Exception: " + e.getMessage());
////        }


        }







        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.i("result " , s);
            String imageLink = null;
//title
            Document document = Jsoup.parse(s);
            Elements divs = document.select("title");
            for (Element div : divs) {
                Log.d("msg", " title  " + div.ownText());
                Log.i("responseInPostExecute1",div.ownText());
            }

//            Elements metaElems = doc.select("meta");
//
//            for (Element metaElem : metaElems) {
//                String property = metaElem.attr("property");
//                if(property.equals("og:image"))// if find the line with the image
//                {
//                    imageLink = metaElem.attr("content");
//                    Log.d(TAG, "Image URL" + imageLink );
//                }
//
//
//            }



//imageurl
            Elements divss = document.select("meta");
          for (Element div1 : divss) {
//                Log.d("msg", " image  " + div1.ownText());
//                Log.i("responseInPostExecute2",div1.ownText());
//            }

        String property = div1.attr("property");
        if(property.equals("og:image"))// if find the line with the image
        {
            imageLink = div1.attr("content");
            //Log.d(TAG, "Image URL" + imageLink );
            Log.i("responseInPostExecute2",imageLink);

        }}

//link
            Elements divsss = document.select("meta");
            for (Element div1 : divsss) {
//                Log.d("msg", " image  " + div1.ownText());
//                Log.i("responseInPostExecute2",div1.ownText());
//            }

                String property = div1.attr("property");
                if(property.equals("og:url"))// if find the line with the image
                {
                    String link = div1.attr("content");
                    //Log.d(TAG, "Image URL" + imageLink );
                    Log.i("responseInPostExecute3",link);

                }}

//ingridient
//            Elements divss1 = document.select("span");
//            for (Element div1 : divss1) {
////                Log.d("msg", " image  " + div1.ownText());
////                Log.i("responseInPostExecute2",div1.ownText());
////            }
//
//                String property = div1.attr("property");
//                if(property.equals("og:image"))// if find the line with the image
//                {
//                    String imageLink = div1.attr("content");
//                    //Log.d(TAG, "Image URL" + imageLink );
//                    Log.i("responseInPostExecute2",imageLink);
//
//                }}

//            Document document1 = Jsoup.parse(s);
//            Elements divs1 = document1.select("link");
//            for (Element div2 : divs1) {
//                Log.d("msg", " link   " + div2.ownText());
//                Log.i("responseInPostExecute3",div2.ownText());
//            }




            BookmarkItemListAdapter mAdapter = new BookmarkItemListAdapter(BookmarkRecipesList.this, titleArray, urlArray,imageLink);
            
            Log.i("titlearray",titleArray.toString());
            Log.i("urlarray",urlArray.toString());
            
           // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
           //final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());

           // rv.setLayoutManager(mLayoutManager);
//            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();


        }
    }


}