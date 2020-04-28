package com.rathore.health.Activity;

//import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
//import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rathore.health.R;
import com.rathore.health.model.HospitalFindModel;
import com.rathore.health.utilities.Utils;

import com.google.android.gms.location.LocationListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ActivityHospitalFind extends AppCompatActivity implements OnMapReadyCallback,
        LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private static final String TAG_RESULTS = "results";
    private static final String TAG_GEOMETRY = "geometry";
    private static final String TAG_VIEWPORT = "viewport";
    private static final String TAG_NORTHEAST = "northeast";
    private static final String TAG_LAT = "lat";
    private static final String TAG_LNG = "lng";
    private JSONArray results = null;


    //    http://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=true_or_false
    private EditText etLocation;
    private static final String TAG = "ActivityHospitalFind";
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mCurrentLocation;
    Location mCurrentLocationn;
    String mLastUpdateTime;
    ImageView tvNearMe;

    InputMethodManager inputMethodManager;


    private static final int PERMISSION_FINE_LOCATION = 100;
    private GoogleMap mMap;
    private Toolbar toolbar;
    //    private String api = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=51.503186,-0.126446&radius=5000&types=hospital&key=AIzaSyBLtqT2B29mDArsE3lDcFtxcILXVggQjzg";
    private Utils utils;
    private String latitude, longitude, addressStr;
    private ArrayList<HospitalFindModel> arraylistHospital;
    private ArrayList<HospitalFindModel> arraylistHospitalbyaddress;


    protected void createLocationRequest() {
        Log.i("Location request", "called");
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

//    public void done(View view) {
//
//        addressStr = etLocation.getText().toString();
//        Log.i("addressString" ,addressStr);
//        if(addressStr!=null){
//            Log.i("addressString" ,addressStr);
//            Log.i("done","if called");
//            new getLatLongFromAddress(addressStr).execute();}
//
//          else{
//            Log.i("done","else called");
//         Toast.makeText(getApplicationContext(),"type an address",Toast.LENGTH_LONG).show();
//        }
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //show error dialog if GoolglePlayServices not available

//        Intent intentbr = getIntent();
//        if(intentbr!=null){
//            if
//            if(intentbr.getAction().equals("com.example.archi.health.Activity.ActivityHospitalFind"));
//            String data = intentbr.getStringExtra("data");
//
//            if(data!=null){
//                Log.i("nearby hospitals",data);
//                if(data.equalsIgnoreCase("close it")){
//                    onBackPressed();
//                }
//            }}
        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        createLocationRequest();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();


        setContentView(R.layout.activity_hospital_find);
        inputMethodManager =
                (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        updateUI();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(ContextCompat.getDrawable(ActivityHospitalFind.this, R.drawable.ic_toolbar_back));
//        if (mGoogleApiClient == null) {
//            mGoogleApiClient = new GoogleApiClient.Builder(this)
//                    .addConnectionCallbacks(this)
//                    .addOnConnectionFailedListener(this)
//                    .addApi(LocationServices.API)
//                    .build();
//        }
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getString(R.string.hospital_finder));
        toolbar.setTitleTextColor(ContextCompat.getColor(ActivityHospitalFind.this, R.color.white));
        utils = new Utils(ActivityHospitalFind.this);
        init();
    }

    private void init() {

//        checkTheNetworkEnable();
        tvNearMe = (ImageView) findViewById(R.id.tvNearMe);
        tvNearMe.setOnClickListener(this);
        etLocation = (EditText) findViewById(R.id.etLocationAddress);
        // etLocation.setOnClickListener(this);

        etLocation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Log.i("pressed", "Enter pressed");
                    inputMethodManager.hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    String name = etLocation.getText().toString();
                    if (name != null) {
                        Log.i("addressString", name);
                        Log.i("done", "if called");
                        new getLatLongFromAddress(name).execute();
                    } else {
                        Log.i("done", "else called");
                        Toast.makeText(getApplicationContext(), "type an address", Toast.LENGTH_LONG).show();
                    }
                }
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case PERMISSION_FINE_LOCATION: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        return;
//                    }
//                    mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
//                            mGoogleApiClient);
//                    if (mLastLocation != null) {
//                        latitude = String.valueOf(mLastLocation.getLatitude());
//                        longitude = String.valueOf(mLastLocation.getLongitude());
//                    }
//                } else {
//                    Toast.makeText(ActivityHospitalFind.this, "Request not granted", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            }
//
//        }
//
//    }

