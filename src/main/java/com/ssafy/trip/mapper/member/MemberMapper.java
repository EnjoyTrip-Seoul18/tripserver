package com.ssafy.trip.mapper.member;

import com.ssafy.trip.dto.member.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface MemberMapper {
    int join(JoinMemberRequest request) throws SQLException;
    MemberDto login(LoginMemberRequest request) throws SQLException;
    void saveRefreshToken(Map<String, String> map) throws SQLException;
    void deleteRefreshToken(Map<String, String> map) throws SQLException;
    int updateMember(@Param("memberId") String memberId, @Param("request") UpdateMemberRequest request) throws SQLException;
    int deleteMember(String memberId) throws SQLException;
    InfoMemberResponse memberInfo(String memberId) throws SQLException;
    int idCheck(String memberId) throws SQLException;
}
