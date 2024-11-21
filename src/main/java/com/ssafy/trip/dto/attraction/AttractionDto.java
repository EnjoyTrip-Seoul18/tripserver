package com.ssafy.trip.dto.attraction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.text.DecimalFormat;

@Data
@Schema(title = "AttractionDto : 관광지정보", description = "관광지 상세 정보를 나타낸다.")
public class AttractionDto {
    private Integer attractionId;
    private String name;
    private String description;
    private String address;
    private DecimalFormat latitude;
    private DecimalFormat longitude;
    private Integer contentTypeId;
    private Integer gugunCode;
}
