package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.models.UserValidInfo;
import ftc.shift.sample.repositories.interfaces.TokenRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryTokenRepository implements TokenRepository {

    private TreeMap<String, String> tokenCache = new TreeMap<>();

    @Override
    public void addToken(@NonNull final UserValidInfo userValidInfo) {
        tokenCache.put(userValidInfo.getId(),userValidInfo.getToken());
    }

    @Override
    public ArrayList<String> getAllTokensUser(@NonNull final String idUser) {

        ArrayList<String> tokens = new ArrayList<>();

        for (Map.Entry element: tokenCache.entrySet()) {
            if (element.getKey().equals(idUser)){
                tokens.add((String) element.getValue());
            }
        }
        return tokens;
    }

    @Override
    public void deleteToken(@NonNull final UserValidInfo userValidInfo) {

        getAllTokensUser(userValidInfo.getId()).remove(userValidInfo.getToken());
    }
}
