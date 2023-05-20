package ru.dvoretckii.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dvoretckii.Entities.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query("select b from Owner b where b.username = :username")
    Owner getByName(@Param("username") String username);
}
