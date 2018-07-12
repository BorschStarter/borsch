package ftc.shift.sample.repositories.interfaces.DataBaseInterfaces;

import ftc.shift.sample.models.UserValidInfo;
import lombok.NonNull;

import java.util.ArrayList;

public interface TokenRepository {

    UserValidInfo addToken(@NonNull UserValidInfo userValidInfo);

    void deleteToken(@NonNull UserValidInfo userValidInfo);

    ArrayList<String> getAllTokensUser(@NonNull final Integer idUser);
}
