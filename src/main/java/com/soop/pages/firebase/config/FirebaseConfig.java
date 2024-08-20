package com.soop.pages.firebase.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    // 로거 객체 생성
    private static final Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);

    @PostConstruct
    public void initialize() {
        try {
            // Firebase 서비스 계정 JSON 파일을 로드하기 위한 리소스 객체 생성
            ClassPathResource resource = new ClassPathResource("soop-profile-pic-firebase-adminsdk-25pru-9ab7c09212.json");
            InputStream serviceAccount = resource.getInputStream();

            // FirebaseOptions 객체를 생성하여 인증 정보를 설정하고, 스토리지 버킷을 지정
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("soop-profile-pic.appspot.com")
                    .build();

            // Firebase 앱이 아직 초기화되지 않았으면 초기화
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                logger.info("Firebase 초기화 성공");
            }
        } catch (IOException e) {
            // 초기화 과정에서 IOException이 발생하면 로그를 기록하고 RuntimeException을 발생시킴
            logger.error("Firebase 초기화 중 오류 발생", e);
            throw new RuntimeException("Firebase 초기화 실패", e);
        }
    }
}
