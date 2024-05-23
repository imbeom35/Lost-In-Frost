package io.ssafy.userservice.user.mypage.service;


import io.ssafy.userservice.global.response.Response;

public interface MyPageService {

    Response<?> getCoinAmount (String memberId);
    Response<?> getCrystalAmount (String memberId);
    Response<?> getCoinAndCrystalAmount (String memberId);
    Response<?> updateMyCostume (String memberId, Long myCostumeSeq);
    Response<?> updateMyInfo (String memberId, String nickname, String password);
    Response<?> validatePassword (String memberId, String password);
    Response<?> getMyCostumeCount (String memberId);
    Response<?> updateMessage(String memberId, String message);
}
