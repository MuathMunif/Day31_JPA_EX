package seu.jpa_ex.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seu.jpa_ex.Model.CategoryModel;
import seu.jpa_ex.Model.ProductModel;
import seu.jpa_ex.Repository.CategoryRepository;
import seu.jpa_ex.Repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    public boolean addProduct(ProductModel productModel) {
        CategoryModel categoryModel = categoryRepository.getById(productModel.getCategoryID());
        if (categoryModel == null) {
            return false;
        }
        productRepository.save(productModel);
        return true;
    }

    public boolean updateProduct(Integer id,ProductModel productModel) {
        ProductModel product = productRepository.getById(id);
        if (product == null) {
            return false;
        }
        product.setName(productModel.getName());
        product.setPrice(productModel.getPrice());
        product.setCategoryID(productModel.getCategoryID());
        productRepository.save(product);
        return true;
    }

    public boolean deleteProduct(Integer id) {
        ProductModel productModel = productRepository.getById(id);
        if (productModel == null) {
            return false;
        }
        productRepository.delete(productModel);
        return true;
    }
}
