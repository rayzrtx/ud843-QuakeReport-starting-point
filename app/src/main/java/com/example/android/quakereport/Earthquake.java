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
    private long mTimeInMilliseconds;

    /**
     * Create a new Earthquake object
     * @param magnitude is the magnitude of the earthquake.
     *
     * @param city is the city where the earthquake hit.
     *
     * @param timeInMilliseconds is the time in milliseconds when the earthquake happened
     */
    public Earthquake(String magnitude, String city, long timeInMilliseconds){
        mMagnitude = magnitude;
        mCity = city;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public String getMagnitude(){
        return mMagnitude;
    }
    public String getCity(){
        return mCity;
    }
    public long getTimeInMilliseconds(){
        return mTimeInMilliseconds;
    }
}
