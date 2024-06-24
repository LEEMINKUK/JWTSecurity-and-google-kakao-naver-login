package com.ohgiraffers.oauthjwt.service;

import com.ohgiraffers.oauthjwt.dto.*;
import com.ohgiraffers.oauthjwt.entity.RefreshEntity;
import com.ohgiraffers.oauthjwt.entity.UserEntity;
import com.ohgiraffers.oauthjwt.repository.RefreshRepository;
import com.ohgiraffers.oauthjwt.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private UserRepository userRepository;
//    private RefreshRepository refreshRepository;

    public CustomOAuth2UserService(UserRepository userRepository,RefreshRepository refreshRepository) {
        this.userRepository = userRepository;
//        this.refreshRepository = refreshRepository;
    }

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);



//        System.out.println(oAuth2User);

        // 어디서 오는 정보인지 확인
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());

        } else if (registrationId.equals("kakao")) {

            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        } else {

            return null;
        }

        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬
        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();

        UserEntity existData = userRepository.findByUsername(username);

        if (existData == null) {

            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setName(oAuth2Response.getName());
            userEntity.setRole("ROLE_USER");

            userRepository.save(userEntity);

//            // Refresh token 관리 예시
//            String refreshToken = generateRefreshToken();
//            addRefreshEntity(username, refreshToken, 86400000L);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole("ROLE_USER");

            return new CustomOAuth2User(userDTO);
        } else {

            existData.setEmail(oAuth2Response.getEmail());
            existData.setName(oAuth2Response.getName());

            userRepository.save(existData);

//            // Refresh token 관리 예시
//            String refreshToken = generateRefreshToken();
//            addRefreshEntity(username, refreshToken ,86400000L);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(existData.getUsername());
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(existData.getRole());


            return new CustomOAuth2User(userDTO);
        }
    }
    // Refresh token 저장 메서드
//    private void addRefreshEntity(String username, String refresh, Long expiredMs) {
//
//        Date date = new Date(System.currentTimeMillis() + expiredMs);
//
//        RefreshEntity refreshEntity = new RefreshEntity();
//        refreshEntity.setUsername(username);
//        refreshEntity.setRefresh(refresh);
//        refreshEntity.setExpiration(date.toString());
//
//        refreshRepository.save(refreshEntity);
//    }


//
//    // Refresh token 생성 메서드 (임시 예시)
//    private String generateRefreshToken() {
//        // 실제 구현에 맞게 Refresh token 생성 로직 구현
//        return "generated_refresh_token";
//    }
}