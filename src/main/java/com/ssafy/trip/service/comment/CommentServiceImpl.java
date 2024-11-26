package com.ssafy.trip.service.comment;

import com.ssafy.trip.dto.comment.CommentDeleteDto;
import com.ssafy.trip.dto.comment.CommentInfoDto;
import com.ssafy.trip.dto.comment.CommentRequestDto;
import com.ssafy.trip.dto.comment.CommentResponseDto;
import com.ssafy.trip.mapper.comment.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    @Transactional(readOnly = true)
    public CommentResponseDto getComment(Integer commentId) throws Exception {
        return commentMapper.getComment(commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getCommentList(Integer boardId) throws Exception {
        return commentMapper.getCommentList(boardId);
    }

    @Override
    public int addComment(String memberId, CommentRequestDto comment) throws Exception {
        CommentInfoDto commentInfoDto = CommentInfoDto.builder().memberId(memberId).comment(comment).build();
        return commentMapper.addComment(commentInfoDto);
    }

    @Override
    public int updateComment(String memberId, CommentRequestDto comment) throws Exception {
        CommentInfoDto commentInfoDto = CommentInfoDto.builder().memberId(memberId).comment(comment).build();
        return commentMapper.updateComment(commentInfoDto);
    }

    @Override
    public int deleteComment(String memberId, Integer commentId) throws Exception {
        CommentDeleteDto commentDeleteDto = CommentDeleteDto.builder().memberId(memberId).commentId(commentId).build();
        return commentMapper.deleteComment(commentDeleteDto);
    }
}
