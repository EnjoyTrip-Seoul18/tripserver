package com.ssafy.trip.service.board;

import com.ssafy.trip.dto.board.WriteBoardRequest;

public interface BoardService {
    void writeArticle(String memberId, WriteBoardRequest request) throws Exception;
}
