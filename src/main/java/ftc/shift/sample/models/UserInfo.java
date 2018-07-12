package ftc.shift.sample.models;

import ftc.shift.sample.entity.UserInfoEntity;
import lombok.*;

import java.io.Serializable;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public final class UserInfo implements Serializable{

    public UserInfo(Integer idUser){
        this.setId(null);
        this.setFirstName("");
        this.setSecondName("");
        this.setCity("");
        this.setUniversity("");
        this.setDormitory("");
        this.setRoom("");
        this.setVk("");
        this.setTelegram("");
        this.setEmail("");
         this.setCookRate(0);
         this.setEatRate(0);
    }

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
