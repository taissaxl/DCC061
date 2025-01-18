package eng.software.reveste;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Métodos personalizados podem ser adicionados aqui
}