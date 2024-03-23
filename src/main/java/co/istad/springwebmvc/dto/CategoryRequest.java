package co.istad.springwebmvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @NotNull
        @NotBlank
        String name,
        @NotNull
        @NotBlank
        String description

) {
}
