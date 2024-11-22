package com.ssafy.trip.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateMemberRequest {

//    @Schema(description = "아이디")
//    private String memberId;
    @Schema(description = "이름")
    private String memberName;
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "주소")
    private String address;
}
