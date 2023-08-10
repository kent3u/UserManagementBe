package com.example.demo.appdomin.user;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IsClientModifyingAllowedForUser {

    public boolean execute(Request request) {
        User.Id loggedInUserId = User.Id.of(UUID.fromString((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        return Objects.equals(request.getUserId(), loggedInUserId);
    }

    @Value(staticConstructor = "of")
    public static class Request {
        User.Id userId;
    }
}
