package org.binar.bioskop.challenge4.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class SeatComposite implements Serializable  {

    @Column(name = "seat_row")
    private Character seat_row;

    @Column(name = "seat_number")
    private Integer seat_number;



}
