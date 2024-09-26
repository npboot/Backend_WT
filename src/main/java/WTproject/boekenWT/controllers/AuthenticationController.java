package WTproject.boekenWT.controllers;

import WTproject.boekenWT.models.*;
import WTproject.boekenWT.repositories.UserRepository;
import WTproject.boekenWT.repositories.UserTypeRepository;
import WTproject.boekenWT.security.CustomUserDetailsService;
import WTproject.boekenWT.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserTypeRepository userTypeRepository;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private UserTypeRepository usertypeRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository,
                                    UserTypeRepository typeRepository,
                                    PasswordEncoder passwordEncoder,
                                    UserTypeRepository userTypeRepository,
                                    JWTGenerator jwtGenerator,
                                    CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userTypeRepository = userTypeRepository;
        this.jwtGenerator = jwtGenerator;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDto){

        if(userRepository.existsByEmail(registerDto.getEmail())){
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        UserType userType = userTypeRepository.findByUserTypeName(registerDto.getUserType()).orElseThrow(() -> new RuntimeException("UserType 'USER' not found"));
        user.setUserType(userType);

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO){
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDTO.getEmail());
        int UserID = customUserDetailsService.UserDetailsUserID(loginDTO.getEmail());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),
                                                                                                                   loginDTO.getPassword(),
                                                                                                                   userDetails.getAuthorities()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication,UserID);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }


}
