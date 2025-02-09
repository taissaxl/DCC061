package eng.software.reveste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eng.software.reveste.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Métodos personalizados podem ser adicionados aqui
}