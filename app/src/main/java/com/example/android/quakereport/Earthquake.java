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
    private Double mMagnitude;

    /** City where earthquake hit */
    private String mCity;

    /** Date the earthquake hit */
    private long mTimeInMilliseconds;

    /** URL to receive more info on the earthquake */
    private String mURL;

    /**
     * Create a new Earthquake object
     * @param magnitude is the magnitude of the earthquake.
     *
     * @param city is the city where the earthquake hit.
     *
     * @param timeInMilliseconds is the time in milliseconds when the earthquake happened
     *
     * @param url is the USGS website URL to recieve more info on earthquake.
     */
    public Earthquake(Double magnitude, String city, long timeInMilliseconds, String url ){
        mMagnitude = magnitude;
        mCity = city;
        mTimeInMilliseconds = timeInMilliseconds;
        mURL = url;
    }

    public Double getMagnitude(){
        return mMagnitude;
    }
    public String getCity(){
        return mCity;
    }
    public long getTimeInMilliseconds(){
        return mTimeInMilliseconds;
    }
    public String getURL(){
        return mURL;
    }
}
