package com.ssafy.trip.dto.board;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(title = "Board", description = "게시글의 상세 정보를 나타낸다.")
public class BoardDto {

    @Schema(description = "글번호")
    private int boardId;
    @Schema(description = "작성자 아이디")
    private String memberId;
    @Schema(description = "작성자 이름")
    private String memberName;
    @Schema(description = "글제목")
    private String subject;
    @Schema(description = "글내용")
    private String content;
    @Schema(description = "조회수")
    private int hit;
    @Schema(description = "작성일")
    private String registerTime;

}
