<!-- Parent: ../../../../../../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# tdMindJelly — Main Application Package

## Purpose
Root application package `com.server.tdMindJelly`. Contains the Spring Boot entry point and all eight domain feature packages. Each domain is self-contained with its own entity, repository, service, controller, and DTO sub-package.

## Key Files

| File | Description |
|------|-------------|
| `TdMindJellyApplication.java` | `@SpringBootApplication` entry point; `@EnableJpaRepositories` scans `com.server.tdMindJelly` |

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `agedEmo/` | Matured emotion records (see `agedEmo/AGENTS.md`) |
| `AgedEmoImage/` | Photos attached to aged emotions (see `AgedEmoImage/AGENTS.md`) |
| `basicEmo/` | Read-only seed emotion catalog (see `basicEmo/AGENTS.md`) |
| `common/` | File upload service and static resource mapping (see `common/AGENTS.md`) |
| `jelly/` | Core jelly emotion records (see `jelly/AGENTS.md`) |
| `JellyCombination/` | Emotion pair → jelly icon lookup table (see `JellyCombination/AGENTS.md`) |
| `jellyImage/` | Photos attached to jelly records (see `jellyImage/AGENTS.md`) |
| `user/` | User accounts and JWT security (see `user/AGENTS.md`) |

## For AI Agents

### Working In This Directory
- Do not move repositories outside `com.server.tdMindJelly` — `@EnableJpaRepositories` is scoped to this base package
- New domains follow the same pattern: create a sub-package with `Entity`, `Repository`, `Service`, `Controller`, and a `DTO/` sub-package
- Use `@RequiredArgsConstructor` for DI; all injected fields must be `final`

### Testing Requirements
- `./gradlew test` from project root
- Tests live in `src/test/java/com/TdMindJelly/demo/` (wrong package — see root AGENTS.md quirks)

### Common Patterns
- Entities use `@Builder` + `@NoArgsConstructor(access = AccessLevel.PROTECTED)` pattern (mixed — some use public no-arg)
- Controllers return `ResponseEntity<T>` with explicit HTTP status
- Services use `@Transactional` at the class level
- `EntityNotFoundException` is the standard throw for missing records (not mapped to HTTP 404 yet)

## Dependencies

### External
- Spring Boot 3.4.1 (Web, Data JPA, Security, Mail, Validation)
- JJWT 0.11.5
- Lombok
- Commons Lang3 3.12.0
- MySQL connector, H2 (test/fallback)

<!-- MANUAL: -->
