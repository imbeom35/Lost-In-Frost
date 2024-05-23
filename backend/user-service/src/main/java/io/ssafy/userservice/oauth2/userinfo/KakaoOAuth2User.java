package io.ssafy.userservice.oauth2.userinfo;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class KakaoOAuth2User extends OAuth2UserInfo {

    private Long id;

    public KakaoOAuth2User(Map<String, Object> attributes) {
        super((Map<String, Object>) attributes.get("kakao_account"));
        log.warn(attributes.toString());
        this.id = (Long) attributes.get("id");
    }

    @Override
    public String getOAuth2Id() {
        return this.id.toString();
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) ((Map<String, Object>) attributes.get("profile")).get("nickname");
    }


    public String getNickname() {
        return (String) ((Map<String, Object>) attributes.get("profile")).get("nickname");
    }

    @Override
    public String profile_imgae() {
        return (String) ((Map<String, Object>) attributes.get("profile")).get("profile_image_url");
    }

    @Override
    public String getAge() {
        return (String) ((Map<String, Object>) attributes.get("profile")).get("age_range");
    }

}