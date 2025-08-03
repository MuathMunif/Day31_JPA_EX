package seu.jpa_ex.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MerchantStock")
public class MerchantStockModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "(The product must not be empty")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer productId;

    @NotNull(message = "The merchantId must not be empty ")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer merchantId;

    @NotNull(message = "The stock must not be empty")
    @Column(columnDefinition = "INT NOT NULL")
    private int stock;
}