//    @Override
//    protected void onStart() {
//        mGoogleApiClient.connect();
//        super.onStart();
//    }

//    @Override
//    protected void onStop() {
//        mGoogleApiClient.disconnect();
//        super.onStop();
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i("onmapready", "called");
        mMap = googleMap;
//        LatLng paloalto = new LatLng(37.468319, -122.143936);
//        Location location;
//
//       // Place current location marker
//        //LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(paloalto);
//        markerOptions.title("Palo Alto");
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//       // mCurrLocationMarker =
//        mMap.addMarker(markerOptions);
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(paloalto));
//        new getLatLongFromAddress("palo alto").execute();


        //Location myLocation = googleMap.getMyLocation();  //Nullpointer exception.........
        //LatLng myLatLng = new LatLng(myLocation.getLatitude(),
        //myLocation.getLongitude());
        //move map camera
        //  mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //  mMap.setMyLocationEnabled(true);
        //  Location myLocation = googleMap.getMyLocation();

//
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onConnected(Bundle bundle) {
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
//                mGoogleApiClient);

        Log.d(TAG, "onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
        startLocationUpdates();


//        if (mLastLocation != null) {
//            if(!latitude.equalsIgnoreCase("") && !longitude.equalsIgnoreCase("")){
////            String strLng = String.valueOf(mLastLocation.getLatitude());
////            String strLong = String.valueOf(mLastLocation.getLongitude());
//
//                String strLng = latitude;
//                String strLong = longitude;
//
//            new getNearHospitals(strLng, strLong).execute();
////            LatLng myCurrentLocation = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
//                LatLng myCurrentLocation = new LatLng(Long.parseLong(latitude),Long.parseLong(longitude));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(myCurrentLocation));
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Long.parseLong(latitude),Long.parseLong(longitude)), 18.0f));
////                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()), 18.0f));
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvNearMe:
                Log.i("Called", "near me");

                Utils.WriteSharePrefrence(getApplicationContext(), "is_first", "1");
                //updateUI();
                //if (etLocation.getText().toString().equalsIgnoreCase("")) {
                getNearByHospitalsOnMap();
//                } else {
//                    addressStr = etLocation.getText().toString();
//                    new getLatLongFromAddress(addressStr).execute();
                // }
                break;
            case R.id.etLocationAddress:
                Log.i("Called", "address");
