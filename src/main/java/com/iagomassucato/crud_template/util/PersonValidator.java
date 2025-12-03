package com.iagomassucato.crud_template.util;

import com.iagomassucato.crud_template.domain.repository.PersonRepository;
import com.iagomassucato.exception_core.exception.ApiException;
import com.iagomassucato.exception_core.exception.ErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PersonValidator {

    private final PersonRepository personRepository;

    public void validateUniqueFields(String email, Long cpf, Long id) {
        Map<String, Object> errors = new LinkedHashMap<>();

        if (isEmailAlreadyUsed(email, id)) {
            errors.put("email", email);
        }

        if (isCpfAlreadyUsed(cpf, id)) {
            errors.put("cpf", cpf);
        }

        if (!errors.isEmpty()) {
            throw new ApiException(
                    ErrorEnum.DATABASE_VIOLATION,
                    errors);
        }
    }

    private boolean isEmailAlreadyUsed(String email, Long id) {
        return (id == null)
                ? personRepository.existsByEmail(email)
                : personRepository.existsByEmailAndIdNot(email, id);
    }

    private boolean isCpfAlreadyUsed(Long cpf, Long id) {
        return (id == null)
                ? personRepository.existsByCpf(cpf)
                : personRepository.existsByCpfAndIdNot(cpf, id);
    }
}
