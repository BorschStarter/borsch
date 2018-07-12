package ftc.shift.sample.repositories.interfaces.DataBaseInterfaces;

import ftc.shift.sample.models.UserValidInfo;
import lombok.NonNull;

import java.util.ArrayList;

public interface TokenRepository {

    UserValidInfo addToken( UserValidInfo userValidInfo);

    void deleteToken( UserValidInfo userValidInfo);

    ArrayList<String> getAllTokensUser( final Integer idUser);
}
