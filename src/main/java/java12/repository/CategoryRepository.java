package java12.repository;

import java12.entity.Category;
import java12.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    default Category getByIds(Long catId) {
        return findById(catId).orElseThrow(() -> new NotFoundException("Нету такой ID " + catId));
    }
}