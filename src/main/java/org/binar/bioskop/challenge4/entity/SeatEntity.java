package org.binar.bioskop.challenge4.entity;

import lombok.*;

import javax.persistence.*;



@Getter
@Setter
@Entity
@Table(name = "seats")
public class SeatEntity {

    @EmbeddedId
    @Column(name = "seat_id")
    private SeatComposite seat_id;

    @Column(name = "status_seat")
    private Boolean status_seat;

}
