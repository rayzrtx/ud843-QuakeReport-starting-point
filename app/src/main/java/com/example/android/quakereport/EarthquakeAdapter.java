package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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

        // Find the TextView in the list_item.xml layout with the ID earthquake_date.
        TextView dateTextView = (TextView) view.findViewById(R.id.earthquake_date);

        // Get the earthquake date from the currentEarthquake object and set this text on
        // the earthquake_date TextView.
        dateTextView.setText(currentEarthquake.getDate());

        // Return the whole list item layout (containing 3 TextViews) so that it can be shown in
        // the ListView in earthquake_activity.
        return view;
    }
}
