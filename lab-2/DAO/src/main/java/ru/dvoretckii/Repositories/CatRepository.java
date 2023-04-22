package ru.dvoretckii.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dvoretckii.Entities.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
}
