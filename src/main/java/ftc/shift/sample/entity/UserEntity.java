package ftc.shift.sample.entity;


import ftc.shift.sample.models.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
@EnableTransactionManagement
@Entity
@Table(name = "user_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
//id, firstname, secondname, city, university, dormitory, room, vk, telegram, email, cookrate, eatrate

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;


    @Column(name="firstname", columnDefinition = "text")
    private String firstName;

    @Column(name="secondname", columnDefinition = "text")
    private String secondName;

    @Column(name="city", columnDefinition = "text")
    private String city;

    @Column(name="university", columnDefinition = "text")
    private String university;

    @Column(name="dormitory", columnDefinition = "text")
    private String dormitory;

    @Column(name="room", columnDefinition = "text")
    private String room;

    @Column(name="vk", columnDefinition = "text")
    private String vk;

    @Column(name="telegram", columnDefinition = "text")
    private String telegram;

    @Column(name="email", columnDefinition = "text")
    private String email;

    @Column(name="cookrate")
    private Integer cookRate;

    @Column(name="eatrate")
    private Integer eatRate;


    //временное решение для теста
    //В теории можно оставить для лучшей инкапсуляции базы
    public UserInfo toUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(this.getId().toString());
        userInfo.setFirstName(this.getFirstName());
        userInfo.setSecondName(this.getSecondName());
        userInfo.setCity(this.getCity());
        userInfo.setUniversity(this.getUniversity());
        userInfo.setDormitory(this.getDormitory());
        userInfo.setRoom(this.getRoom());
        userInfo.setVk(this.getVk());
        userInfo.setTelegram(this.getTelegram());
        userInfo.setEmail(this.getEmail());
        userInfo.setCookRate(this.getCookRate());
        userInfo.setEatRate(this.getEatRate());
        return userInfo;

    }



}
