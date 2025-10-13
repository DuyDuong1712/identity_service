package com.devteria.identity_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    @NotBlank(message = "USER_NAME_INVALID")
    @Size(min = 5, max = 40, message = "USER_NAME_INVALID")
    String username;

    @NotBlank(message = "PASSWORD_INVALID")
    @Size(min = 8, max = 40, message = "PASSWORD_INVALID")
    String password;
}
