<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# AgedEmoImage — Aged Emotion Photo Domain

## Purpose
Stores photo references (file names) attached to an `AgedEmo` record. Each `AgedEmoImage` holds the filename of an image saved to the local filesystem via `FileService`. This is a child entity of `AgedEmo`.

## Key Files

| File | Description |
|------|-------------|
| `AgedEmoImage.java` | JPA entity; table `agedEmoImage`; fields: `agedEmoId`, `imageName` (stored filename) |
| `AgedEmoImageController.java` | REST controller at `/agedEmoImage`; create and list-by-parent endpoints |
| `AgedEmoImageService.java` | Business logic; correctly uses `final` field injection |
| `AgedEmoImageRepository.java` | Spring Data JPA; requires `findByAgedEmoId(Long)` custom query |

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `DTO/` | `AgedEmoImageResDTO` and `AgedEmoImageSaveReqDTO` (see `DTO/AGENTS.md`) |

## For AI Agents

### Working In This Directory
- `imageName` stores only the filename (UUID + extension), not a full path — the serving path is `/images/<imageName>` via `WebConfig`
- Images are physically stored at `file.upload-dir` (configured in `application.yml`)
- The parent `AgedEmo` has `cascade = CascadeType.REMOVE` on `agedEmoImages` — images are auto-deleted when the parent is deleted via JPA
- `insertable=false, updatable=false` on the `@ManyToOne` association — only the `agedEmoId` Long column is writable

### API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/agedEmoImage` | Create an image record (filename only — upload handled separately) |
| `GET` | `/agedEmoImage/{agedEmoId}` | List all images for a given aged emotion |

### Testing Requirements
- Verify `findByAgedEmoId` returns correct results when multiple images share a parent
- Test empty list is returned (not null) when no images exist for a parent

## Dependencies

### Internal
- `agedEmo.AgedEmo` — parent entity
- `common.FileService` — file persistence (used at controller layer, not yet wired here)

<!-- MANUAL: -->
