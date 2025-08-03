package seu.jpa_ex.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seu.jpa_ex.Model.CategoryModel;
import seu.jpa_ex.Repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(CategoryModel categoryModel) {
        categoryRepository.save(categoryModel);
    }

    public boolean updateCategory(Integer id ,CategoryModel categoryModel) {
        CategoryModel category = categoryRepository.getById(id);
        if (category == null) {
            return false;
        }
        category.setName(categoryModel.getName());
        categoryRepository.save(category);
        return true;
    }



    public boolean deleteCategory(Integer id) {
        CategoryModel category = categoryRepository.getById(id);
        if (category == null) {
            return false;
        }
        categoryRepository.delete(category);
        return true;
    }
}
