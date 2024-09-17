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
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username).orElseThrow(()->new UsernameNotFoundException(username + "not found"));
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), mapUserTypeToAuthorities(user.getUserType())
        );
    }

    //  convert a role/usertype to a GrantedAuthority and put it in a collection.
    private Collection<GrantedAuthority> mapUserTypeToAuthorities(UserType userType) {
        return List.of(new SimpleGrantedAuthority(userType.getUserTypeName()));
    }

}
