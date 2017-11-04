package com.mahesaiqbal.darkskyclient.events;

import models.Weather;

/**
 * Created by mahesaiqbal on 11/3/2017.
 */

public class WeatherEvent {

    private final Weather weather;

    public WeatherEvent(Weather weather) {
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }

}
