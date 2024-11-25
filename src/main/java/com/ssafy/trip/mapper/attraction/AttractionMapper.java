package com.ssafy.trip.mapper.attraction;

import com.ssafy.trip.dto.attraction.AttractionDto;
import com.ssafy.trip.dto.attraction.ContentTypeDto;
import com.ssafy.trip.dto.attraction.GugunDto;
import com.ssafy.trip.dto.attraction.SidoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AttractionMapper {
    List<SidoDto> getSidoList();
    List<GugunDto> getGugunList(int sidoCode);
    List<ContentTypeDto> getContentTypeList();
    List<AttractionDto> getAttractionList(Map<String, String> searchMap);
}
