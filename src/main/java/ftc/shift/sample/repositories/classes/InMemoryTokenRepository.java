package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.entity.TokenEntity;
import ftc.shift.sample.models.UserValidInfo;
import ftc.shift.sample.repositories.interfaces.TokenRepository;
import ftc.shift.sample.repositories.interfaces.TokenRepositoryEntity;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryTokenRepository implements TokenRepository {

//    private TreeMap<String, String> tokenCache = new TreeMap<>();
    @Autowired
    private TokenRepositoryEntity service;

    public InMemoryTokenRepository() {}

    @Override
    public void addToken(@NonNull final UserValidInfo userValidInfo) {

//        tokenCache.put(userValidInfo.getId(),userValidInfo.getToken());
        TokenEntity token = new TokenEntity();
        token.setLogin(userValidInfo.getLogin());
        token.setUserId(userValidInfo.getIdUser());
        token.setToken(userValidInfo.getToken());

        service.save(token);
    }

    @Override
    public ArrayList<String> getAllTokensUser(@NonNull final Integer idUser) {

//        ArrayList<String> tokens = new ArrayList<>();

//        for (Map.Entry element: tokenCache.entrySet()) {
//            if (element.getKey().equals(idUser)){
//                tokens.add((String) element.getValue());
//            }
//        }
//        return tokens;
        Iterable<TokenEntity> allTokens = service.findAll();
        ArrayList<String> result = new ArrayList<>();
        allTokens.forEach(item ->{
            if(item.getUserId().equals(idUser)){
                result.add(item.getToken());
            }
        });
        return result;
    }

    @Override
    public void deleteToken(@NonNull final UserValidInfo userValidInfo) {

        Iterable<TokenEntity> allTokens = service.findAll();
        allTokens.forEach(item ->{
            if(item.getToken().equals(userValidInfo.getToken())){
                service.delete(item);
            }
        });
//        getAllTokensUser(userValidInfo.getId()).remove(userValidInfo.getToken());
    }
}
