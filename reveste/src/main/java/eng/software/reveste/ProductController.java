package eng.software.reveste;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    @GetMapping("/products")
    public ModelAndView listProducts() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("products", ProductRepository.getAllProducts());
        return mv;
    }

    @GetMapping("/products/{id}")
    public ModelAndView productDetails(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("product");
        Product product = ProductRepository.getProductById(id);
        mv.addObject("product", product);
        return mv;
    }
}