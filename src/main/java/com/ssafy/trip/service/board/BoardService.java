package com.ssafy.trip.service.board;

import com.ssafy.trip.dto.board.BoardDto;
import com.ssafy.trip.dto.board.BoardListResponse;
import com.ssafy.trip.dto.board.WriteBoardRequest;

import java.util.Map;

public interface BoardService {
    void writeArticle(String memberId, WriteBoardRequest request) throws Exception;
    BoardListResponse listArticle(Map<String, String> map) throws Exception;
    BoardDto getArticle(String memberId, int articleNo) throws Exception;
    void updateHit(int articleNo) throws Exception;
}
