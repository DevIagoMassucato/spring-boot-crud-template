package com.iagomassucato.crud_template.dto.response;

import com.iagomassucato.crud_template.domain.model.PersonEntity;
import org.jspecify.annotations.NonNull;


public record PersonResponse(
        @NonNull Long id,
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull String email,
        @NonNull Long cpf,
        @NonNull Integer age) {

    public static PersonResponse fromEntity(PersonEntity personEntity) {
        return new PersonResponse(
                personEntity.getId(),
                personEntity.getFirstName(),
                personEntity.getLastName(),
                personEntity.getEmail(),
                personEntity.getCpf(),
                personEntity.getAge()
        );
    }
}
