package com.ssafy.trip.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginMemberRequest {

    @Schema(description = "아이디")
    private String memberId;
    @Schema(description = "비밀번호")
    private String memberPwd;
}
