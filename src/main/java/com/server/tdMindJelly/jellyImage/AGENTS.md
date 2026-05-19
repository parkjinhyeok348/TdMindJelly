<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# jellyImage — Jelly Photo Domain

## Purpose
Stores photo references (file names) attached to a `Jelly` record. Each `JellyImage` holds the UUID-based filename of an image saved to the local filesystem. This is a child entity of `Jelly`.

## Key Files

| File | Description |
|------|-------------|
| `JellyImage.java` | JPA entity; table `jellyImage`; fields: `jellyId`, `imageName` (stored filename) |
| `JellyImageController.java` | REST controller at `/jellyImage`; create and list-by-parent endpoints |
| `JellyImageService.java` | Business logic; has dead field `private static String bucketName = "mind-jelly-bucket"` — S3 remnant, safe to remove |
| `JellyImageRepository.java` | Spring Data JPA; requires `findByJellyId(Long)` custom query |

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `DTO/` | `JellyImageResDTO` and `JellyImageSaveReqDTO` (see `DTO/AGENTS.md`) |

## For AI Agents

### Working In This Directory
- `imageName` stores only the filename (UUID + extension), not a full path — served at `/images/<imageName>` via `WebConfig`
- Images are physically stored at `file.upload-dir` (configured in `application.yml`)
- The dead `bucketName` static field is an S3 remnant from a removed feature — it can be deleted
- Parent `Jelly` has `cascade = CascadeType.REMOVE` + `orphanRemoval = true` — images are deleted when the parent jelly is deleted or the image list is cleared
- `insertable=false, updatable=false` on the `@ManyToOne` association — only the `jellyId` Long column is writable

### API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/jellyImage` | Create an image record for a jelly |
| `GET` | `/jellyImage/{jellyId}` | List all images for a given jelly |

### Testing Requirements
- Verify `findByJellyId` returns all images for a given parent
- Confirm empty list (not null) is returned when no images exist

## Dependencies

### Internal
- `jelly.Jelly` — parent entity
- `common.FileService` — file persistence (used at controller layer, not yet wired into this service)

<!-- MANUAL: -->
