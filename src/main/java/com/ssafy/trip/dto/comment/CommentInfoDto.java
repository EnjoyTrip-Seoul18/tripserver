package com.ssafy.trip.dto.comment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentInfoDto {
    private String memberId;
    private CommentRequestDto comment;
}
