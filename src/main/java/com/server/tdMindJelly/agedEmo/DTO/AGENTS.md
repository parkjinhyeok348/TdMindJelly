<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# agedEmo/DTO — Aged Emotion DTOs

## Purpose
Request and response DTOs for the `agedEmo` domain. Prevents direct entity exposure in API responses.

## Key Files

| File | Description |
|------|-------------|
| `AgedEmoSaveReqDTO.java` | Create request; maps to `AgedEmo` entity via `toEntity()` |
| `AgedEmoUpdateReqDTO.java` | Update request; carries `agedEmoName`, `content`, and replacement `agedEmoImages` list |
| `AgedEmoUpdateResDTO.java` | Update form pre-fill response; returns current `agedEmoName`, `content`, and image list |
| `AgedEmoResDTO.java` | Detailed view response; wraps the full `AgedEmo` entity fields |
| `AgedEmoMuseumResDTO.java` | Museum list item response; lightweight summary for the list view |

## For AI Agents

### Working In This Directory
- All DTOs follow the `*ReqDTO` / `*ResDTO` naming convention
- `AgedEmoSaveReqDTO.toEntity()` is the single construction path for new `AgedEmo` instances
- `AgedEmoUpdateReqDTO` carries an `agedEmoImages` list — these are passed directly to `AgedEmo.updateAgedEmo()` for in-place replacement
- `AgedEmoMuseumResDTO` is the list-view projection — keep it lightweight; do not add heavy nested objects

<!-- MANUAL: -->
