package seu.jpa_ex.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.jpa_ex.Model.MerchantModel;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantModel , Integer> {
}
