package com.ssafy.trip.service.board;

import com.ssafy.trip.dto.board.BoardDto;
import com.ssafy.trip.dto.board.BoardListResponse;
import com.ssafy.trip.dto.board.UpdateBoardRequest;
import com.ssafy.trip.dto.board.WriteBoardRequest;
import com.ssafy.trip.dto.member.UpdateMemberRequest;
import com.ssafy.trip.mapper.board.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;

    @Override
    @Transactional(readOnly = true)
    public BoardListResponse listArticle(Map<String, String> map) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("word", map.getOrDefault("word", ""));
        int currentPage = Math.max(1, Integer.parseInt(map.getOrDefault("pgno", "1")));
        int sizePerPage = Math.max(1, Integer.parseInt(map.getOrDefault("spp", "20")));
        int start = (currentPage - 1) * sizePerPage;
        param.put("start", start);
        param.put("listsize", sizePerPage);
        String word = map.get("word");
        if (word != null && !word.isEmpty()) {
            param.put("key", "subject");
        }
        String userId = map.get("user_id");
        if (userId != null && !userId.isEmpty()) {
            param.put("key", "user_id");
        }
        log.info("Pagination keyword : {}", param);
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
    public void writeArticle(String memberId, WriteBoardRequest request) throws Exception {
        boardMapper.writeArticle(memberId, request);
    }

    @Override
    @Transactional(readOnly = true)
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