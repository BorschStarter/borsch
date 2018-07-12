package ftc.shift.sample.models;

import ftc.shift.sample.entity.UserEntity;
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
    
    public UserEntity toUserEntity(){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(this.getId());
        userEntity.setFirstName(this.getFirstName());
        userEntity.setSecondName(this.getSecondName());
        userEntity.setCity(this.getCity());
        userEntity.setUniversity(this.getUniversity());
        userEntity.setDormitory(this.getDormitory());
        userEntity.setRoom(this.getRoom());
        userEntity.setVk(this.getVk());
        userEntity.setTelegram(this.getTelegram());
        userEntity.setEmail(this.getEmail());
        userEntity.setCookRate(this.getCookRate());
        userEntity.setEatRate(this.getEatRate());
        return userEntity;
    }
}