//                addressStr = etLocation.getText().toString();
//                new getLatLongFromAddress(addressStr).execute();
        }
    }

    private void getNearByHospitalsOnMap() {
        if (!Utils.ReadSharePrefrence(getApplicationContext(), "LAT").equalsIgnoreCase("") && (!Utils.ReadSharePrefrence(getApplicationContext(), "LNG").equalsIgnoreCase(""))) {
            String lat = Utils.ReadSharePrefrence(getApplicationContext(), "LAT");
            String lng = Utils.ReadSharePrefrence(getApplicationContext(), "LNG");

            Log.i("latlongg", lat + lng);
            new getNearHospitals(lat, lng).execute();
        } else {
            Toast.makeText(ActivityHospitalFind.this, "No Location Found, Please Try Again", Toast.LENGTH_SHORT).show();
        }
    }


    private class getNearHospitals extends AsyncTask<String, String, String> {
        String strLng, strLong;

        getNearHospitals(String strLng, String strLong) {
            this.strLng = strLng;
            this.strLong = strLong;
        }

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(ActivityHospitalFind.this);
            pd.setMessage("please wait...");
            pd.setCancelable(false);
            arraylistHospital = new ArrayList<>();
            arraylistHospital.clear();
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String apiKeyForGetNerHospital = getString(R.string.api_key_acces_near_hospital);
            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + strLng + "," + strLong + "&radius=5000&types=hospital&key=AIzaSyBajTJggDyp3CkrTj_DW7uUjvoM8tIgk4I";// + apiKeyForGetNerHospital;
            return utils.getResponseofGet(url);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("Response", "" + s.toString());
            try {
                JSONObject jsonObject = new JSONObject(s.toString());

                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++) {

                    HospitalFindModel hospitalFindModel = new HospitalFindModel();
                    JSONObject jsonObjectMain = jsonArray.getJSONObject(i);
                    JSONObject jsonObjectGeometry = jsonObjectMain.getJSONObject("geometry");
                    JSONObject jsonObjectLatLong = jsonObjectGeometry.getJSONObject("location");

                    String strAddress = jsonObjectMain.getString("vicinity");
                    String strHospitalLat = jsonObjectLatLong.getString("lat");
                    String strHospitalLong = jsonObjectLatLong.getString("lng");
                    String strHospitalName = jsonObjectMain.getString("name");

                    hospitalFindModel.setStrHospitalAddress(strAddress);
                    hospitalFindModel.setStrHospitalLat(strHospitalLat);
                    hospitalFindModel.setStrHospitalLong(strHospitalLong);
                    hospitalFindModel.setStrHospitalName(strHospitalName);

                    arraylistHospital.add(hospitalFindModel);

                    double dblLat = Double.parseDouble(strHospitalLat);
                    double dblLong = Double.parseDouble(strHospitalLong);
                    LatLng hospitalLocation = new LatLng(dblLat, dblLong);
                    mMap.addMarker(new MarkerOptions().position(hospitalLocation).title(strHospitalName));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(hospitalLocation));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dblLat, dblLong), 18.0f));

                }
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                    @Override
                    public boolean onMarkerClick(Marker arg0) {
                        for (int i = 0; i < arraylistHospital.size(); i++) {

                            final HospitalFindModel details = arraylistHospital.get(i);

                            if (arg0.getTitle() != null) {
                                if (arg0.getTitle().equals(details.getStrHospitalName())) {
                                    final Dialog dialog = new Dialog(ActivityHospitalFind.this);
                                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    dialog.setContentView(R.layout.dialog_hospital_info);

                                    TextView hosNameTv = (TextView) dialog.findViewById(R.id.dialog_hospital_name);
                                    TextView hosAddressTv = (TextView) dialog.findViewById(R.id.dialog_hospital_address);
                                    Button cancelBtn = (Button) dialog.findViewById(R.id.dialog_hos_dismiss);
                                    Button moreInfoBtn = (Button) dialog.findViewById(R.id.dialog_hos_details);

                                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog.dismiss();
                                        }
                                    });
                                    hosAddressTv.setText(details.getStrHospitalAddress());
                                    hosNameTv.setText(details.getStrHospitalName());

                                    dialog.show();
                                }
                            }
                        }
                        return true;
                    }

                });

            } catch (JSONException e) {
                e.printStackTrace();
            }


            pd.dismiss();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart fired ..............");
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop fired ..............");
        mGoogleApiClient.disconnect();
        Log.d(TAG, "isConnected ...............: " + mGoogleApiClient.isConnected());
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

