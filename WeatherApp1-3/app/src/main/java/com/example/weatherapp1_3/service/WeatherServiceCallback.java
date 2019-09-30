package com.example.weatherapp1_3.service;

import com.example.weatherapp1_3.data.Channel;

public interface WeatherServiceCallback {

    void serviceSuccess(Channel channel);

    void serviceFailure(Exception exception);

}
