package ftc.shift.sample.services;

public class ServiceResources {
    public static final String INCORRECT_LOGIN_FIELD = "Логин некорректен " +
            "Ваш логин должен быть длинее 5 символов и короче 16" +
            "и состоять только из букв латинского алфавита и цифр";

    public static final String INCORRECT_PASSWORD_FIELD = "Пароль некорректен " +
            "Ваш пароль должен быть длинее 5 символов и короче 16" +
            "и состоять только из букв латинского алфавита и цифр";

    public static final String USER_NOT_FOUND = "Пользователь не найден";
    public static final String LOGIN_IS_RESERVED = "Логин занят";
    public static final String INCORRECT_PASSWORD = "Вы ввели неверный пароль";
    public static final String CANT_FIND_USER_WITH_THAT_LOGIN = "Пользователь с таким логином не найден";
    public static final String CANT_VALID_USER_WITH_THAT_LOGIN = "Невозможно предоставить доступ по этому логину";
    public static final String NO_TOKEN_FOR_USER = "Нет открытых сессий у данного пользователя";

    public static final String USERS_FRIDGE_IS_EMPTY ="Холодильник пользователя пуст";
    public static final String INCORRECT_PRODUCT_FORM = "Передана некорректная форма проодукта";
    public static final String PRODUCT_ALREADY_IN_USER_FRIDGE = "Продукт уже в холодильнике пользователя";
    public static final String NO_THAT_PRODUCT_IN_USER_FRIDGE = "В холодильнике пользователя нет такого продукта";


}
