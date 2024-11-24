package com.ssafy.trip.mapper.board;

import com.ssafy.trip.dto.board.BoardDto;
import com.ssafy.trip.dto.board.UpdateBoardRequest;
import com.ssafy.trip.dto.board.WriteBoardRequest;
import com.ssafy.trip.dto.member.UpdateMemberRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    void writeArticle(@Param("memberId") String memberId, @Param("request") WriteBoardRequest request) throws SQLException;
    List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;
    int getTotalArticleCount(Map<String, Object> param) throws SQLException;
    BoardDto getArticle(int boardId) throws SQLException;
    void updateHit(int boardId) throws SQLException;
    void modifyArticle(@Param("memberId")String memberId, @Param("boardId") int boardId, @Param("request") UpdateBoardRequest request) throws SQLException;
    void deleteArticle(String memberId, int boardId) throws SQLException;
}
