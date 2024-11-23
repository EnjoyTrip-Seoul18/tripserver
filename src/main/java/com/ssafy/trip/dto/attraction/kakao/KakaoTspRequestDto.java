package com.ssafy.trip.dto.attraction.kakao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoTspRequestDto {
    private Integer index;
    private String name;
    private Double lat;
    private Double lng;
}
