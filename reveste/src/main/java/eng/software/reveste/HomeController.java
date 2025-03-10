package eng.software.reveste;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import eng.software.reveste.model.Product;
import eng.software.reveste.model.User;
import eng.software.reveste.repository.ProductRepository;
import eng.software.reveste.repository.UserRepository;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // Rota para a página inicial (home.html)
    @GetMapping("/home")
    public String homePage() {
        return "home"; // Retorna a view home.html
    }

    // Rota padrão (redireciona para /home)
    @RequestMapping("/")
    public String home() {
        return "redirect:/login"; // Redireciona para a página de login
    }

    // Rota para a página de login
    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", "Usuário ou senha inválidos.");
        }
        return "login";
    }

     // Usuário e senha fixos para simular autenticação
     private static final String USERNAME = "admin";
     private static final String PASSWORD = "1234";
     
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password, Model model) {
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            return "redirect:/home"; // Redireciona para a página inicial se o login for correto
        } else {
            model.addAttribute("loginError", "Usuário ou senha inválidos.");
            return "login"; // Retorna para a página de login com mensagem de erro
        }
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

    // Rota para a página de produtos
    @GetMapping("/produtos")
    public String produtosPage(Model model) {
        // Busca todos os produtos do banco de dados usando o ProductRepository
        List<Product> products = productRepository.findAll();

        // Adiciona a lista de produtos ao modelo
        model.addAttribute("products", products);

        return "product";
    }

    // Rota para a página de dicas
    @RequestMapping("/dicas")
    public String dicasPage(Model model) {
        return "dicas"; // Retorna a view dicas.html
    }

    @GetMapping("/user")
    public String userPage(Model model, Principal principal) {
        try {
            // Verifica se o usuário está autenticado
            if (principal == null) {
                return "redirect:/login"; // Redireciona para a página de login
            }

            // Obtém o nome do usuário autenticado
            String username = principal.getName();

            // Busca o usuário no banco de dados
            Optional<User> userOptional = userRepository.findByUsername(username);

            if (userOptional.isEmpty()) {
                throw new RuntimeException("Usuário não encontrado!");
            }

            // Adiciona o usuário ao modelo
            model.addAttribute("user", userOptional.get());

            return "user"; // Retorna a view user.html
        } catch (Exception e) {
            e.printStackTrace(); // Log do erro no console
            throw new RuntimeException("Erro ao carregar a página do usuário: " + e.getMessage());
        }
    }

    // Rota para a página do carrinho de compras
    @GetMapping("/cart")
    public String cartPage() {
        return "cart"; // Retorna a view cart.html
    }

    // Rota para a página de favoritos
    @GetMapping("/favorites")
    public String favoritesPage() {
        return "favorites"; // Retorna a view favorites.html
    }

    // 2 endopoints iguais, pois um é de GET (ir à página) e o outro POST (validar
    // cadastro do parâmetro)
    @GetMapping("/register")
    public ModelAndView registerUser1() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("register.html");
        mv.addObject("user", new User());
        return mv;
    }

    @PostMapping("/register")
    public ModelAndView registerUser2(@ModelAttribute User user, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("register");
            return mv;
        }
        userRepository.save(user);
        mv.setViewName("redirect:/login");
        return mv;
    }

    // Só pra testar e ver os usuarios ligados (digitar a url no navegador pra
    // visualizar)
    @RequestMapping({ "/listaUsuarios" })
    public ModelAndView listaUsuarios() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usersTeste.html");
        List<User> lu = userRepository.findAll();
        mv.addObject("usuarios", lu);
        return mv;
    }

    // Rota para a página de compra
    @RequestMapping("/checkout")
    public String checkout() {
        return "checkout.html";
    }

    // Rota para exibir a página de checkout
    @GetMapping("/checkout")
    public String checkoutPage(Model model) {
        model.addAttribute("checkoutDTO", new CheckoutDTO());
        return "checkout";
    }

    // Rota para processar o formulário de checkout
    @PostMapping("/checkout")
    public String processCheckout(@Valid @ModelAttribute("checkoutDTO") CheckoutDTO checkoutDTO,
            BindingResult bindingResult, Model model) {
        // Verifica se há erros de validação
        if (bindingResult.hasErrors()) {
            // Se houver erros, retorna para a página de checkout com as mensagens de erro
            return "checkout";
        }

        // Se não houver erros, redireciona para a página de confirmação
        return "redirect:/checkout-confirmation";
    }

    // Rota para a página de confirmação de compra
    @RequestMapping("/checkout-confirmation")
    public String checkoutConfirmation() {
        return "checkout-confirmation";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/order-confirmation")
    public String orderConfirmation() {
        return "order-confirmation";
    }

    @GetMapping("/sustainability")
    public String sustainabilityPage() {
        return "sustainability";
    }

    @GetMapping("/edit-user")
    public String editUserPage(Model model, Principal principal) {
        // Obtém o nome do usuário autenticado
        String username = principal.getName();

        // Busca o usuário no banco de dados
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        // Adiciona o usuário ao modelo
        model.addAttribute("user", userOptional.get());

        return "edit-user"; // Retorna a view edit-user.html
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute User updatedUser, Principal principal) {
        try {
            // Obtém o nome do usuário autenticado
            String username = principal.getName();

            // Busca o usuário no banco de dados
            Optional<User> userOptional = userRepository.findByUsername(username);

            if (userOptional.isEmpty()) {
                throw new RuntimeException("Usuário não encontrado!");
            }

            // Atualiza os dados do usuário
            User user = userOptional.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setCep(updatedUser.getCep());
            user.setEndereco(updatedUser.getEndereco());
            user.setNumero(updatedUser.getNumero());
            user.setComplemento(updatedUser.getComplemento());
            user.setCidade(updatedUser.getCidade());
            user.setEstado(updatedUser.getEstado());

            // Salva as alterações no banco de dados
            userRepository.save(user);

            // Redireciona para a página do usuário
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace(); // Log do erro no console
            throw new RuntimeException("Erro ao atualizar o usuário: " + e.getMessage());
        }
    }
}