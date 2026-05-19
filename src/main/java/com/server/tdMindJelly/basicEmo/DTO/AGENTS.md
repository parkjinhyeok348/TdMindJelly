<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# basicEmo/DTO — Basic Emotion DTOs

## Purpose
Response DTO for the `basicEmo` domain. No request DTOs exist because `BasicEmo` is a read-only catalog with no write endpoints.

## Key Files

| File | Description |
|------|-------------|
| `BasicEmoResDTO.java` | Response; wraps `BasicEmo` fields (`emoId`, `emoName`, `emoIcon`) for client consumption |

## For AI Agents

### Working In This Directory
- `emoIcon` in the response is a filename — clients resolve the image at `/icons/<emoIcon>`
- No `toEntity()` needed — this domain is read-only; no create/update operations exist

<!-- MANUAL: -->
