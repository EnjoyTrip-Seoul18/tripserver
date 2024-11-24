package com.ssafy.trip.mapper.board;

import com.ssafy.trip.dto.board.WriteBoardRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardMapper {
    void writeArticle(@Param("memberId") String memberId, @Param("request") WriteBoardRequest request) throws Exception;
}
