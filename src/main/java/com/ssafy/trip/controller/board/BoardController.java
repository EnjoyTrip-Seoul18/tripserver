package com.ssafy.trip.controller.board;

import com.ssafy.trip.dto.board.BoardDto;
import com.ssafy.trip.dto.board.WriteBoardRequest;
import com.ssafy.trip.service.board.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Tag(name = "커뮤니티", description = "")
public class BoardController {
    private final BoardService boardService;

    @Operation(summary = "게시판 글작성", description = "새로운 게시글 정보를 입력한다.")
    @PostMapping("/")
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

//    @Operation(summary = "게시판 글목록", description = "모든 게시글의 정보를 반환한다.")
//    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "게시글목록 OK!!"),
//            @ApiResponse(responseCode = "404", description = "페이지없어!!"),
//            @ApiResponse(responseCode = "500", description = "서버에러!!") })
//    @GetMapping
//    public ResponseEntity<?> listArticle(
//            @RequestParam @Parameter(description = "게시글을 얻기위한 부가정보.", required = true) Map<String, String> map) {
//        log.info("listArticle map - {}", map);
//        try {
//            BoardListDto boardListDto = boardService.listArticle(map);
//            HttpHeaders header = new HttpHeaders();
//            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//            return ResponseEntity.ok().headers(header).body(boardListDto);
//        } catch (Exception e) {
//            return exceptionHandling(e);
//        }
//    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
