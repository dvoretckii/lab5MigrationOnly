package ru.dvoretckii.DAO.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dvoretckii.DAO.Entities.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query("select b from Owner b where b.username = :username")
    Owner getByName(@Param("username") String username);
    @Query("select b from Owner b where b.owner_id = :id")
    Owner myGetById(@Param("id") Long id);
}