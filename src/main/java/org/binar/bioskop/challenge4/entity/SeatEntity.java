package org.binar.bioskop.challenge4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Seats")
public class SeatEntity {

    @EmbeddedId
    private SeatComposite seat_id;

    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "studio_id")
    private List<StudioEntity> studioEntities;

}
