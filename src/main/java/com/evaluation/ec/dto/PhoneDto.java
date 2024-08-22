package com.evaluation.ec.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {

    @Schema(description = "Phone number", example = "1234567", type = "string", format = "string")
    private String number;

    @Schema(description = "Phone city code", example = "1", type = "string", format = "string")
    private String citycode;

    @Schema(description = "Phone country code", example = "56", type = "string", format = "string")
    private String countrycode;
}
