package com.ssafy.trip.service.attraction;

import com.ssafy.trip.dto.attraction.gpt.GptRequestDto;
import com.ssafy.trip.dto.attraction.gpt.GptResponseDto;
import com.ssafy.trip.dto.attraction.kakao.KakaoTspRequestDto;
import com.ssafy.trip.dto.attraction.kakao.KakaoTspResponseDto;

import java.util.List;

public interface AttractionService {
    KakaoTspResponseDto tsp(List<KakaoTspRequestDto> attractions) throws Exception;
    GptResponseDto gptResponse(List<GptRequestDto> gptRequest) throws Exception;
}
