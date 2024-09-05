package com.acs.springbasic.service.implement;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.acs.springbasic.service.object.CustomOAuth2User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
//# OAuth2를 통해서 클라이언트 정보를 받은 후 진행할 비즈니스 로직을 작성하는 서비스
// - DefaultOAuth2UserService 클래스를 확장해야 함
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService {

    // OAuth2 인증 정보를 받고 실행할 비즈니스 로직 메서드
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registration = userRequest.getClientRegistration().getClientName();

        // 실제로는 정보 출력 행위X, 공부 중이니~
        try {
            System.out.println(registration);
            System.out.println("=============================================================================================");
            System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
            System.out.println("=============================================================================================");
            System.out.println(oAuth2User.getName());
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        // 데이터베이스 작업이 들어감

        return new CustomOAuth2User(oAuth2User.getName(), oAuth2User.getAttributes());

    }

}
