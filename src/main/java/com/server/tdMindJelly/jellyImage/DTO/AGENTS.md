<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# jellyImage/DTO — Jelly Image DTOs

## Purpose
Request and response DTOs for the `jellyImage` domain.

## Key Files

| File | Description |
|------|-------------|
| `JellyImageSaveReqDTO.java` | Create request; carries `jellyId` and `imageName`; has `toEntity()` method |
| `JellyImageResDTO.java` | Response; wraps `JellyImage` fields for client consumption |

## For AI Agents

### Working In This Directory
- `imageName` in the request should be a pre-saved UUID filename — the physical file must be uploaded via `FileService` before this record is created
- `toEntity()` is the single path for constructing new `JellyImage` instances
- `JellyImageSaveReqDTO` is also used in `JellySaveReqDTO` (jelly creation carries an image list)

<!-- MANUAL: -->
