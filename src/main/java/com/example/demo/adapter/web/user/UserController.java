package com.example.demo.adapter.web.user;

import com.example.demo.appdomin.user.FindUserClients;
import com.example.demo.appdomin.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
class UserController {

    private final FindUserClients findUserClients;

    @GetMapping("/clients")
    public Set<UserClientDto> findCurrentUserClients() {
        UUID userId = UUID.fromString((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return findUserClients.execute(FindUserClients.Request.of(User.Id.of(userId))).stream()
                .map(UserClientDto::of)
                .collect(Collectors.toUnmodifiableSet());
    }
}
