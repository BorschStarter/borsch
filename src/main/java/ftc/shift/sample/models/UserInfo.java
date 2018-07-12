package ftc.shift.sample.models;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public final class UserInfo implements Serializable{
    private Integer id;
    private String firstName;
    private String secondName;
    private String city;
    private String university;
    private String dormitory;
    private String room;
    private String vk;
    private String telegram;
    private String email;
    private Integer cookRate;
    private Integer eatRate;
}
