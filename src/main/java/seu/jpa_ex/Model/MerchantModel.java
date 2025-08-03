package seu.jpa_ex.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Merchant")
public class MerchantModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NotEmpty(message =  "The name must not be empty")
    @Size(min = 4 , message = "The name must be more than 3 length long")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;
}
