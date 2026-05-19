package com.server.tdMindJelly.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.icon-dir}")
    private String iconDir;

    public String saveFile(MultipartFile file) throws IOException {
        return saveToDirectory(file, uploadDir);
    }

    public String saveIcon(MultipartFile file) throws IOException {
        return saveToDirectory(file, iconDir);
    }

    private String saveToDirectory(MultipartFile file, String directoryPath) throws IOException {
        if (file == null || file.isEmpty()) return null;

        // 저장 디렉토리 확인 및 생성
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // UUID를 사용하여 중복 없는 파일명 생성
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String savedFilename = UUID.randomUUID().toString() + extension;

        // 파일 물리적 저장
        File targetFile = new File(directoryPath + savedFilename);
        file.transferTo(targetFile);

        return savedFilename;
    }
}
