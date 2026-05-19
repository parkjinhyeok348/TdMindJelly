<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# jelly — Core Jelly Emotion Domain

## Purpose
The primary user-facing domain. A `Jelly` is a user's emotion record created by selecting a `JellyCombination`. It has a name, memo (`content`), an aging period (`agingPeriod`), and an `isAging` flag. When the aging period passes, the jelly can be promoted to an `AgedEmo`. Users can also attach photos (`JellyImage`).

## Key Files

| File | Description |
|------|-------------|
| `Jelly.java` | JPA entity; table `jelly`; fields: `userId`, `jellyCombId`, `jellyName`, `content`, `isAging`, `agingPeriod`, `createDate`, `jellyImages` |
| `JellyController.java` | REST controller at `/jelly`; multipart create, update, detail, and user-list endpoints |
| `JellyService.java` | Business logic; correctly uses `final` field injection |
| `JellyRepository.java` | Spring Data JPA; requires `findByUserId(Long)` custom query |

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `DTO/` | Request/response DTOs for jelly operations (see `DTO/AGENTS.md`) |

## For AI Agents

### Working In This Directory
- `POST /jelly` accepts `multipart/form-data`: JSON part `"data"` as `JellySaveReqDTO` + optional `"image"` file part
- Image handling in `JellyController.createJelly` is **incomplete** — the file is saved via `FileService` but the returned filename is never added to the DTO's image list (the comment shows the intended logic but it is commented out)
- `isAging` and `agingPeriod` track maturation state — business logic for promoting to `AgedEmo` is not yet implemented in this domain
- Entity update uses in-place mutation (`updateJelly`) on the managed instance — do not replace/re-save
- `cascade = CascadeType.REMOVE` + `orphanRemoval = true` on `jellyImages` — clearing and re-adding images in `updateJelly` correctly removes orphans
- Dates (`agingPeriod`, `createDate`) are `LocalDate` formatted as `yyyy-MM-dd` (Asia/Seoul)

### API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/jelly` | Create jelly (multipart/form-data) |
| `GET` | `/jelly/{jellyId}` | Get jelly detail |
| `GET` | `/jelly/{jellyId}/info` | Get fields needed for update form |
| `PUT` | `/jelly/{jellyId}` | Update name, content, images |
| `GET` | `/jelly/user/{userId}` | List all jellies for a user (drawer view) |

### Testing Requirements
- Test multipart create with and without image file
- Verify image list is properly replaced (not appended) on update
- Test `findByUserId` returns empty list for users with no jellies

## Dependencies

### Internal
- `JellyCombination` — parent combination type
- `jellyImage.JellyImage` — child image records (cascade delete)
- `user.Users` — owning user
- `common.FileService` — file persistence for image uploads

<!-- MANUAL: -->
