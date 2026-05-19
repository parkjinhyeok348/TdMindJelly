package com.server.tdMindJelly.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.icon-dir}")
    private String iconDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 젤리 이미지 매핑
        String formattedUploadDir = formatPath(uploadDir);
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///" + formattedUploadDir)
                .setCachePeriod(3600); // 1시간 캐싱

        // 감정 아이콘 매핑
        String formattedIconDir = formatPath(iconDir);
        registry.addResourceHandler("/icons/**")
                .addResourceLocations("file:///" + formattedIconDir)
                .setCachePeriod(3600);
    }

    private String formatPath(String path) {
        // 경로가 슬래시(/)로 끝나지 않으면 추가 (탐색을 위해 필수)
        if (!path.endsWith("/")) {
            path += "/";
        }
        // 역슬래시(\)를 슬래시(/)로 통일
        return path.replace("\\", "/");
    }
}
