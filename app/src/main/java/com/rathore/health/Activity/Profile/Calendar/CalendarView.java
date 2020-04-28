package com.rathore.health.Activity.Profile.Calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.example.archi.health.R;

//import com.pierfrancescosoffritti.aytplayersample.R;
import com.rathore.health.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;


/**
 * Created by a7med on 28/06/2015.
 */
public class CalendarView extends LinearLayout {
    // for logging
    private static final String LOGTAG = "Calendar View";
    // default date format
    private static final String DATE_FORMAT = "MMM yyyy";
    // how many days to show, defaults to six weeks, 42 days
    private static final int DAYS_COUNT=42;
    // seasons' rainbow
    int[] rainbow = new int[]{
            R.color.black,
            R.color.black,
            R.color.black,
            R.color.black
    };
    // month-season association (northern hemisphere, sorry australia :)
    int[] monthSeason = new int[]{2, 2, 3, 3, 3, 0, 0, 0, 1, 1, 1, 2};
    // date format
    private String dateFormat;
    // current displayed month
    private Calendar currentDate = Calendar.getInstance();
    //event handling
    private EventHandler eventHandler = null;
    // internal components
    private LinearLayout header;
    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView txtDate;
    private GridView grid;

    public CalendarView(Context context) {
        super(context);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }

