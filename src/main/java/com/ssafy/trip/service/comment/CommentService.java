package com.ssafy.trip.service.comment;

import com.ssafy.trip.dto.comment.CommentRequestDto;
import com.ssafy.trip.dto.comment.CommentResponseDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto getComment(Integer commentId) throws Exception;
    List<CommentResponseDto> getCommentList(Integer boardId) throws Exception;
    int addComment(String memberId, CommentRequestDto comment) throws Exception;
    int updateComment(String memberId, CommentRequestDto comment) throws Exception;
    int deleteComment(String memberId, Integer commentId) throws Exception;
}
