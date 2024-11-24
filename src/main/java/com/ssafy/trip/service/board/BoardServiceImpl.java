package com.ssafy.trip.service.board;

import com.ssafy.trip.dto.board.BoardDto;
import com.ssafy.trip.dto.board.BoardListResponse;
import com.ssafy.trip.dto.board.WriteBoardRequest;
import com.ssafy.trip.mapper.board.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;

    @Override
    public void writeArticle(String memberId, WriteBoardRequest request) throws Exception {
        boardMapper.writeArticle(memberId, request);
    }

    @Override
    public BoardListResponse listArticle(Map<String, String> map) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("word", map.get("word") == null ? "" : map.get("word"));
        int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
        int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
        int start = currentPage * sizePerPage - sizePerPage;
        param.put("start", start);
        param.put("listsize", sizePerPage);

        String key = map.get("key");
        param.put("key", key == null ? "" : key);
        if ("user_id".equals(key))
            param.put("key", key == null ? "" : "b.user_id");
        List<BoardDto> list = boardMapper.listArticle(param);

        if ("user_id".equals(key))
            param.put("key", key == null ? "" : "user_id");
        int totalArticleCount = boardMapper.getTotalArticleCount(param);
        int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

        BoardListResponse boardList = new BoardListResponse();
        boardList.setArticles(list);
        boardList.setCurrentPage(currentPage);
        boardList.setTotalPageCount(totalPageCount);

        return boardList;
    }

    @Override
    public BoardDto getArticle(String memberId, int articleNo) throws Exception {
        return boardMapper.getArticle(memberId, articleNo);
    }

    @Override
    public void updateHit(int articleNo) throws Exception {
        boardMapper.updateHit(articleNo);
    }

}