package eng.software.reveste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eng.software.reveste.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Métodos personalizados podem ser adicionados aqui
}