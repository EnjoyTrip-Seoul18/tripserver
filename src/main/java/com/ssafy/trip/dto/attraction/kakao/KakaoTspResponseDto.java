package com.ssafy.trip.dto.attraction.kakao;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class KakaoTspResponseDto {
    private List<KakaoTspRequestDto> attractionList;
    private List<Double> distanceList;
}
