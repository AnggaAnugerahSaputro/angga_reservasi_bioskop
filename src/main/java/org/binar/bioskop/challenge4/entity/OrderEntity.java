package org.binar.bioskop.challenge4.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Orders")
public class OrderEntity {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer order_id;

    @Column(name = "order_date")
    private Date order_date;

    @Column(name = "order_time")
    private LocalDateTime order_time;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntitiess;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private ScheduleEntity scheduleEntities;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumns({@JoinColumn(name = "seat_row"), @JoinColumn(name = "seat_number"), @JoinColumn(name = "studio_id")})
//    private SeatEntity seatEntities;

    @OneToMany(mappedBy = "orderEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetailEntities;
}
