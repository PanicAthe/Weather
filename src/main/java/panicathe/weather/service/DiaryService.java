package panicathe.weather.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import panicathe.weather.domain.DateWeather;
import panicathe.weather.domain.Diary;
import panicathe.weather.repository.DateWeatherRepository;
import panicathe.weather.repository.DiaryRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true) // 기본적으로 읽기 전용 트랜잭션
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DateWeatherRepository dateWeatherRepository;
    private final WeatherService weatherService;

    private static final Logger logger = LoggerFactory.getLogger(DiaryService.class);

    public DiaryService(DiaryRepository diaryRepository, DateWeatherRepository dateWeatherRepository, WeatherService weatherService) {
        this.diaryRepository = diaryRepository;
        this.dateWeatherRepository = dateWeatherRepository;
        this.weatherService = weatherService;
    }

    @Transactional
    public void createDiary(LocalDate date, String text) {
        logger.info("Started to create diary");

        // 날씨 데이터 가져오기(DB에서)
        DateWeather dateWeather = getDateWeather(date);

        // DB에 넣기
        Diary nowDiary = new Diary();
        nowDiary.setText(text);
        nowDiary.setDateWeather(dateWeather);

        diaryRepository.save(nowDiary);
        logger.info("Finished to create diary");
    }

    private DateWeather getDateWeather(LocalDate date) {
        List<DateWeather> dateWeatherListFromDB = dateWeatherRepository.findAllByDate(date);
        if (dateWeatherListFromDB.isEmpty()) {
            // API에서 과거 날씨 정보 가져오기(유료 API 호출 필요. 현재 코드에서는 오늘 날씨를 가져옴)
            return weatherService.getWeatherFromAPI();
        } else {
            return dateWeatherListFromDB.get(0);
        }
    }

    @Transactional(readOnly = true)
    public List<Diary> readDiary(LocalDate date) {
        logger.debug("Read diary");
        return diaryRepository.findAllByDate(date);
    }

    @Transactional(readOnly = true)
    public List<Diary> readDiaries(LocalDate startDate, LocalDate endDate) {
        return diaryRepository.findAllByDateBetween(startDate, endDate);
    }

    @Transactional
    public void updateDiary(LocalDate date, String text) {
        Diary diary = diaryRepository.getFirstByDate(date);
        diary.setText(text);
        diaryRepository.save(diary);
    }

    @Transactional
    public void deleteDiary(LocalDate date) {
        diaryRepository.deleteAllByDate(date);
    }
}
