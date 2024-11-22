package com.ssafy.trip.controller.attraction;

import com.ssafy.trip.dto.attraction.KakaoTspDto;
import com.ssafy.trip.service.attraction.AttractionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/attraction")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @PostMapping("/path")
    public ResponseEntity<?> getPath(@RequestBody List<KakaoTspDto> attractions) {
        try {
            List<KakaoTspDto> path = attractionService.tsp(attractions);
            return new ResponseEntity<>(path, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }
}
