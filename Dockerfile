# syntax=docker/dockerfile:1

# ---- build stage ----
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /workspace

# 1) 의존성 레이어: gradle 설정만 먼저 복사 (소스 변경 시 캐시 재사용)
COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
RUN chmod +x gradlew && sed -i 's/\r//' gradlew

# 2) 소스 복사
COPY src ./src

# 3) BuildKit 캐시 마운트로 의존성/래퍼 배포본 캐싱 → 재다운로드/디스크쓰기 최소화
#    --no-daemon: 데몬 상주 방지, -x test: 테스트 스킵으로 빌드 시간 단축
RUN --mount=type=cache,target=/root/.gradle \
    ./gradlew bootJar -x test --no-daemon \
 && cp build/libs/*-SNAPSHOT.jar /workspace/app.jar

# ---- runtime stage ----
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app
COPY --from=build /workspace/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
