package ftc.shift.sample.repositories.classes;

import ftc.shift.sample.models.Token;
import ftc.shift.sample.repositories.interfaces.TokenRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryTokenRepository implements TokenRepository {

    private TreeMap<String, Token> tokenCache = new TreeMap<>();

    public InMemoryTokenRepository() {}

    @Override
    public Token addToken(final Token token, final String id) {
        tokenCache.put(id,token);
        return token;
    }

    @Override
    public ArrayList<Token> getTokens(final String id) {

        ArrayList<Token> tokens = new ArrayList<>();

        for (Map.Entry element: tokenCache.entrySet()) {
            if (element.getKey().equals(id)){
                tokens.add((Token) element.getValue());
            }
        }
        return tokens;
    }
}
