<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# JellyCombination — Emotion Pair Lookup Table

## Purpose
A pre-seeded lookup table that maps two `BasicEmo` IDs (`firstEmo`, `secondEmo`) plus an `isAwaken` flag to a jelly icon filename (`JellyIcon`). This defines the full set of possible emotion combinations in the app. Clients query this table to determine which jelly icon to display and which combination a user has selected.

## Key Files

| File | Description |
|------|-------------|
| `JellyCombination.java` | JPA entity; table `jellyCombination`; fields: `firstEmo`, `secondEmo`, `isAwaken`, `JellyIcon` |
| `JellyCombController.java` | REST controller at `/jellyComb`; get-by-id and icon-lookup endpoints |
| `JellyCombService.java` | Business logic; returns icon path as `/images/<iconName>` |
| `JellyCombRepository.java` | Spring Data JPA; requires `findJellyIconByCombination(Long, Long, Boolean)` custom query |

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `DTO/` | `JellyCombResDTO` — response shape for combination detail (see `DTO/AGENTS.md`) |

## For AI Agents

### Working In This Directory
- This table is **pre-seeded** — do not add create/update/delete endpoints
- `JellyIcon` field name has an uppercase `J` (inconsistent with Java conventions) — do not rename without a DB migration
- `getJellyIcon` returns a path string `"/images/<iconName>"` — the icon images live in `jellyImages/` (~100+ PNG files)
- `isAwaken` represents a "awakened" state variant of the combination; the same emotion pair can have both an awakened and non-awakened combination record
- The jelly-icon endpoint wraps the response in extra quotes (`"\"" + jellyIcon + "\""`) to ensure valid JSON — this is intentional to avoid malformed JSON from Spring's string serialization

### API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| `GET` | `/jellyComb/{jellyCombId}` | Get combination detail by ID |
| `GET` | `/jellyComb/jelly-icon/{firstEmo}/{secondEmo}/{isAwaken}` | Get jelly icon path for an emotion pair |

### Testing Requirements
- Verify `findJellyIconByCombination` correctly matches all three parameters
- Test that `EntityNotFoundException` is thrown when no match is found
- Confirm both awakened and non-awakened variants can be retrieved independently

## Dependencies

### Internal
- `basicEmo.BasicEmo` — `firstEmo` and `secondEmo` reference `BasicEmo.emoId`
- `jelly.Jelly` — child records (not cascade-deleted — `orphanRemoval = false`)
- `agedEmo.AgedEmo` — child records (not cascade-deleted — `orphanRemoval = false`)

<!-- MANUAL: -->
