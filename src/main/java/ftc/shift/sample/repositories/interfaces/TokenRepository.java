package ftc.shift.sample.repositories.interfaces;

import ftc.shift.sample.models.Token;
import ftc.shift.sample.models.User;
import ftc.shift.sample.models.UserValidInfo;

import java.util.ArrayList;
import java.util.Collection;

public interface TokenRepository {

    Token addToken(final Token token, final String id);
    ArrayList<Token> getTokens(final String id);
}
