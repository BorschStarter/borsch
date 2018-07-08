package ftc.shift.sample.models;

import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public final class UserValidInfo {
    private String id;
    private String token;
}
