package io.ssafy.userservice.oauth2.userinfo;

import java.util.Map;

public class GoogleOAuth2User extends OAuth2UserInfo {

    public GoogleOAuth2User(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getOAuth2Id() {
        return (String) attributes.get("sub");
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
        return (String) attributes.get("name");
    }

    @Override
    public String profile_imgae() {
        return (String) attributes.get("picture");
    }

    @Override
    public String getAge() {
        return (String) attributes.get("age");
    }

}