    /**
     * Load control xml layout
     */
    private void initControl(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calendar_control, this);
        loadDateFormat(attrs);
        assignUiElements();
        assignClickHandlers();
        updateCalendar();
    }

    private void loadDateFormat(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView);

        try {
            // try to load provided date format, and fallback to default otherwise
            dateFormat = ta.getString(R.styleable.CalendarView_dateFormat);
            if (dateFormat == null)
                dateFormat = DATE_FORMAT;
        } finally {
            ta.recycle();
        }


    }

    private void assignUiElements() {
        // layout is inflated, assign local variables to components

        header = (LinearLayout) findViewById(R.id.calendar_header);
        btnPrev = (ImageView) findViewById(R.id.calendar_prev_button);
        btnNext = (ImageView) findViewById(R.id.calendar_next_button);
        txtDate = (TextView) findViewById(R.id.calendar_date_display);
        grid = (GridView) findViewById(R.id.calendar_grid);
    }

    private void assignClickHandlers() {
        // add one month and refresh UI
        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDate.add(Calendar.MONTH, 1);
                updateCalendar();
            }
        });

        // subtract one month and refresh UI
        btnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDate.add(Calendar.MONTH, -1);
                updateCalendar();
            }
        });

        // long-pressing a day
        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> view, View cell, int position, long id) {
                // handle long-press
                if (eventHandler == null)
                    return false;

                eventHandler.onDayLongPress((Date) view.getItemAtPosition(position));
                return true;
            }
        });


        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (eventHandler != null) {
                    eventHandler.onDayClickPress((Date) parent.getItemAtPosition(position), view);

//                    new SimpleTooltip.Builder(getContext())
//                            .anchorView(view)
//                            .text(R.string.btn_inside_modal)
//                            .gravity(Gravity.BOTTOM)
//                            .dismissOnOutsideTouch(false)
//                            .dismissOnInsideTouch(true)
//                            .modal(true)
//                            .build()
//                            .show();
                }
            }
        });


    }

    /**
     * Display dates correctly in grid
     */
    public void updateCalendar() {
        updateCalendar(null);
    }

    /**
     * Display dates correctly in grid
     */
    public void updateCalendar(HashSet<Date> events) {

        ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar) currentDate.clone();

        // determine the cell for current month's beginning
        calendar.set(Calendar.DAY_OF_MONTH,1);
        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK)-1;
        Log.d("msg", "monthBeginningCell   " + monthBeginningCell);

        // move calendar backwards to the beginning of the week
        calendar.add(Calendar.DAY_OF_MONTH,-monthBeginningCell);
        Log.d("msg","month count "+DAYS_COUNT+  "  month  "+calendar.getTime().getMonth());

        // fill cells
        while (cells.size() < DAYS_COUNT) {

            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);

            int day= calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            Log.d("msg","day "+calendar.getTime().getMonth()+"  day   count "+day);

        }

        // update grid
        grid.setAdapter(new CalendarAdapter(getContext(), cells, events));

        // update title
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        txtDate.setText(sdf.format(currentDate.getTime()));

        // set header color according to current season
        int month = currentDate.get(Calendar.MONTH);
        int season = monthSeason[month];
        int color = rainbow[season];

        header.setBackgroundColor(getResources().getColor(color));
    }

    /**
     * Assign event handler to be passed needed events
     */
    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    /**
     * This interface defines what events to be reported to
     * the outside world
     */

    public interface EventHandler {

        void onDayLongPress(Date date);

        void onDayClickPress(Date date, View view);
    }

    private class CalendarAdapter extends ArrayAdapter<Date> {

        // days with events
        private HashSet<Date> eventDays;

        // for view inflation
        private LayoutInflater inflater;

        public CalendarAdapter(Context context, ArrayList<Date> days, HashSet<Date> eventDays) {
            super(context, R.layout.calendar_control_day, days);
            this.eventDays = eventDays;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            // day in question
            Date date = getItem(position);
            int day = date.getDate();
            int month = date.getMonth();
            int year = date.getYear();
            //    Log.d("msg", "getview " + day + ":" + month + ":" + year);
            // today
            Date today = new Date();
            // inflate item if it does not exist yet
            if (view == null)
                view = inflater.inflate(R.layout.calendar_control_day, parent, false);
            // if this day has an event, specify event image
            TextView tvDaySquare = (TextView) view.findViewById(R.id.dateSquare);
            ImageView ivIcon= (ImageView) view.findViewById(R.id.calendar_control_day_iv_image);
            view.setBackgroundResource(0);
            if (MainCalendar.events != null) {
                for (Date eventDate : MainCalendar.events) {
                    if (eventDate.getDate() == day &&
                            eventDate.getMonth() == month &&
                            eventDate.getYear() == year) {

                        // mark this day for event
                        ivIcon.setVisibility(VISIBLE);
                        tvDaySquare.setTextColor(ContextCompat.getColor(getContext(),R.color.black));
                        //view.setBackgroundResource(R.drawable.);
                       // tvDaySquare.setText("this is test ");
                        break;
                    }
                }
            }
            // clear styling
       //     tvDaySquare.setTypeface(null, Typeface.NORMAL);
           //
            if (month != today.getMonth() || year != today.getYear()) {
                // if this day is outside current month, grey it out
              tvDaySquare.setTextColor(ContextCompat.getColor(getContext(),R.color.black));
            } else if (day == today.getDay()) {
                // if it is today, set it to blue/bold
              //  tvDaySquare.setTypeface(null, Typeface.BOLD);
                tvDaySquare.setTextColor(ContextCompat.getColor(getContext(),R.color.black));
            }
            // set text
            tvDaySquare.setText(String.valueOf(date.getDate()));
            if (MainCalendar.events != null) {
                for (Date eventDate : MainCalendar.events) {
                    if (eventDate.getDate() == day &&
                            eventDate.getMonth() == month &&
                            eventDate.getYear() == year) {
                        // mark this day for event
                     //   view.setBackgroundResource(R.drawable.cast_album_art_placeholder);
                      //  tvDaySquare.setText(String.valueOf(date.getDate()) + " this is test");
                        //String text = "<font color=#00a7e1>" + String.valueOf(date.getDate() + "<br> <br> <br>") + "</font> <font color=#ffffff> this is testing </font>";
                       // tvDaySquare.setText(Html.fromHtml(text));
                        break;
                    }
                }

            }

         //   Log.d("msg", "date " + date.getDate());

            return view;
        }
    }
}
