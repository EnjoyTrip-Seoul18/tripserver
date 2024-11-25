package com.ssafy.trip.dto.attraction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "AttractionDto : 관광지정보", description = "관광지 상세 정보를 나타낸다.")
public class AttractionDto {
    private Integer attractionId;
    private String name;
    private String image;
    private String description;
    private String address;
    private Double latitude;
    private Double longitude;
    private Integer contentTypeId;
    private Integer gugunCode;
}
