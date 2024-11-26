package com.ssafy.trip.controller.attraction;

import com.ssafy.trip.dto.attraction.AttractionSearchDto;
import com.ssafy.trip.dto.attraction.SidoDto;
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

    @GetMapping("/sido")
    public ResponseEntity<?> getSidos() {
        try {
            return new ResponseEntity<>(attractionService.getSidoList(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/gugun/{sidoCode}")
    public ResponseEntity<?> getGuguns(@PathVariable("sidoCode") int sidoCode) {
        try {
            return new ResponseEntity<>(attractionService.getGugunList(sidoCode), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/contentType")
    public ResponseEntity<?> getContentTypes() {
        try {
            return new ResponseEntity<>(attractionService.getContentTypeList(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/list")
    public ResponseEntity<?> getList(@RequestBody AttractionSearchDto searchDto) {
        try {
            return new ResponseEntity<>(attractionService.getAttractionList(searchDto), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
