package com.iagomassucato.crud_template.domain.repository;

import com.iagomassucato.crud_template.domain.model.PersonEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository< @NonNull PersonEntity, @NonNull Long> {


    boolean existsByEmail(String email);

    boolean existsByCpf(Long cpf);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByCpfAndIdNot(Long cpf, Long id);
}
