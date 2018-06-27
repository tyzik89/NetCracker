package ru.vladimir.springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vladimir.springboot.entities.Visit;

@Repository
public interface VisitsRepository extends CrudRepository<Visit, Long> {
}
