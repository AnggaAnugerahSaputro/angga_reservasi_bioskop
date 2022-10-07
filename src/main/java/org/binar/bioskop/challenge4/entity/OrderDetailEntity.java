package org.binar.bioskop.challenge4.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "order_detail")
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String orderDetail_id;

    @Column(name = "total_price")
    private BigDecimal total_price;

    @Column(name = "created_at")
    private LocalTime created_at;

    @Column(name = "updated_at")
    private LocalDate update_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

}
