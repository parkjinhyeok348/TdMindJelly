<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# user/DTO — User DTOs

## Purpose
Request and response DTOs for the `user` domain covering registration, login, profile update, and account recovery flows.

## Key Files

| File | Description |
|------|-------------|
| `UserSaveReqDTO.java` | Registration request; carries all user fields; `toEntity(encodedPassword)` accepts pre-encoded password |
| `UserLoginReqDTO.java` | Login request; carries `email` and `password` (plaintext — encoded in service) |
| `UserUpdateReqDTO.java` | Profile update request; carries `nickName`, `profileImage`, `isMarketing` |
| `UserUpdateResDTO.java` | Profile update pre-fill response; returns current `nickName`, `profileImage`, `isMarketing` |
| `UserResDTO.java` | Detailed user view response; wraps user entity fields for client consumption |
| `FindPasswordReqDTO.java` | Password recovery request; carries `userName`, `email`, `mobilePhoneNumber` for identity verification |

## For AI Agents

### Working In This Directory
- `UserSaveReqDTO.toEntity(encodedPassword)` takes the BCrypt-encoded password — never pass plaintext
- `UserLoginReqDTO` carries plaintext password — encoding and verification happen in `UserService.login()`
- `UserResDTO` must not expose `password` — verify the field is excluded before adding new fields to the entity
- `FindPasswordReqDTO` requires all three identity fields to match the database record before a temporary password is issued

<!-- MANUAL: -->
