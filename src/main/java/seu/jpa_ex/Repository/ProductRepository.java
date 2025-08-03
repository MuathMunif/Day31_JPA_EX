package seu.jpa_ex.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.jpa_ex.Model.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel , Integer> {
}
