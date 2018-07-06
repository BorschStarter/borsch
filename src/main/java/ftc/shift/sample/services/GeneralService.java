package ftc.shift.sample.services;

import ftc.shift.sample.repositories.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralService {
    private final UserRepository userRepository;

    @Autowired
    public GeneralService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
