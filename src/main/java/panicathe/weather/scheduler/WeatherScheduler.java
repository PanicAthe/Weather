package panicathe.weather.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import panicathe.weather.domain.DateWeather;
import panicathe.weather.repository.DateWeatherRepository;
import panicathe.weather.service.WeatherService;

@Component
public class WeatherScheduler {

    private final WeatherService weatherService;
    private final DateWeatherRepository dateWeatherRepository;

    public WeatherScheduler(WeatherService weatherService, DateWeatherRepository dateWeatherRepository) {
        this.weatherService = weatherService;
        this.dateWeatherRepository = dateWeatherRepository;
    }

    //@Scheduled(cron = "0/5 * * * * *") // 5초마다
    @Scheduled(cron = "0 0 1 * * *") // 매일 새벽 1시마다
    public void saveWeatherData() {
        DateWeather dateWeather = weatherService.getWeatherFromAPI();
        dateWeatherRepository.save(dateWeather);
    }
}
