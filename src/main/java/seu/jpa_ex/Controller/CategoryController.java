package seu.jpa_ex.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import seu.jpa_ex.Api.ApiResponse;
import seu.jpa_ex.Model.CategoryModel;
import seu.jpa_ex.Service.CategoryService;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<?> getCategory(){
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }


    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryModel category , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(201).body(new ApiResponse("Category added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable  Integer id, @Valid@RequestBody CategoryModel category , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        boolean isCategoryUpdated = categoryService.updateCategory(id, category);
        if(isCategoryUpdated){
            return ResponseEntity.status(201).body(new ApiResponse("Category updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Category not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable  Integer id){
        boolean isCategoryDeleted = categoryService.deleteCategory(id);
        if(isCategoryDeleted){
            return ResponseEntity.status(201).body(new ApiResponse("Category deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Category not found"));
    }
}
