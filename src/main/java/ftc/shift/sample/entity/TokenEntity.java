package ftc.shift.sample.entity;


import ftc.shift.sample.models.UserValidInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;

@EnableTransactionManagement
@Entity
@Table(name = "token")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="login", columnDefinition = "text")
    private String login;

    @Column(name="token", columnDefinition = "text")
    private String token;

    public UserValidInfo toUserValidInfo(){
        UserValidInfo userValidInfo = new UserValidInfo();
        userValidInfo.setLogin(this.getLogin());
        userValidInfo.setIdUser(this.getUserId());
        userValidInfo.setToken(this.getToken());
        return userValidInfo;
    }
}


