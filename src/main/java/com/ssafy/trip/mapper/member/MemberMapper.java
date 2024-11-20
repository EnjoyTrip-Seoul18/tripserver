package com.ssafy.trip.mapper.member;

import com.ssafy.trip.dto.member.JoinMemberRequest;
import com.ssafy.trip.dto.member.LoginMemberRequest;
import com.ssafy.trip.dto.member.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface MemberMapper {
    int join(JoinMemberRequest request) throws SQLException;
    MemberDto login(LoginMemberRequest request) throws SQLException;
    void saveRefreshToken(Map<String, String> map) throws SQLException;
}
