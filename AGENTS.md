<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# TdMindJelly

## Purpose
Spring Boot REST API backend for "오늘의 마음젤리" (Today's Mind Jelly) — an emotion-tracking app where users record daily emotions as "jellies," combine two basic emotions into jelly types, and let them "age" into matured emotion records.

## Language

- 기본 응답 언어는 한국어로 한다.
- 기술 용어는 필요한 경우 영어 원어를 병기한다.

## Harness Engineering Policy

이 프로젝트에서는 coding agent와 agent harness를 명확히 구분한다.

- Coding agent: 코드를 읽고, 계획하고, 수정하고, 실행하는 AI 작업자
- Agent harness: coding agent가 안전하고 일관되게 일하도록 만드는 규칙, 도구, 권한, 검증, 역할 분리, 피드백 루프

## Mandatory Brainstorming Gate

새로운 하네스 설계, agent 설계, workflow 설계, AGENTS.md 수정, hook 설계, MCP 설계, 검증 루프 설계를 요청받으면 바로 구현하지 않는다.

먼저 Socratic Harness Interview를 수행한다.

최소한 아래 항목이 명확해지기 전까지 구현 계획을 확정하지 않는다.

- 정의
- 포함 범위
- 제외 범위
- 대상 사용자
- 대상 coding agent runtime
- harness 구성요소
- 성공 기준
- 실패 유형
- 제약 조건
- 승인 기준

## Implementation Gate

인터뷰 결과가 정리되기 전까지 실제 파일 수정, hook 생성, agent 생성, MCP 추가를 하지 않는다.

## Harness Interview Checklist

하네스 관련 설계 전에는 `docs/harness/socratic-interview-checklist.md`의 항목을 채우거나, 대화 안에서 동등한 수준으로 답변을 정리해야 한다.

## Key Files

| File | Description |
|------|-------------|
| `build.gradle` | Gradle build config — Java 21, Spring Boot 3.4.1, JJWT 0.11.5, Spring Mail |
| `docker-compose.yml` | MySQL 8.0.22 container on port **3305** |
| `settings.gradle` | Project name: `TdMindJelly` |
| `src/main/resources/application.yml` | DB, JPA, JWT, mail, and file-path config |

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `src/main/java/com/server/tdMindJelly/` | All application source code (see nested AGENTS.md) |
| `src/main/resources/` | `application.yml` configuration |
| `src/test/` | JUnit 5 integration tests |
| `jellyImages/` | Static jelly combination images served at `/images/**` |
| `emoIcons/` | Static emotion icon images served at `/icons/**` |

## For AI Agents

### Build & Run
- `gradlew.bat bootRun` — start the app (Windows)
- `./gradlew test` — run all tests (JUnit 5)
- `docker compose up -d db` — start MySQL on port 3305
- `./gradlew build` — full build

### Notable Quirks

| Quirk | Detail |
|-------|--------|
| **MySQL port** | 3305 (not 3306). Defined in `docker-compose.yml` and `application.yml` |
| **Spring profiles** | `local` is active by default. `docker` profile overrides file upload paths to `/app/images` and `/app/icons` |
| **File uploads** | `file.upload-dir` and `file.icon-dir` in YAML. Served via `WebConfig` resource handlers at `/images/**` and `/icons/**` |
| **JWT secret** | Hardcoded in `application.yml` (dev only; externalize for prod) |
| **Test package mismatch** | Tests are under `com.TdMindJelly.demo` instead of `com.server.tdMindJelly`. The `@SpringBootTest` references itself rather than the main app class — context-loading tests may fail |
| **`.gitignore`** | Line 6 has `.yml` (matches only a file literally named `.yml`); harmless but misleading |
| **S3 remnant** | `JellyImageService` has a dead `private static String bucketName = "mind-jelly-bucket"` field — S3 was removed; local file storage is active |
| **Missing @Autowired** | `AgedEmoService.agedEmoRepository` and `UserService.jwtUtil` are non-`final` fields not picked up by `@RequiredArgsConstructor` — they will be `null` at runtime |

### Architecture

- **Base package:** `com.server.tdMindJelly`
- **Layered** per domain: `Entity → Repository → Service → Controller` + `DTO/` sub-package
- **8 domain packages:** `agedEmo`, `AgedEmoImage`, `basicEmo`, `common`, `jelly`, `JellyCombination`, `jellyImage`, `user`
- **Security:** JWT auth in `user/JWT/` (`JwtUtil`, `JwtRequestFilter`, `SecurityConfig`). `local` profile disables auth — all requests permitted

### Entity Relationships
```
Users 1──* Jelly
Users 1──* AgedEmo
BasicEmo ──> JellyCombination (firstEmo / secondEmo FK)
JellyCombination 1──* Jelly
JellyCombination 1──* AgedEmo
Jelly 1──* JellyImage
AgedEmo 1──* AgedEmoImage
```

### Coding Conventions
- Lombok (`@Builder`, `@Getter`, `@NoArgsConstructor`) on all entities
- `@EnableJpaRepositories(basePackages = "com.server.tdMindJelly")` on the application class
- `ddl-auto: update` — Hibernate manages schema from entities
- Commit message style: `[Feat]` / `[FIX]` prefix with Korean descriptions
- DTOs named `*ReqDTO` / `*ResDTO`; entities are never returned directly from API (controllers should use DTOs — some currently violate this by returning raw entities)

### Work-in-Progress
The working tree has uncommitted S3-related changes (`S3Config.java` deleted, `build.gradle` deps removed). Run `git diff HEAD` before assuming committed state is current.

<!-- MANUAL: Any manually added notes below this line are preserved on regeneration -->
