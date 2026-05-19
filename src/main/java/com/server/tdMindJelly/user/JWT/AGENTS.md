<!-- Parent: ../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# user/JWT — JWT Authentication Infrastructure

## Purpose
Spring Security integration for JWT-based authentication. Handles token generation, validation, per-request filtering, password encoding, and security rule configuration. Active for all profiles; authentication is bypassed only in the `local` profile via `SecurityConfig`.

## Key Files

| File | Description |
|------|-------------|
| `JwtUtil.java` | Generates and validates HMAC-SHA256 JWT tokens; expiry is 10 hours; secret loaded from `jwt.secret` via `@PostConstruct` |
| `JwtRequestFilter.java` | `OncePerRequestFilter`; extracts Bearer token from `Authorization` header, validates it, and sets `SecurityContextHolder` authentication |
| `SecurityConfig.java` | Spring Security config; `local` profile: all requests permitted, CSRF/form-login disabled; non-local: GET `/public/**` open, `/admin/**` requires ADMIN role, all else requires auth |
| `CustomUserDetails.java` | `UserDetails` adapter for `Users` entity; uses `email` as username; grants `ROLE_USER` |
| `PasswordEncryptService.java` | BCrypt password encoding and matching via Spring's `PasswordEncoder` bean |

## For AI Agents

### Working In This Directory
- `JwtUtil` uses `@PostConstruct` to load the secret key — do not call `generateToken` or `extractEmail` before the bean is fully initialised
- Token subject is the user's **email** address (not userId)
- `JwtRequestFilter` looks up the user from DB on every authenticated request — consider caching if this becomes a performance concern
- `SecurityConfig` has two `SecurityFilterChain` beans gated by `@Profile` — only one is active per environment. The `local` profile bean permits everything (no JWT required during local dev)
- `CustomUserDetails.getUsername()` returns `email` — this is how Spring Security identifies the principal throughout the filter chain
- `PasswordEncryptService` wraps the `PasswordEncoder` bean defined in `SecurityConfig` — do not create a second `BCryptPasswordEncoder` bean
- **Note:** `JwtRequestFilter` is defined as a `@Component` but is not explicitly registered in `SecurityConfig.addFilterBefore()` — verify it is being applied in the filter chain for non-local profiles

### Security Rules (non-local profile)

| Pattern | Rule |
|---------|------|
| `GET /public/**` | Permitted without auth |
| `/admin/**` | Requires `ADMIN` role |
| All other requests | Requires valid JWT |

### Testing Requirements
- Local profile disables all security — integration tests run without tokens by default
- For non-local security tests, generate a valid token via `JwtUtil.generateToken(email)` and pass it as `Authorization: Bearer <token>`

## Dependencies

### External
- JJWT 0.11.5 (`jjwt-api`, `jjwt-impl`, `jjwt-jackson`)
- Spring Security 6.x

<!-- MANUAL: -->
