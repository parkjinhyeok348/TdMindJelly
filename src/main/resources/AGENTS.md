<!-- Parent: ../../../AGENTS.md -->
<!-- Generated: 2026-05-08 | Updated: 2026-05-08 -->

# resources

## Purpose
Spring Boot application configuration. Contains `application.yml` with profile-separated settings for local development and Docker deployment.

## Key Files

| File | Description |
|------|-------------|
| `application.yml` | All runtime config: DB connection, JPA, JWT secret, mail (Gmail SMTP), file upload paths, Docker profile override |

## For AI Agents

### Working In This Directory
- Active profile is `local` by default (`spring.profiles.active: local`)
- The `docker` profile activates via `spring.config.activate.on-profile: docker` and overrides file paths to `/app/images/` and `/app/icons/`
- MySQL connects to `localhost:3305` (non-standard port) with database `mindjelly_db`
- `ddl-auto: update` — schema is managed by Hibernate from entities; never run destructive DDL manually
- JWT secret (`jwt.secret`) is Base64-encoded and hardcoded — externalize via environment variable before production deployment
- Gmail SMTP credentials in `spring.mail` are app passwords — do not commit real credentials

### Key Config Values

| Key | Value | Note |
|-----|-------|------|
| `spring.datasource.url` | `localhost:3305/mindjelly_db` | Port 3305, not 3306 |
| `spring.jpa.hibernate.ddl-auto` | `update` | Auto-migrates schema |
| `jwt.secret` | base64 string | Hardcoded dev secret |
| `file.upload-dir` | `C:/Users/KTDS/git/TdMindJelly/jellyImages/` | Local path, overridden in docker profile |
| `file.icon-dir` | `C:/Users/KTDS/git/TdMindJelly/emoIcons/` | Local path, overridden in docker profile |

<!-- MANUAL: -->
