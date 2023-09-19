package ru.dvoretckii.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dvoretckii.DAO.Entities.Cat;


@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    @Query("select b from Cat b where b.cat_id = :cat_id")
    Cat myGetById(@Param("cat_id") Long cat_id);
}
