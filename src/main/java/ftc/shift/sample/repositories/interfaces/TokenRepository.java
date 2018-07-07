package ftc.shift.sample.repositories.interfaces;

import ftc.shift.sample.models.Token;

import java.util.ArrayList;

public interface TokenRepository {

    Token addToken(final Token token, final String id);
    ArrayList<Token> getTokens(final String id);
}
