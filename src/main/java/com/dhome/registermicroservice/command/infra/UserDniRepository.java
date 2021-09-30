package com.dhome.registermicroservice.command.infra;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserDniRepository extends JpaRepository<UserDni,Long> {
        Optional<UserDni> getUserDniById(Long Id);

        @Query(value = "SELECT * FROM user_dni WHERE id <> :Id AND dni = :dni LIMIT 1", nativeQuery = true)
        Optional<UserDni> getByDniForDistinctId(String dni,Long Id);
}
