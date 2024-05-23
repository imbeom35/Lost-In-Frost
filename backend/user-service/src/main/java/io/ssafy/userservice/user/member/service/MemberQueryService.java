package io.ssafy.userservice.user.member.service;


import io.ssafy.userservice.global.response.Response;

public interface MemberQueryService {

    Response<?> getMyInfo (String memberId);

    Response<?> searchNickname (String memberNickname);

    Response<?> getMemberRecordInfo(String memberId);
}
