package com.iagomassucato.crud_template.endpoint.service;

import com.iagomassucato.crud_template.domain.model.PersonEntity;
import com.iagomassucato.crud_template.domain.repository.PersonRepository;
import com.iagomassucato.crud_template.dto.request.PersonRequest;
import com.iagomassucato.crud_template.dto.response.PersonResponse;
import com.iagomassucato.crud_template.util.PersonValidator;
import com.iagomassucato.exception_core.exception.ApiException;
import com.iagomassucato.exception_core.exception.ErrorEnum;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonValidator personValidator;

    public PersonResponse savePerson(PersonRequest personRequest){
        PersonEntity personEntity = PersonEntity.builder()
                .firstName(personRequest.firstName())
                .lastName(personRequest.lastName())
                .email(personRequest.email())
                .cpf(personRequest.cpf())
                .age(personRequest.age())
                .build();

        PersonEntity personEntitySaved = personRepository.save(personEntity);

        return PersonResponse.fromEntity(personEntitySaved);
    }

    public PersonResponse createPerson(PersonRequest personRequest){
       personValidator.validateUniqueFields(personRequest.email(), personRequest.cpf(), null);

        return savePerson(personRequest);
    }

    public Page<@NonNull PersonEntity> findAllPagePerson(Pageable pageable) {

        return personRepository.findAll(pageable);
    }

    private PersonEntity getPersonEntityById(Long id) {

        return personRepository.findById(id)
                .orElseThrow(() -> new ApiException(
                        ErrorEnum.NOT_FOUND,
                        Map.of(
                            "id:", id,
                                "message:","Person with id " + id + " not found")
                ));
    }

    public PersonResponse findByIdPerson(Long id) {
        return PersonResponse.fromEntity(getPersonEntityById(id));
    }

    public PersonResponse replacePerson(Long id, PersonRequest personRequest) {
        PersonEntity personEntity = getPersonEntityById(id);

        personValidator.validateUniqueFields(
                personRequest.email(),
                personRequest.cpf(),
                id
        );

        personEntity.setFirstName(personRequest.firstName());
        personEntity.setLastName(personRequest.lastName());
        personEntity.setEmail(personRequest.email());
        personEntity.setCpf(personRequest.cpf());
        personEntity.setAge(personRequest.age());

        PersonEntity personEntitySaved = personRepository.save(personEntity);

        return PersonResponse.fromEntity(personEntitySaved);
    }

    public void deletePerson(Long id){
        PersonEntity personEntity = getPersonEntityById(id);
        personRepository.delete(personEntity);
    }
}
