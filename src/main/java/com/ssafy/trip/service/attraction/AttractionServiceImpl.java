package com.ssafy.trip.service.attraction;

import com.ssafy.trip.dto.attraction.AttractionDto;
import com.ssafy.trip.dto.attraction.KakaoTspDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AttractionServiceImpl implements AttractionService {
    private int size;
    private double[][] dp;
    private int[][] parent;

    private static final double EARTH_RADIUS = 6371;

    @Override
    public List<KakaoTspDto> tsp(List<KakaoTspDto> attractions) {
        initTsp(attractions);
        List<KakaoTspDto> path = new ArrayList<>();
        log.info("Total Cost of TSP {}", dfs(attractions, 0, 1));
        int current = 0;
        int visited = 1;
        int cnt = 0;
        while (cnt++ < size - 1) {
            path.add(attractions.get(current));
            current = parent[current][visited];
            visited |= (1 << current);
        }
        path.add(attractions.get(size - 1));
        return path;
    }

    private void initTsp(List<KakaoTspDto> attractions) {
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

    private double dfs(List<KakaoTspDto> attractions, int current, int visited) {
        if (visited == (1 << (size - 1)) - 1) return haversine(attractions.get(current), attractions.get(size - 1));
        if (dp[current][visited] != Double.POSITIVE_INFINITY) return dp[current][visited];
        for (int next = 0; next < size - 1; next++) {
            if ((visited & (1 << next)) == 0) {
                double cost = haversine(attractions.get(current), attractions.get(next));
                double nextCost = dfs(attractions, next, visited | (1 << next));;
                if (dp[current][visited] > cost + nextCost) {
                    dp[current][visited] = cost + nextCost;
                    parent[current][visited] = next;
                }
            }
        }
        return dp[current][visited];
    }

    private double haversine(KakaoTspDto A, KakaoTspDto B) {
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
