package eng.software.reveste;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    // Rota para a página inicial (home.html)
    @GetMapping("/home")
    public String homePage() {
        return "home"; // Retorna a view home.html
    }

    // Rota padrão (redireciona para /home)
    @RequestMapping("/")
    public String home() {
        return "redirect:/home"; // Redireciona para a página inicial
    }

    // Rota para a página de login
    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }

    // Rota para a página de menu (usuário)
    @GetMapping("/menu")
    public ModelAndView getMethodName(@RequestParam String name) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user.html");
        mv.addObject("userName", name);
        return mv;
    }

    // Rota para a página de índice (index.html)
    @GetMapping("/index")
    public ModelAndView index(@ModelAttribute("usuario") Map<String, String> usuario) {
        ModelAndView mv = new ModelAndView("index");

        // Verifica se o usuário está na sessão
        if (usuario != null && usuario.containsKey("nome")) {
            String nomeUsuario = usuario.get("nome");
            mv.addObject("userName", nomeUsuario);
        } else {
            mv.addObject("userName", "Visitante"); // Valor padrão se o usuário não estiver na sessão
        }

        return mv;
    }
}