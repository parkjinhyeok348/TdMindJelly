package com.server.tdMindJelly.user.JWT;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService {

    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails userDetails)) {
            throw new AccessDeniedException("Authentication required");
        }
        return userDetails.getUserId();
    }

    public void assertCurrentUser(Long userId) {
        if (!getCurrentUserId().equals(userId)) {
            throw new AccessDeniedException("Access denied");
        }
    }
}
