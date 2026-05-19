<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# common — Shared Infrastructure

## Purpose
Cross-cutting utilities used by multiple domains. Contains file upload handling (`FileService`) and static resource serving configuration (`WebConfig`). These are not domain-specific and should not contain business logic.

## Key Files

| File | Description |
|------|-------------|
| `FileService.java` | Saves `MultipartFile` uploads to local disk with UUID filenames; supports `upload-dir` (jelly images) and `icon-dir` (emotion icons) |
| `WebConfig.java` | Maps local filesystem directories to HTTP resource handlers: `jellyImages/` → `/images/**`, `emoIcons/` → `/icons/**` |

## For AI Agents

### Working In This Directory
- `FileService.saveFile()` saves to `file.upload-dir`; `saveIcon()` saves to `file.icon-dir` — both paths come from `application.yml`
- Saved filenames are UUID + original extension (e.g. `3f2a1b...jpg`) — the original filename is discarded
- `WebConfig` normalises paths: replaces backslashes with forward slashes and ensures trailing slash before registering with Spring MVC
- Cache period is set to 3600 seconds (1 hour) for both resource handlers
- The `docker` profile in `application.yml` overrides both directories to `/app/images/` and `/app/icons/`
- `S3Config.java` was deleted (see root AGENTS.md work-in-progress note) — the project currently uses local filesystem only

### Testing Requirements
- `FileService` requires a writable directory at the configured path — integration tests need the directory to exist or use a temp dir
- `WebConfig` resource handlers are tested implicitly by fetching `/images/<filename>` and `/icons/<filename>` via HTTP

## Dependencies

### Internal
- Used by `jelly.JellyController` (file upload on jelly creation)
- Intended for use by `AgedEmoImage` and `jellyImage` controllers (not yet fully wired)

### External
- `spring.web.multipart` for `MultipartFile`
- `file.upload-dir` / `file.icon-dir` config properties from `application.yml`

<!-- MANUAL: -->
