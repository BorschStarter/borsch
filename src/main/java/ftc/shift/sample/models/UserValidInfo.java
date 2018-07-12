package ftc.shift.sample.models;

import ftc.shift.sample.entity.TokenEntity;
import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public final class UserValidInfo {
    private Integer idUser;
    private String token;
    private String login;

    public TokenEntity toTokenEntity(){
        TokenEntity tokenEntity = new TokenEntity();

        tokenEntity.setLogin(this.getLogin());
        tokenEntity.setToken(this.getToken());
        tokenEntity.setUserId(this.getIdUser());
        return tokenEntity;
    }

}
