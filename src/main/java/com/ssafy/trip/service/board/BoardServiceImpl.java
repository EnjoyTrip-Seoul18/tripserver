package com.ssafy.trip.service.board;

import com.ssafy.trip.dto.board.BoardDto;
import com.ssafy.trip.dto.board.BoardListResponse;
import com.ssafy.trip.dto.board.UpdateBoardRequest;
import com.ssafy.trip.dto.board.WriteBoardRequest;
import com.ssafy.trip.dto.member.UpdateMemberRequest;
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
        param.put("word", map.getOrDefault("word", ""));
        int currentPage = Math.max(1, Integer.parseInt(map.getOrDefault("pgno", "1")));
        int sizePerPage = Math.max(1, Integer.parseInt(map.getOrDefault("spp", "20")));
        int start = (currentPage - 1) * sizePerPage;
        param.put("start", start);
        param.put("listsize", sizePerPage);
        String key = map.get("key");
        if (key != null && !key.isEmpty()) {
            if ("user_id".equals(key)) {
                param.put("key", "b.user_id");
            } else {
                param.put("key", key);
            }
        } else {
            param.put("key", null); // key가 없을 경우 null로 처리
        }
        List<BoardDto> list = boardMapper.listArticle(param);

        int totalArticleCount = boardMapper.getTotalArticleCount(param);
        int totalPageCount = (totalArticleCount + sizePerPage - 1) / sizePerPage;

        BoardListResponse boardList = new BoardListResponse();
        boardList.setArticles(list);
        boardList.setCurrentPage(currentPage);
        boardList.setTotalPageCount(totalPageCount);

        return boardList;
    }

    @Override
    public BoardDto getArticle(int boardId) throws Exception {
        return boardMapper.getArticle(boardId);
    }

    @Override
    public void updateHit(int boardId) throws Exception {
        boardMapper.updateHit(boardId);
    }

    @Override
    public void modifyArticle(String memberId, int boardId, UpdateBoardRequest request) throws Exception {
        boardMapper.modifyArticle(memberId, boardId, request);
    }

    @Override
    public void deleteArticle(String memberId, int boardId) throws Exception {
        boardMapper.deleteArticle(memberId, boardId);
    }
}