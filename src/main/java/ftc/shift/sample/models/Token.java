package ftc.shift.sample.models;

import lombok.*;

import java.util.Random;


@NoArgsConstructor
@Data
public class Token {

    public String token;

    public Token (String characters) {

        Random random = new Random();
        char[] text = new char[characters.length()];
        for (int i = 0; i < characters.length(); i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        this.token = new String(text);
    }

    public boolean equals(Token obj) {
        return this.token.equals(obj.token);
    }

}
