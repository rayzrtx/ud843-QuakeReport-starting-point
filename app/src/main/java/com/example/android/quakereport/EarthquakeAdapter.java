package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by raylinares on 1/3/18.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Check if an existing view is being reused, otherwise inflate the view
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);


        // Find the TextView in the list_item.xml layout with the ID earthquake_magnitude.
        TextView magnitudeTextView = (TextView) view.findViewById(R.id.earthquake_magnitude);
        // Get the earthquake magnitude from the currentEarthquake object and set this text on
        // the earthquake_magnitude TextView.
        magnitudeTextView.setText(currentEarthquake.getMagnitude());


        // Find the TextView in the list_item.xml layout with the ID city_name.
        TextView cityNameTextView = (TextView) view.findViewById(R.id.city_name);
        // Get the city name from the currentEarthquake object and set this text on
        // the city_name TextView.
        cityNameTextView.setText(currentEarthquake.getCity());


        // Create a new Date object from the time in milliseconds (from API) of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());


        // Find the TextView in the list_item.xml layout with the ID earthquake_date.
        TextView dateTextView = (TextView) view.findViewById(R.id.earthquake_date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateTextView.setText(formattedDate);


        TextView timeTextView = (TextView) view.findViewById(R.id.earthquake_time);
        String formattedTime = formatTime(dateObject);
        timeTextView.setText(formattedTime);

        // Return the whole list item layout (containing 3 TextViews) so that it can be shown in
        // the ListView in earthquake_activity.
        return view;
    }
    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
