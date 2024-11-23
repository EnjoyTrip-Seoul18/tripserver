package com.ssafy.trip.controller.attraction;

import com.ssafy.trip.dto.attraction.gpt.GptRequestDto;
import com.ssafy.trip.dto.attraction.gpt.GptResponseDto;
import com.ssafy.trip.dto.attraction.kakao.KakaoTspRequestDto;
import com.ssafy.trip.dto.attraction.kakao.KakaoTspResponseDto;
import com.ssafy.trip.service.attraction.AttractionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/attraction")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @PostMapping("/path")
    public ResponseEntity<?> getPath(@RequestBody List<KakaoTspRequestDto> attractions) {
        try {
            KakaoTspResponseDto responseDto = attractionService.tsp(attractions);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/review")
    public ResponseEntity<?> getReview(@RequestBody List<GptRequestDto> gptRequest) {
        try {
            GptResponseDto responseDto = attractionService.gptResponse(gptRequest);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }
}
