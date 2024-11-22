package com.ssafy.trip.service.board;

import com.ssafy.trip.dto.board.WriteBoardRequest;
import com.ssafy.trip.mapper.board.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;

    @Override
    public void writeArticle(String memberId, WriteBoardRequest request) throws Exception {
        boardMapper.writeArticle(memberId, request);
    }

}