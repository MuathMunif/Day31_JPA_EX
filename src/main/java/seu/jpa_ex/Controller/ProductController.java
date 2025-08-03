package seu.jpa_ex.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import seu.jpa_ex.Api.ApiResponse;
import seu.jpa_ex.Model.ProductModel;
import seu.jpa_ex.Service.ProductService;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @GetMapping("/get")
    public ResponseEntity<?> getProducts(){
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductModel product , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        if(productService.addProduct(product)){
            return ResponseEntity.status(200).body(new ApiResponse("Product added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not added The Category ID Not Correct"));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id , @Valid @RequestBody ProductModel product , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse (Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        boolean isUpdated = productService.updateProduct(id, product);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Product updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        boolean isDeleted = productService.deleteProduct(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Product deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found"));
    }

    //Extra to check the sales for product
    @GetMapping("/sales/{id}")
    public ResponseEntity<?> getProductCount(@PathVariable Integer id){
        return ResponseEntity.status(200).body(productService.getProductCount(id));
    }

    //Extra to get all product income
    @GetMapping("/get-product-income/{id}")
    public ResponseEntity<?> getProductIncome(@PathVariable Integer id){
        return ResponseEntity.status(200).body(productService.getProductIncome(id));
    }


}
