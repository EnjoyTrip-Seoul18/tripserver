package com.ssafy.trip.dto.comment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDeleteDto {
    private String memberId;
    private Integer commentId;
}
