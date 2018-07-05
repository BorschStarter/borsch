package ftc.shift.sample.models;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserInfo implements Serializable{
    private String id;
    private String firstName;
    private String secondName;
    private String city;
    private String university;
    private String dormitory;
    private String room;
    private String vk;
    private String telegram;
    private String email;
    private Double cookRate;
    private Double eatRate;
}
