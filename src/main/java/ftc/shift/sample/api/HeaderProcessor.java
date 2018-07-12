package ftc.shift.sample.api;

import ftc.shift.sample.models.UserValidInfo;

import javax.servlet.http.HttpServletRequest;

public class HeaderProcessor {

    public static UserValidInfo pullUserValidInfo(final HttpServletRequest request){
        UserValidInfo userValidInfo = new UserValidInfo();
        userValidInfo.setIdUser(request.getIntHeader("idUser"));
        userValidInfo.setToken(request.getHeader("Token"));
        userValidInfo.setLogin(request.getHeader("Login"));
        return  userValidInfo;
    }

}
