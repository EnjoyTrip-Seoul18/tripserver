package com.ssafy.trip.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(title = "MemberDto : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class MemberDto {

    @Schema(description = "아이디")
    private String memberId;
    @Schema(description = "이름")
    private String memberName;
    @Schema(description = "비밀번호")
    private String memberPwd;
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "주소")
    private String address;
    @Schema(description = "가입일")
    private String joinDate;
    @Schema(description = "refreshToken")
    private String refreshToken;
}
