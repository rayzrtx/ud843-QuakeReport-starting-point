package com.example.android.quakereport;

/**
 * Created by raylinares on 1/3/18.
 */

/**
 * {@link Earthquake} represents an Earthquake object which consists of the magnitude of the earthquake,
 * the city where the earthquake hit, and the date of the earthquake.
 */
public class Earthquake {

    /** Magnitude of the earthquake */
    private String mMagnitude;

    /** City where earthquake hit */
    private String mCity;

    /** Date the earthquake hit */
    private String mDate;

    /**
     * Create a new Earthquake object
     * @param magnitude is the magnitude of the earthquake.
     *
     * @param city is the city where the earthquake hit.
     *
     * @param date is the date the earthquake hit
     */
    public Earthquake(String magnitude, String city, String date){
        mMagnitude = magnitude;
        mCity = city;
        mDate = date;
    }

    public String getMagnitude(){
        return mMagnitude;
    }
    public String getCity(){
        return mCity;
    }
    public String getDate(){
        return mDate;
    }
}
