package com.soop.pages.firebase.service;

import org.springframework.stereotype.Service;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.firebase.cloud.StorageClient;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadService {

    //파일을 Firebase 스토리지에 업로드하는 메서드
    public String uploadFile(MultipartFile file) throws IOException {
        // 파일 이름 생성: UUID와 원본 파일 이름을 조합
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // BlobId 생성: Firebase 스토리지의 버킷과 파일 경로 설정
        BlobId blobId = BlobId.of("soop-profile-pic.appspot.com", "profile_pictures/" + fileName);

        // BlobInfo 생성: 파일의 메타데이터 설정 (컨텐츠 타입 등)
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .build();

        // Firebase 스토리지 클라이언트를 통해 Storage 객체를 가져옴
        Storage storage = StorageClient.getInstance().bucket().getStorage();

        // 파일을 스토리지에 업로드
        Blob blob = storage.create(blobInfo, file.getInputStream());

        // 업로드된 파일의 미디어 링크 반환
        return blob.getMediaLink();
    }
}
