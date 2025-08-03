package seu.jpa_ex.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class ProductModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message =  "The name must not be empty")
    @Size(min = 4 , message = "The name must be more than 3 length long")
    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    private String name;

    @NotNull(message =  "The price must not be empty")
    @Positive(message =  "The price must be positive number")
    @Column(columnDefinition = "DOUBLE NOT NULL")
    private double price;

    @NotNull(message =  "The categoryID must not be empty")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer categoryID;

    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    private int soldCount = 0;

    @Column(columnDefinition = "DOUBLE")
    private double totalIncome;

}
