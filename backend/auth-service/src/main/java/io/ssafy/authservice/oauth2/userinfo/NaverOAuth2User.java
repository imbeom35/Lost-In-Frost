package io.ssafy.authservice.oauth2.userinfo;

import java.util.Map;

public class NaverOAuth2User extends OAuth2UserInfo {

    public NaverOAuth2User(Map<String, Object> attributes) {
        super((Map<String, Object>) attributes.get("response"));
    }

    @Override
    public String getOAuth2Id() {
        return (String) attributes.get("id");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }


    public String getNickname() {
        return (String) attributes.get("nickname");
    }

    @Override
    public String profile_imgae() {
        return (String) attributes.get("profile_image");
    }

    @Override
    public String getAge() {
        return (String) attributes.get("age");
    }

}
