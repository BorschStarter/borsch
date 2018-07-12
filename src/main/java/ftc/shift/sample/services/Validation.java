package ftc.shift.sample.services;

import ftc.shift.sample.api.BaseResponse;
import ftc.shift.sample.api.ValidationStatus;
import ftc.shift.sample.models.UserValidInfo;
import ftc.shift.sample.services.Interfaces.UserServiceInterface;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static ftc.shift.sample.api.ExceptionsConst.*;
import static ftc.shift.sample.api.ValidationStatus.ERROR;
import static ftc.shift.sample.api.ValidationStatus.NON_VALID;
import static ftc.shift.sample.api.ValidationStatus.VALID;

@Service
public class Validation {
    @Autowired
    private UserServiceInterface userService;
    public ValidationStatus checkValidation(@NonNull UserValidInfo userValidInfo, BaseResponse response){

        try{

            if(userService.checkAccess(userValidInfo)){
                return VALID;
            }else{
                return NON_VALID;
            }
        }catch (IllegalArgumentException ex) {
            response.setStatus(ILLEGAL_ARGUMENT_ERROR_STATUS);
            response.setMessage(ex.getMessage());
            return ERROR;
        }catch (Exception ex){
            response.setStatus(UNEXPECTED_ERROR_STATUS);
            response.setMessage(UNEXPECTED_ERROR_MESSAGE+" "+ex.getMessage());
            return ERROR;
        }
    }
}
