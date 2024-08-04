package panicathe.weather.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import panicathe.weather.domain.Diary;
import panicathe.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @Operation(summary = "일기 생성", description = "일기 텍스트와 날씨를 이용해서 DB에 저장합니다.")
    @PostMapping("/create/diary")
    void createDiary(
            @RequestParam
            @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
            @Parameter(description = "일기 날짜", example = "2020-12-12")
            LocalDate date,
            @RequestBody String text
    ){
        diaryService.createDiary(date, text);
    }

    @Operation(summary = "일기 읽기", description = "선택한 날짜의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diary")
    List<Diary> readDiary(
            @RequestParam
            @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
            @Parameter(description = "조회할 날짜", example = "2020-12-12")
            LocalDate date)
    {
        return diaryService.readDiary(date);

    }

    @Operation(summary = "일기들 읽기", description = "선택한 기간의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(
            @RequestParam
            @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
            @Parameter(description = "조회할 시작 날짜", example = "2020-12-12")
            LocalDate startDate,
            @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
            @Parameter(description = "조회할 마지막 날짜", example = "2020-12-12")
            LocalDate endDate
            )
    {
        return diaryService.readDiaries(startDate, endDate);

    }

    @Operation(summary = "일기 수정", description = "선택한 날짜의 일기 데이터를 수정합니다.")
    @PutMapping("/update/diary")
    void updateDiary(
            @RequestParam
            @Parameter(description = "수정할 일기 날짜", example = "2020-12-12")
            @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestBody String text
    )
    {
        diaryService.updateDiary(date, text);

    }

    @Operation(summary = "일기 삭제", description = "선택한 날짜의 일기를 삭제합니다.")
    @DeleteMapping("/delete/diary")
    void deleteDiary(
            @RequestParam
            @Parameter(description = "삭제할 일기 날짜", example = "2020-12-12")
            @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date
    )
    {
        diaryService.deleteDiary(date);

    }


}
