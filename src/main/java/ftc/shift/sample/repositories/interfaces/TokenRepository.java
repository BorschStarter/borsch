package ftc.shift.sample.repositories.interfaces;

import ftc.shift.sample.models.UserValidInfo;
import lombok.NonNull;

import java.util.ArrayList;

public interface TokenRepository {

    void addToken(@NonNull UserValidInfo userValidInfo, Integer userId, String login);
    ArrayList<String> getAllTokensUser(@NonNull String idUser);
    void deleteToken(@NonNull UserValidInfo userValidInfo);
}
