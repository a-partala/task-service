package net.partala.taskservice.dto.response;

import jakarta.validation.constraints.*;
import net.partala.taskservice.user.UserRole;

import java.time.LocalDateTime;
import java.util.Set;

public record UserResponse(

        @Null
        Long id,

        @NotNull
        String login,

        @NotNull
        @Email
        String email,

        @Null
        LocalDateTime registrationDateTime,

        @Null
        Set<UserRole> roles,

        @Null
        Set<Long> assignedTaskIds,

        @Null
        boolean emailVerified
) {
}
