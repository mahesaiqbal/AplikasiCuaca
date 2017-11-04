package com.mahesaiqbal.darkskyclient.services;

import models.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mahesaiqbal on 11/2/2017.
 */

public interface WeatherService {

    @GET("{lat},{lng}")
    Call<Weather> getWeather(@Path("lat") double lat, @Path("lng") double lng);

}
