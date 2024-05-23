package io.ssafy.userservice.user.member.service;


import io.ssafy.userservice.global.response.Response;

public interface MemberService {

    Response<?> withdrawalMember (String memberId);
}