//    @Override
//    public void onConnected(Bundle bundle) {
//        Log.d(TAG, "onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
//        startLocationUpdates();
//    }

    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        Log.d(TAG, "Location update started ..............: ");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed: " + connectionResult.toString());
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Firing onLocationChanged..............................................");
        mCurrentLocation = location;

        Log.i("location", String.valueOf(mCurrentLocation.getLatitude()));
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();
    }

    private void updateUI() {
        Log.i("update ui", "called");
        Log.d(TAG, "UI update initiated .............");
        if (null != mCurrentLocation) {
            latitude = String.valueOf(mCurrentLocation.getLatitude());
            longitude = String.valueOf(mCurrentLocation.getLongitude());

            Utils.WriteSharePrefrence(getApplicationContext(), "LAT", latitude);
            Utils.WriteSharePrefrence(getApplicationContext(), "LNG", longitude);

            if (!latitude.equalsIgnoreCase("") && !longitude.equalsIgnoreCase("")) {
                String strLng = latitude;
                String strLong = longitude;
                if (Utils.ReadSharePrefrence(getApplicationContext(), "is_first").equalsIgnoreCase("1")) {
                    Utils.WriteSharePrefrence(getApplicationContext(), "is_first", "0");
//                    new getNearHospitals(strLng, strLong).execute();
                    Toast.makeText(getApplicationContext(), "Current Lat " + latitude + "  " + longitude, Toast.LENGTH_SHORT).show();
                    LatLng myCurrentLocation = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                    mMap.addMarker(new MarkerOptions().position(myCurrentLocation));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myCurrentLocation));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)), 18.0f));
//               mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//                   @Override
//                   public boolean onMarkerClick(Marker marker) {
//                       final Dialog dialog = new Dialog(ActivityHospitalFind.this);
//                       dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                       dialog.setContentView(R.layout.dialog_hospital_info);
//
//                       TextView hosNameTv = (TextView) dialog.findViewById(R.id.dialog_hospital_name);
//                       TextView hosAddressTv = (TextView) dialog.findViewById(R.id.dialog_hospital_address);
//                       Button cancelBtn = (Button) dialog.findViewById(R.id.dialog_hos_dismiss);
//                       Button moreInfoBtn = (Button) dialog.findViewById(R.id.dialog_hos_details);
//
//                       cancelBtn.setOnClickListener(new View.OnClickListener() {
//                           @Override
//                           public void onClick(View view) {
//                               dialog.dismiss();
//                           }
//                       });
//                       hosAddressTv.setText(latitude);
//                       hosNameTv.setText(longitude);
//                       return true;
//                   }
//               });
                }
//            LatLng myCurrentLocation = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());

//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()), 18.0f));
            } else {
                Toast.makeText(getApplicationContext(), "No Location Found", Toast.LENGTH_SHORT).show();
            }
