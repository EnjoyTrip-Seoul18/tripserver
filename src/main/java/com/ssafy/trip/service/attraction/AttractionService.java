package com.ssafy.trip.service.attraction;

import com.ssafy.trip.dto.attraction.KakaoTspDto;

import java.util.List;

public interface AttractionService {
    public List<KakaoTspDto> tsp(List<KakaoTspDto> attractions) throws Exception;
}
