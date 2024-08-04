# 날씨 일기

이 프로젝트는 날씨와 관련된 정보를 기록하고 관리할 수 있는 백엔드 API입니다. 사용자들은 일기를 생성하고, 수정하고, 삭제하며, 특정 날짜의 일기나 기간에 따른 일기를 조회할 수 있습니다.

## 기능

- 일기 생성
- 일기 읽기 (특정 날짜, 특정 기간 동안)
- 일기 수정
- 일기 삭제
- 날씨 데이터 자동 저장 (매일 새벽 1시)

## 필요 설정

- **OpenWeatherMap API 키 설정**:
    - `application.properties` 파일에 OpenWeatherMap API 키를 설정합니다.
      ```
      openweathermap.key=YOUR_API_KEY
      ```
- **MySQL 데이터베이스 설정**:
    - `application.properties` 파일에 MySQL 데이터베이스 설정을 추가합니다. 아래는 설정 예시입니다:
      ```
      spring.application.name=weather
      spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
      spring.datasource.url=jdbc:mysql://localhost:3306/weather?serverTimezone=UTC&characterEncoding=UTF-8
      spring.datasource.username=root
      spring.datasource.password=root
      spring.jpa.show-sql=true
      spring.jpa.database=mysql
      ```

## 기술 스택

- **Spring Boot**: 프레임워크
- **Spring Data JPA**: ORM
- **Spring JDBC**: 데이터베이스 연결
- **MySQL**: 데이터베이스
- **Springdoc OpenAPI**: API 문서화 및 Swagger UI 제공
- **Lombok**: 자바 코드 단순화
- **Json-simple**: JSON 처리
- **JUnit**: 단위 테스트
- **Logback**: 로깅 프레임워크

## 스케줄링

- `service` 파일의 `saveWeatherDate()` 메서드는 매일 새벽 1시에 자동으로 호출되어 현재 날씨 데이터를 저장합니다. `@Scheduled` 어노테이션을 통해 설정된 cron 표현식을 조정하여 주기를 변경할 수 있습니다.

