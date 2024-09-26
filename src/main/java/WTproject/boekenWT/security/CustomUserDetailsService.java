package WTproject.boekenWT.security;

import WTproject.boekenWT.models.User;
import WTproject.boekenWT.models.UserType;
import WTproject.boekenWT.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException(username + "not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapUserTypeToAuthorities(user.getUserType())
        );
    }
    public int UserDetailsUserID(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
        return user.getUserId();
    }
    //  convert a role/usertype to a GrantedAuthority and put it in a collection.
    private Collection<GrantedAuthority> mapUserTypeToAuthorities(UserType userType) {
        return List.of(new SimpleGrantedAuthority(userType.getUserTypeName()));
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
