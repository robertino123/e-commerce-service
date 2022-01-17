package com.ecommerce.api.repository;

import com.ecommerce.api.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p " +
            "FROM Product p " +
            "WHERE p.name LIKE :searchedTerm " +
            "OR p.pricePerUnit LIKE :searchedTerm " +
            "OR p.description LIKE :searchedTerm " +
            "OR p.category.name LIKE :searchedTerm ")
    List<Product> findAllBySearchedTerm(@Param("searchedTerm") String searchedTerm);
}