//            String lat = String.valueOf(mCurrentLocation.getLatitude());
//            String lng = String.valueOf(mCurrentLocation.getLongitude());
//            tvLocation.setText("At Time: " + mLastUpdateTime + "\n" +
//                    "Latitude: " + lat + "\n" +
//                    "Longitude: " + lng + "\n" +
//                    "Accuracy: " + mCurrentLocation.getAccuracy() + "\n" +
//                    "Provider: " + mCurrentLocation.getProvider());
        } else {
            Log.d(TAG, "location is null ...............");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        Log.d(TAG, "Location update stopped .......................");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
            Log.d(TAG, "Location update resumed .....................");
        }
    }

    private class getLatLongFromAddress extends AsyncTask<String, String, String> {
        String strAddress;
//        ProgressDialog pd;

        public getLatLongFromAddress(String addressStr) {
            this.strAddress = addressStr;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            arraylistHospitalbyaddress = new ArrayList<>();
            arraylistHospitalbyaddress.clear();
//            pd = new ProgressDialog(ActivityHospitalFind.this);
//            pd.setMessage("please wiat...");
//            pd.setCancelable(false);
//            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            // String apiKeyForGetNerHospital = getString(R.string.api_key_acces_near_hospital);
            HashMap<String, String> hashmap = new HashMap<>();
            hashmap.put("address", addressStr);
            hashmap.put("sensor", "true");
            //String url =  "http://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=true_or_false";
            //String url = "http://maps.googleapis.com/maps/api/geocode/json?&address=" + addressStr + "&sensor=true";
            // String request = addressStr;//+"&radius=500&type=hospital&key=AIzaSyBajTJggDyp3CkrTj_DW7uUjvoM8tIgk4I";
            String request = strAddress;
            Log.i("addresstext", request);
            String encodedString = null;
            try {
                encodedString = URLEncoder.encode(request, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + encodedString + "&radius=500&type=hospital&key=AIzaSyBajTJggDyp3CkrTj_DW7uUjvoM8tIgk4I";
            Log.i("address1", url);
            //String url="https://maps.googleapis.com/maps/api/place/textsearch/json?query=arekere&radius=500&type=hospital&key=AIzaSyBLtqT2B29mDArsE3lDcFtxcILXVggQjzg";
            return utils.getResponseofGet(url);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                Log.i("response1", s);
                JSONObject object = new JSONObject(s.toString());
                if (object.has(TAG_RESULTS)) {
                    results = object.getJSONArray(TAG_RESULTS);
                    Log.d("RESULT", "@@>> " + results);

                    for (int i = 0; i < results.length(); i++) {
                        HospitalFindModel hospitalFindModel = new HospitalFindModel();
                        JSONObject r = results.getJSONObject(i);
                        Log.d("R", "@@>> " + r);
                        // geometry and location is again JSON Object
                        JSONObject geometry = r.getJSONObject(TAG_GEOMETRY);
                        // JSONObject jsonObjectLatLong = geometry.getJSONObject("location");
                        Log.d("geometry ", "@@>> " + geometry);
                        JSONObject viewport = geometry.getJSONObject(TAG_VIEWPORT);
                        Log.d("viewport ", "@@>> " + viewport);
                        JSONObject northest = viewport.getJSONObject(TAG_NORTHEAST);
                        Log.d("northest ", "@@>> " + northest);
                        String strAddress = r.getString("formatted_address");
                        //String strHospitalLat = jsonObjectLatLong.getString("lat");
                        //String strHospitalLong = jsonObjectLatLong.getString("lng");
                        String strHospitalName = r.getString("name");
                        String lat = northest.getString(TAG_LAT);
                        String lng = northest.getString(TAG_LNG);
                        Log.e("MAHI", "" + lat + " " + lng);


                        hospitalFindModel.setStrHospitalAddress(strAddress);
                        hospitalFindModel.setStrHospitalLat(lat);
                        hospitalFindModel.setStrHospitalLong(lng);
                        hospitalFindModel.setStrHospitalName(strHospitalName);

                        arraylistHospitalbyaddress.add(hospitalFindModel);


                        Utils.WriteSharePrefrence(getApplicationContext(), "LAT", lat);
                        Utils.WriteSharePrefrence(getApplicationContext(), "LNG", lng);
                        double dblLat = Double.parseDouble(lat);
                        double dblLong = Double.parseDouble(lng);
                        LatLng hospitalLocation = new LatLng(dblLat, dblLong);
                        mMap.addMarker(new MarkerOptions().position(hospitalLocation).title(strHospitalName));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(hospitalLocation));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dblLat, dblLong), 18.0f));

                    }
                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                        @Override
                        public boolean onMarkerClick(Marker arg0) {
                            for (int i = 0; i < arraylistHospitalbyaddress.size(); i++) {

                                final HospitalFindModel details = arraylistHospitalbyaddress.get(i);
                                if (arg0.getTitle().equals(details.getStrHospitalName())) {
                                    final Dialog dialog = new Dialog(ActivityHospitalFind.this);
                                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    dialog.setContentView(R.layout.dialog_hospital_info);

                                    TextView hosNameTv = (TextView) dialog.findViewById(R.id.dialog_hospital_name);
                                    TextView hosAddressTv = (TextView) dialog.findViewById(R.id.dialog_hospital_address);
                                    Button cancelBtn = (Button) dialog.findViewById(R.id.dialog_hos_dismiss);
                                    Button moreInfoBtn = (Button) dialog.findViewById(R.id.dialog_hos_details);

                                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog.dismiss();
                                        }
                                    });
                                    hosAddressTv.setText(details.getStrHospitalAddress());
                                    hosNameTv.setText(details.getStrHospitalName());

                                    dialog.show();
                                }
                            }
                            return true;
                        }

                    });


                    // getNearByHospitalsOnMap();
                } else {
                    Toast.makeText(ActivityHospitalFind.this, "No Data Found, Please Try Again", Toast.LENGTH_SHORT).show();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
