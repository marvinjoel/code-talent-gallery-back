package code_talent_gallery_back.com.service;

import code_talent_gallery_back.com.model.Category;
import code_talent_gallery_back.com.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategory(){
        return categoryRepository.findAll();
    }
}
