package org.binar.bioskop.challenge4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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


//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @Column(name = "studio_id")
//    private List<StudioEntity> studioEntities;


}
