<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# user — User Account & Authentication Domain

## Purpose
Manages user accounts (`Users` entity) and all authentication flows: registration, login (JWT issuance), password reset via email, duplicate checks, and account deletion. JWT validation is handled by the `JWT/` sub-package which integrates with Spring Security.

## Key Files

| File | Description |
|------|-------------|
| `Users.java` | JPA entity; table `users`; fields: email, password (BCrypt), phone, nickName, profileImage, point, ageRange, isMarketing |
| `UserController.java` | REST controller at `/users`; registration, login, profile, duplicate checks, email/password recovery, delete |
| `UserService.java` | Business logic; **BUG: `jwtUtil` is non-`final` — not injected by `@RequiredArgsConstructor`, will be `null` at runtime** |
| `UserRepository.java` | Spring Data JPA; custom finders: `findByEmail`, `findByMobilePhoneNumber`, `findByNickName`, `findByUserNameAndMobilePhoneNumber`, `findByUserNameAndEmailAndMobilePhoneNumber` |

## Subdirectories

| Directory | Purpose |
|-----------|---------|
| `DTO/` | Request/response DTOs for user operations (see `DTO/AGENTS.md`) |
| `JWT/` | JWT utilities, request filter, and Spring Security config (see `JWT/AGENTS.md`) |

## For AI Agents

### Working In This Directory
- **Critical bug:** `UserService` declares `private JwtUtil jwtUtil` without `final` — fix by adding `final`. This makes `login()` throw `NullPointerException` at runtime
- Password is BCrypt-encoded via `PasswordEncryptService` before saving — never store plaintext passwords
- `findPassword` generates an 8-char random password using `SecureRandom` + `RandomStringUtils`, then sends it via Gmail SMTP
- `checkDuplicateEmail` and `checkDuplicateMobilePhoneNumber` use `orElseThrow` then compare to null — this logic is inverted and will always throw before the null check; should use `existsByEmail` or `findByEmail(...).isEmpty()` instead
- `email` is the unique login identifier (`username` in Spring Security terms — `CustomUserDetails.getUsername()` returns the email)
- `Users` entity has `cascade = CascadeType.REMOVE` on both `jellyList` and `agedEmoList` — deleting a user cascades to all their content

### API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/users` | Register new user |
| `GET` | `/users/{userId}` | Get user detail |
| `GET` | `/users/{userId}/profile` | Get editable profile fields |
| `PUT` | `/users/{userId}` | Update nickname, profile image, marketing consent |
| `DELETE` | `/users/{userId}` | Delete user account |
| `GET` | `/users/check-email` | Check email availability |
| `GET` | `/users/check-phone` | Check phone number availability |
| `GET` | `/users/check-nickname` | Check nickname availability |
| `POST` | `/users/find-email` | Find email by name + phone |
| `POST` | `/users/find-password` | Reset password and send temporary password via email |
| `POST` | `/users/login` | Login — returns JWT token string |

### Testing Requirements
- Fix `jwtUtil` injection before testing login
- Fix duplicate-check logic inversion before testing availability endpoints
- Test password reset email delivery (requires Gmail SMTP config in `application.yml`)

## Dependencies

### Internal
- `JWT/JwtUtil` — token generation and validation
- `JWT/PasswordEncryptService` — BCrypt encoding
- `jelly.Jelly` — child records (cascade delete on user removal)
- `agedEmo.AgedEmo` — child records (cascade delete on user removal)

### External
- Spring Security, JJWT 0.11.5
- Spring Mail (Gmail SMTP)
- Commons Lang3 `RandomStringUtils`

<!-- MANUAL: -->
