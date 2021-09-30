package com.dhome.registermicroservice.query.projections;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserViewRepository extends JpaRepository<UserView,Long> {
    Optional<UserView> getByDNI(Long DNI);
    @Query(value = "SELECT * FROM user_view WHERE Id <> :Id AND DNI = :DNI", nativeQuery = true)
    Optional<UserView> getByUserIdAndDni(Long Id, Long DNI);

}
