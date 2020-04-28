package com.rathore.health.DbHelper;

/**
 * Created by archi on 12/24/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper {
    RequestQueue queue;
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "MEDICAL";

    //table name
    private static final String TABLE_PROFILE_CALENDER = "profilecalender";
    private static final String TABLE_REMINDER = "reminder";
    private static final String TABLE_MEDICANCES = "medicances";
    private static final String TABLE_HEALTH_RECORD = "healthrecord";
    private static final String TABLE_VITALS_TEMPERATURE = "vtemperature";
    private static final String TABLE_VITALS_PULSE = "vpulse";
    private static final String TABLE_VITALS_BLOOD_PRESSURE = "vbloodpressure";
    private static final String TABLE_VITALS_HEIGHT = "vheight";
    private static final String TABLE_VITALS_WEIGHT = "vweight";
    private static final String TABLE_VITALS_INTAKE_CALORIES = "vintakecalories";
    private static final String TABLE_VITALS_BURN_CALORIES = "vburncalories";
    private static final String TABLE_VITALS_ALCOHOL = "valcohol";
    private static final String TABLE_VITALS_CAFFEINE = "vcaffeine";
    private static final String TABLE_INVESTIGATION = "investigation";
    private static final String TABLE_PROFILE_TREATMENT_MEDICATION = "treatmentMedication";
    private static final String TABLE_PROFILE_TREATMENT_STAYING_HOME = "treatmentstayinhome";
    private static final String TABLE_PROFILE_TREATMENT_OTHER = "treatmentother";
    private static final String TABLE_PROFILE_TREATMENT_DOCTOR_VISIT = "treatmentdoctorvisit";
    private static final String TABLE_PROFILE_TREATMENT_SPECIALITY = "treatmentspeciality";
    private static final String TABLE_PROFILE_TREATMENT_VACCINE = "treatmentvaccine";


    //table profile calander

    private static final String KEY_PROFILR_CAL_ID = "key_profile_cal_id";
    private static final String KEY_PROFILE_CAL_NAME = "key_profile_cal_name";
    private static final String KEY_PROFILE_CAL_DATE = "key_profile_cal_date";
    private static final String KEY_PROFILE_CAL_STRING = "key_profile_cal_string";
    private static final String KEY_PROFILE_CAL_UNIQ_ID = "key_profile_cal_uniq";


    //table treatment medication
    private static final String KEY_TRETMENT_MEDI_ID = "key_tret_medi_id";
    private static final String KEY_TRETMENT_MEDI_DATE = "key_tret_medi_date";
    private static final String KEY_TRETMENT_MEDI_NAME = "key_tret_medi_name";
    private static final String KEY_TRETMENT_MEDI_DESCRIPTION = "key_tret_medi_description";
    //table treatment stay home
    private static final String KEY_TRETMENT_STAY_HOME_ID = "key_tret_stay_home_id";
    private static final String KEY_TRETMENT_STAY_HOME_DATE = "key_tret_stay_home_date";
    private static final String KEY_TRETMENT_STAY_HOME_NAME = "key_tret_stay_home_name";
    private static final String KEY_TRETMENT_STAY_HOME_DESCRIPTION = "key_tret_stay_home_description";
    // table treatment othes
    private static final String KEY_TREATMENT_OTHER_ID = "key_tret_other_id";
    private static final String KEY_TREATMENT_OTHER_DATE = "key_tret_other_date";
    private static final String KEY_TREATMENT_OTHER_NAME = "key_tret_other_name";
    private static final String KEY_TREATMENT_OTHER_DESCRIPTION = "key_tret_other_description";
    // table treatment doctor visit
    private static final String KEY_TREATMENT_DOCTOR_VISIT_ID = "key_tret_doctor_visit_id";
    private static final String KEY_TREATMENT_DOCTOR_VISIT_DATE = "key_tret_doctor_visit_date";
    private static final String KEY_TREATMENT_DOCTOR_VISIT_NAME = "key_tret_doctor_visit_name";
    private static final String KEY_TREATMENT_DOCTOR_VISIT_DESCRIPTION = "key_tret_doctor_visit_description";
    // table treatment doctor speciality
    private static final String KEY_TREATMENT_SPECIALITY_ID = "key_tret_speciality_id";
    private static final String KEY_TREATMENT_SPECIALITY_DATE = "key_tret_speciality_date";
    private static final String KEY_TREATMENT_SPECIALITY_NAME = "key_tret_speciality_name";
    private static final String KEY_TREATMENT_SPECIALITY_DESCRIPTION = "key_tret_speciality_description";
    // table treatment doctor speciality
    private static final String KEY_TREATMENT_VACCINE_ID = "key_tret_vaccinne_id";
    private static final String KEY_TREATMENT_VACCINE_DATE = "key_tret_vaccinne_date";
    private static final String KEY_TREATMENT_VACCINE_NAME = "key_tret_vaccinne_name";
    private static final String KEY_TREATMENT_VACCINE_DESCRIPTION = "key_tret_vaccinne_description";


    //table reminder
    private static final String KEY_R_ID = "r_id";
    private static final String KEY_R_NAME = "r_name";
    private static final String KEY_R_RESON_FOR_REMINDER = "r_reasion_reminder";
    private static final String KEY_R_DATE = "r_date";
    private static final String KEY_R_TIME = "r_time";
    private static final String KEY_R_REPEAT = "r_repeat";
    private static final String KEY_R_NOTE = "r_note";
    //table medicances
    private static final String KEY_M_ID = "m_id";
    private static final String KEY_M_NAME = "m_name";
    private static final String KEY_M_TYPE = "m_type";
    private static final String KEY_M_ROUTE = "m_route";
    private static final String KEY_M_DOSAGE = "m_dosage";
    private static final String KEY_M_INSTRUCTION = "m_instruction";
    private static final String KEY_M_REASON_TALKING = "m_reason_of_talking";
    private static final String KEY_M_DURATION_HOURS = "m_duration_hours";
    private static final String KEY_M_DURATION_DAYS = "m_duration_days";
    private static final String KEY_M_START_DATE = "m_start_date";
    private static final String KEY_M_END_DATE = "m_end_date";
    private static final String KEY_M_TOTAL_QUANTITY = "m_total_quantity";
    private static final String KEY_M_PRESCRIBE_BY = "m_prescribe_by";
    //table health record
    private static final String KEY_HEALTH_ID = "h_id";
    private static final String KEY_HEALTH_NAME = "h_name";
    private static final String KEY_HEALTH_GENDER = "h_gender";
    private static final String KEY_HEALTH_PROFILE_IMG = "h_profile_img";
    private static final String KEY_HEALTH_BIRTHDATE = "h_birthdate";
    private static final String KEY_HEALTH_BLOODTYPE = "h_bloodtype";
    private static final String KEY_HEALTH_NORMAL_BODY_TEMPATURE = "h_normal_body_temp";
    private static final String KEY_HEALTH_ALLERGIES = "h_allergies";
    private static final String KEY_HEALTH_HEALTH_CONDITION = "h_health_condition";
    private static final String KEY_HEALTH_SURGERIES_HISTORY = "h_surgeries_history";
    private static final String KEY_HEALTH_HOSPITALIZATION_HISTORY = "h_hospitalization_history";
    private static final String KEY_HEALTH_NOTES = "h_notes";

//table tempeature

    private static final String KEY_V_TEMPERATURE_ID = "v_temp_id";
    private static final String KEY_V_TEMPERATURE_DATE = "v_temp_date";
    private static final String KEY_V_TEMPERATURE_TIME = "v_temp_time";
    private static final String KEY_V_TEMPERATURE_TEMPERATURE = "v_temp_temperature";
    private static final String KEY_V_TEMPERATURE_UNIT = "v_temp_unit";

    //table pulse
    private static final String KEY_V_PULSE_ID = "v_pulse_id";
    private static final String KEY_V_PULSE_DATE = "v_pulse_date";
    private static final String KEY_V_PULSE_TIME = "v_pulse_time";
    private static final String KEY_V_PULSE_PILSE = "v_pulse_pulse";
    private static final String KEY_V_PULSE_UNIT = "v_pulse_unit";

    //table blood pressure

    private static final String KEY_V_BLOOD_PRESAR_ID = "v_bloodpressure_id";
    private static final String KEY_V_BLOOD_PRESAR_DATE = "v_bloodpressure_date";
    private static final String KEY_V_BLOOD_PRESAR_TIME = "v_bloodpressure_time";
    private static final String KEY_V_BLOOD_PRESAR_SYSTOLIC = "v_bloodpressure_ststolic";
    private static final String KEY_V_BLOOD_PRESAR_DIASTOLIC = "v_bloodpressure_diastolic";


    //table height

    private static final String KEY_V_HEIGHT_ID = "v_height_id";
    private static final String KEY_V_HEIGHT_DATE = "v_height_date";
    private static final String KEY_V_HEIGHT_TIME = "v_height_time";
    private static final String KEY_V_HEIGHT_HEIGHT = "v_height_height";
    private static final String KEY_V_HEIGHT_UNIT = "v_height_unit";


    //table weight

    private static final String KEY_V_WEIGHT_ID = "v_weight_id";
    private static final String KEY_V_WEIGHT_DATE = "v_weight_date";
    private static final String KEY_V_WEIGHT_TIME = "v_weight_time";
    private static final String KEY_V_WEIGHT_WEIGHT = "v_weight_weight";
    private static final String KEY_V_WEIGHT_UNIT = "v_weight_unit";


    //intake calories

    private static final String KEY_V_INTAKE_CALORIES_ID = "v_intake_calories_id";
    private static final String KEY_V_INTAKE_CALORIES_DATE = "v_intake_calories_date";
    private static final String KEY_V_INTAKE_CALORIES_TIME = "v_intake_calories_time";
    private static final String KEY_V_INTAKE_CALORIES_INTAKE_CALORIES = "v_intake_calories_intake_calories";
    private static final String KEY_V_INTAKE_CALORIES_UNIT = "v_intake_calories_unit";

// burn calories

    private static final String KEY_V_BURN_CALORIES_ID = "v_burn_calories_id";
    private static final String KEY_V_BURN_CALORIES_DATE = "v_burn_calories_date";
    private static final String KEY_V_BURN_CALORIES_TIME = "v_burn_calories_time";
    private static final String KEY_V_BURN_CALORIES_BURN_CALORIES = "v_burn_calories_burn_calories";
    private static final String KEY_V_BURN_CALORIES_UNIT = "v_burn_calories_unit";

    //alcohol

    private static final String KEY_V_ALCOHOL_ID = "v_alcohol_id";
    private static final String KEY_V_ALCOHOL_DATE = "v_alcohol_date";
    private static final String KEY_V_ALCOHOL_TIME = "v_alcohol_time";
    private static final String KEY_V_ALCOHOL_ALCOHOL = "v_alcohol_alcohol";
    private static final String KEY_V_ALCOHOL_UNIT = "v_alcohol_unit";

    //Caffeine

    private static final String KEY_V_CAFFEINE_ID = "v_caffeine_id";
    private static final String KEY_V_CAFFEINE_DATE = "v_caffeine_date";
    private static final String KEY_V_CAFFEINE_TIME = "v_caffeine_time";
    private static final String KEY_V_CAFFEINE_CAFFEINE = "v_caffeine_caffeine";
    private static final String KEY_V_CAFFEINE_UNIT = "v_caffeine_unit";

    //investigation

    private static final String KEY_INVESTIGATION_ID = "investigation_id";
    private static final String KEY_INVESTIGATION_DATE = "investigation_date";
    private static final String KEY_INVESTIGATION_TIME = "investigation_time";
    private static final String KEY_INVESTIGATION_NAME = "investigation_name";
    private static final String KEY_INVESTIGATION_RESULT = "investigation_result";
    private static final String KEY_INVESTIGATION_UNIT = "investigation_unit";
    private static final String KEY_INVESTIGATION_REPEAT = "investigation_repeat";



    public Context context;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_REMINDER = "CREATE TABLE " + TABLE_REMINDER + "("
                + KEY_R_ID + " INTEGER PRIMARY KEY,"
                + KEY_R_NAME + " TEXT,"
                + KEY_R_RESON_FOR_REMINDER + " TEXT,"
                + KEY_R_DATE + " INTEGER,"
                + KEY_R_TIME + " INTEGER,"
                + KEY_R_REPEAT + " TEXT,"
                + KEY_R_NOTE + " TEXT" + ")";



        String CREATE_TABLE_MEDICANCES = "CREATE TABLE " + TABLE_MEDICANCES + "("
                + KEY_M_ID + " INTEGER PRIMARY KEY,"
                + KEY_M_NAME + " TEXT,"
                + KEY_M_TYPE + " TEXT,"
                + KEY_M_ROUTE + " TEXT,"
                + KEY_M_DOSAGE + " TEXT,"
                + KEY_M_INSTRUCTION + " TEXT,"
                + KEY_M_REASON_TALKING + " TEXT,"
                + KEY_M_DURATION_HOURS + " INTEGER,"
                + KEY_M_DURATION_DAYS + " TEXT,"
                + KEY_M_START_DATE + " INTEGER,"
                + KEY_M_END_DATE + " INTEGER,"
                + KEY_M_TOTAL_QUANTITY + " INTEGER,"
                + KEY_M_PRESCRIBE_BY + " TEXT" + ")";


        String CREATE_TABLE_HEALTHRECORD = "CREATE TABLE " + TABLE_HEALTH_RECORD + "("
                + KEY_HEALTH_ID + " INTEGER PRIMARY KEY,"
                + KEY_HEALTH_NAME + " TEXT,"
                + KEY_HEALTH_GENDER + " TEXT,"
                + KEY_HEALTH_PROFILE_IMG + " TEXT,"
                + KEY_HEALTH_BIRTHDATE + " INTEGER,"
                + KEY_HEALTH_BLOODTYPE + " TEXT,"
                + KEY_HEALTH_NORMAL_BODY_TEMPATURE + " INTEGER,"
                + KEY_HEALTH_ALLERGIES + " TEXT,"
                + KEY_HEALTH_HEALTH_CONDITION + " TEXT,"
                + KEY_HEALTH_SURGERIES_HISTORY + " TEXT,"
                + KEY_HEALTH_HOSPITALIZATION_HISTORY + " TEXT,"
                + KEY_HEALTH_NOTES + " TEXT" + ")";

        String CREATE_TABLE_PROFILE_CAL = "CREATE TABLE " + TABLE_PROFILE_CALENDER + "("
                +KEY_PROFILR_CAL_ID + " INTEGER PRIMARY KEY,"
                +KEY_PROFILE_CAL_DATE + " TEXT,"
                +KEY_PROFILE_CAL_UNIQ_ID + " TEXT,"
                +KEY_PROFILE_CAL_NAME + " TEXT,"
                +KEY_PROFILE_CAL_STRING + " TEXT" +")";



        String CREATE_TABLE_VITALS_TEMPERATURE = "CREATE TABLE " + TABLE_VITALS_TEMPERATURE + "("
                + KEY_V_TEMPERATURE_ID + " INTEGER PRIMARY KEY,"
                + KEY_V_TEMPERATURE_DATE + " TEXT,"
                + KEY_V_TEMPERATURE_TIME + " TEXT,"
                + KEY_V_TEMPERATURE_TEMPERATURE + " INTEGER,"
                + KEY_V_TEMPERATURE_UNIT + " TEXT" + ")";


        String CREATE_TABLE_VITALS_PULSE = "CREATE TABLE " + TABLE_VITALS_PULSE + "("
                + KEY_V_PULSE_ID + " INTEGER PRIMARY KEY,"
                + KEY_V_PULSE_DATE + " TEXT,"
                + KEY_V_PULSE_TIME + " TEXT,"
                + KEY_V_PULSE_PILSE + " INTEGER,"
                + KEY_V_PULSE_UNIT + " TEXT" + ")";


        String CREATE_TABLE_VITALS_BLOOD_PRESSURE = "CREATE TABLE " + TABLE_VITALS_BLOOD_PRESSURE + "("
                + KEY_V_BLOOD_PRESAR_ID + " INTEGER PRIMARY KEY,"
                + KEY_V_BLOOD_PRESAR_DATE + " TEXT,"
                + KEY_V_BLOOD_PRESAR_TIME + " TEXT,"
                + KEY_V_BLOOD_PRESAR_SYSTOLIC + " INTEGER,"
                + KEY_V_BLOOD_PRESAR_DIASTOLIC + " TEXT" + ")";

        String CREATE_TABLE_VITALS_HEIGHT = "CREATE TABLE " + TABLE_VITALS_HEIGHT + "("
                + KEY_V_HEIGHT_ID + " INTEGER PRIMARY KEY,"
                + KEY_V_HEIGHT_DATE + " TEXT,"
                + KEY_V_HEIGHT_TIME + " TEXT,"
                + KEY_V_HEIGHT_HEIGHT + " INTEGER,"
                + KEY_V_HEIGHT_UNIT + " TEXT" + ")";

        String CREATE_TABLE_VITALS_WEIGHT = "CREATE TABLE " + TABLE_VITALS_WEIGHT + "("
                + KEY_V_WEIGHT_ID + " INTEGER PRIMARY KEY,"
                + KEY_V_WEIGHT_DATE + " TEXT,"
                + KEY_V_WEIGHT_TIME + " TEXT,"
                + KEY_V_WEIGHT_WEIGHT + " INTEGER,"
                + KEY_V_WEIGHT_UNIT + " TEXT" + ")";


        String CREATE_TABLE_VITALS_INTAKE_CALORIES = "CREATE TABLE " + TABLE_VITALS_INTAKE_CALORIES + "("
                + KEY_V_INTAKE_CALORIES_ID + " INTEGER PRIMARY KEY,"
                + KEY_V_INTAKE_CALORIES_DATE + " TEXT,"
                + KEY_V_INTAKE_CALORIES_TIME + " TEXT,"
                + KEY_V_INTAKE_CALORIES_INTAKE_CALORIES + " INTEGER,"
                + KEY_V_INTAKE_CALORIES_UNIT + " TEXT" + ")";


        String CREATE_TABLE_VITALS_BURN_CALORIES = "CREATE TABLE " + TABLE_VITALS_BURN_CALORIES + "("
                + KEY_V_BURN_CALORIES_ID + " INTEGER PRIMARY KEY,"
                + KEY_V_BURN_CALORIES_DATE + " TEXT,"
                + KEY_V_BURN_CALORIES_TIME + " TEXT,"
                + KEY_V_BURN_CALORIES_BURN_CALORIES + " INTEGER,"
                + KEY_V_BURN_CALORIES_UNIT + " TEXT" + ")";

        String CREATE_TABLE_VITALS_ALCOHOL = "CREATE TABLE " + TABLE_VITALS_ALCOHOL + "("
                + KEY_V_ALCOHOL_ID + " INTEGER PRIMARY KEY,"
                + KEY_V_ALCOHOL_DATE + " TEXT,"
                + KEY_V_ALCOHOL_TIME + " TEXT,"
                + KEY_V_ALCOHOL_ALCOHOL + " INTEGER,"
                + KEY_V_ALCOHOL_UNIT + " TEXT" + ")";

        String CREATE_TABLE_VITALS_CAFFEINE = "CREATE TABLE " + TABLE_VITALS_CAFFEINE + "("
                + KEY_V_CAFFEINE_ID + " INTEGER PRIMARY KEY,"
                + KEY_V_CAFFEINE_DATE + " TEXT,"
                + KEY_V_CAFFEINE_TIME + " TEXT,"
                + KEY_V_CAFFEINE_CAFFEINE + " INTEGER,"
                + KEY_V_CAFFEINE_UNIT + " TEXT" + ")";

        String CREATE_TABLE_INVESTIGATION = "CREATE TABLE " + TABLE_INVESTIGATION + "("
                + KEY_INVESTIGATION_ID + " INTEGER PRIMARY KEY,"
                + KEY_INVESTIGATION_DATE + " TEXT,"
                + KEY_INVESTIGATION_TIME + " TEXT,"
                + KEY_INVESTIGATION_NAME + " TEXT,"
                + KEY_INVESTIGATION_RESULT + " INTEGER,"
                + KEY_INVESTIGATION_UNIT + " TEXT,"
                + KEY_INVESTIGATION_REPEAT + " TEXT" + ")";


        String CREATE_TABLE_PROFILE_INVE_MEDICATION = "CREATE TABLE " + TABLE_PROFILE_TREATMENT_MEDICATION + "("
                + KEY_TRETMENT_MEDI_ID + " INTEGER PRIMARY KEY,"
                + KEY_TRETMENT_MEDI_DATE + " TEXT,"
                + KEY_TRETMENT_MEDI_NAME + " TEXT,"
                + KEY_TRETMENT_MEDI_DESCRIPTION + " TEXT" + ")";


        String CREATE_TABLE_PROFILE_INVE_STAY_HOME = "CREATE TABLE " + TABLE_PROFILE_TREATMENT_STAYING_HOME + "("
                + KEY_TRETMENT_STAY_HOME_ID + " INTEGER PRIMARY KEY,"
                + KEY_TRETMENT_STAY_HOME_DATE + " TEXT,"
                + KEY_TRETMENT_STAY_HOME_NAME + " TEXT,"
                + KEY_TRETMENT_STAY_HOME_DESCRIPTION + " TEXT" + ")";


        String CREATE_TABLE_PROFILE_INVE_VACCINE = "CREATE TABLE " + TABLE_PROFILE_TREATMENT_VACCINE + "("
                + KEY_TREATMENT_VACCINE_ID + " INTEGER PRIMARY KEY,"
                + KEY_TREATMENT_VACCINE_DATE + " TEXT,"
                + KEY_TREATMENT_VACCINE_NAME + " TEXT,"
                + KEY_TREATMENT_VACCINE_DESCRIPTION + " TEXT" + ")";

        String CREATE_TABLE_PROFILE_INVE_OTHER = "CREATE TABLE " + TABLE_PROFILE_TREATMENT_OTHER + "("
                + KEY_TREATMENT_OTHER_ID + " INTEGER PRIMARY KEY,"
                + KEY_TREATMENT_OTHER_DATE + " TEXT,"
                + KEY_TREATMENT_OTHER_NAME + " TEXT,"
                + KEY_TREATMENT_OTHER_DESCRIPTION + " TEXT" + ")";

        String CREATE_TABLE_PROFILE_INVE_SPECIALITY = "CREATE TABLE " + TABLE_PROFILE_TREATMENT_SPECIALITY + "("
                + KEY_TREATMENT_SPECIALITY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TREATMENT_SPECIALITY_DATE + " TEXT,"
                + KEY_TREATMENT_SPECIALITY_NAME + " TEXT,"
                + KEY_TREATMENT_SPECIALITY_DESCRIPTION + " TEXT" + ")";


        String CREATE_TABLE_PROFILE_INVE_DOC_VISIT = "CREATE TABLE " + TABLE_PROFILE_TREATMENT_DOCTOR_VISIT + "("
                + KEY_TREATMENT_DOCTOR_VISIT_ID + " INTEGER PRIMARY KEY,"
                + KEY_TREATMENT_DOCTOR_VISIT_DATE + " TEXT,"
                + KEY_TREATMENT_DOCTOR_VISIT_NAME + " TEXT,"
                + KEY_TREATMENT_DOCTOR_VISIT_DESCRIPTION + " TEXT" + ")";


        sqLiteDatabase.execSQL(CREATE_TABLE_PROFILE_CAL);
        sqLiteDatabase.execSQL(CREATE_TABLE_PROFILE_INVE_MEDICATION);
        sqLiteDatabase.execSQL(CREATE_TABLE_PROFILE_INVE_STAY_HOME);
        sqLiteDatabase.execSQL(CREATE_TABLE_PROFILE_INVE_VACCINE);
        sqLiteDatabase.execSQL(CREATE_TABLE_PROFILE_INVE_OTHER);
        sqLiteDatabase.execSQL(CREATE_TABLE_PROFILE_INVE_SPECIALITY);
        sqLiteDatabase.execSQL(CREATE_TABLE_PROFILE_INVE_DOC_VISIT);
        sqLiteDatabase.execSQL(CREATE_TABLE_REMINDER);
        sqLiteDatabase.execSQL(CREATE_TABLE_MEDICANCES);
        sqLiteDatabase.execSQL(CREATE_TABLE_HEALTHRECORD);
        sqLiteDatabase.execSQL(CREATE_TABLE_VITALS_TEMPERATURE);
        sqLiteDatabase.execSQL(CREATE_TABLE_VITALS_PULSE);
        sqLiteDatabase.execSQL(CREATE_TABLE_VITALS_BLOOD_PRESSURE);
        sqLiteDatabase.execSQL(CREATE_TABLE_VITALS_HEIGHT);
        sqLiteDatabase.execSQL(CREATE_TABLE_VITALS_WEIGHT);
        sqLiteDatabase.execSQL(CREATE_TABLE_VITALS_INTAKE_CALORIES);
        sqLiteDatabase.execSQL(CREATE_TABLE_VITALS_BURN_CALORIES);
        sqLiteDatabase.execSQL(CREATE_TABLE_VITALS_ALCOHOL);
        sqLiteDatabase.execSQL(CREATE_TABLE_VITALS_CAFFEINE);
        sqLiteDatabase.execSQL(CREATE_TABLE_INVESTIGATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_PROFILE_CALENDER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_PROFILE_TREATMENT_MEDICATION);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_PROFILE_TREATMENT_STAYING_HOME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_PROFILE_TREATMENT_VACCINE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_PROFILE_TREATMENT_OTHER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_PROFILE_TREATMENT_SPECIALITY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE_TREATMENT_DOCTOR_VISIT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICANCES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HEALTH_RECORD);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VITALS_TEMPERATURE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VITALS_PULSE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VITALS_BLOOD_PRESSURE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VITALS_HEIGHT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VITALS_WEIGHT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VITALS_INTAKE_CALORIES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VITALS_BURN_CALORIES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VITALS_ALCOHOL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VITALS_CAFFEINE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_INVESTIGATION);

    }


    public void updateReminder(String rid, String rname, String resonreminder,
                               String rdate, String rtime,
                               String rrepeat, String rnote) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_R_NAME, rname);
        values.put(KEY_R_RESON_FOR_REMINDER, resonreminder);
        values.put(KEY_R_DATE, rdate);
        values.put(KEY_R_TIME, rtime);
        values.put(KEY_R_REPEAT, rrepeat);
        values.put(KEY_R_NOTE, rnote);
        db.update(TABLE_REMINDER, values, KEY_R_ID + " = ?", new String[]{String.valueOf(rid)});
    }


    public void updateMedicines(String mId, String mname, String mtype,
                                String mroute, String mdosage,
                                String minstn, String mreasiontalking,
                                String mdurationhours, String mduratondays,
                                String mstartdate, String menddate,
                                String mtotalquantity, String mprescribeby)

    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_M_NAME, mname);
        values.put(KEY_M_TYPE, mtype);
        values.put(KEY_M_ROUTE, mroute);
        values.put(KEY_M_DOSAGE, mdosage);
        values.put(KEY_M_INSTRUCTION, minstn);
        values.put(KEY_M_REASON_TALKING, mreasiontalking);
        values.put(KEY_M_DURATION_HOURS, mdurationhours);
        values.put(KEY_M_DURATION_DAYS, mduratondays);
        values.put(KEY_M_START_DATE, mstartdate);
        values.put(KEY_M_END_DATE, menddate);
        values.put(KEY_M_TOTAL_QUANTITY, mtotalquantity);
        values.put(KEY_M_PRESCRIBE_BY, mprescribeby);
        db.update(TABLE_MEDICANCES, values, KEY_M_ID + " = ?", new String[]{String.valueOf(mId)});

    }



    public void updateInvestigation(String iid, String idate, String itime, String iname, String iresult, String iunit, String irepeat )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_INVESTIGATION_DATE, idate);
        values.put(KEY_INVESTIGATION_TIME, itime);
        values.put(KEY_INVESTIGATION_NAME, iname);
        values.put(KEY_INVESTIGATION_RESULT, iresult);
        values.put(KEY_INVESTIGATION_UNIT, iunit);
        values.put(KEY_INVESTIGATION_REPEAT, irepeat);
        db.update(TABLE_INVESTIGATION, values, KEY_INVESTIGATION_ID + " = ?", new String[] { String.valueOf(iid)});
    }




    public void updateVAlcohol(String aid, String adate, String atime, String alcohol) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_ALCOHOL_DATE, adate);
        values.put(KEY_V_ALCOHOL_TIME, atime);
        values.put(KEY_V_ALCOHOL_ALCOHOL, alcohol);
        db.update(TABLE_VITALS_ALCOHOL, values, KEY_V_ALCOHOL_ID + " = ?", new String[]{String.valueOf(aid)});
    }

    public void updateVBloodPressure(String aid, String adate, String atime, String asos, String ados) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_BLOOD_PRESAR_DATE, adate);
        values.put(KEY_V_BLOOD_PRESAR_TIME, atime);
        values.put(KEY_V_BLOOD_PRESAR_SYSTOLIC, asos);
        values.put(KEY_V_BLOOD_PRESAR_DIASTOLIC, ados);
        db.update(TABLE_VITALS_BLOOD_PRESSURE, values, KEY_V_BLOOD_PRESAR_ID + " = ?", new String[]{String.valueOf(aid)});
    }


    public void updateVBurnCalories(String aid, String adate, String atime, String abc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_BURN_CALORIES_DATE, adate);
        values.put(KEY_V_BURN_CALORIES_TIME, atime);
        values.put(KEY_V_BURN_CALORIES_BURN_CALORIES, abc);
        db.update(TABLE_VITALS_BURN_CALORIES, values, KEY_V_BURN_CALORIES_ID + " = ?", new String[]{String.valueOf(aid)});
    }

    public void updateVCoffine(String aid, String adate, String atime, String abc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_CAFFEINE_DATE, adate);
        values.put(KEY_V_CAFFEINE_TIME, atime);
        values.put(KEY_V_CAFFEINE_CAFFEINE, abc);
        db.update(TABLE_VITALS_CAFFEINE, values, KEY_V_CAFFEINE_ID + " = ?", new String[]{String.valueOf(aid)});
    }

    public void updateAHeight(String aid, String adate, String atime, String aheight, String aunit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_HEIGHT_DATE, adate);
        values.put(KEY_V_HEIGHT_TIME, atime);
        values.put(KEY_V_HEIGHT_HEIGHT, aheight);
        values.put(KEY_V_HEIGHT_UNIT, aunit);
        db.update(TABLE_VITALS_HEIGHT, values, KEY_V_HEIGHT_ID + " = ?", new String[]{String.valueOf(aid)});
    }

    public void updateAIntakeCalories(String aid, String adate, String atime, String acal, String aunit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_INTAKE_CALORIES_DATE, adate);
        values.put(KEY_V_INTAKE_CALORIES_TIME, atime);
        values.put(KEY_V_INTAKE_CALORIES_INTAKE_CALORIES, acal);
        values.put(KEY_V_INTAKE_CALORIES_UNIT, aunit);
        db.update(TABLE_VITALS_INTAKE_CALORIES, values, KEY_V_INTAKE_CALORIES_ID + " = ?", new String[]{String.valueOf(aid)});
    }

    public void updateAPulse(String aid, String adate, String atime, String acal, String aunit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_PULSE_DATE, adate);
        values.put(KEY_V_PULSE_TIME, atime);
        values.put(KEY_V_PULSE_PILSE, acal);
        values.put(KEY_V_PULSE_UNIT, aunit);
        db.update(TABLE_VITALS_PULSE, values, KEY_V_PULSE_ID + " = ?", new String[]{String.valueOf(aid)});
    }


    public void updateATemperature(String aid, String adate, String atime, String atemp, String aunit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_TEMPERATURE_DATE, adate);
        values.put(KEY_V_TEMPERATURE_TIME, atime);
        values.put(KEY_V_TEMPERATURE_TEMPERATURE, atemp);
        values.put(KEY_V_TEMPERATURE_UNIT, aunit);
        db.update(TABLE_VITALS_TEMPERATURE, values, KEY_V_TEMPERATURE_ID + " = ?", new String[]{String.valueOf(aid)});
    }


    public void updateAWeight(String aid, String adate, String atime, String aweight, String aunit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_WEIGHT_DATE, adate);
        values.put(KEY_V_WEIGHT_TIME, atime);
        values.put(KEY_V_WEIGHT_WEIGHT, aweight);
        values.put(KEY_V_WEIGHT_UNIT, aunit);
        db.update(TABLE_VITALS_WEIGHT, values, KEY_V_WEIGHT_ID + " = ?", new String[]{String.valueOf(aid)});
    }



    public void addInvestigation(String idate, String itime,
                                 String iinvestigation, String iresult,
                                 String iunit, String irepeat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_INVESTIGATION_DATE, idate);
        values.put(KEY_INVESTIGATION_TIME, itime);
        values.put(KEY_INVESTIGATION_NAME, iinvestigation);
        values.put(KEY_INVESTIGATION_RESULT, iresult);
        values.put(KEY_INVESTIGATION_UNIT, iunit);
        values.put(KEY_INVESTIGATION_REPEAT, irepeat);
        db.insert(TABLE_INVESTIGATION, null, values);
        String url = "http://10.10.10.1/ApiHealth.php?apicall=createinvestigation&date="+idate+"&time="+itime+"&name="+iinvestigation+"&result="+iresult+"&repeat="+irepeat;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
            //    Toast.makeText(context ,error.toString(),Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);

    }

    public long addReminder(String rname, String resonreminder,
                            String rdate, String rtime,
                            String rrepeat, String rnote) {


        String name = null,reason =null,date = null,time = null,reapeat = null;
        String note = null;
        try {
            name = URLEncoder.encode(rname,"UTF-8");
            reason = URLEncoder.encode(resonreminder,"UTF-8");
            date = URLEncoder.encode(rdate,"UTF-8");
            time = URLEncoder.encode(rtime,"UTF-8");
            reapeat = URLEncoder.encode(rrepeat,"UTF-8");

            note = URLEncoder.encode(rnote,"UTF-8");


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://10.10.10.1/ApiHealth.php?apicall=creathealthreminder&name="+name+"&resone="+reason+"&date="+date+"&time="+time+"&repeat="+reapeat+"&note="+note;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_R_NAME, rname);
        values.put(KEY_R_RESON_FOR_REMINDER, resonreminder);
        values.put(KEY_R_DATE, rdate);
        values.put(KEY_R_TIME, rtime);
        values.put(KEY_R_REPEAT, rrepeat);
        values.put(KEY_R_NOTE, rnote);
       long id= db.insert(TABLE_REMINDER, null, values);

        Log.i("dbid", String.valueOf(id));

return id;
    }



    public void addProfileCalender(String date, String uniqID,
                                   String name, String data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PROFILE_CAL_DATE, date);
        values.put(KEY_PROFILE_CAL_UNIQ_ID, uniqID);
        values.put(KEY_PROFILE_CAL_NAME, name);
        values.put(KEY_PROFILE_CAL_STRING, data);
        db.insert(TABLE_PROFILE_CALENDER, null, values);
        String url = "http://10.10.10.1/ApiHealth.php?apicall=createhealthprofilecalender&name="+name+"&date="+date+"&string="+data+"&uid="+uniqID;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
               // Toast.makeText(context ,error.toString(),Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);
    }








    public void addMedicanes(String mname, String mtype,
                             String mroute, String mdosage,
                             String minstn, String mreasiontalking,
                             String mdurationhours, String mduratondays,
                             String mstartdate, String menddate,
                             String mtotalquantity, String mprescribeby) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_M_NAME, mname);
        values.put(KEY_M_TYPE, mtype);
        values.put(KEY_M_ROUTE, mroute);
        values.put(KEY_M_DOSAGE, mdosage);
        values.put(KEY_M_INSTRUCTION, minstn);
        values.put(KEY_M_REASON_TALKING, mreasiontalking);
        values.put(KEY_M_DURATION_HOURS, mdurationhours);
        values.put(KEY_M_DURATION_DAYS, mduratondays);
        values.put(KEY_M_START_DATE, mstartdate);
        values.put(KEY_M_END_DATE, menddate);
        values.put(KEY_M_TOTAL_QUANTITY, mtotalquantity);
        values.put(KEY_M_PRESCRIBE_BY, mprescribeby);
        db.insert(TABLE_MEDICANCES, null, values);

        String name1= mname;
        String type1 = mtype;
        String route1 = mroute;
        String mdosage1 = mdosage;
        String minstn1 = minstn;

        String mreasiontalking1 = mreasiontalking;
        String mdurationhours1 = mdurationhours;
        String mduratondays1 = mduratondays;
        String mstartdate1 = mstartdate;
        String menddate1 = menddate;

        String mtotalquantity1 = mtotalquantity;
        String mprescribeby1 = mprescribeby;

        String name = null,type =null,route = null,dosage = null,instn = null;
        String reasiontalking = null,durationhours =null,duratondays = null,startdate = null,enddate = null;
        String totalquantity = null,prescribeby =null;
        try {
            name = URLEncoder.encode(name1,"UTF-8");
            type = URLEncoder.encode(type1,"UTF-8");
            route = URLEncoder.encode(route1,"UTF-8");
            dosage = URLEncoder.encode(mdosage1,"UTF-8");
            instn = URLEncoder.encode(minstn1,"UTF-8");

            reasiontalking = URLEncoder.encode(mreasiontalking1,"UTF-8");
            durationhours = URLEncoder.encode(mdurationhours1,"UTF-8");
            duratondays = URLEncoder.encode(mduratondays1,"UTF-8");
            startdate = URLEncoder.encode(mstartdate1,"UTF-8");
            enddate = URLEncoder.encode(menddate1,"UTF-8");

            totalquantity = URLEncoder.encode(mtotalquantity1,"UTF-8");
            prescribeby = URLEncoder.encode(mprescribeby1,"UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = "http://10.10.10.1/ApiHealth.php?apicall=createmedicine&name="+name+"&type="+type+"&route="+route+"&dosage="+dosage+"&instruction="+instn+"&resone="+reasiontalking+"&durationhour="+durationhours+"&durationdays="+duratondays+"&startdate="+startdate+"&enddate="+enddate+"&totalquantity="+totalquantity+"&prescribed="+prescribeby;

        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);
    }


    public void addHealthRecord(String hrname, String hrgender,
                                String hrprofileimg, String hrbirthdate,
                                String hrbloodtype, String hrnormalbodytemp,
                                String hrallergies, String hrhealthconditon,
                                String hrsurgerieshistory, String hrhospitalhistory,
                                String hrnotes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HEALTH_NAME, hrname);
        values.put(KEY_HEALTH_GENDER, hrgender);
        values.put(KEY_HEALTH_PROFILE_IMG, hrprofileimg);
        values.put(KEY_HEALTH_BIRTHDATE, hrbirthdate);
        values.put(KEY_HEALTH_BLOODTYPE, hrbloodtype);
        values.put(KEY_HEALTH_NORMAL_BODY_TEMPATURE, hrnormalbodytemp);
        values.put(KEY_HEALTH_ALLERGIES, hrallergies);
        values.put(KEY_HEALTH_HEALTH_CONDITION, hrhealthconditon);
        values.put(KEY_HEALTH_SURGERIES_HISTORY, hrsurgerieshistory);
        values.put(KEY_HEALTH_HOSPITALIZATION_HISTORY, hrhospitalhistory);
        values.put(KEY_HEALTH_NOTES, hrnotes);
        db.insert(TABLE_HEALTH_RECORD, null, values);

        String name = null,gender =null,img = null,birthdate = null,bloodtype = null;
        String bodytemp = null,allergies =null,healthcondition = null,surgeryhistory = null,hospitalhistory = null;
        String notes = null;
        try {
            name = URLEncoder.encode(hrname,"UTF-8");
            gender = URLEncoder.encode(hrgender,"UTF-8");
           if(hrprofileimg!=null){
            img = URLEncoder.encode(hrprofileimg,"UTF-8");}
            else {
               img=hrprofileimg;
           }
            birthdate = URLEncoder.encode(hrbirthdate,"UTF-8");
            bloodtype = URLEncoder.encode(hrbloodtype,"UTF-8");

            bodytemp = URLEncoder.encode(hrnormalbodytemp,"UTF-8");
            allergies = URLEncoder.encode(hrallergies,"UTF-8");
            healthcondition = URLEncoder.encode(hrhealthconditon,"UTF-8");
            surgeryhistory = URLEncoder.encode(hrsurgerieshistory,"UTF-8");
            hospitalhistory = URLEncoder.encode(hrhospitalhistory,"UTF-8");

            notes = URLEncoder.encode(hrnotes,"UTF-8");


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String url = "http://10.10.10.1/ApiHealth.php?apicall=createhealthrecord&name="+name+"&gender="+gender+"&img="+img+"&birthday="+birthdate+"&bloodtype="+bloodtype+"&normal_body_temp="+bodytemp+"&allergies="+allergies+"&healthcondition="+healthcondition+"&sugeryhistory="+surgeryhistory+"&hospitalization_history="+hospitalhistory+"&notes="+notes;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);
//        Toast.makeText(context, ""+hrprofileimg, Toast.LENGTH_SHORT).show();
    }

    public void DeleteReminderRaw(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_REMINDER + " WHERE " + KEY_R_ID + "='" + id + "'");
        db.close();
    }

    public void deleteMedicinsRaw(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_MEDICANCES + " WHERE " + KEY_M_ID + "='" + id + "'");
        db.close();
    }

    public void deleteHealthRecord(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_HEALTH_RECORD + " WHERE " + KEY_HEALTH_ID + "='" + id + "'");
        db.close();
    }

    public void deleteInvestigation(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_INVESTIGATION + " WHERE " + KEY_INVESTIGATION_ID + "='" + id + "'");
        db.close();
    }


    public void deleteVTemperature(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_VITALS_TEMPERATURE + " WHERE " + KEY_V_TEMPERATURE_ID + "='" + id + "'");
        db.close();
    }


    public void deleteVPulse(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_VITALS_PULSE + " WHERE " + KEY_V_PULSE_ID + "='" + id + "'");
        db.close();
    }

    public void deleteVBloodPressure(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_VITALS_BLOOD_PRESSURE + " WHERE " + KEY_V_BLOOD_PRESAR_ID + "='" + id + "'");
        db.close();
    }

    public void deleteVHeight(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_VITALS_HEIGHT + " WHERE " + KEY_V_HEIGHT_ID + "='" + id + "'");
        db.close();
    }

    public void deleteVWeight(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_VITALS_WEIGHT + " WHERE " + KEY_V_WEIGHT_ID + "='" + id + "'");
        db.close();
    }

    public void deleteVIntakeCalories(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_VITALS_INTAKE_CALORIES + " WHERE " + KEY_V_INTAKE_CALORIES_ID + "='" + id + "'");
        db.close();
    }

    public void deleteVBurnCalories(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_VITALS_BURN_CALORIES + " WHERE " + KEY_V_BURN_CALORIES_ID + "='" + id + "'");
        db.close();
    }

    public void deleteVAlcohol(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_VITALS_ALCOHOL + " WHERE " + KEY_V_ALCOHOL_ID + "='" + id + "'");
        db.close();
    }

    public void deleteVCaffeine(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_VITALS_CAFFEINE + " WHERE " + KEY_V_CAFFEINE_ID + "='" + id + "'");
        db.close();
    }

    public void updateHealthRecord(String hrId, String hrname, String hrgender,
                                   String hrprofileimg, String hrbirthdate,
                                   String hrbloodtype, String hrnormalbodytemp,
                                   String hrallergies, String hrhealthconditon,
                                   String hrsurgerieshistory, String hrhospitalhistory,
                                   String hrnotes)

    {
       //Log.i("imagepathh",hrprofileimg);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HEALTH_NAME, hrname);
        values.put(KEY_HEALTH_GENDER, hrgender);
        values.put(KEY_HEALTH_PROFILE_IMG, hrprofileimg);
        values.put(KEY_HEALTH_BIRTHDATE, hrbirthdate);
        values.put(KEY_HEALTH_BLOODTYPE, hrbloodtype);
        values.put(KEY_HEALTH_NORMAL_BODY_TEMPATURE, hrnormalbodytemp);
        values.put(KEY_HEALTH_ALLERGIES, hrallergies);
        values.put(KEY_HEALTH_HEALTH_CONDITION, hrhealthconditon);
        values.put(KEY_HEALTH_SURGERIES_HISTORY, hrsurgerieshistory);
        values.put(KEY_HEALTH_HOSPITALIZATION_HISTORY, hrhospitalhistory);
        values.put(KEY_HEALTH_NOTES, hrnotes);
        db.update(TABLE_HEALTH_RECORD, values, KEY_HEALTH_ID + " = ?", new String[]{String.valueOf(hrId)});

    }



    public void addProfileTreatMedication(String date, String name,
                                          String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TRETMENT_MEDI_DATE,date);
        values.put(KEY_TRETMENT_MEDI_NAME, name);
        values.put(KEY_TRETMENT_MEDI_DESCRIPTION, description);
        db.insert(TABLE_PROFILE_TREATMENT_MEDICATION, null, values);
        String url = "http://10.10.10.1/ApiHealth.php?apicall=createtreatmentmedication&name="+name+"&date="+date+"&description="+description;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);

    }


    public void addProfileTreatVaccine(String date, String name,
                                       String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TREATMENT_VACCINE_DATE,date);
        values.put(KEY_TREATMENT_VACCINE_NAME, name);
        values.put(KEY_TREATMENT_VACCINE_DESCRIPTION, description);
        db.insert(TABLE_PROFILE_TREATMENT_VACCINE, null, values);
        String url = "http://10.10.10.1/ApiHealth.php?apicall=createtreatmentvaccine&name="+name+"&date="+date+"&description="+description;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);
    }



    public void addProfileTreatOthers(String date, String name,
                                      String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TREATMENT_OTHER_DATE,date);
        values.put(KEY_TREATMENT_OTHER_NAME, name);
        values.put(KEY_TREATMENT_OTHER_DESCRIPTION, description);
        db.insert(TABLE_PROFILE_TREATMENT_OTHER, null, values);
        String url = "http://10.10.10.1/ApiHealth.php?apicall=createtreatmentother&name="+name+"&date="+date+"&description="+description;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);
    }


    public void addProfileTreatSpeciality(String date, String name,
                                          String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TREATMENT_SPECIALITY_DATE,date);
        values.put(KEY_TREATMENT_SPECIALITY_NAME, name);
        values.put(KEY_TREATMENT_SPECIALITY_DESCRIPTION, description);
        db.insert(TABLE_PROFILE_TREATMENT_SPECIALITY, null, values);
        String url = "http://10.10.10.1/ApiHealth.php?apicall=createtreatmentspecility&name="+name+"&date="+date+"&description="+description;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);
    }

    public void addProfileTreatDoctorVisit(String date, String name,
                                           String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TREATMENT_DOCTOR_VISIT_DATE,date);
        values.put(KEY_TREATMENT_DOCTOR_VISIT_NAME, name);
        values.put(KEY_TREATMENT_DOCTOR_VISIT_DESCRIPTION, description);
        db.insert(TABLE_PROFILE_TREATMENT_DOCTOR_VISIT, null, values);
        String url = "http://10.10.10.1/ApiHealth.php?apicall=createdoctorvisit&name="+name+"&date="+date+"&description="+description;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);
    }

    public void addProfileTreatStayHome(String date, String name,
                                        String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TRETMENT_STAY_HOME_DATE,date);
        values.put(KEY_TRETMENT_STAY_HOME_NAME, name);
        values.put(KEY_TRETMENT_STAY_HOME_DESCRIPTION, description);
        db.insert(TABLE_PROFILE_TREATMENT_STAYING_HOME, null, values);
        String url = "http://10.10.10.1/ApiHealth.php?apicall=createstyinghome&name="+name+"&date="+date+"&description="+description;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
              //  Toast.makeText(context ,error.toString(),Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);
    }


    public void addVTemperature(String tdate, String ttime,
                                String ttemp, String tunit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_TEMPERATURE_DATE, tdate);
        values.put(KEY_V_TEMPERATURE_TIME, ttime);
        values.put(KEY_V_TEMPERATURE_TEMPERATURE, ttemp);
        values.put(KEY_V_TEMPERATURE_UNIT, tunit);
        db.insert(TABLE_VITALS_TEMPERATURE, null, values);
                    String url = "http://10.10.10.1/ApiHealth.php?apicall=createvitaltemprature&date="+tdate+"&time="+ttime+"&temp="+ttemp+"&unit="+tunit;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                //Toast.makeText(context ,error.toString(),Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);
//        Toast.makeText(context, ""+hrprofileimg, Toast.LENGTH_SHORT).show();
    }
    //}

    public void addVPulse(String pdate, String ptime,
                          String ppulse, String punit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_PULSE_DATE, pdate);
        values.put(KEY_V_PULSE_TIME, ptime);
        values.put(KEY_V_PULSE_PILSE, ppulse);
        values.put(KEY_V_PULSE_UNIT, punit);
        db.insert(TABLE_VITALS_PULSE, null, values);
        String url = "http://10.10.10.1/ApiHealth.php?apicall=createvitalpulse&date="+pdate+"&time="+ptime+"&pulse="+ppulse+"&unit="+punit;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);


    }


    public void addVBloodPressure(String bpdate, String bptime,
                                  String bpsystolic, String bpdiastolic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_BLOOD_PRESAR_DATE, bpdate);
        values.put(KEY_V_BLOOD_PRESAR_TIME, bptime);
        values.put(KEY_V_BLOOD_PRESAR_SYSTOLIC, bpsystolic);
        values.put(KEY_V_BLOOD_PRESAR_DIASTOLIC, bpdiastolic);
        db.insert(TABLE_VITALS_BLOOD_PRESSURE, null, values);
        String url = "http://10.10.10.1/ApiHealth.php?apicall=createvitalbloodpressure&date="+bpdate+"&time="+bptime+"&systolic="+bpsystolic+"&diastolic="+bpdiastolic;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);


    }


    public void addVHeight(String hdate, String htime,
                           String hheight, String hunit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_HEIGHT_DATE, hdate);
        values.put(KEY_V_HEIGHT_TIME, htime);
        values.put(KEY_V_HEIGHT_HEIGHT, hheight);
        values.put(KEY_V_HEIGHT_UNIT, hunit);
        db.insert(TABLE_VITALS_HEIGHT, null, values);
        String url = "http://10.10.10.1/ApiHealth.php?apicall=createvitalheight&date="+hdate+"&time="+htime+"&height="+hheight+"&unit="+hunit;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);


    }

    public void addVWeight(String wdate, String wtime,
                           String wweight, String wunit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_WEIGHT_DATE, wdate);
        values.put(KEY_V_WEIGHT_TIME, wtime);
        values.put(KEY_V_WEIGHT_WEIGHT, wweight);
        values.put(KEY_V_WEIGHT_UNIT, wunit);
        db.insert(TABLE_VITALS_WEIGHT, null, values);
        String url = "http://10.10.10.1/ApiHealth.php?apicall=createvitalweight&date="+wdate+"&time="+wtime+"&weight="+wweight+"&unit="+wunit;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);


    }

    public void addVIntakeCalories(String icdate, String ictime,
                                   String intakecory, String icunit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_INTAKE_CALORIES_DATE, icdate);
        values.put(KEY_V_INTAKE_CALORIES_TIME, ictime);
        values.put(KEY_V_INTAKE_CALORIES_INTAKE_CALORIES, intakecory);
        values.put(KEY_V_INTAKE_CALORIES_UNIT, icunit);
        db.insert(TABLE_VITALS_INTAKE_CALORIES, null, values);
        String url = "http://10.10.10.1/ApiHealth.php?apicall=createvitalintakecalories&date="+icdate+"&time="+ictime+"&intake_calories="+intakecory+"&unit="+icunit;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);


    }

    public void addVBurnCalories(String bcdate, String bctime,
                                 String burncory, String bunit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_BURN_CALORIES_DATE, bcdate);
        values.put(KEY_V_BURN_CALORIES_TIME, bctime);
        values.put(KEY_V_BURN_CALORIES_BURN_CALORIES, burncory);
        values.put(KEY_V_BURN_CALORIES_UNIT, bunit);
        db.insert(TABLE_VITALS_BURN_CALORIES, null, values);
        String url = "http://10.10.10.1/ApiHealth.php?apicall=createvitaburncalories&date="+bcdate+"&time="+bctime+"&burn_calories="+burncory+"&unit="+bunit;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);


    }

    public void addVAlcohol(String adate, String atime,
                            String alcohol, String aunit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_ALCOHOL_DATE, adate);
        values.put(KEY_V_ALCOHOL_TIME, atime);
        values.put(KEY_V_ALCOHOL_ALCOHOL, alcohol);
        values.put(KEY_V_ALCOHOL_UNIT, aunit);
        db.insert(TABLE_VITALS_ALCOHOL, null, values);
        String url = "http://10.10.10.1/ApiHealth.php?apicall=createvitaalchohol&date="+adate+"&time="+atime+"&alchohol="+alcohol+"&unit="+aunit;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);

    }


    public void addVCaffeine(String cdate, String ctime,
                             String caffeine, String cunit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_V_CAFFEINE_DATE, cdate);
        values.put(KEY_V_CAFFEINE_TIME, ctime);
        values.put(KEY_V_CAFFEINE_CAFFEINE, caffeine);
        values.put(KEY_V_CAFFEINE_UNIT, cunit);
        db.insert(TABLE_VITALS_CAFFEINE, null, values);
        String url = "http://10.10.10.1/ApiHealth.php?apicall=createvitacaffeine&date="+cdate+"&time="+ctime+"&caffeine="+caffeine+"&unit="+cunit;
        url=url.replaceAll(" ", "%20");
        queue = Volley.newRequestQueue(context);
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                // hide the progress dialog
                Toast.makeText(context ,error.toString(), Toast.LENGTH_LONG).show();
            }


        }) ;
// {
//
//
//        };

        queue.add(jsonObjReq);


    }


    public List<HashMap<String, String>> getReminder() {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_REMINDER;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("r_id", c.getString(c.getColumnIndex(KEY_R_ID)));
                map.put("r_name", c.getString(c.getColumnIndex(KEY_R_NAME)));
                map.put("r_for_r", c.getString(c.getColumnIndex(KEY_R_RESON_FOR_REMINDER)));
                map.put("r_date", c.getString(c.getColumnIndex(KEY_R_DATE)));
                map.put("r_time", c.getString(c.getColumnIndex(KEY_R_TIME)));
                map.put("r_repeat", c.getString(c.getColumnIndex(KEY_R_REPEAT)));
                map.put("r_note", c.getString(c.getColumnIndex(KEY_R_NOTE)));
                users.add(map);
            } while (c.moveToNext());
        }

        return users;
    }

    public List<HashMap<String, String>> getMedicances() {
        List<HashMap<String, String>> parents = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_MEDICANCES;
        Log.e("DATA", selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("m_id", c.getString(c.getColumnIndex(KEY_M_ID)));
                map.put("m_name", c.getString(c.getColumnIndex(KEY_M_NAME)));
                map.put("m_type", c.getString(c.getColumnIndex(KEY_M_TYPE)));
                map.put("m_route", c.getString(c.getColumnIndex(KEY_M_ROUTE)));
                map.put("m_dosage", c.getString(c.getColumnIndex(KEY_M_DOSAGE)));
                map.put("m_instrtion", c.getString(c.getColumnIndex(KEY_M_INSTRUCTION)));
                map.put("m_resontalk", c.getString(c.getColumnIndex(KEY_M_REASON_TALKING)));
                map.put("m_d_hours", c.getString(c.getColumnIndex(KEY_M_DURATION_HOURS)));
                map.put("m_d_days", c.getString(c.getColumnIndex(KEY_M_DURATION_DAYS)));
                map.put("m_startdate", c.getString(c.getColumnIndex(KEY_M_START_DATE)));
                map.put("m_enddate", c.getString(c.getColumnIndex(KEY_M_END_DATE)));
                map.put("m_quantity", c.getString(c.getColumnIndex(KEY_M_TOTAL_QUANTITY)));
                map.put("m_prescrobeby", c.getString(c.getColumnIndex(KEY_M_PRESCRIBE_BY)));
                parents.add(map);
            } while (c.moveToNext());
        }
        return parents;
    }

    public List<HashMap<String, String>> getHealthRecord() {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_HEALTH_RECORD;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("hr_id", c.getString(c.getColumnIndex(KEY_HEALTH_ID)));
                map.put("hr_name", c.getString(c.getColumnIndex(KEY_HEALTH_NAME)));
                map.put("hr_gender", c.getString(c.getColumnIndex(KEY_HEALTH_GENDER)));
                map.put("hr_profile_img", c.getString(c.getColumnIndex(KEY_HEALTH_PROFILE_IMG)));
                map.put("hr_birthdate", c.getString(c.getColumnIndex(KEY_HEALTH_BIRTHDATE)));
                map.put("hr_bloodtype", c.getString(c.getColumnIndex(KEY_HEALTH_BLOODTYPE)));
                map.put("hr_nomal_body_temp", c.getString(c.getColumnIndex(KEY_HEALTH_NORMAL_BODY_TEMPATURE)));
                map.put("hr_allergies", c.getString(c.getColumnIndex(KEY_HEALTH_ALLERGIES)));
                map.put("hr_health_condition", c.getString(c.getColumnIndex(KEY_HEALTH_HEALTH_CONDITION)));
                map.put("hr_surgeries_his", c.getString(c.getColumnIndex(KEY_HEALTH_SURGERIES_HISTORY)));
                map.put("hr_hospitalization_his", c.getString(c.getColumnIndex(KEY_HEALTH_HOSPITALIZATION_HISTORY)));
                map.put("hr_notes", c.getString(c.getColumnIndex(KEY_HEALTH_NOTES)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }

    public List<HashMap<String, String>> getInvestigation() {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_INVESTIGATION;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("investigation_id", c.getString(c.getColumnIndex(KEY_INVESTIGATION_ID)));
                map.put("investigation_date", c.getString(c.getColumnIndex(KEY_INVESTIGATION_DATE)));
                map.put("investigation_time", c.getString(c.getColumnIndex(KEY_INVESTIGATION_TIME)));
                map.put("investigation_name", c.getString(c.getColumnIndex(KEY_INVESTIGATION_NAME)));
                map.put("investigation_result", c.getString(c.getColumnIndex(KEY_INVESTIGATION_RESULT)));
                map.put("investigation_unit", c.getString(c.getColumnIndex(KEY_INVESTIGATION_UNIT)));
                map.put("investigation_repeat", c.getString(c.getColumnIndex(KEY_INVESTIGATION_REPEAT)));
//                map.put("v_temp_unit", c.getString(c.getColumnIndex(KEY_V_TEMPERATURE_UNIT)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }


    public List<HashMap<String, String>> getVTemperature() {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_VITALS_TEMPERATURE;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("v_temp_id", c.getString(c.getColumnIndex(KEY_V_TEMPERATURE_ID)));
                map.put("v_temp_date", c.getString(c.getColumnIndex(KEY_V_TEMPERATURE_DATE)));
                map.put("v_temp_time", c.getString(c.getColumnIndex(KEY_V_TEMPERATURE_TIME)));
                map.put("v_temp_temperature", c.getString(c.getColumnIndex(KEY_V_TEMPERATURE_TEMPERATURE)));
                map.put("v_temp_unit", c.getString(c.getColumnIndex(KEY_V_TEMPERATURE_UNIT)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }

    public List<HashMap<String, String>> getVPulse() {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_VITALS_PULSE;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("v_pulse_id", c.getString(c.getColumnIndex(KEY_V_PULSE_ID)));
                map.put("v_pulse_date", c.getString(c.getColumnIndex(KEY_V_PULSE_DATE)));
                map.put("v_pulse_time", c.getString(c.getColumnIndex(KEY_V_PULSE_TIME)));
                map.put("v_pulse_pulse", c.getString(c.getColumnIndex(KEY_V_PULSE_PILSE)));
                map.put("v_pulse_unit", c.getString(c.getColumnIndex(KEY_V_PULSE_UNIT)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }

    public List<HashMap<String, String>> getVBloodPressure() {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_VITALS_BLOOD_PRESSURE;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("v_bloodpressure_id", c.getString(c.getColumnIndex(KEY_V_BLOOD_PRESAR_ID)));
                map.put("v_bloodpressure_date", c.getString(c.getColumnIndex(KEY_V_BLOOD_PRESAR_DATE)));
                map.put("v_bloodpressure_time", c.getString(c.getColumnIndex(KEY_V_BLOOD_PRESAR_TIME)));
                map.put("v_bloodpressure_ststolic", c.getString(c.getColumnIndex(KEY_V_BLOOD_PRESAR_SYSTOLIC)));
                map.put("v_bloodpressure_diastolic", c.getString(c.getColumnIndex(KEY_V_BLOOD_PRESAR_DIASTOLIC)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }

    public List<HashMap<String, String>> getVHeight() {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_VITALS_HEIGHT;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("v_height_id", c.getString(c.getColumnIndex(KEY_V_HEIGHT_ID)));
                map.put("v_height_date", c.getString(c.getColumnIndex(KEY_V_HEIGHT_DATE)));
                map.put("v_height_time", c.getString(c.getColumnIndex(KEY_V_HEIGHT_TIME)));
                map.put("v_height_height", c.getString(c.getColumnIndex(KEY_V_HEIGHT_HEIGHT)));
                map.put("v_height_unit", c.getString(c.getColumnIndex(KEY_V_HEIGHT_UNIT)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }


    public List<HashMap<String, String>> getVWeight() {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_VITALS_WEIGHT;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("v_weight_id", c.getString(c.getColumnIndex(KEY_V_WEIGHT_ID)));
                map.put("v_weight_date", c.getString(c.getColumnIndex(KEY_V_WEIGHT_DATE)));
                map.put("v_weight_time", c.getString(c.getColumnIndex(KEY_V_WEIGHT_TIME)));
                map.put("v_weight_weight", c.getString(c.getColumnIndex(KEY_V_WEIGHT_WEIGHT)));
                map.put("v_weight_unit", c.getString(c.getColumnIndex(KEY_V_WEIGHT_UNIT)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }


    public List<HashMap<String, String>> getVIntakeCalories() {

        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String MY_QUERY = "SELECT  * FROM " + TABLE_VITALS_INTAKE_CALORIES;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("v_intake_calories_id", c.getString(c.getColumnIndex(KEY_V_INTAKE_CALORIES_ID)));
                map.put("v_intake_calories_date", c.getString(c.getColumnIndex(KEY_V_INTAKE_CALORIES_DATE)));
                map.put("v_intake_calories_time", c.getString(c.getColumnIndex(KEY_V_INTAKE_CALORIES_TIME)));
                map.put("v_intake_calories_intake_calories", c.getString(c.getColumnIndex(KEY_V_INTAKE_CALORIES_INTAKE_CALORIES)));
                map.put("v_intake_calories_unit", c.getString(c.getColumnIndex(KEY_V_INTAKE_CALORIES_UNIT)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }


    public List<HashMap<String, String>> getVBurnCalories() {

        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String MY_QUERY = "SELECT  * FROM " + TABLE_VITALS_BURN_CALORIES;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("v_burn_calories_id", c.getString(c.getColumnIndex(KEY_V_BURN_CALORIES_ID)));
                map.put("v_burn_calories_date", c.getString(c.getColumnIndex(KEY_V_BURN_CALORIES_DATE)));
                map.put("v_burn_calories_time", c.getString(c.getColumnIndex(KEY_V_BURN_CALORIES_TIME)));
                map.put("v_burn_calories_burn_calories", c.getString(c.getColumnIndex(KEY_V_BURN_CALORIES_BURN_CALORIES)));
                map.put("v_burn_calories_unit", c.getString(c.getColumnIndex(KEY_V_BURN_CALORIES_UNIT)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }


    public List<HashMap<String, String>> getVAlcohol() {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_VITALS_ALCOHOL;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("v_alcohol_id", c.getString(c.getColumnIndex(KEY_V_ALCOHOL_ID)));
                map.put("v_alcohol_date", c.getString(c.getColumnIndex(KEY_V_ALCOHOL_DATE)));
                map.put("v_alcohol_time", c.getString(c.getColumnIndex(KEY_V_ALCOHOL_TIME)));
                map.put("v_alcohol_alcohol", c.getString(c.getColumnIndex(KEY_V_ALCOHOL_ALCOHOL)));
                map.put("v_alcohol_unit", c.getString(c.getColumnIndex(KEY_V_ALCOHOL_UNIT)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }


    public List<HashMap<String, String>> getVCoffine() {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_VITALS_CAFFEINE;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("v_caffeine_id", c.getString(c.getColumnIndex(KEY_V_CAFFEINE_ID)));
                map.put("v_caffeine_date", c.getString(c.getColumnIndex(KEY_V_CAFFEINE_DATE)));
                map.put("v_caffeine_time", c.getString(c.getColumnIndex(KEY_V_CAFFEINE_TIME)));
                map.put("v_caffeine_caffeine", c.getString(c.getColumnIndex(KEY_V_CAFFEINE_CAFFEINE)));
                map.put("v_caffeine_unit", c.getString(c.getColumnIndex(KEY_V_CAFFEINE_UNIT)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }


    public List<HashMap<String, String>> getProfileTreatmentMedication() {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_PROFILE_TREATMENT_MEDICATION;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("key_treatment_medi_id", c.getString(c.getColumnIndex(KEY_TRETMENT_MEDI_ID)));
                map.put("key_treatment_medi_date", c.getString(c.getColumnIndex(KEY_TRETMENT_MEDI_DATE)));
                map.put("key_treatment_medi_name", c.getString(c.getColumnIndex(KEY_TRETMENT_MEDI_NAME)));
                map.put("key_treatment_medi_description", c.getString(c.getColumnIndex(KEY_TRETMENT_MEDI_DESCRIPTION)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }

    public List<HashMap<String, String>> getProfileTreatmentStayHome() {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_PROFILE_TREATMENT_STAYING_HOME;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("key_treatment_medi_id", c.getString(c.getColumnIndex(KEY_TRETMENT_STAY_HOME_ID)));
                map.put("key_treatment_stay_home_date", c.getString(c.getColumnIndex(KEY_TRETMENT_STAY_HOME_DATE)));
                map.put("key_treatment_medi_name", c.getString(c.getColumnIndex(KEY_TRETMENT_STAY_HOME_NAME)));
                map.put("key_treatment_stay_home_description", c.getString(c.getColumnIndex(KEY_TRETMENT_STAY_HOME_DESCRIPTION)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }


    public List<HashMap<String, String>> getProfileTreatmentVaccine() {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_PROFILE_TREATMENT_VACCINE;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("key_treatment_medi_id", c.getString(c.getColumnIndex(KEY_TREATMENT_VACCINE_ID)));
                map.put("key_treatment_vaccine_date", c.getString(c.getColumnIndex(KEY_TREATMENT_VACCINE_DATE)));
                map.put("key_treatment_medi_name", c.getString(c.getColumnIndex(KEY_TREATMENT_VACCINE_NAME)));
                map.put("key_treatment_vaccine_description", c.getString(c.getColumnIndex(KEY_TREATMENT_VACCINE_DESCRIPTION)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }

    public List<HashMap<String, String>> getProfileCalenderData(String id) {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_PROFILE_CALENDER;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("key_profile_cal_id", c.getString(c.getColumnIndex(KEY_PROFILR_CAL_ID)));
                map.put("key_profile_cal_name", c.getString(c.getColumnIndex(KEY_PROFILE_CAL_NAME)));
                map.put("key_profile_cal_date", c.getString(c.getColumnIndex(KEY_PROFILE_CAL_DATE)));
                map.put("key_profile_cal_string", c.getString(c.getColumnIndex(KEY_PROFILE_CAL_STRING)));
                map.put("key_profile_cal_uniq", c.getString(c.getColumnIndex(KEY_PROFILE_CAL_UNIQ_ID)));
                users.add(map);
                Log.i("datedb",c.getString(c.getColumnIndex(KEY_PROFILE_CAL_DATE)));
            } while (c.moveToNext());
        }
        return users;
    }




    public List<HashMap<String, String>> getProfileTreatmentOther() {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_PROFILE_TREATMENT_OTHER;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("key_treatment_medi_id", c.getString(c.getColumnIndex(KEY_TREATMENT_OTHER_ID)));
                map.put("key_treatment_other_date", c.getString(c.getColumnIndex(KEY_TREATMENT_OTHER_DATE)));
                map.put("key_treatment_medi_name", c.getString(c.getColumnIndex(KEY_TREATMENT_OTHER_NAME)));
                map.put("key_treatment_other_description", c.getString(c.getColumnIndex(KEY_TREATMENT_OTHER_DESCRIPTION)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }





    public List<HashMap<String, String>> getProfileTreatmentDocotorVisit() {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_PROFILE_TREATMENT_DOCTOR_VISIT;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("key_treatment_medi_id", c.getString(c.getColumnIndex(KEY_TREATMENT_DOCTOR_VISIT_ID)));
                map.put("key_treatment_doc_visit_date", c.getString(c.getColumnIndex(KEY_TREATMENT_DOCTOR_VISIT_DATE)));
                map.put("key_treatment_medi_name", c.getString(c.getColumnIndex(KEY_TREATMENT_DOCTOR_VISIT_NAME)));
                map.put("key_treatment_doc_visit_description", c.getString(c.getColumnIndex(KEY_TREATMENT_DOCTOR_VISIT_DESCRIPTION)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }



    public List<HashMap<String, String>> getProfileTreatmentSpeciality() {
        List<HashMap<String, String>> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String MY_QUERY = "SELECT  * FROM " + TABLE_PROFILE_TREATMENT_SPECIALITY;
        Cursor c = db.rawQuery(MY_QUERY, null);
        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("key_treatment_medi_id", c.getString(c.getColumnIndex(KEY_TREATMENT_SPECIALITY_ID)));
                map.put("key_treatment_doc_speciality_date", c.getString(c.getColumnIndex(KEY_TREATMENT_SPECIALITY_DATE)));
                map.put("key_treatment_medi_name", c.getString(c.getColumnIndex(KEY_TREATMENT_SPECIALITY_NAME)));
                map.put("key_treatment_doc_speciality_description", c.getString(c.getColumnIndex(KEY_TREATMENT_SPECIALITY_DESCRIPTION)));
                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }

    public void deleteProfileTreatMedication(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PROFILE_TREATMENT_MEDICATION + " WHERE " + KEY_TRETMENT_MEDI_ID + "='" + id + "'");
        db.close();
    }

    public void deleteProfileTreatStayHome(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PROFILE_TREATMENT_STAYING_HOME + " WHERE " + KEY_TRETMENT_STAY_HOME_ID + "='" + id + "'");
        db.close();
    }
    public void deleteProfileTreatOther(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PROFILE_TREATMENT_OTHER+ " WHERE " + KEY_TREATMENT_OTHER_ID + "='" + id + "'");
        db.close();
    } public void deleteProfileTreatDoctorVisit(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PROFILE_TREATMENT_DOCTOR_VISIT+ " WHERE " + KEY_TREATMENT_DOCTOR_VISIT_ID + "='" + id + "'");
        db.close();
    }

    public void deleteProfileTreatSpeciality(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PROFILE_TREATMENT_SPECIALITY+ " WHERE " + KEY_TREATMENT_SPECIALITY_ID + "='" + id + "'");
        db.close();
    }
    public void deleteProfileTreatVaccine(String id){
    SQLiteDatabase db = this.getWritableDatabase();
    db.execSQL("DELETE FROM " + TABLE_PROFILE_TREATMENT_VACCINE+ " WHERE " + KEY_TREATMENT_VACCINE_ID + "='" + id + "'");
    db.close();
}


    public void test() {
        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();

    }
    public ArrayList selectTriggredAlarm(long id){
       // List<HashMap<String, String>> users = new ArrayList<>();
        ArrayList users = new ArrayList();

        SQLiteDatabase db = this.getReadableDatabase();
       // String MY_QUERY = "SELECT  * FROM " + TABLE_REMINDER +"WHERE reminder.r_id ="+id;
        String MY_QUERY =  "SELECT * FROM " + TABLE_REMINDER+" WHERE reminder.r_id = "+id;
        Cursor c = db.rawQuery(MY_QUERY, null);
        Log.i("columcount", String.valueOf(c.getColumnCount()));
              int a =   c.getColumnIndex(KEY_R_NAME);
        int b = c.getColumnIndex(KEY_R_RESON_FOR_REMINDER);
        int c1 = c.getColumnIndex(KEY_R_REPEAT);
        if (c.moveToFirst()) {
            do {
                users.add( c.getString(a));
                users.add(c.getString(b));
                users.add(c.getString(c1));

//                HashMap<String, String> map = new HashMap<>();
//                map.put("key_treatment_medi_id", c.getString(c.getColumnIndex(KEY_TREATMENT_OTHER_ID)));
//                map.put("key_treatment_other_date", c.getString(c.getColumnIndex(KEY_TREATMENT_OTHER_DATE)));
//                map.put("key_treatment_medi_name", c.getString(c.getColumnIndex(KEY_TREATMENT_OTHER_NAME)));
//                map.put("key_treatment_other_description", c.getString(c.getColumnIndex(KEY_TREATMENT_OTHER_DESCRIPTION)));
//                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }


}
