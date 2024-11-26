package com.ssafy.trip.dto.comment;

import lombok.Data;

@Data
public class CommentRequestDto {
    private Integer commentId;
    private String content;
    private Integer boardId;
}
