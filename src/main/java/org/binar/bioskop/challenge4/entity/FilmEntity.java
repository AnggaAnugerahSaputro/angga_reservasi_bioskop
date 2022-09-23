package org.binar.bioskop.challenge4.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Films")
public class FilmEntity {

    @Id
    @Column(name = "film_code")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String film_code;

    @Column(name = "film_name")
    private String film_name;

    @Column(name = "film_status")
    private Boolean show_status;

    @OneToMany(mappedBy = "filmEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ScheduleEntity> scheduleEntities;
}
