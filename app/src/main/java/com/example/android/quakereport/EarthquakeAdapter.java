package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
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

        // Format the magnitude to show 1 decimal place
        DecimalFormat magnitudeFormatter = new DecimalFormat("0.0");
        String magnitude = magnitudeFormatter.format(currentEarthquake.getMagnitude());
        // Get the earthquake magnitude from the currentEarthquake object and set this text on
        // the earthquake_magnitude TextView.
        magnitudeTextView.setText(magnitude);


        //Get the current city from the current Earthquake object
        String place = currentEarthquake.getCity();
        //If string contains the word "km" then it contains location offset info.
        //String will then be split into two parts: locationOffset portion and primaryLocation portion
        if (place.contains("km")) {
            //find index of the word "of" which is where string will be split
            int ofIndex = place.indexOf("of");
            //locationOffset portion will contain words starting from first letter to two spaces
            // after index of word "of"
            String locationOffset = place.substring(0, ofIndex + 2);
            //primaryLocation portion will contain words starting 3 spaces after the word "of" which
            // is where city name (primary location) is located
            String primaryLocation = place.substring(ofIndex + 3);

            //Find the TextView in the list_item layout with the ID location_offset
            TextView locationOffsetView = (TextView) view.findViewById(R.id.location_offset);
            //Get the location offset portion of complete city string and set to the view
            locationOffsetView.setText(locationOffset);

            // Find the TextView in the list_item.xml layout with the ID city_name.
            TextView primaryLocationTextView = (TextView) view.findViewById(R.id.city_name);
            // Get the city name from the currentEarthquake object and set this text on
            // the city_name TextView.
            primaryLocationTextView.setText(primaryLocation);
        }else {
            //place string doesn't contain location offset info so set locationOffset portion as
            // permanent string "Near the" to satisfy UI requirements
            String locationOffset = getContext().getString(R.string.near_the);

            //Find the TextView in the list_item layout with the ID location_offset
            TextView locationOffsetView = (TextView) view.findViewById(R.id.location_offset);
            //Get the location offset portion of complete city string and set to the view
            locationOffsetView.setText(locationOffset);

            // Find the TextView in the list_item.xml layout with the ID city_name.
            TextView primaryLocationTextView = (TextView) view.findViewById(R.id.city_name);
            // Since place string doesn't contain location offset, can use the entire place string
            // from the currentEarthquake object and set this text on the city_name TextView.
            primaryLocationTextView.setText(place);

        }


        // Create a new Date object from the time in milliseconds (from API) of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());


        // Find the TextView in the list_item.xml layout with the ID earthquake_date.
        TextView dateTextView = (TextView) view.findViewById(R.id.earthquake_date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateTextView.setText(formattedDate);


        // Find the TextView in the list_item.xml layout with the ID earthquake_time.
        TextView timeTextView = (TextView) view.findViewById(R.id.earthquake_time);
        // Format the date string to the correct time (i.e. "3:00 PM")
        String formattedTime = formatTime(dateObject);
        //Display the time of the current earthquake in that TextView
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
