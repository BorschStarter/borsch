package ftc.shift.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;

@EnableTransactionManagement
@Entity
@Table(name = "login")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="_id")
    private Integer id;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="login", columnDefinition = "text")
    private String login;

    @Column(name="password", columnDefinition = "text")
    private String password;
}