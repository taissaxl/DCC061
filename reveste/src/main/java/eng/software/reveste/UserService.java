package eng.software.reveste;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import eng.software.reveste.model.User;
import eng.software.reveste.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Método para salvar um novo usuário
    public User salvarUsuario(User user) {
        // Codifica a senha antes de salvar
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Método para atualizar um usuário existente
    public User atualizarUsuario(User user) {
        // Verifica se o usuário já existe no banco de dados
        User usuarioExistente = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Atualiza os campos
        usuarioExistente.setUsername(user.getUsername());
        usuarioExistente.setPassword(passwordEncoder.encode(user.getPassword())); // Codifica a nova senha
        usuarioExistente.setName(user.getName());
        usuarioExistente.setCpf(user.getCpf());
        usuarioExistente.setDateBirth(user.getDateBirth());

        return userRepository.save(usuarioExistente);
    }
}