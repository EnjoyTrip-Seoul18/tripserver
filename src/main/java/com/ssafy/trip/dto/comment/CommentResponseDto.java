package com.ssafy.trip.dto.comment;

import lombok.Data;

@Data
public class CommentResponseDto {
    private Integer commentId;
    private Integer refId;
    private String content;
    private String memberId;
    private String memberName;
    private String boardId;
    private String status;
}
