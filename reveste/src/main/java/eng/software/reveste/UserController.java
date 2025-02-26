package eng.software.reveste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @Autowired
    private UserService userService;

    @PostMapping("/editar-usuario")
    public String editarUsuario(@ModelAttribute User user, Model model) {
        try {
            userService.atualizarUsuario(user); // Atualiza o usuário
            model.addAttribute("mensagem", "Usuário atualizado com sucesso!");
            return "redirect:/user"; // Redireciona para a página do usuário
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao atualizar usuário: " + e.getMessage());
            return "erro"; // Retorna a página de erro personalizada
        }
    }
}
