package com.ssafy.trip.service.member;

import com.ssafy.trip.dto.member.*;
import com.ssafy.trip.mapper.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public int join(JoinMemberRequest request) throws Exception {
        return memberMapper.join(request);
    }

    @Override
    public MemberDto login(LoginMemberRequest request) throws Exception {
        return memberMapper.login(request);
    }

    @Override
    public void saveRefreshToken(String memberId, String refreshToken) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("refreshToken", refreshToken);
//        System.out.println("memberId: "+ map.get("memberId")+" refreshToken: "+map.get("refreshToken"));
        memberMapper.saveRefreshToken(map);
    }

    @Override
    public void deleteRefreshToken(String memberId) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("refreshToken", null);
        memberMapper.deleteRefreshToken(map);
    }

    @Override
    public int updateMember(String memberId, UpdateMemberRequest request) throws Exception {
        return memberMapper.updateMember(memberId, request);
    }

    @Override
    public int deleteMember(String memberId) throws Exception {
        return memberMapper.deleteMember(memberId);
    }

    @Override
    public InfoMemberResponse memberInfo(String memberId) throws Exception {
        return memberMapper.memberInfo(memberId);
    }


}
