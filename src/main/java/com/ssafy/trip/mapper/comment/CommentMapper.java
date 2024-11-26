package com.ssafy.trip.mapper.comment;

import com.ssafy.trip.dto.comment.CommentDeleteDto;
import com.ssafy.trip.dto.comment.CommentInfoDto;
import com.ssafy.trip.dto.comment.CommentResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CommentMapper {
    CommentResponseDto getComment(Integer commentId) throws SQLException;
    List<CommentResponseDto> getCommentList(Integer boardId) throws SQLException;
    int addComment(CommentInfoDto commentInfoDto) throws SQLException;
    int updateComment(CommentInfoDto commentInfoDto) throws SQLException;
    int deleteComment(CommentDeleteDto commentDeleteDto) throws SQLException;
}
