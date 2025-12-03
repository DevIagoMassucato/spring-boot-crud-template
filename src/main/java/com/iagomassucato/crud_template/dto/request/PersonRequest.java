package com.iagomassucato.crud_template.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PersonRequest(
        @NotBlank(message = "firstName field written incorrectly")
        String firstName,
        @NotBlank(message = "lastName field written incorrectly")
        String lastName,
        @Email(message = "Invalid Email")
        @NotBlank(message = "email field written incorrectly")
        String email,
        @NotNull(message = "cpf field written incorrectly")
        Long cpf,
        @NotNull(message = "age field written incorrectly")
        Integer age) {
}