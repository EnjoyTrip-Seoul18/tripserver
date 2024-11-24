package com.ssafy.trip.controller.board;

import com.ssafy.trip.dto.board.BoardDto;
import com.ssafy.trip.dto.board.BoardListResponse;
import com.ssafy.trip.dto.board.UpdateBoardRequest;
import com.ssafy.trip.dto.board.WriteBoardRequest;
import com.ssafy.trip.dto.member.UpdateMemberRequest;
import com.ssafy.trip.service.board.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Tag(name = "커뮤니티", description = "")
public class BoardController {
    private final BoardService boardService;

    @Operation(summary = "게시판 글작성", description = "새로운 게시글 정보를 입력한다.")
    @PostMapping()
    public ResponseEntity<?> writeArticle(
            @RequestAttribute("userId") String memberId,
            @RequestBody @Parameter(description = "", required = true) WriteBoardRequest request) {
        log.info("writeBoardRequest - {}", request);
        try {
            boardService.writeArticle(memberId, request);
//			return ResponseEntity.ok().build();
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "게시판 글목록", description = "모든 게시글의 정보를 반환한다.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "게시글목록 OK!!"),
            @ApiResponse(responseCode = "404", description = "페이지없어!!"),
            @ApiResponse(responseCode = "500", description = "서버에러!!") })
    @GetMapping("/list")
    public ResponseEntity<?> listArticle(
            @RequestParam @Parameter(description = "게시글을 얻기위한 부가정보.", required = true) Map<String, String> map) {
        log.info("listArticle map - {}", map);
        try {
            BoardListResponse boardList = boardService.listArticle(map);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(boardList);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "게시판 글보기", description = "글번호에 해당하는 게시글의 정보를 반환한다.")
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDto> getArticle(
            @RequestAttribute("userId") String memberId,
            @PathVariable("boardId") @Parameter(name = "boardId", description = "얻어올 글의 글번호.", required = true) int boardId)
            throws Exception {
        log.info("getArticle - 호출 : " + boardId);
        boardService.updateHit(boardId);
        return new ResponseEntity<BoardDto>(boardService.getArticle(boardId), HttpStatus.OK);
    }

    @Operation(summary = "게시판 글수정", description = "수정할 게시글 정보를 입력한다.(제목, 내용)")
    @PutMapping("/{boardId}")
    public ResponseEntity<String> modifyArticle(
            @RequestAttribute("userId") String memberId,
            @PathVariable("boardId") @Parameter(name = "boardId", description = "살제할 글의 글번호.", required = true) int boardId,
            @RequestBody @Parameter(description = "수정할 글정보.", required = true) UpdateBoardRequest request) throws Exception {
        log.info("modifyArticle - 호출 {}", request);

        boardService.modifyArticle(memberId, boardId, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "게시판 글삭제", description = "글번호에 해당하는 게시글의 정보를 삭제한다.")
    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteArticle(
            @RequestAttribute("userId") String memberId,
            @PathVariable("boardId") @Parameter(name = "boardId", description = "살제할 글의 글번호.", required = true) int boardId) throws Exception {
        log.info("deleteArticle - 호출");
        boardService.deleteArticle(memberId, boardId);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
