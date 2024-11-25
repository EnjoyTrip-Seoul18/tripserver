package com.ssafy.trip.service.attraction;

import com.ssafy.trip.dto.attraction.*;
import com.ssafy.trip.dto.attraction.gpt.GptRequestDto;
import com.ssafy.trip.dto.attraction.gpt.GptResponseDto;
import com.ssafy.trip.dto.attraction.kakao.KakaoTspRequestDto;
import com.ssafy.trip.dto.attraction.kakao.KakaoTspResponseDto;
import com.ssafy.trip.mapper.attraction.AttractionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {
    private int size;
    private double[][] dp;
    private int[][] parent;

    private static final double EARTH_RADIUS = 6371;

    private final AttractionMapper attractionMapper;

    @Override
    public KakaoTspResponseDto tsp(List<KakaoTspRequestDto> attractions) {
        initTsp(attractions);
        List<KakaoTspRequestDto> attractionList = new ArrayList<>();
        List<Double> distanceList = new ArrayList<>();
        log.info("Total Cost of TSP {}", dfs(attractions, 0, 1));
        int current = 0;
        int visited = 1;
        while (true) {
            attractionList.add(attractions.get(current));
            int next = parent[current][visited];
            if(next < 0) break;
            distanceList.add(haversine(attractions.get(next), attractions.get(current)));
            current = next;
            visited |= (1 << current);
        }
        log.info("Distance : {}", distanceList);
        KakaoTspResponseDto responseDto
                = KakaoTspResponseDto
                .builder()
                .attractionList(attractionList)
                .distanceList(distanceList)
                .build();
        return responseDto;
    }

    @Override
    public List<SidoDto> getSidoList() throws Exception {
        return attractionMapper.getSidoList();
    }

    @Override
    public List<GugunDto> getGugunList(int sidoCode) throws Exception {
        return attractionMapper.getGugunList(sidoCode);
    }

    @Override
    public List<ContentTypeDto> getContentTypeList() throws Exception {
        return attractionMapper.getContentTypeList();
    }

    @Override
    public List<AttractionDto> getAttractionList(AttractionSearchDto searchDto) throws Exception {
        HashMap<String, String> searchMap = new HashMap<>();
        if(Integer.parseInt(searchDto.getSido()) > 0) searchMap.put("sido_code", searchDto.getSido());
        if(Integer.parseInt(searchDto.getGugun()) > 0) searchMap.put("gugun_code", searchDto.getGugun());
        if(Integer.parseInt(searchDto.getContentType()) > 0) searchMap.put("content_type_id", searchDto.getContentType());
        searchMap.put("keyword", Optional.ofNullable(searchDto.getKeyword()).orElse(""));
        return attractionMapper.getAttractionList(searchMap);
    }

    private void initTsp(List<KakaoTspRequestDto> attractions) {
        size = attractions.size();
        dp = new double[size][1 << size];
        parent = new int[size][1 << size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < (1 << size); j++) {
                dp[i][j] = Double.POSITIVE_INFINITY;
                parent[i][j] = -1;
            }
        }
    }

    private double dfs(List<KakaoTspRequestDto> attractions, int current, int visited) {
        if (visited == (1 << (size - 1)) - 1) {
            parent[current][visited] = size - 1;
            return haversine(attractions.get(current), attractions.get(size - 1));
        }
        if (dp[current][visited] != Double.POSITIVE_INFINITY) return dp[current][visited];
        for (int next = 0; next < size - 1; next++) {
            if ((visited & (1 << next)) == 0) {
                double cost = haversine(attractions.get(current), attractions.get(next));
                double nextCost = dfs(attractions, next, visited | (1 << next));
                if (dp[current][visited] > cost + nextCost) {
                    dp[current][visited] = cost + nextCost;
                    parent[current][visited] = next;
                }
            }
        }
        return dp[current][visited];
    }

    private double haversine(KakaoTspRequestDto A, KakaoTspRequestDto B) {
        double dLat = Math.toRadians(A.getLat() - B.getLat());
        double dLon = Math.toRadians(A.getLng() - B.getLng());
        double rLat1 = Math.toRadians(A.getLat());
        double rLat2 = Math.toRadians(B.getLat());

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(rLat1) * Math.cos(rLat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }
}
