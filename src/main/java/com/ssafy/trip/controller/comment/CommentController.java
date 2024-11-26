package com.ssafy.trip.controller.comment;

import com.ssafy.trip.dto.comment.CommentRequestDto;
import com.ssafy.trip.dto.comment.CommentResponseDto;
import com.ssafy.trip.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{commentId}")
    public ResponseEntity<?> getComment(@RequestAttribute("userId") String memberId,
                                        @PathVariable Integer commentId) {
        try {
            CommentResponseDto responseDto = commentService.getComment(commentId);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/list/{boardId}")
    public ResponseEntity<?> getCommentList(@RequestAttribute("userId") String memberId,
                                            @PathVariable Integer boardId) {
        try {
            List<CommentResponseDto> responseDto = commentService.getCommentList(boardId);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> writeComment(@RequestAttribute("userId") String memberId,
                                          @RequestBody CommentRequestDto comment) {
        try {
            int response = commentService.addComment(memberId, comment);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@RequestAttribute("userId") String memberId,
                                           @PathVariable("commentId") Integer commentId,
                                           @RequestBody CommentRequestDto comment) {
        try {
            comment.setCommentId(commentId);
            int response = commentService.updateComment(memberId, comment);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@RequestAttribute("userId") String memberId,
                                           @PathVariable("commentId") Integer commentId) {
        try {
            int response = commentService.deleteComment(memberId, commentId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }
}
