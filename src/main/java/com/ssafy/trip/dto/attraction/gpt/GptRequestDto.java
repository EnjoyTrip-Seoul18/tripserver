package com.ssafy.trip.dto.attraction.gpt;

import lombok.Data;

@Data
public class GptRequestDto {
    private AttractionGptInfoDto before;
    private AttractionGptInfoDto after;
    private Double distance;
}
