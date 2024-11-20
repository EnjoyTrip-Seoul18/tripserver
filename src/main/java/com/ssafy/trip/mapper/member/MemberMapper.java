package com.ssafy.trip.mapper.member;

import com.ssafy.trip.dto.member.JoinMemberRequest;
import com.ssafy.trip.dto.member.LoginMemberRequest;
import com.ssafy.trip.dto.member.MemberDto;
import com.ssafy.trip.dto.member.UpdateMemberRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface MemberMapper {
    int join(JoinMemberRequest request) throws SQLException;
    MemberDto login(LoginMemberRequest request) throws SQLException;
    void saveRefreshToken(Map<String, String> map) throws SQLException;
    void deleteRefreshToken(Map<String, String> map) throws SQLException;
    int updateMember(UpdateMemberRequest request) throws SQLException;
}
