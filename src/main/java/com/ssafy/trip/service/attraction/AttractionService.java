package com.ssafy.trip.service.attraction;

import com.ssafy.trip.dto.attraction.*;
import com.ssafy.trip.dto.attraction.kakao.KakaoTspRequestDto;
import com.ssafy.trip.dto.attraction.kakao.KakaoTspResponseDto;

import java.util.List;

public interface AttractionService {
    KakaoTspResponseDto tsp(List<KakaoTspRequestDto> attractions) throws Exception;
    List<SidoDto> getSidoList() throws Exception;
    List<GugunDto> getGugunList(int sidoCode) throws Exception;
    List<ContentTypeDto> getContentTypeList() throws Exception;
    List<AttractionDto> getAttractionList(AttractionSearchDto searchDto) throws Exception;
}
