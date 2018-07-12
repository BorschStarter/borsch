package ftc.shift.sample.Controllers;

import ftc.shift.sample.entity.TokenEntity;
import ftc.shift.sample.models.UserValidInfo;

import javax.persistence.Convert;
import javax.xml.crypto.Data;
import java.util.Random;

    public class StringGenerator {

    public static UserValidInfo generateUserValidInfo (Integer idUser, String login){
        String token = generateToken(login.concat(idUser.toString()));
        UserValidInfo userValidInfo = new UserValidInfo(idUser,token,login);
        return userValidInfo;
    }
    private static String generateToken(String key){
        String timeKey = String.valueOf(System.currentTimeMillis());

        key.concat(timeKey);
        Random random = new Random();
        char[] text = new char[key.length()];
        for (int i = 0; i < key.length(); i++) {
            text[i] = key.charAt(random.nextInt(key.length()));
        }
        return String.valueOf(text);
    }

}
