package com.evaluation.ec.dto.response;

import com.evaluation.ec.dto.PhoneDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @Schema(description = "User id", example = "123e4567-e89b-12d3-a456-426614174000", type = "string", format = "uuid")
    private UUID id;

    @Schema(description = "User name", example = "John Doe", type = "string", format = "string")
    private String name;

    @Schema(description = "User email", example = "aaaaa@dominio.cl", type = "string", format = "email")
    private String email;

    @Schema(description = "User password", example = "Password123", type = "string", format = "password")
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modified;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLogin;

    private String token;

    private boolean isActive;

    private List<PhoneDto> phones;
}