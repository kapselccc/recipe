package kpc.springframework.recipe.repositories;

import kpc.springframework.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
