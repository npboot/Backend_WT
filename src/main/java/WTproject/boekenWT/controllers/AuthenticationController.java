package WTproject.boekenWT.controllers;

import WTproject.boekenWT.models.RegisterDTO;
import WTproject.boekenWT.models.User;
import WTproject.boekenWT.models.UserType;
import WTproject.boekenWT.repositories.UserRepository;
import WTproject.boekenWT.repositories.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserTypeRepository userTypeRepository;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private UserTypeRepository usertypeRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository,
                                    UserTypeRepository typeRepository,
                                    PasswordEncoder passwordEncoder,
                                    UserTypeRepository userTypeRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userTypeRepository = userTypeRepository;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDto){
        if(userRepository.existsByName(registerDto.getName())){
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        UserType userType = userTypeRepository.findByUserTypeName("Trainer").orElseThrow(() -> new RuntimeException("UserType 'USER' not found"));
        user.setUserType(userType);

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
}
