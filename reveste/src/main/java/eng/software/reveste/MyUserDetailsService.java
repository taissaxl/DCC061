package eng.software.reveste;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import eng.software.reveste.model.User; // Importação da entidade User
import eng.software.reveste.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados
        Optional<User> userOptional = userRepository.findByUsername(username);

        // Verifica se o usuário foi encontrado
        if (userOptional.isPresent()) {
            User user = userOptional.get(); // Obtém o usuário do Optional

            // Cria um UserDetails usando a classe User do Spring Security
            return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>() // Lista de autoridades (roles) vazia
            );
        } else {
            // Lança uma exceção se o usuário não for encontrado
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
    }
}