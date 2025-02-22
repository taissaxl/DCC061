package eng.software.reveste;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

//import eng.software.reveste.model.User;
import eng.software.reveste.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        eng.software.reveste.model.User user = userRepository.findByUsername((username));
        if (user != null){
            return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
        }
        else{
            throw new UsernameNotFoundException("Usuario ou senha invalidos.");
        }
    }
}
