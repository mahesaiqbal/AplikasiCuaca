package com.mahesaiqbal.darkskyclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mahesaiqbal.darkskyclient.events.ErrorEvent;
import com.mahesaiqbal.darkskyclient.events.WeatherEvent;
import com.mahesaiqbal.darkskyclient.services.WeatherService;
import com.mahesaiqbal.darkskyclient.services.WeatherServiceProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.Currently;
import models.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.timezone)
    TextView timezoneTextView;

    @BindView(R.id.temp_text_view)
    TextView tempTextView;

    @BindView(R.id.icon_image_view)
    ImageView iconImageView;

    @BindView(R.id.summary_textview)
    TextView summaryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestCurrentWeather(-6.21462, 106.84513);

        ButterKnife.bind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherEvent(WeatherEvent weatherEvent) {
        Weather weather = weatherEvent.getWeather();
        Currently currently = weatherEvent.getWeather().getCurrently();

        timezoneTextView.setText(weather.getTimezone());
        tempTextView.setText(String.valueOf(Math.round(currently.getTemperature())));
        summaryTextView.setText(currently.getSummary());

        Map<String, Integer> iconMap = new HashMap<>();
        iconMap.put("clear-day", R.drawable.ic_clear_day);
        iconMap.put("clear-night", R.drawable.ic_clear_night);
        iconMap.put("rain", R.drawable.ic_rain);
        iconMap.put("snow", R.drawable.ic_snow);
        iconMap.put("sleet", R.drawable.ic_sleet);
        iconMap.put("wind", R.drawable.ic_wind);
        iconMap.put("fog", R.drawable.ic_fog);
        iconMap.put("cloudy", R.drawable.ic_cloudy);
        iconMap.put("partly-cloudy-day", R.drawable.ic_partly_cloudy_day);
        iconMap.put("partly-cloudy-night", R.drawable.ic_partly_cloudy_night);
        iconMap.put("thunderstorm", R.drawable.ic_thunderstorm);

        iconImageView.setImageResource(iconMap.get(currently.getIcon()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent errorEvent) {
        Toast.makeText(this, errorEvent.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    private void requestCurrentWeather(double lat, double lng) {
        WeatherServiceProvider weatherServiceProvider = new WeatherServiceProvider();
        weatherServiceProvider.getWeather(lat, lng);
    }
}
