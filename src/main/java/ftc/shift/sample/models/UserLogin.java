package ftc.shift.sample.models;

import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public final class UserLogin {
    public Integer idUser;
    public String userName;
    public String password;
}
