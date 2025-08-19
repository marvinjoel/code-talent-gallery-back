package code_talent_gallery_back.com.repository;

import code_talent_gallery_back.com.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
