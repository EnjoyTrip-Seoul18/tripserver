package com.ssafy.trip.dto.board;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(title = "Board", description = "게시글을 작성할 때 사용하는 DTO")
public class WriteBoardRequest {
    @Schema(description = "글번호")
    private int boardId;
    @Schema(description = "글제목")
    private String subject;
    @Schema(description = "글내용")
    private String content;
}

