package eng.software.reveste;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1, "Jaqueta Jeans", "Jaqueta jeans com retalhos", 51.56, "https://cdn.shopify.com/s/files/1/2205/1221/files/Sans_titre_332033d5-3f69-4876-919a-b8b41991d3d2_480x480.png?v=1685003032"));
        products.add(new Product(2, "Vestido Estampado", "Vestido de retalhos estampado", 20.90, "https://i.pinimg.com/originals/e5/39/55/e53955d65bb330644ef856c6b3644a44.jpg"));
        products.add(new Product(3, "Calça Jeans", "Calça jeans estilizada", 45.99, "https://cdn.faire.com/fastly/c9713e5ce0e106ed0bb47aef672018a6fbc3433a84b618cfe8e45ef5488efc2f.jpeg?bg-color=FFFFFF&dpr=1&fit=crop&format=jpg&height=720&precrop=799,799,x0,y0,safe&width=720"));
    }

    public static List<Product> getAllProducts() {
        return products;
    }

    public static Product getProductById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
}