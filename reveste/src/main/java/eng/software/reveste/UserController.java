package eng.software.reveste;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import eng.software.reveste.model.User;

@RestController
public class UserController {
    
    @PostMapping("/verificaCadastro")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        System.out.println("Data de nascimento recebida: " + user.getDateBirth());
        return ResponseEntity.ok("Usuário cadastrado!");
    }
}
