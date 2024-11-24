package com.ssafy.trip.service.member;

import com.ssafy.trip.dto.member.*;

public interface MemberService {
    int join(JoinMemberRequest request) throws Exception;
    MemberDto login(LoginMemberRequest request) throws Exception;
    void saveRefreshToken(String memberId, String refreshToken) throws Exception;
    void deleteRefreshToken(String memberId) throws Exception;
    int updateMember(String memberId, UpdateMemberRequest request) throws Exception;
    int deleteMember(String memberId) throws Exception;
    InfoMemberResponse memberInfo(String memberId) throws Exception;
    int idCheck(String memberId) throws Exception;
}
