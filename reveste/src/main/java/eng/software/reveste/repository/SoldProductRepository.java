package eng.software.reveste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import eng.software.reveste.model.SoldProduct;

public interface SoldProductRepository extends JpaRepository<SoldProduct, Long> {

    // Método para buscar produtos vendidos por vendedor (usando o username)
    List<SoldProduct> findBySellerUsername(String username);
}