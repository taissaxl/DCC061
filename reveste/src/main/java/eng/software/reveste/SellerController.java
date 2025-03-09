package eng.software.reveste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

import eng.software.reveste.model.SoldProduct;
import eng.software.reveste.repository.SoldProductRepository;

@Controller
public class SellerController {

    @Autowired
    private SoldProductRepository soldProductRepository;

    // Endpoint para exibir a página do vendedor
    @GetMapping("/seller")
    public String sellerPage(Model model, Principal principal) {
        // Verifica se o usuário está autenticado
        if (principal == null) {
            return "redirect:/login"; // Redireciona para a página de login
        }

        // Obtém o nome do vendedor logado
        String username = principal.getName();

        // Recupera os produtos vendidos do vendedor
        List<SoldProduct> soldProducts = soldProductRepository.findBySellerUsername(username);

        // Adiciona os produtos vendidos ao modelo
        model.addAttribute("soldProducts", soldProducts);

        return "seller"; // Retorna a view seller.html
    }
}