<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# basicEmo — Basic Emotion Catalog

## Purpose
Read-only catalog of seed emotions (기본 감정). Each `BasicEmo` has a name and an icon filename. These are the building blocks for `JellyCombination` — users pick two `BasicEmo` entries to create a jelly type. The catalog is pre-seeded in the database; there are no create/update/delete endpoints.

## Key Files

| File | Description |
|------|-------------|
| `BasicEmo.java` | JPA entity; table `basicEmo`; fields: `emoId`, `emoName` (max 10 chars), `emoIcon` (filename) |
| `BasicEmoController.java` | REST controller at `/basicEmo`; get-by-id and get-all endpoints only |
| `BasicEmoService.java` | Business logic; read-only queries |
| `BasicEmoRepository.java` | Spring Data JPA; uses `findAll()` and `findById()` only |

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `DTO/` | `BasicEmoResDTO` — response shape for client consumption (see `DTO/AGENTS.md`) |

## For AI Agents

### Working In This Directory
- This is a **read-only** domain from the API perspective — do not add write endpoints without explicit requirements
- `emoIcon` stores a filename served at `/icons/<emoIcon>` via `WebConfig`
- Icons are physically stored in `emoIcons/` (31 PNG files in the working tree)
- `emoName` is capped at 10 characters by the column definition
- `BasicEmo` is referenced by `JellyCombination` via `firstEmo` and `secondEmo` FK columns (stored as raw `Long`, not a JPA association on the `JellyCombination` side)

### API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| `GET` | `/basicEmo/{emoId}` | Get single basic emotion by ID |
| `GET` | `/basicEmo` | List all basic emotions |

### Testing Requirements
- Verify the full list returns all seeded emotions
- Confirm icon filenames resolve correctly via the `/icons/**` resource handler

## Dependencies

### Internal
- `JellyCombination` — references `BasicEmo.emoId` as `firstEmo` / `secondEmo`

<!-- MANUAL: -->
