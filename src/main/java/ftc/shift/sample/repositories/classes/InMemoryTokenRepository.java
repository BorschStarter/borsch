package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.entity.TokenEntity;
import ftc.shift.sample.models.UserValidInfo;
import ftc.shift.sample.repositories.interfaces.DataBaseInterfaces.TokenRepository;
import ftc.shift.sample.repositories.interfaces.EntityUnterfaces.TokenRepositoryEntity;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryTokenRepository implements TokenRepository {

    @Autowired
    private TokenRepositoryEntity service;

    @Override
    public UserValidInfo addToken(@NonNull final UserValidInfo userValidInfo) {
        TokenEntity token = userValidInfo.toTokenEntity();
        token=service.save(token);
        return token.toUserValidInfo();
    }

    @Override
    public void deleteToken(@NonNull final UserValidInfo userValidInfo) {

        Iterable<TokenEntity> allTokens = service.findAll();
        allTokens.forEach(item ->{
            if(item.getToken().equals(userValidInfo.getToken())){
                service.delete(item);
            }
        });
    }

    @Override
    public ArrayList<String> getAllTokensUser(@NonNull final Integer idUser) {

        Iterable<TokenEntity> allTokens = service.findAll();
        ArrayList<String> result = new ArrayList<>();
        allTokens.forEach(item ->{
            if(item.getUserId().equals(idUser)){
                result.add(item.getToken());
            }
        });
        return result;
    }
}
