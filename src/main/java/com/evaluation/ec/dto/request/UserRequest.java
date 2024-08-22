package com.evaluation.ec.dto.request;

import com.evaluation.ec.dto.PhoneDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @Schema(description = "User name", example = "John Doe", type = "string", format = "string", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @JsonFormat(pattern = "${custom.email.pattern}")
    @Email(message = "Email is not valid", regexp = "${custom.email.pattern}")
    @Schema(description = "User email", example = "aaaa@dominio.cl", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @JsonFormat(pattern = "${custom.password.pattern}")
    @Check(constraints = "${custom.password.pattern}")
    @Schema(description = "User password", example = "Password123", requiredMode = Schema.RequiredMode.REQUIRED, type = "string", format = "password")
    private String password;

    @ArraySchema(schema = @Schema(description = "User phones", implementation = PhoneDto.class))
    private List<PhoneDto> phones;
}