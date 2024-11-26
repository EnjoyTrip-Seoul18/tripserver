package com.ssafy.trip.dto.comment;

import com.ssafy.trip.dto.board.BoardDto;
import com.ssafy.trip.dto.member.MemberDto;
import lombok.Data;

@Data
public class CommentDto {
    private Integer commentId;
    private Integer refId;
    private String content;
    private MemberDto member;
    private BoardDto board;
    private String status;
}
