package ftc.shift.sample.models;

import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public final class UserValidInfo {
    private Integer idUser;
    private String token;
    private String login;

}
