package seu.jpa_ex.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.jpa_ex.Model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
}
