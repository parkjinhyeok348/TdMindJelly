<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# jelly/DTO — Jelly DTOs

## Purpose
Request and response DTOs for the `jelly` domain.

## Key Files

| File | Description |
|------|-------------|
| `JellySaveReqDTO.java` | Create request; carries `userId`, `jellyCombId`, `jellyName`, `content`, `isAging`, `agingPeriod`, `createDate`, image list; has `toEntity()` |
| `JellyUpdateReqDTO.java` | Update request; carries `jellyName`, `content`, and replacement `jellyImages` list |
| `JellyUpdateResDTO.java` | Update form pre-fill response; returns current `jellyName`, `content`, and image list |
| `JellyResDTO.java` | Detailed view response; wraps full `Jelly` entity fields |
| `JellyDrawerResDTO.java` | Drawer list item response; lightweight summary for the user's jelly list view |

## For AI Agents

### Working In This Directory
- `JellySaveReqDTO.toEntity()` is the single path for constructing new `Jelly` instances
- `agingPeriod` and `createDate` are `LocalDate` — ensure ISO-8601 (`yyyy-MM-dd`) format in JSON
- `JellyDrawerResDTO` is the list projection — keep it lightweight; do not add heavy nested fields
- `JellyUpdateReqDTO.jellyImages` replaces the entire image list on update (in-place via `Jelly.updateJelly`)

<!-- MANUAL: -->
