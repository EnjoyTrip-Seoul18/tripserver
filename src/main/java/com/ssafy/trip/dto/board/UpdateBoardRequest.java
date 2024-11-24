package com.ssafy.trip.dto.board;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(title = "UpdateBoardRequest", description = "게시글을 수정(제목, 내용)할 때 사용하는 DTO")
public class UpdateBoardRequest {
    @Schema(description = "글제목")
    private String subject;
    @Schema(description = "글내용")
    private String content;
}
