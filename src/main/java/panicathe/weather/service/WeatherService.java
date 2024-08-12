package panicathe.weather.service;

import panicathe.weather.domain.DateWeather;

public interface WeatherService {
    DateWeather getWeatherFromAPI();
}
