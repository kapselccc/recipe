package kpc.springframework.recipe.repositories;

import kpc.springframework.recipe.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
}
