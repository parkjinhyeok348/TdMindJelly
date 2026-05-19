<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# AgedEmoImage/DTO — Aged Emotion Image DTOs

## Purpose
Request and response DTOs for the `AgedEmoImage` domain.

## Key Files

| File | Description |
|------|-------------|
| `AgedEmoImageSaveReqDTO.java` | Create request; carries `agedEmoId` and `imageName`; has `toEntity()` method |
| `AgedEmoImageResDTO.java` | Response; wraps `AgedEmoImage` fields for client consumption |

## For AI Agents

### Working In This Directory
- `imageName` in the request should be a pre-saved filename (UUID format) — the physical file must be uploaded separately via `FileService` before creating the record
- `toEntity()` is the single path for constructing new `AgedEmoImage` instances

<!-- MANUAL: -->
