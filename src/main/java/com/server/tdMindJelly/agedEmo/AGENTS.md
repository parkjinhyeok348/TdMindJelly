<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# agedEmo — Aged Emotion Domain

## Purpose
Represents a "matured" (숙성된) emotion record — a jelly that has passed its aging period and been promoted to an archived state. Each `AgedEmo` belongs to a `Users` and is linked to a `JellyCombination`. Users can attach photos (`AgedEmoImage`), give it a name, and leave a memo.

## Key Files

| File | Description |
|------|-------------|
| `AgedEmo.java` | JPA entity; table `agedEmo`; fields: `userId`, `jellyCombId`, `agedEmoName`, `content`, `createDate`, `agedEmoImages` |
| `AgedEmoController.java` | REST controller at `/agedEmo`; CRUD + museum list endpoints |
| `AgedEmoService.java` | Business logic; **BUG: `agedEmoRepository` is non-`final` and not injected — will be `null` at runtime** |
| `AgedEmoRepository.java` | Spring Data JPA repository; requires `findByUserId(Long)` custom query |

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `DTO/` | Request/response DTOs for aged emotion operations (see `DTO/AGENTS.md`) |

## For AI Agents

### Working In This Directory
- **Critical bug:** `AgedEmoService` declares `private AgedEmoRepository agedEmoRepository` without `final`. `@RequiredArgsConstructor` only injects `final` fields — fix by adding `final` to the field declaration
- Entity update uses null-safe in-place mutation (`updateAgedEmo`) — do not replace the entity, call the method on the managed instance
- `cascade = CascadeType.REMOVE` on `agedEmoImages` — deleting an `AgedEmo` cascades to its images
- `createDate` is a `LocalDate`; always format as `yyyy-MM-dd` (Asia/Seoul timezone)
- Foreign keys (`userId`, `jellyCombId`) are stored as plain `Long` columns; the JPA associations (`@ManyToOne`) use `insertable=false, updatable=false`

### API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/agedEmo` | Create aged emotion |
| `GET` | `/agedEmo/{agedEmoId}` | Get detail |
| `GET` | `/agedEmo/{agedEmoId}/update-info` | Get fields needed for update form |
| `PUT` | `/agedEmo/{agedEmoId}` | Update name, content, images |
| `GET` | `/agedEmo/user/{userId}` | List all aged emotions for a user (museum view) |

### Testing Requirements
- Test `AgedEmoService` only after fixing the `agedEmoRepository` injection bug
- Verify cascade delete removes `AgedEmoImage` records when parent is deleted

## Dependencies

### Internal
- `AgedEmoImage` — child images (cascade delete)
- `JellyCombination` — parent combination record
- `user.Users` — owning user

<!-- MANUAL: -->
