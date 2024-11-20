package com.ssafy.trip.service.member;

import com.ssafy.trip.dto.member.JoinMemberRequest;
import com.ssafy.trip.dto.member.LoginMemberRequest;
import com.ssafy.trip.dto.member.MemberDto;

public interface MemberService {
    int join(JoinMemberRequest request) throws Exception;
    MemberDto login(LoginMemberRequest request) throws Exception;
    void saveRefreshToken(String memberId, String refreshToken) throws Exception;
}
