package co.istad.springwebmvc.dto;

import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductEditRequest(
        @NotNull
        @NotBlank
        String name,
        @NotNull
        @Positive
        Double price
) {
}
