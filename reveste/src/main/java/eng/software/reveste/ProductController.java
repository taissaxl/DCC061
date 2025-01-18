package eng.software.reveste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller // Alterado de @RestController para @Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Endpoint para exibir os detalhes de um produto
    @GetMapping("/products/{id}")
    public String productDetails(@PathVariable int id, Model model) {
        // Busca o produto pelo ID
        Product product = productRepository.findById(id).orElse(null);

        // Adiciona o produto ao modelo para ser exibido na view
        if (product != null) {
            model.addAttribute("product", product);
        } else {
            // Caso o produto não seja encontrado, redireciona para uma página de erro
            return "redirect:/error";
        }

        return "product"; // Retorna a view product.html
    }
}