package seu.jpa_ex.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NotEmpty(message =  "The name must not be empty")
    @Size(min = 6 , message = "The name must be more than 5 length long")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String username;

    @NotEmpty(message = "The password must be not empty ")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Password must contain at least one letter and one digit, and be at least 6 characters long")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String password;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL UNIQUE")
    private String email;

    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp = "^(Admin|Customer)$", message = "Role must be either 'Admin' or 'Customer'")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String role;

    @NotNull(message = "Balance must not be null")
    @Positive(message = "Balance must be a positive number")
    @Column(columnDefinition = "DOUBLE NOT NULL")
    private Double balance;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean active = true;

}

