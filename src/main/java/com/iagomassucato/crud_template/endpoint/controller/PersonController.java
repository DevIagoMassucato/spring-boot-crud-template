package com.iagomassucato.crud_template.endpoint.controller;

import com.iagomassucato.crud_template.domain.model.PersonEntity;
import com.iagomassucato.crud_template.dto.request.PersonRequest;
import com.iagomassucato.crud_template.dto.response.PageResponse;
import com.iagomassucato.crud_template.dto.response.PersonResponse;
import com.iagomassucato.crud_template.endpoint.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/crud")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<@NonNull PersonResponse> createPerson(@Valid @RequestBody PersonRequest personRequest){
        PersonResponse personResponse = personService.createPerson(personRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<@NonNull PersonResponse> replacePerson(
            @PathVariable Long id,
            @Valid @RequestBody PersonRequest personRequest){
        PersonResponse personResponse = personService.replacePerson(id, personRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personResponse);
    }

    @GetMapping
    public ResponseEntity<@NonNull PageResponse<PersonResponse>> findAllPagePerson(Pageable pageable) {
        Page<@NonNull PersonEntity> personEntityPage = personService.findAllPagePerson(pageable);
        Page<@NonNull PersonResponse> personResponsePage = personEntityPage.map(PersonResponse::fromEntity);
        PageResponse<PersonResponse> response = PageResponse.of(personResponsePage);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull PersonResponse> findByIdPerson(@PathVariable Long id) {
        PersonResponse personResponse = personService.findByIdPerson(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
