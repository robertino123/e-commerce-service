package com.ecommerce.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @OneToMany
    @JoinTable
            (
                    name = "order_products",
                    joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
                    inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
            )
    private List<Product> products;

    private Long totalAmount;

    private Date createdDate;

    public UserOrder(Long userId, List<Product> products, Long totalAmount, Date createdDate) {
        this.userId = userId;
        this.products = products;
        this.totalAmount = totalAmount;
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOrder userOrder = (UserOrder) o;
        return Objects.equals(id, userOrder.id) && Objects.equals(userId, userOrder.userId) && Objects.equals(products, userOrder.products) && Objects.equals(totalAmount, userOrder.totalAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, products, totalAmount);
    }
}
