/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    /**
     * URL to query the USGS dataset for earthquake information
     */
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=4&limit=10";

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle bundle) {
        // Create a new loader for the given URL
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakeList) {
        if (earthquakeList == null){
            return;
        }
        // Update the information displayed to the user.
        updateUI(earthquakeList);
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        loader.reset();
    }


    private void updateUI(final List<Earthquake> earthquakeList) {
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_state_view);

        earthquakeListView.setEmptyView(mEmptyStateTextView);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this, earthquakeList);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(earthquakeAdapter);

        //Set click listener on all ListView items
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Earthquake currentEarthquake = earthquakeList.get(position);
                //Get the URL string from the JSON data of the current Earthquake that was clicked
                String url = currentEarthquake.getURL();
                // Create a new intent to view the earthquake URL
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                // Convert the String URL into a URI object (to pass into the Intent constructor)
                browserIntent.setData(Uri.parse(url));
                // Send the intent to launch a new activity
                startActivity(browserIntent);
            }
        });
    }


}
