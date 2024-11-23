package com.ssafy.trip.service.attraction;

import com.ssafy.trip.dto.attraction.kakao.KakaoTspRequestDto;
import com.ssafy.trip.dto.attraction.kakao.KakaoTspResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AttractionServiceImplTest {
    private AttractionService attractionService;

    @BeforeEach
    void setUp() {
        attractionService = new AttractionServiceImpl();
    }

    @Test
    public void testTspAlgorithm() throws Exception {
        // Given: 10개 도시 데이터
        List<KakaoTspRequestDto> attractions = Arrays.asList(
                new KakaoTspRequestDto(0,"Seoul", 37.5665, 126.9780),    // 서울
                new KakaoTspRequestDto(1,"Incheon", 37.4563, 126.7052),  // 인천
                new KakaoTspRequestDto(2,"Daejeon", 36.3504, 127.3845),  // 대전
                new KakaoTspRequestDto(3,"Daegu", 35.8722, 128.6014),    // 대구
                new KakaoTspRequestDto(4,"Busan", 35.1796, 129.0756),    // 부산
                new KakaoTspRequestDto(5,"Ulsan", 35.5384, 129.3114),    // 울산
                new KakaoTspRequestDto(6,"Gwangju", 35.1595, 126.8526),  // 광주
                new KakaoTspRequestDto(7,"Suwon", 37.2636, 127.0286),    // 수원
                new KakaoTspRequestDto(8,"Jeonju", 35.8251, 127.1453),   // 전주
                new KakaoTspRequestDto(9,"Jeju", 33.5007, 126.5312)      // 제주
        );

        // When: TSP 알고리즘 실행
        KakaoTspResponseDto result = attractionService.tsp(attractions);

        // Then: 예상 경로 확인
        List<String> expectedPath = Arrays.asList(
                "Seoul", "Incheon", "Suwon", "Daejeon", "Daegu", "Ulsan", "Busan", "Jeonju", "Gwangju", "Jeju"
        );

        // 결과 경로의 이름 리스트 추출
        List<String> actualPath = result.getAttractionList().stream()
                .map(KakaoTspRequestDto::getName)
                .toList();

        // 예상 경로와 실제 경로 비교
        assertEquals(expectedPath, actualPath, "TSP 최적 경로가 예상과 다릅니다.");
    }
}