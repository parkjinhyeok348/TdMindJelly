<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# JellyCombination/DTO — Jelly Combination DTOs

## Purpose
Response DTO for the `JellyCombination` domain. No request DTOs exist because combinations are pre-seeded and not created via the API.

## Key Files

| File | Description |
|------|-------------|
| `JellyCombResDTO.java` | Response; wraps `JellyCombination` fields (`jellyCombId`, `firstEmo`, `secondEmo`, `isAwaken`, `JellyIcon`) |

## For AI Agents

### Working In This Directory
- No `toEntity()` needed — this domain is read-only from the API perspective
- `JellyIcon` in the response is a filename; clients resolve it at `/images/<JellyIcon>`
- Note the inconsistent field casing: `JellyIcon` starts with uppercase in the entity — ensure the DTO mirrors this or maps it to camelCase (`jellyIcon`) for JSON output

<!-- MANUAL: -->
