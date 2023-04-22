package ru.dvoretckii.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dvoretckii.Entities.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
